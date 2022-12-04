package com.springboot.demo.beans.Fortunes;

import com.springboot.demo.interfaces.FortuneInterface;
import org.springframework.stereotype.Component;

@Component("restFortuneService")
public class RESTFortuneService implements FortuneInterface {

    @Override
    public String getFortune() {
        return "get Fortune REST";
    }
}
