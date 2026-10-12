package com.juaracoding;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class DateTime {
    public static void main(String[] args) {
        
        LocalDateTime now = LocalDateTime.now();
        // Pola: TahunBulanTanggal_JamMenitDetik
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = now.format(formatter);
        String uniqueEmail = "user_test_" + timestamp + "@juaracoding.com";
        String uniqueInvoice = "INV-" + timestamp;
        System.out.println("Generated Dynamic Email : " + uniqueEmail);
        System.out.println("Generated Dynamic Invoice : " + uniqueInvoice);
        {


        String voucherExpiryText = "15-10-2026"; // Format: dd-MM-yyyy

        LocalDate expiryDate = LocalDate.parse(voucherExpiryText, formatter);
        LocalDate today = LocalDate.now(); // Misal: 2026-10-08
        System.out.println("Tanggal Hari Ini : " + today);
        System.out.println("Masa Berlaku Voucher : " + expiryDate);
        // Validasi Expiry
        if (today.isBefore(expiryDate) || today.isEqual(expiryDate)) {
        long remainingDays = ChronoUnit.DAYS.between(today, expiryDate);
        System.out.println("Status Voucher : [VALID] Sisa masa aktif: " +
        remainingDays + " hari.");
        }
        else {
        System.out.println("Status Voucher : [EXPIRED] Voucher sudah kedaluwarsa!");
        }
    }
  }
}
