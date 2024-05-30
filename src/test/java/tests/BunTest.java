package tests;

import parameters.Constants;
import org.junit.Assert;
import org.junit.Test;
import praktikum.Bun;


public class BunTest {
    Bun bun = new Bun(Constants.SESAME_NAME,Constants.SESAME_PRICE);

    @Test
    public void getNameTest() {
        Assert.assertEquals(bun.getName(),Constants.SESAME_NAME);
    }

    @Test
    public void getPrice() {
        Assert.assertEquals(bun.getPrice(),Constants.SESAME_PRICE, Constants.SESAME_PRICE_DELTA);
    }

}
