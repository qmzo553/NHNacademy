package com.nhnacademy.app;

import org.apache.commons.lang3.StringUtils;

public class EmptyString {
    
    public static void main(String[] args) {
        
        String str1 = "hello";

        System.out.println("java library : "  + str1.isEmpty());

        String str2 = "hi";
        
        System.out.println("apache.commons.lang : " + StringUtils.isEmpty(str2));
    }
}
