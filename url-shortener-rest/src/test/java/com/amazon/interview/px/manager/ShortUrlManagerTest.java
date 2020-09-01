package com.amazon.interview.px.manager;

import org.junit.Assert;
import org.junit.Test;

public class ShortUrlManagerTest {

   @Test
    public void test_isStartWithHttpOrHttps() {
       boolean start = ShortUrlManager.isStartWithHttpOrHttps("http://github.io");
       Assert.assertTrue(start);
   }
}
