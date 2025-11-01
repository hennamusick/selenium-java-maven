package com.framework.utils;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AppTest {
    @Test
    public void testApp() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(true);
        softAssert.assertAll();
    }
}
