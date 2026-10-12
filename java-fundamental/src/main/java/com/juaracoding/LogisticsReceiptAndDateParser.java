package com.juaracoding;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogisticsReceiptAndDateParser {
public static void main(String[] args){
    String emailBody = """
    Halo Budi Santoso,
    Pesanan Anda #99012 telah dikirim dengan nomor resi TRX-JNE-48920194.
    Tanggal Pengiriman: 2026-10-08.
    Estimasi Tiba: 2026-10-10.
    Kurir logistik: JNE Express.
    """;
    // 1. Ekstraksi Resi via Regex
    Pattern receiptPattern = Pattern.compile("(TRX-([A-Z]{3,5})-(\\d{8}))");
    Matcher receiptMatcher = receiptPattern.matcher(emailBody);
    // 2. Ekstraksi Tanggal via Regex
    Pattern datePattern = Pattern.compile("Tanggal Pengiriman: (\\d{4}-\\d{2}-\\d{2}).*Estimasi Tiba: (\\d{4}-\\d{2}-\\d{2})", Pattern.DOTALL);
    Matcher dateMatcher = datePattern.matcher(emailBody);
    System.out.println("=================================================================");
    System.out.println(" VERIFIKASI LOGISTIK & SLA PENGIRIMAN");
    System.out.println("=================================================================");
    
    if (receiptMatcher.find() && dateMatcher.find()) {
        String fullReceipt = receiptMatcher.group(1);
        String courierCode = receiptMatcher.group(2);
        String dispatchDateStr = dateMatcher.group(1);
        String arrivalDateStr = dateMatcher.group(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dispatchDate = LocalDate.parse(dispatchDateStr, formatter);
        LocalDate arrivalDate = LocalDate.parse(arrivalDateStr, formatter);
        long leadTimeDays = ChronoUnit.DAYS.between(dispatchDate, arrivalDate);
        System.out.println("Nomor Resi : " + fullReceipt);
        System.out.println("Kurir : " + courierCode);
        System.out.println("Tgl Pengiriman : " + dispatchDate);
        System.out.println("Estimasi Tiba : " + arrivalDate);
        System.out.println("Lead Time (Hari) : " + leadTimeDays + " hari");
        // SLA Assertion: Pengiriman reguler maksimal 3 hari
        if (leadTimeDays <= 3) {
            System.out.println("Status Verifikasi : [PASS] Lead time pengiriman memenuhi SLA!");
        } else {
            System.out.println("Status Verifikasi : [FAIL] SLA Breach! Estimasi tiba melebihi 3 hari.");
        }}
        else {
            System.out.println("Status Verifikasi : [FAIL] Komponen data tidak lengkap pada notifikasi.");
        }
        System.out.println("=================================================================");
    }
}
