package com.springboot.demo.beans.Fortunes;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.springboot.demo.interfaces.FortuneInterface;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;


@Component
public class RandomFortuneService implements FortuneInterface {

    private List<String[]> randoms;
    private Random random = new Random();

    public RandomFortuneService() {
        System.out.println("-----------------------------------");
        System.out.println("Random Constructor");
    }

    @PostConstruct
    //for java 9-11 add avax.annotation-api-1.3.2.jar
    private void fillRandomsArray(){
        System.out.println("Random Post Construct");
        InputStream inputStream = getClass().getResourceAsStream("/CSV/RandomFortunes.csv");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        CSVReader csvReader = new CSVReader(inputStreamReader);
        try {
            randoms = csvReader.readAll();
            inputStream.close();
            inputStreamReader.close();
            csvReader.close();
        } catch (CsvException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getFortune() {
        //fillRandomsArray();
        int pos1 = random.nextInt(randoms.size());
        int pos2 = random.nextInt(randoms.get(pos1).length);
        return randoms.get(pos1)[pos2];
    }

    @PreDestroy
    private void preDestruct(){
        System.out.println("Pre Destroy Random");
        System.out.println("-----------------------------------");
    }
}
