package com.zyccx.springbootdemo02;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 通过 value 的方式获取配置文件内容的测试方法
 *
 * @author by Zhangyichao
 * @date 2019/12/23 16:57
 * @see AuthorValueTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorValueTest {
    @Autowired
    private AuthorValue authorValue;

    @Test
    public void getUserMessage(){
        Integer age = 10;
        Assert.assertEquals(authorValue.getName(), "summer");
        Assert.assertEquals(authorValue.getAge(), age);

        System.out.println(authorValue.getVersion());
    }
}
