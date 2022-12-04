package com.springboot.demo.beans.Coaches;

import com.springboot.demo.interfaces.CoachInterface;
import com.springboot.demo.interfaces.FortuneInterface;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/*equivalente a
<bean id = "myBaseballCoach"
class="com.springboot.demo.BaseballCoach">*/
//If no beanid is specified, spring generates default id = classname -> first letter lowercased
@Component("footballCoach")
@Scope("prototype")
public class FootballCoach implements CoachInterface, DisposableBean {

    //field dependecy injection
    @Autowired
    @Qualifier("randomFortuneService")
    private FortuneInterface fortuneInterface;

    public FootballCoach() {
        System.out.println("-----------------------------------");
        System.out.println("Empty Constructor");
        System.out.println("-----------------------------------");
    }

    //Inject dependency using autowired annotation
   /* @Autowired
    public FootballCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }*/

    @Override
    public String getWorkout() {
        return "Siiiiiiuuuuuuuuuuuu";
    }

    @Override
    public String getFortune() {
        System.out.println(fortuneInterface);
        return fortuneInterface.getFortune();
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Football destroy method invoked");
    }

    // setter/any method name dependency injection
    /*@Autowired
    public void methodFortuneService(FortuneService fortuneService) {
        System.out.println("-----------------------------------");
        System.out.println(" method executed");
        System.out.println("-----------------------------------");
        this.fortuneService = fortuneService;
    }*/
}
