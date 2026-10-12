package com.juaracoding;

import java.util.Scanner;
public class StringComparison {
    public static void main(String[] args) {
        String expectedTitle = "Dashboard Nasabah";
        String actualTitle = new String("Dashboard Nasabah");

        System.out.println("Hasil (==) :"+(expectedTitle == actualTitle));
        System.out.println("Hasil (.equals) : " + actualTitle.equals(expectedTitle));
    }

}
