package com.zyccx.springbootdemo02;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * RandomValueTest
 * @author by Zhangyichao
 * @date 2019/12/23 17:34
 * @see RandomValueTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RandomValueTest {
    @Autowired
    private RandomValue randomValue;

    @Test
    public void getUserMessage(){
        Assert.assertNotNull(randomValue.getValue());
        Assert.assertNotNull(randomValue.getNumber());
        Assert.assertNotNull(randomValue.getBignumber());
        Assert.assertNotNull(randomValue.getNumber1());
        Assert.assertNotNull(randomValue.getNumber2());

        System.out.println(randomValue.getValue());

    }
}
