package com.juaracoding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static void main(String[] args) {
        
    String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    String testEmail = "sdet.engineer@juaracoding.com";
    System.out.println("Email Valid? " + testEmail.matches(emailRegex));
    // 2. Validasi Nomor HP Indonesia (08xx atau +628xx atau 628xx)
    String phoneRegex = "^(\\+62|62|0)8[1-9][0-9]{7,10}$";
    String testPhone = "+6281234567890";
    System.out.println("No HP Valid? " + testPhone.matches(phoneRegex));
    // 3. Validasi Format Tanggal ISO (YYYY-MM-DD)
    String dateRegex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
    String testDate = "2026-10-08";
    System.out.println("Format Tanggal Valid? " + testDate.matches(dateRegex));

    String smsNotification = "Jangan berikan kepada siapapun. Kode OTP verifikasi transaksi Anda adalah 849201. Berlaku 5 menit.";
    // Pola mencari 6 digit angka berurutan
    Pattern pattern = Pattern.compile("\\b(\\d{6})\\b");
    Matcher matcher = pattern.matcher(smsNotification);
    if (matcher.find()) {
    String extractedOtp = matcher.group(1);
    System.out.println("✅ Ekstraksi Berhasil! Kode OTP: " + extractedOtp);
    } else {
    System.out.println("❌ Gagal menemukan kode OTP.");
    }
    }
}
