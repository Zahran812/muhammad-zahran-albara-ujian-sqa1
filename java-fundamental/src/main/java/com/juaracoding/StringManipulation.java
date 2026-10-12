package com.juaracoding;

import javax.xml.crypto.Data;

public class StringManipulation {
    public static void main(String[] args) {
        StringBuilder testLog = new StringBuilder();
        testLog.append("Step 1: Buka halaman Checkout\n");
        testLog.append("Step 2: Login sebagai user valid");
        testLog.append("Step 3: Pilih metode pembayaran COD\n");
        testLog.append("Step 4: Verifikasi total harga");

        String finalLog = testLog.toString();

        System.out.println(finalLog);

        String rawHeader = "  Selamat datang di Juara Coding\n";
        String cleanHeader = rawHeader.strip();
        System.out.println(cleanHeader);

        String invoiceText = "Nomor Transaksi : INV-202610-8891 (Telah Dibayar)";
        int startIndex = invoiceText.indexOf("INV-");
        int endIndex = invoiceText.indexOf("(");
        String invoiceId = invoiceText.substring(startIndex, endIndex);
        System.out.println("Extracted Invoice ID: " + invoiceId);

        //3. Split Data Respons CSV
        String csvRow = "TC-101;UserCheckout;PASS;245ms";
        String[] columns = csvRow.split(";");
        System.out.println("Test ID : " + columns[0]);
        System.out.println("Test Name : " + columns[1]);
        System.out.println("Status : " + columns[2]);
        System.out.println("Duration : " + columns[3]);

        String rawStock = "90";
        int stok = Integer.parseInt(rawStock);
        stok = stok-10;
        System.out.println("Stok : " + stok);
    }

}
