package com.juaracoding.tugas;

public class Latihan {
    public static void main(String[] args) {
        int stok = 10;
        double jarak = 1.5;
        int harga;

        if (jarak <= 1) {
            harga = 10000;
        } else {
            harga = 15000;
        }

        System.out.println("Harga pemesanan: Rp " + harga);
        int jumlahBeli = 5;

        if (jumlahBeli > 0) {

            if (jumlahBeli <= stok) {
                System.out.println("Pembelian berhasil");
                System.out.println("Jumlah beli : " + jumlahBeli);
                System.out.println("Sisa stok   : " + (stok - jumlahBeli));
            } else {
                System.out.println("Pembelian gagal!");
                System.out.println("Jumlah beli melebihi stok.");
                System.out.println("Stok tersedia : " + stok);
            }

        } else {
            System.out.println("Pembelian gagal!");
            System.out.println("Jumlah beli harus lebih dari 0.");
        }     
    }

}
