package com.nhnacademy.app;

import java.util.Random;
import org.apache.commons.math3.random.RandomDataGenerator;

public class RandomNum {
    
    public static void main(String[] args) {
        Random r = new Random();
    
        System.out.println("java.util.Random : " + r.nextInt(101));

        RandomDataGenerator rg = new RandomDataGenerator();
        System.out.println("apache.commons.math3.random : " + rg.nextInt(1, 101));
    }
}
