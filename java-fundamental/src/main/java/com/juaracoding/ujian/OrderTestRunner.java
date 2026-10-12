package com.juaracoding.ujian;

public class OrderTestRunner {
    public static void main(String[] args) {
        // 1. Membuat minimal 4 objek OrderTestCase dalam array orderSuite
        OrderTestCase[] orderSuite = {
            new OrderTestCase("ORD-2026-001", "Budi Santoso", 5000000.0, 1, 0.0),   // Skenario 1: Pembayaran Pas
            new OrderTestCase("ORD-2026-002", "Siti Rahma", 2000000.0, 2, 10.0),  // Skenario 2: Pembayaran Kurang (Underpaid)
            new OrderTestCase("ORD-2026-003", "Andi Wijaya", 10000000.0, 1, 20.0), // Skenario 3: Diskon Voucher 20% (PASS)
            new OrderTestCase("ORD-2026-004", "Rina Amelia", 1500000.0, 3, 0.0)    // Skenario 4: Pembayaran Lebih (Overpaid)
        };

        // Simulasi nominal pembayaran aktual yang dibayarkan nasabah untuk masing-masing skenario
        double[] simulatedPayments = {
            5000000.00, // Skenario 1: Dibayar pas (Grand Total: Rp 5.000.000) -> PASS
            3000000.00, // Skenario 2: Dibayar Rp 3.000.000 (Grand Total: Rp 3.600.000) -> FAIL (Underpaid)
            8000000.00, // Skenario 3: Dibayar pas setelah diskon 20% (Grand Total: Rp 8.000.000) -> PASS
            5500000.00  // Skenario 4: Dibayar Rp 5.500.000 (Grand Total: Rp 4.500.000) -> FAIL (Overpaid)
        };

        // Header Tabel Rincian Pengujian
        System.out.println("=========================================================================================================================================");
        System.out.println("                                                    LAPORAN PENGUJIAN QA - ORDER TEST SUITE                                              ");
        System.out.println("=========================================================================================================================================");
        System.out.printf("%-14s | %-16s | %-15s | %-3s | %-8s | %-15s | %-7s | %-28s\n",
                "Order ID", "Customer Name", "Item Price", "Qty", "Disc (%)", "Grand Total", "Status", "Failure Reason / Notes");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");

        int passCount = 0;
        int failCount = 0;
        double totalNetRevenue = 0;

        // Eksekusi pengujian per item di array
        for (int i = 0; i < orderSuite.length; i++) {
            orderSuite[i].evaluatePayment(simulatedPayments[i]);
            orderSuite[i].displayRow();

            // Akumulasi statistik
            if (orderSuite[i].getTestStatus().equals("PASS")) {
                passCount++;
                totalNetRevenue += orderSuite[i].getGrandTotal(); // Omzet hanya dihitung dari transaksi yang PASS
            } else {
                failCount++;
            }
        }

        int totalTests = orderSuite.length;
        double passPercentage = ((double) passCount / totalTests) * 100.0;

        // Rekapitulasi Laporan Uji QA
        System.out.println("=========================================================================================================================================");
        System.out.println("                                                     REKAPITULASI LAPORAN UJI QA                                                         ");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-48s : Rp %,18.2f\n", "Total Akumulasi Omzet Transaksi Bersih (PASS)", totalNetRevenue);
        System.out.printf("%-48s : %d ( dari %d total pengujian )\n", "Jumlah Test PASS", passCount, totalTests);
        System.out.printf("%-48s : %d ( dari %d total pengujian )\n", "Jumlah Test FAIL", failCount, totalTests);
        System.out.printf("%-48s : %.2f%%\n", "Persentase Kelulusan (Pass Rate)", passPercentage);
        System.out.println("=========================================================================================================================================");
    }
}