package com.springboot.demo.beans.Fortunes;

import com.springboot.demo.interfaces.FortuneInterface;
import org.springframework.stereotype.Component;

@Component
public class FortuneService implements FortuneInterface {

    @Override
    public String getFortune() {
        return "from fortune service";
    }
}
