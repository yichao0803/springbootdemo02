package com.zyccx.springbootdemo02;

import com.sun.javafx.binding.StringFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * TODO
 *
 * @author by Zhangyichao
 * @date 2019/12/5 22:52
 * @see IndexContronller
 */
@RestController
public class IndexContronller {

    @Autowired
    private AuthorValue authorValue;
    String sp = " | ";
    @RequestMapping("/")
    String Index() {

        StringBuilder stb = new StringBuilder();
        stb.append("Hello World").append(sp);
        stb.append(StringFormatter.format("Version:{0}",authorValue.getVersion())).append(sp);
        stb.append("<a href=\"/hostName\">hostName<a/>");
        stb.append("\n");
        return stb.toString();
    }

    @RequestMapping("/hostName")
    String HostName() {
        InetAddress localHost = null;
        try {
            localHost = InetAddress.getLocalHost();
            StringBuilder stb=new StringBuilder();
            stb.append(StringFormatter.format("Address：{0}",localHost.getHostAddress())).append(sp);
            stb.append(StringFormatter.format("Hostname：{0}",localHost.getHostName())).append(sp);
            stb.append("<a href=\"/\">home<a/>").append(sp);
            stb.append("\n");
            return  stb.toString();

        } catch (UnknownHostException e) {
            e.getMessage();
            e.getStackTrace();
        }
        return "";
    }

}
