package com.kai.models; // Menandakan lokasi package/folder models

import com.kai.interfaces.Cetak; // Import interface Cetak

// Class transaksi Tiket yang meng-implement interface Cetak
public class Tiket implements Cetak {
    private String kodeBooking; // Variabel kode booking transaksi
    private String status; // Variabel status bayar
    private String channelPemesanan; // Variabel media pesan
    private String tglTransaksi; // Variabel tanggal beli
    private String nomorKursi; // Variabel nomor tempat duduk
    private double hargaJual; // Variabel total harga tiket
    private Penumpang penumpang; // Objek data penumpang
    private Admin admin; // Objek data admin bertugas
    private Kereta kereta; // Objek data kereta yang dinaiki

    public Tiket() {} // Constructor kosong

    // Constructor lengkap transaksi tiket
    public Tiket(String kodeBooking, String status, String channelPemesanan, String tglTransaksi, String nomorKursi, double hargaJual, Penumpang penumpang, Admin admin, Kereta kereta) {
        this.kodeBooking = kodeBooking; // Set kodeBooking
        this.status = status; // Set status
        this.channelPemesanan = channelPemesanan; // Set channelPemesanan
        this.tglTransaksi = tglTransaksi; // Set tglTransaksi
        this.nomorKursi = nomorKursi; // Set nomorKursi
        this.hargaJual = hargaJual; // Set hargaJual
        this.penumpang = penumpang; // Set objek penumpang
        this.admin = admin; // Set objek admin
        this.kereta = kereta; // Set objek kereta
    }

    @Override
    public void cetakTiket() { // Implementasi cetak tiket ke terminal
        System.out.println("=========================================="); // Garis batas
        System.out.println("            TIKET KERETA API             "); // Judul tiket
        System.out.println("=========================================="); // Garis batas
        System.out.println("Kode Booking : " + kodeBooking); // Tampilkan kode booking
        System.out.println("Nama         : " + (penumpang != null ? penumpang.getNama() : "-")); // Tampilkan nama penumpang
        System.out.println("Kereta       : " + (kereta != null ? kereta.getNamaKereta() : "-")); // Tampilkan nama kereta
        System.out.println("Rute         : " + (kereta != null ? kereta.getStasiunAsal() + " -> " + kereta.getStasiunTujuan() : "-")); // Tampilkan rute
        System.out.println("Kursi        : " + nomorKursi); // Tampilkan nomor kursi
        System.out.println("Status       : " + status); // Tampilkan status pembayaran
        System.out.println("=========================================="); // Garis batas
    }

    @Override
    public void cetakStruk() { // Implementasi cetak struk pembayaran
        System.out.println("------------------------------------------"); // Garis pemisah
        System.out.println("         STRUK PEMBAYARAN " + PERUSAHAAN); // Judul struk memakai variabel interface
        System.out.println("------------------------------------------"); // Garis pemisah
        System.out.println("Tgl Transaksi: " + tglTransaksi); // Tampilkan tgl beli
        System.out.println("Channel      : " + channelPemesanan); // Tampilkan channel transaksi
        System.out.println("Petugas Admin: " + (admin != null ? admin.getNama() : "-")); // Tampilkan nama admin
        System.out.println("Total Bayar  : Rp " + hargaJual); // Tampilkan total biaya
        System.out.println("------------------------------------------"); // Garis pemisah
    }

    public String toFileFormat() { // Mengubah data tiket ke baris teks dipisahkan titik koma
        return kodeBooking + ";" + status + ";" + channelPemesanan + ";" + tglTransaksi + ";" + nomorKursi + ";" + hargaJual + ";" +
                (penumpang != null ? penumpang.getNama() : "-") + ";" +
                (admin != null ? admin.getNama() : "-") + ";" +
                (kereta != null ? kereta.getNamaKereta() : "-"); // Format penulisan txt
    }

    
    // Getter dan Setter
    public String getKodeBooking() { return kodeBooking; } // Ambil kodeBooking
    public void setKodeBooking(String kodeBooking) { this.kodeBooking = kodeBooking; } // Ubah kodeBooking

    public String getStatus() { return status; } // Ambil status
    public void setStatus(String status) { this.status = status; } // Ubah status

    public String getChannelPemesanan() { return channelPemesanan; } // Ambil channelPemesanan
    public void setChannelPemesanan(String channelPemesanan) { this.channelPemesanan = channelPemesanan; } // Ubah channelPemesanan

    public String getTglTransaksi() { return tglTransaksi; } // Ambil tglTransaksi
    public void setTglTransaksi(String tglTransaksi) { this.tglTransaksi = tglTransaksi; } // Ubah tglTransaksi

    public String getNomorKursi() { return nomorKursi; } // Ambil nomorKursi
    public void setNomorKursi(String nomorKursi) { this.nomorKursi = nomorKursi; } // Ubah nomorKursi

    public double getHargaJual() { return hargaJual; } // Ambil hargaJual
    public void setHargaJual(double hargaJual) { this.hargaJual = hargaJual; } // Ubah hargaJual

    public Penumpang getPenumpang() { return penumpang; } // Ambil penumpang
    public void setPenumpang(Penumpang penumpang) { this.penumpang = penumpang; } // Ubah penumpang

    public Admin getAdmin() { return admin; } // Ambil admin
    public void setAdmin(Admin admin) { this.admin = admin; } // Ubah admin

    public Kereta getKereta() { return kereta; } // Ambil kereta
    public void setKereta(Kereta kereta) { this.kereta = kereta; } // Ubah kereta
}