package com.example.simpleweb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    ICalculateurService calculateurService;

    @Test
    public void contextLoads() {
        Assert.assertNotNull(calculateurService);
    }

    @Test
    public void testCalc() {
        Assert.assertEquals(4.0, calculateurService.doubler(2), 1e-10);
    }

}
