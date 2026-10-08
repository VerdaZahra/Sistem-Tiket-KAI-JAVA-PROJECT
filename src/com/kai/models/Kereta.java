package com.kai.models; // Menandakan lokasi package/folder models

// Class abstrak cetak biru untuk jenis-jenis kereta
public abstract class Kereta {
    private String namaKereta; // Variabel nama kereta
    private String stasiunAsal; // Variabel stasiun keberangkatan
    private String stasiunTujuan; // Variabel stasiun tujuan
    private String waktuBerangkat; // Variabel jam berangkat
    private String waktuTiba; // Variabel jam tiba
    private String durasi; // Variabel lama perjalanan
    private double hargaDasar; // Variabel harga dasar tiket

    public Kereta() {} // Constructor kosong

    // Constructor isi lengkap data kereta
    public Kereta(String namaKereta, String stasiunAsal, String stasiunTujuan, String waktuBerangkat, String waktuTiba, String durasi, double hargaDasar) {
        this.namaKereta = namaKereta; // Set namaKereta
        this.stasiunAsal = stasiunAsal; // Set stasiunAsal
        this.stasiunTujuan = stasiunTujuan; // Set stasiunTujuan
        this.waktuBerangkat = waktuBerangkat; // Set waktuBerangkat
        this.waktuTiba = waktuTiba; // Set waktuTiba
        this.durasi = durasi; // Set durasi
        this.hargaDasar = hargaDasar; // Set hargaDasar
    }

    // Getter dan Setter untuk mengambil dan mengisi nilai atribut
    public String getNamaKereta() { return namaKereta; } // Ambil namaKereta
    public void setNamaKereta(String namaKereta) { this.namaKereta = namaKereta; } // Ubah namaKereta

    public String getStasiunAsal() { return stasiunAsal; } // Ambil stasiunAsal
    public void setStasiunAsal(String stasiunAsal) { this.stasiunAsal = stasiunAsal; } // Ubah stasiunAsal

    public String getStasiunTujuan() { return stasiunTujuan; } // Ambil stasiunTujuan
    public void setStasiunTujuan(String stasiunTujuan) { this.stasiunTujuan = stasiunTujuan; } // Ubah stasiunTujuan

    public String getWaktuBerangkat() { return waktuBerangkat; } // Ambil waktuBerangkat
    public void setWaktuBerangkat(String waktuBerangkat) { this.waktuBerangkat = waktuBerangkat; } // Ubah waktuBerangkat

    public String getWaktuTiba() { return waktuTiba; } // Ambil waktuTiba
    public void setWaktuTiba(String waktuTiba) { this.waktuTiba = waktuTiba; } // Ubah waktuTiba

    public String getDurasi() { return durasi; } // Ambil durasi
    public void setDurasi(String durasi) { this.durasi = durasi; } // Ubah durasi

    public double getHargaDasar() { return hargaDasar; } // Ambil hargaDasar
    public void setHargaDasar(double hargaDasar) { this.hargaDasar = hargaDasar; } // Ubah hargaDasar

    public abstract double hitungHargaTotal(); // Abstract method tanpa isi/body
}