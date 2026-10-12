package com.juaracoding.tugas;

import java.util.Scanner;

public class ScholarshipEvaluatorTest {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Applicant ID: ");
        String applicantId = scanner.nextLine();

        System.out.print("Applicant Name: ");
        String applicantName = scanner.nextLine();

        System.out.print("Education Level (SMK/D3/S1/S2): ");
        String educationLevel = scanner.nextLine();

        System.out.print("Logic Score (0-100): ");
        double logicScore = scanner.nextDouble();

        System.out.print("Coding Score (0-100): ");
        double codingScore = scanner.nextDouble();

        System.out.print("Has Completed Documents (true/false): ");
        boolean hasCompletedDocuments = scanner.nextBoolean();

        System.out.print("Years of Experience: ");
        int yearsOfExperience = scanner.nextInt();

        // VALIDASI INPUT

        if (logicScore < 0 || logicScore > 100) {
            System.out.println("Logic score harus berada di antara 0 dan 100.");
            scanner.close();
            return;
        }

        if (codingScore < 0 || codingScore > 100) {
            System.out.println("Coding score harus berada di antara 0 dan 100.");
            scanner.close();
            return;
        }

        if (yearsOfExperience < 0) {
            System.out.println("Years of experience tidak boleh kurang dari 0.");
            scanner.close();
            return;
        }

        // KALKULASI SKOR AKHIR

        double finalScore = (logicScore * 0.40) + (codingScore * 0.60);

        // PENENTUAN GRADE

        String grade;

        if (finalScore >= 85.0) {
            grade = "A (EXCELLENT)";
        } else if (finalScore >= 75.0) {
            grade = "B (GOOD)";
        } else if (finalScore >= 65.0) {
            grade = "C (SATISFACTORY)";
        } else {
            grade = "D (UNQUALIFIED)";
        }

        // PENENTUAN STATUS BEASISWA

        String scholarshipStatus;

        // Dokumen tidak lengkap = langsung ditolak
        if (!hasCompletedDocuments) {

            scholarshipStatus =
                    "REJECTED (Dokumen Administrasi Tidak Lengkap)";

        } else {
            // Dokumen lengkap
            if (finalScore >= 85.0) {
                scholarshipStatus =
                        "FULL_SCHOLARSHIP_100% (Beasiswa Penuh Bebas Biaya)";

            } else if (finalScore >= 75.0) {
                // Skor 75 - <85
                if (yearsOfExperience >= 1) {

                    scholarshipStatus =
                            "PARTIAL_SCHOLARSHIP_75%";

                } else {
                    // Fresh graduate
                    scholarshipStatus =
                            "PARTIAL_SCHOLARSHIP_50%";
                }

            } else {

                scholarshipStatus =
                        "REGULAR_ADMISSION (Tidak Mendapatkan Beasiswa)";
            }
        }

        // =========================
        // OUTPUT HASIL EVALUASI
        // =========================

        System.out.println("==================================================");
        System.out.println("    JUARACODING - SCHOLARSHIP QUALIFICATION ENGINE");
        System.out.println("             Bootcamp SQA Automation 2026");
        System.out.println("==================================================");

        System.out.println("ID Pendaftar        : " + applicantId);
        System.out.println("Nama Lengkap        : " + applicantName);
        System.out.println("Pendidikan Terakhir : " + educationLevel);
        System.out.println("Pengalaman Kerja    : " + yearsOfExperience + " Tahun");

        if (hasCompletedDocuments) {
            System.out.println("Dokumen Lengkap     : Lengkap (TRUE)");
        } else {
            System.out.println("Dokumen Lengkap     : Tidak Lengkap (FALSE)");
        }

        System.out.println("--------------------------------------------------");

        System.out.printf("Skor Tes Logika     : %.2f (Bobot 40%%)%n", logicScore);
        System.out.printf("Skor Tes Coding     : %.2f (Bobot 60%%)%n", codingScore);
        System.out.printf("SKOR RATA-RATA      : %.2f%n", finalScore);
        System.out.println("GRADE PENCAPAIAN    : " + grade);

        System.out.println("--------------------------------------------------");

        System.out.println("HASIL KELAYAKAN     : " + scholarshipStatus);

        // Status pengujian berdasarkan kelayakan
        if (scholarshipStatus.startsWith("REJECTED")) {
            System.out.println("STATUS PENGUJIAN    : FAIL [NOT QUALIFIED]");
        } else {
            System.out.println("STATUS PENGUJIAN    : PASS [QUALIFIED]");
        }

        System.out.println("==================================================");

        scanner.close();
    }
}