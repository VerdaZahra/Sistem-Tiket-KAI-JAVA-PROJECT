package com.kai.models; // Menandakan lokasi package/folder models

// Class khusus Kereta Ekonomi turunan dari Kereta
public class KeretaEkonomi extends Kereta {
    private String jenisKursi; // Variabel model kursi ekonomi
    private String posisiKursi; // Variabel sub-kelas (C/P/Q)

    public KeretaEkonomi() { super(); } // Constructor kosong memanggil parent

    // Constructor untuk mengisi data kereta ekonomi
    public KeretaEkonomi(String namaKereta, String stasiunAsal, String stasiunTujuan, String waktuBerangkat, String waktuTiba, String durasi, double hargaDasar, String jenisKursi, String posisiKursi) {
        super(namaKereta, stasiunAsal, stasiunTujuan, waktuBerangkat, waktuTiba, durasi, hargaDasar); // Mengirim data ke class Kereta
        this.jenisKursi = jenisKursi; // Set jenisKursi
        this.posisiKursi = posisiKursi; // Set posisiKursi
    }

    // Getter dan Setter
    public String getJenisKursi() { return jenisKursi; } // Ambil jenisKursi
    public void setJenisKursi(String jenisKursi) { this.jenisKursi = jenisKursi; } // Ubah jenisKursi

    public String getposisiKursi() { return posisiKursi; } // Ambil posisiKursi
    public void setposisiKursi(String posisiKursi) { this.posisiKursi = posisiKursi; } // Ubah posisiKursi

    @Override
    public double hitungHargaTotal() {
        return getHargaDasar(); // Harga total ekonomi sama dengan harga dasar
    }
}