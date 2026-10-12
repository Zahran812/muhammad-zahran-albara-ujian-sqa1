package com.juaracoding;

public class Perulangan1 {
    public static void main(String[]args){
        int[] latency = {320, 150, 480, 210, 890, 175, 430};

        int total = 0;
        int minLatency = latency[0];
        int maxLatency = latency[0];
        int slowRequests = 0;

        for (int ms:latency){
            total += ms;
            if(ms < minLatency){
                minLatency = ms;
            }
            if (ms > maxLatency){
                maxLatency = ms;
            }
            if (ms > 400){
                slowRequests ++;
            }
        }

        double avgLatency = (double) total / latency.length;

        System.out.println("Respon Time Analysis");
        System.out.println("---------------------");
        System.out.println("Total Request    : " + latency.length);
        System.out.println("Rata-rata        : " + avgLatency + "ms");
        System.out.println("Minimal          : " + minLatency + "ms");
        System.out.println("Maksimal         : " + maxLatency + "ms");
        System.out.println("Request Lambat   : " + slowRequests);
        
    }

}


