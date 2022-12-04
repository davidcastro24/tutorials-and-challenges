package com.springboot.demo.beans.Fortunes;

import com.springboot.demo.interfaces.FortuneInterface;

public class OtherFortuneService implements FortuneInterface {

    @Override
    public String getFortune() {
        return "Other fortune service";
    }
}
