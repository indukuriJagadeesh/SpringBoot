package com.springboot.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) {
        String name ="DC501GCCCA098911";

        String[] arr = name.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");

        for(String str : arr){

            char[] c =str.toCharArray();
            Arrays.sort(c);

            System.out.print(c);

        }
        /*Pattern p = Pattern.compile("[a-z]+|[\\d]+");
        Matcher m = p.matcher(name);
        while(m.find()){
            char[] ch = m.group().toCharArray();
            Arrays.sort(ch);
            System.out.println(ch);
        }*/
    }
}
