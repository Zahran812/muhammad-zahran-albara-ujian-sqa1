package com.juaracoding.tugas;

import java.util.Scanner;

public class LoanCalculatorTest {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("==============================================");
            System.out.println("  FAST_DANA DIGITAL - QA LOAN DATA SIMULATOR");
            System.out.println("        JuaraCoding Bootcamp SQA 2026");
            System.out.println("==============================================");

            // Input dari terminal
            System.out.print("ID Pengajuan         : ");
            String loanApplicationId = scanner.nextLine();

            System.out.print("Nama Nasabah         : ");
            String borrowerName = scanner.nextLine();

            System.out.print("Pokok Pinjaman       : Rp ");
            double principalAmount = scanner.nextDouble();

            System.out.print("Tenor (bulan)        : ");
            int tenorMonths = scanner.nextInt();

            System.out.print("Bunga Tahunan (%)    : ");
            double annualInterestRate = scanner.nextDouble();

            System.out.print("Biaya Provisi (%)    : ");
            double provisionFeePercent = scanner.nextDouble();

            // Kalkulasi
            double provisionFeeAmount =
                    principalAmount * (provisionFeePercent / 100.0);

            double netAmountReceived =
                    principalAmount - provisionFeeAmount;

            double monthlyInterestRate =
                    annualInterestRate / 12.0;

            double totalInterestAmount =
                    principalAmount
                            * (annualInterestRate / 100.0)
                            * (tenorMonths / 12.0);

            double totalRepaymentAmount =
                    principalAmount + totalInterestAmount;

            double monthlyInstallment =
                    totalRepaymentAmount / tenorMonths;

            boolean isEligible =
                    principalAmount >= 1_000_000
                            && principalAmount <= 50_000_000;

            // Output
            System.out.println();
            System.out.println("==============================================");
            System.out.printf("ID Pengajuan       : %s%n", loanApplicationId);
            System.out.printf("Nama Nasabah       : %s%n", borrowerName);
            System.out.printf("Pokok Pinjaman     : Rp %,.2f%n", principalAmount);
            System.out.printf("Tenor              : %d Bulan%n", tenorMonths);
            System.out.printf("Biaya Provisi (%.1f%%): Rp %,.2f%n",
                    provisionFeePercent, provisionFeeAmount);
            System.out.printf("Dana Diterima Cair : Rp %,.2f%n",
                    netAmountReceived);
            System.out.println("----------------------------------------------");
            System.out.printf("Bunga Bulanan      : %.2f%%%n", monthlyInterestRate);
            System.out.printf("Total Bunga (%.1f%%): Rp %,.2f%n",
                    annualInterestRate, totalInterestAmount);
            System.out.printf("Total Pengembalian : Rp %,.2f%n",
                    totalRepaymentAmount);
            System.out.printf("Cicilan per Bulan  : Rp %,.2f / bulan%n",
                    monthlyInstallment);
            System.out.println("----------------------------------------------");
            System.out.printf("Status Approval    : %s%n",
                    isEligible ? "ELIGIBLE [PASS]" : "NOT ELIGIBLE [FAIL]");
            System.out.println("==============================================");
        }
    }
}
