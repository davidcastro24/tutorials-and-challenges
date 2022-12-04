package com.springboot.demo;

import com.springboot.demo.interfaces.CoachInterface;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
       /* ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");*/
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfiguration.class);
        CoachInterface footballCoach = context.getBean("footballCoach", CoachInterface.class);
        CoachInterface soccerCoach = context.getBean("footballCoach", CoachInterface.class);
        CoachInterface basketballCoach = context.getBean("basketballCoach",CoachInterface.class);
        CoachInterface kobe = context.getBean("basketballCoach",CoachInterface.class);
        System.out.println("-----------------------------------");
        System.out.println(footballCoach==soccerCoach);
        System.out.println(footballCoach);
        System.out.println(soccerCoach);
        System.out.println("-----------------------------------");
        System.out.println(footballCoach.getWorkout());
        System.out.println(basketballCoach.getWorkout());
        System.out.println("-----------------------------------");
        System.out.println(footballCoach.getFortune());
        System.out.println(soccerCoach.getFortune());
        System.out.println(basketballCoach.getFortune());
        System.out.println("-----------------------------------");
        System.out.println(kobe);
        System.out.println(basketballCoach);
        System.out.println("-----------------------------------");
        System.out.println(kobe.toString());
        System.out.println("-----------------------------------");
        context.close();

    }
}
