package com.juaracoding.ujian;

public class OrderTestCase {
    private String orderId;
    private String customerName;
    private double itemPrice;
    private int quantity;
    private double voucherDiscountPercent;
    private String testStatus;
    private String failureReason;

    // Constructor Berparameter
    public OrderTestCase(String orderId, String customerName, double itemPrice, int quantity, double voucherDiscountPercent) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.voucherDiscountPercent = voucherDiscountPercent;
        this.testStatus = "BLOCKED"; // Status awal sebelum evaluasi
        this.failureReason = "-";
    }

    // Method Menghitung Subtotal
    public double calculateSubtotal() {
        return itemPrice * quantity;
    }

    // Method Menghitung Nominal Diskon Voucher
    public double calculateDiscountAmount() {
        return calculateSubtotal() * (voucherDiscountPercent / 100.0);
    }

    // Method Menghitung Grand Total Akhir
    public double calculateGrandTotal() {
        return calculateSubtotal() - calculateDiscountAmount();
    }

    // Method Evaluasi Pembayaran Uji QA
    public void evaluatePayment(double actualAmountPaid) {
        double grandTotal = calculateGrandTotal();
        
        // Perbandingan dengan toleransi 0.001
        if (Math.abs(actualAmountPaid - grandTotal) <= 0.001) {
            this.testStatus = "PASS";
            this.failureReason = "-";
        } else {
            this.testStatus = "FAIL";
            if (actualAmountPaid < grandTotal) {
                double diff = grandTotal - actualAmountPaid;
                this.failureReason = String.format("Underpaid (Kurang Rp %,.2f)", diff);
            } else {
                double diff = actualAmountPaid - grandTotal;
                this.failureReason = String.format("Overpaid (Lebih Rp %,.2f)", diff);
            }
        }
    }

    // Method Menampilkan Baris Tabel Konsol
    public void displayRow() {
        System.out.printf("%-14s | %-16s | Rp %,12.2f | %-3d | %-7.1f%% | Rp %,12.2f | %-7s | %-28s\n",
                orderId, customerName, itemPrice, quantity, voucherDiscountPercent, calculateGrandTotal(), testStatus, failureReason);
    }

    // Getter untuk kebutuhan rekapitulasi di runner
    public String getTestStatus() {
        return testStatus;
    }

    public double getGrandTotal() {
        return calculateGrandTotal();
    }
}