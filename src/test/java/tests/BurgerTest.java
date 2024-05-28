package tests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import parameters.Constants;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class BurgerTest {


    @Test
    public void setBunsTest() {
        Burger burger = new Burger();
        Bun bun = new Bun(Constants.SESAME_NAME, Constants.SESAME_PRICE);

        burger.setBuns(bun);
        assertThat(burger.bun, is(notNullValue()));
    }

    @Test
    public void addIngredientTest() {
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(
                IngredientType.SAUCE,
                Constants.INGREDIENT_1_DEFAULT_NAME,
                Constants.INGREDIENT_1_DEFAULT_PRICE
        );

        burger.addIngredient(ingredient);
        assertThat(burger.ingredients.size(), is(Constants.ONE_INGREDIENT));
    }

    @Test
    public void removeIngredientTest() {
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(
                IngredientType.SAUCE,
                Constants.INGREDIENT_1_DEFAULT_NAME,
                Constants.INGREDIENT_1_DEFAULT_PRICE
        );

        int pinIngredientsSize = burger.ingredients.size();
        burger.addIngredient(ingredient);
        burger.removeIngredient(burger.ingredients.size()-1);
        assertThat(burger.ingredients.size(), is(pinIngredientsSize));
    }

    @Test
    public void moveIngredientTest() {
        Burger burger = new Burger();
        Ingredient ingredient_1 = new Ingredient(
                IngredientType.SAUCE,
                Constants.INGREDIENT_1_DEFAULT_NAME,
                Constants.INGREDIENT_1_DEFAULT_PRICE
        );

        Ingredient ingredient_2 = new Ingredient(
                IngredientType.FILLING,
                Constants.INGREDIENT_2_DEFAULT_NAME,
                Constants.INGREDIENT_2_DEFAULT_PRICE
        );

        burger.addIngredient(ingredient_1);
        burger.addIngredient(ingredient_2);
        burger.moveIngredient(burger.ingredients.size()-1, Constants.NEW_INDEX);
        assertThat(burger.ingredients.indexOf(ingredient_2) , is(Constants.NEW_INDEX));
    }

    @Test
    public void getPriceTest() {
        Burger burger = new Burger();
        Bun bun = new Bun(Constants.SESAME_NAME, Constants.SESAME_PRICE);
        burger.setBuns(bun);
        Ingredient ingredient = new Ingredient(
                IngredientType.SAUCE,
                Constants.INGREDIENT_1_DEFAULT_NAME,
                Constants.INGREDIENT_1_DEFAULT_PRICE
        );
        burger.addIngredient(ingredient);
        float expPrice = bun.price * 2 + ingredient.getPrice();
        assertThat(burger.getPrice(), is(expPrice));

    }


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    Burger burger = new Burger();
    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient;
    @Test
    public void getReceipt() {

        Mockito.when(bun.getName()).thenReturn(Constants.SESAME_NAME);
        Mockito.when(bun.getPrice()).thenReturn(Constants.SESAME_PRICE);

        Mockito.when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient.getName()).thenReturn(Constants.INGREDIENT_1_DEFAULT_NAME);
        Mockito.when(ingredient.getPrice()).thenReturn(Constants.INGREDIENT_1_DEFAULT_PRICE);


        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        String actMessage = burger.getReceipt();

        Mockito.verify(bun, Mockito.times(2)).getName();
        Mockito.verify(bun).getPrice();
        Mockito.verify(ingredient).getName();
        Mockito.verify(ingredient).getPrice();
        Mockito.verify(ingredient).getType();

        //проверка на наличие в тексте рецепта назаваний булочки и ингредиента
        assertThat(actMessage,allOf(containsString(Constants.SESAME_NAME), containsString(Constants.INGREDIENT_1_DEFAULT_NAME)));
        //проверка на наличие в тексте рецепта корректного прайса
        float expPrice = Constants.SESAME_PRICE * 2 + Constants.INGREDIENT_1_DEFAULT_PRICE;
        assertThat(actMessage,containsString(String.valueOf(expPrice).replace('.', ',')));
    }
}
