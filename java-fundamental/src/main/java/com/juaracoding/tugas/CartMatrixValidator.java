package com.juaracoding.tugas;

public class CartMatrixValidator {

    public static void main(String[] args) {
        // 1. Dataset Item Belanja
        String[] namaBarang = {
            "Laptop Asus SDET", 
            "Mechanical Keyboard", 
            "Mouse Wireless", 
            "USB-C Hub", 
            "Monitor 24 Inch"
        };
        long[] hargaSatuan = {14500000, 850000, 250000, 350000, 2100000};
        int[] kuantitas = {1, 2, 2, 1, 1};
        boolean[] statusStok = {true, true, true, false, true};

        long totalBelanjaBersih = 0;
        long hargaPalingMahal = 0;
        String barangPalingMahal = "-";

        System.out.println("========================================================================");
        System.out.println("                        STRUK VALIDASI KERANJANG                       ");
        System.out.println("========================================================================");
        System.out.printf("%-22s %-15s %-5s %-16s %-10s\n", "Nama Barang", "Harga Satuan", "Qty", "Subtotal", "Status");
        System.out.println("------------------------------------------------------------------------");

        // 2. Perulangan & Kalkulasi
        for (int i = 0; i < namaBarang.length; i++) {
            long subtotal = hargaSatuan[i] * kuantitas[i];

            // Cek ketersediaan stok
            if (!statusStok[i]) {
                System.out.printf("%-22s Rp %,12d %-5d Rp %,13d [WARNING: Stok Kosong!]\n", 
                        namaBarang[i], hargaSatuan[i], kuantitas[i], subtotal);
                continue; // Mengabaikan barang dari total
            }

            // Tambahkan ke total belanja bersih
            totalBelanjaBersih += subtotal;

            // Cek barang paling mahal
            if (hargaSatuan[i] > hargaPalingMahal) {
                hargaPalingMahal = hargaSatuan[i];
                barangPalingMahal = namaBarang[i];
            }

            // Cetak barang in-stock
            System.out.printf("%-22s Rp %,12d %-5d Rp %,13d %-10s\n", 
                    namaBarang[i], hargaSatuan[i], kuantitas[i], subtotal, "Tersedia");
        }

        // 3. Kalkulasi Diskon Voucher
        long diskon = 0;
        if (totalBelanjaBersih >= 10000000) {
            diskon = (long) (totalBelanjaBersih * 0.10); // Diskon 10%
            if (diskon > 1500000) {                      // Maksimal Rp 1.500.000
                diskon = 1500000;
            }
        } else if (totalBelanjaBersih >= 5000000) {
            diskon = (long) (totalBelanjaBersih * 0.05); // Diskon 5%
        }

        long totalBayarAkhir = totalBelanjaBersih - diskon;

        // 4. Cetak Ringkasan Total
        System.out.println("========================================================================");
        System.out.println("RINGKASAN PEMBAYARAN & VALIDASI UJI:");
        System.out.println("------------------------------------------------------------------------");
        System.out.printf("%-35s : %s (Rp %,d)\n", "Barang Termahal Dibeli", barangPalingMahal, hargaPalingMahal);
        System.out.printf("%-35s : Rp %,d\n", "Total Belanja Bersih (In-Stock)", totalBelanjaBersih);
        System.out.printf("%-35s : -Rp %,d\n", "Diskon Voucher", diskon);
        System.out.println("------------------------------------------------------------------------");
        System.out.printf("%-35s : Rp %,d\n", "TOTAL BAYAR AKHIR", totalBayarAkhir);
        System.out.println("========================================================================");
    }
}
