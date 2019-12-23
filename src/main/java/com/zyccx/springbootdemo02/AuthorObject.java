package com.zyccx.springbootdemo02;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 配置文件对应的实体
 *
 * @author by Zhangyichao
 * @date 2019/12/23 17:11
 * @see AuthorObject
 */
@Component
@ConfigurationProperties(prefix = "author2")
public class AuthorObject {
    /**
     * 用户名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 妻子信息, 主要用来演示 map
     */
    private Map<String, Object> wife;
    /**
     * 出品的书籍 用于演示 list
     */
    private List<String> books;

    /**
     * 简介信息, 用于测试配置文件间的参数调用
     */
    private String introduce;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Map<String, Object> getWife() {
        return wife;
    }

    public void setWife(Map<String, Object> wife) {
        this.wife = wife;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
