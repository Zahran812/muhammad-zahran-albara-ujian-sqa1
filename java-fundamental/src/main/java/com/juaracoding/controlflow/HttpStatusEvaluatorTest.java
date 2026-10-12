package com.juaracoding.controlflow;

import java.util.Scanner;
public class HttpStatusEvaluatorTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=================================================");
        System.out.println(" API CONTRACT & HTTP STATUS CODE EVALUATOR ");
        System.out.println(" JuaraCoding SQA Bootcamp 2026 ");
        System.out.println("=================================================");
        System.out.print("1. Masukkan API Endpoint (misal: /api/v1/auth) : ");
        String endpoint = scanner.nextLine();
        System.out.print("2. Masukkan HTTP Method (GET/POST/PUT/DELETE) : ");
        String httpMethod = scanner.nextLine().toUpperCase();
        System.out.print("3. Masukkan HTTP Status Code Respon : ");
        int statusCode = scanner.nextInt();
        // Konsumsi newline buffer
        scanner.nextLine();
        System.out.print("4. Masukkan Respon Body Message : ");
        String responseBody = scanner.nextLine();// 1. Evaluasi Kategori HTTP Status Code via Switch Expression
        String category = switch (statusCode / 100) {
        case 2 -> "2xx - SUCCESS";
        case 3 -> "3xx - REDIRECTION";
        case 4 -> "4xx - CLIENT_ERROR";
        case 5 -> "5xx - SERVER_ERROR";
        default -> "UNKNOWN_STATUS_CATEGORY";
        };
        // 2. Evaluasi Detail Test Result
        String testStatus;
        String diagnosticReason;
        if (statusCode == 200 || statusCode == 201) {
            testStatus = "PASS";
            diagnosticReason = "Permintaan berhasil diproses oleh endpoint " +endpoint;
        } else if (statusCode == 400) {
            testStatus = "FAIL (BAD_REQUEST)";
            diagnosticReason = "Format payload atau parameter request tidak sesuai kontrak JSON";
        } else if (statusCode == 401) {
            testStatus = "FAIL (UNAUTHORIZED)";
            diagnosticReason = "Autentikasi gagal: Bearer Token tidak disertakan atau kadaluarsa";
        } else if (statusCode == 403) {
            testStatus = "FAIL (FORBIDDEN)";
            diagnosticReason = "Nasabah tidak memiliki hak akses (Role Authorization Denied)";
        } else if (statusCode == 404) {
            testStatus = "FAIL (NOT_FOUND)";
            diagnosticReason = "Resource URL endpoint " + endpoint + " tidak ditemukan di server";
        } else if (statusCode == 422) {
        testStatus = "FAIL (UNPROCESSABLE_ENTITY)";
        diagnosticReason = "Kegagalan validasi logika bisnis (Saldo kurang atau limit terlampaui)";
        } else if (statusCode >= 500) {
        testStatus = "FAIL (SERVER_FAULT)";
        diagnosticReason = "Backend server mengalami crash / database deadlock";
        } else {
        testStatus = "SKIP";
        diagnosticReason = "Status code tidak terdaftar dalam matriks test";
        } 
        //3. Tampilkan Laporan Eksekusi Pengujian
        System.out.println("\n=================================================");
        System.out.println(" LAPORAN EVALUASI PENGUJIAN API ");
        System.out.println("=================================================");
        System.out.println("Endpoint Target : [" + httpMethod + "] " + endpoint);
        System.out.println("Status Code Respon : HTTP " + statusCode + " (" + category + ")");
        System.out.println("Pesan Respon : " + responseBody);System.out.println("-------------------------------------------------");
        System.out.println("HASIL PENGUJIAN : " + testStatus);
        System.out.println("Diagnosa SQA : " + diagnosticReason);
        System.out.println("=================================================");
        scanner.close();
    }
}