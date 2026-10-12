package com.juaracoding;

public class Variable {

    public static void main(String[] args) {
        // 1. Variabel Tipe Data Primitif
        int httpStatusCode = 200;
        long accountBalance = 150_000_000L; // Akhiran 'L' untuk long
        double itemPrice = 749_999.50;
        boolean isElementVisible = true;char currencySymbol = '$';
        // 2. Variabel Tipe Data Referensi (String)
        String testCaseId = "TC-LOGIN-001";
        String expectedMessage = "Login Berhasil Diproses";
        // Menampilkan log variabel
        System.out.println("ID Kasus Uji : " + testCaseId);
        System.out.println("Status Respons : " + httpStatusCode);
        System.out.println("Saldo Akun : Rp " + accountBalance);
        System.out.println("Harga Produk : " + currencySymbol + itemPrice);
        System.out.println("Elemen Muncul : " + isElementVisible);
        System.out.println("Pesan Diharapkan: " + expectedMessage);
    }
}
