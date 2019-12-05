package com.zyccx.springbootdemo02;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author by Zhangyichao
 * @date 2019/12/5 22:52
 * @see IndexContronller
 */
@RestController
public class IndexContronller {

    @RequestMapping("/")
    String Index(){
      return "Hello World";
    }
}
