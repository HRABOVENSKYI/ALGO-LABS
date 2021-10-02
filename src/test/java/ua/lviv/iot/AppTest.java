package ua.lviv.iot;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class AppTest {

    // 9 products in bounds [0, 99]
    private static int[] PRODUCTS_1 = {27, 46, 97, 78, 42, 44, 2, 69, 60};
    private static int DISCOUNT_1 = 55;
    private static double EXPECTED_MIN_SUM_1 = 330.8;

    // 10 products in bounds [0, 99]
    private static int[] PRODUCTS_2 = {88, 16, 96, 92, 31, 82, 78, 31, 94, 38};
    private static int DISCOUNT_2 = 86;
    private static double EXPECTED_MIN_SUM_2 = 403.48;

    // 11 products in bounds [0, 999]
    private static int[] PRODUCTS_3 = {646, 28, 468, 693, 435, 287, 83, 641, 507, 492, 985};
    private static int DISCOUNT_3 = 34;
    private static double EXPECTED_MIN_SUM_3 = 4474.84;

    @Test
    public void testGetMinSum() throws IOException {
        Assert.assertEquals(EXPECTED_MIN_SUM_1, App.getMinSum(PRODUCTS_1, DISCOUNT_1), 0.01);
        Assert.assertEquals(EXPECTED_MIN_SUM_2, App.getMinSum(PRODUCTS_2, DISCOUNT_2), 0.01);
        Assert.assertEquals(EXPECTED_MIN_SUM_3, App.getMinSum(PRODUCTS_3, DISCOUNT_3), 0.01);
    }
}
