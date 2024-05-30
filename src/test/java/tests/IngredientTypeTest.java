package tests;

import org.junit.Assert;
import org.junit.Test;
import praktikum.IngredientType;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class IngredientTypeTest {
    @Test
    public void ingredientTypeValuesTest() {
        Assert.assertEquals(IngredientType.SAUCE.toString(), "SAUCE");
        Assert.assertEquals(IngredientType.FILLING.toString(), "FILLING");
    }

}
