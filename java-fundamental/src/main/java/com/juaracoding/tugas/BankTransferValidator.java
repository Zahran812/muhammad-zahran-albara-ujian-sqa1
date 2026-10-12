package com.juaracoding.tugas;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BankTransferValidator {

    // Method sanitasi & parsing nominal uang
    public static double sanitizeAndParse(String priceStr) {
        // Menghapus 'Rp', titik, spasi, lalu mengubah koma desimal menjadi titik
        String clean = priceStr.replaceAll("[^0-9,]", "").replace(",", ".");
        return Double.parseDouble(clean);
    }

    public static void main(String[] args) {
        // Dataset Uji Mentah (Raw Banking Transfer Logs)
        String[] rawTransfers = {
            "TRX: FT20261008-882190 | Waktu: 2026-10-08 09:15:30 | Rek Asal: 5210984122 (Budi Santoso) | Rek Tujuan: BCA-8830192841 (Siti Rahma) | Nominal: Rp 2.500.000,00 | Tipe: BI-FAST | Status: SUCCESS",
            "TRX: FT20261008-882191 | Waktu: 2026-10-08 11:20:10 | Rek Asal: 5210984122 (Budi Santoso) | Rek Tujuan: MANDIRI-1200098124 (Toko Komputer) | Nominal: Rp 14.750.000,00 | Tipe: REALTIME_ONLINE | Status: SUCCESS",
            "TRX: FT20261008-882192 | Waktu: 2026-10-08 14:45:00 | Rek Asal: 5210984122 (Budi Santoso) | Rek Tujuan: BRI-0209182391 (Andi Wijaya) | Nominal: Rp 500.000,00 | Tipe: BI-FAST | Status: SUCCESS",
            "TRX: FT20261008-882193 | Waktu: 2026-10-08 20:10:45 | Rek Asal: 5210984122 (Budi Santoso) | Rek Tujuan: BNI-9910293841 (Rina Amelia) | Nominal: Rp 50.000.000,00 | Tipe: RTGS | Status: SUCCESS"
        };

        // Pola Regex untuk Ekstraksi Komponen Mutasi
        String regex = "TRX:\\s*(FT\\d{8}-\\d{6})\\s*\\|\\s*Waktu:\\s*(\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}:\\d{2})\\s*\\|\\s*Rek Asal:.*?\\|\\s*Rek Tujuan:\\s*(.*?)\\s*\\|\\s*Nominal:\\s*(Rp\\s*[0-9.,]+)\\s*\\|\\s*Tipe:\\s*(BI-FAST|REALTIME_ONLINE|RTGS)";
        Pattern pattern = Pattern.compile(regex);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalTime cutoffTime = LocalTime.of(15, 0, 0);

        double grandTotalTransfer = 0;
        double grandTotalAdmin = 0;
        double grandTotalDebit = 0;

        System.out.println("=============================================================================================================================");
        System.out.println("                                             REKAPITULASI AUDIT MUTASI TRANSFER                                              ");
        System.out.println("=============================================================================================================================");
        System.out.printf("%-18s | %-19s | %-32s | %-15s | %-15s | %-10s | %-28s\n", 
                "Kode Transaksi", "Timestamp", "Rekening Tujuan", "Nominal", "Biaya Admin", "Tipe", "Status Kliring");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        for (String raw : rawTransfers) {
            Matcher matcher = pattern.matcher(raw);

            if (matcher.find()) {
                String kodeTrx = matcher.group(1);
                String timestampStr = matcher.group(2);
                String rekTujuan = matcher.group(3);
                String nominalStr = matcher.group(4);
                String tipeTransfer = matcher.group(5);

                // Sanitasi dan parsing nominal
                double nominal = sanitizeAndParse(nominalStr);

                // Penentuan Biaya Admin
                double adminFee = 0;
                if (tipeTransfer.equals("BI-FAST")) {
                    adminFee = 2500;
                } else if (tipeTransfer.equals("REALTIME_ONLINE")) {
                    adminFee = 6500;
                } else if (tipeTransfer.equals("RTGS")) {
                    adminFee = 30000;
                }

                double totalDebit = nominal + adminFee;

                // Parsing Timestamp & Validasi Cut-off RTGS
                LocalDateTime dateTime = LocalDateTime.parse(timestampStr, formatter);
                LocalTime transactionTime = dateTime.toLocalTime();

                String statusKliring = "SETTLED";
                if (tipeTransfer.equals("RTGS")) {
                    if (transactionTime.isAfter(cutoffTime)) {
                        statusKliring = "[PENDING_NEXT_DAY_SETTLEMENT]";
                    } else {
                        statusKliring = "[SAME_DAY_SETTLED]";
                    }
                } else {
                    statusKliring = "[SAME_DAY_SETTLED]";
                }

                // Akumulasi Total
                grandTotalTransfer += nominal;
                grandTotalAdmin += adminFee;
                grandTotalDebit += totalDebit;

                // Cetak per Baris
                System.out.printf("%-18s | %-19s | %-32s | Rp %,12.2f | Rp %,12.2f | %-15s | %-28s\n",
                        kodeTrx, timestampStr, rekTujuan, nominal, adminFee, tipeTransfer, statusKliring);
            }
        }

        System.out.println("=============================================================================================================================");
        System.out.println("                                                      REKAPITULASI TOTAL                                                     ");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-40s : Rp %,16.2f\n", "Total Transfer Bersih", grandTotalTransfer);
        System.out.printf("%-40s : Rp %,16.2f\n", "Total Biaya Admin Perbankan", grandTotalAdmin);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-40s : Rp %,16.2f\n", "TOTAL PENGURANGAN SALDO (GRAND DEBIT)", grandTotalDebit);
        System.out.println("=============================================================================================================================");
    }
}