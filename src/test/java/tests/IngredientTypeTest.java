package tests;

import org.junit.Test;
import praktikum.IngredientType;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class IngredientTypeTest {
    @Test
    public void values() {
        assertThat(IngredientType.SAUCE, is(notNullValue()));
        assertThat(IngredientType.FILLING, is(notNullValue()));
    }

}
