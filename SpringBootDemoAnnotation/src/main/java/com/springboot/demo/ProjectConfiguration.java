package com.springboot.demo;

import com.springboot.demo.beans.Coaches.BasketballCoach;
import com.springboot.demo.beans.Fortunes.OtherFortuneService;
import com.springboot.demo.beans.Fortunes.RandomFortuneService;
import com.springboot.demo.interfaces.CoachInterface;
import com.springboot.demo.interfaces.FortuneInterface;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.springboot.demo")
@PropertySource("classpath:Sources/coach.properties")
public class ProjectConfiguration {

    @Bean
    public FortuneInterface otherFortuneService(){
        return new OtherFortuneService();
    }

    @Bean
    @Scope("prototype")
    public CoachInterface basketballCoach(){
        return new BasketballCoach(otherFortuneService());

    }

}
