package com.juaracoding;

public class Perulangan {
    public static void main(String[]args){

        for(int i = 1; i <=5; i++){
            for (int j = 1; j <= 5; j++){
                System.out.println("* ");
            }
            System.out.println();
        }

        int sisi = 5;

        for (int i = sisi; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }

        String[] browserList = { "Chrome","Firefox","Edge","Safari"};

        for(String b : browserList){
            System.out.println("test case browser " + b);
        }

        int httpStatusCode;
        int attempt = 0;

        do {
            attempt++;
            System.out.println("Mengirim API Request (Attempt #" + attempt + ")...");
            httpStatusCode = (attempt == 2) ? 200 : 503;
        }while(httpStatusCode != 200 && attempt <3);

        System.out.println("Hasil Akhir: " + httpStatusCode);

        String[] testResluts = {"Pass", "PASS", "FAIL", "PASS", "BLOCKED", "PASS"};
        for(int i = 0; i < testResluts.length; i++){
            System.out.println("Test Case #" + (i+1) + ": " + testResluts[i]);
        }


        String [] cities = {"Jakarta", "Bandung", "Surabaya", "Yogyakarta"};

        String city = "Surabaya";
        
        for (String c : cities){
            System.out.println("Mencari kota: " + c);
            if(c.equals(city)){
                System.out.println("Menemukan kota tujuan!");
                break;
            }
        }



    }
}
