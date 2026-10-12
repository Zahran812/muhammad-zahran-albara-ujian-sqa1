package com.juaracoding;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Voucher {

    public static double sanitizeAndParse(String priceStr) {
        String clean = priceStr.replaceAll("[^0-9,]", "").replace(",", ".");
        return Double.parseDouble(clean);
    }
    
    public static void main(String[] args) {
        String[][] cartItems = {
            {"Logitech MX Master 3S", "Rp 1.650.000,00", "2"},
            {"Keychron K2 Wireless", "Rp 1.200.000,00", "1"},
            {"Dell 27 Inch 4K Monitor", "Rp 5.800.000,00", "1"}
        };

        String voucherDiscountText = "Rp 500.000,00";
        String voucherExpiryText = "2026-10-15"; // Tanggal kedaluwarsa voucher
        LocalDate today = LocalDate.of(2026, 10, 8); // Simulasi hari ini

        double calculatedSubtotal = 0;

        System.out.println("==========================================================================");
        System.out.println("            VERIFIKASI KALKULASI KERANJANG & VALIDASI VOUCHER EXPIRED     ");
        System.out.println("==========================================================================");

        for (String[] item : cartItems) {
            String name = item[0];
            double unitPrice = sanitizeAndParse(item[1]);
            int qty = Integer.parseInt(item[2]);

            double itemTotal = unitPrice * qty;
            calculatedSubtotal += itemTotal;

            System.out.printf("%-26s | @Rp %,12.2f x %d = Rp %,14.2f\n", name, unitPrice, qty, itemTotal);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate expiryDate = LocalDate.parse(voucherExpiryText, formatter);

        double appliedDiscount = 0;
        boolean isVoucherValid = !today.isAfter(expiryDate);

        if (isVoucherValid) {
            appliedDiscount = sanitizeAndParse(voucherDiscountText);
            System.out.println("Status Voucher           : [ACTIVE] Diskon diterapkan.");
        } else {
            System.out.println("Status Voucher           : [EXPIRED] Voucher kedaluwarsa, diskon Rp 0.");
        }

        double grandTotal = calculatedSubtotal - appliedDiscount;

        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("Subtotal Belanja         : Rp %,14.2f\n", calculatedSubtotal);
        System.out.printf("Potongan Voucher         : Rp %,14.2f\n", appliedDiscount);
        System.out.printf("Grand Total Akhir        : Rp %,14.2f\n", grandTotal);
        System.out.println("==========================================================================");
    }
}