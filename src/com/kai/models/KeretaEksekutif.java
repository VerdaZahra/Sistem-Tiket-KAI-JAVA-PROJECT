package com.kai.models; // Menandakan lokasi package/folder models

// Class khusus Kereta Eksekutif turunan dari Kereta
public class KeretaEksekutif extends Kereta {
    private String tipeRecliningSeat; // Variabel tipe kemiringan kursi
    private String fasilitasLayanan; // Variabel daftar fasilitas eksekutif

    public KeretaEksekutif() { super(); } // Constructor kosong

    // Constructor isi data kereta eksekutif
    public KeretaEksekutif(String namaKereta, String stasiunAsal, String stasiunTujuan, String waktuBerangkat, String waktuTiba, String durasi, double hargaDasar, String tipeRecliningSeat, String fasilitasLayanan) {
        super(namaKereta, stasiunAsal, stasiunTujuan, waktuBerangkat, waktuTiba, durasi, hargaDasar); // Mengirim data ke parent Kereta
        this.tipeRecliningSeat = tipeRecliningSeat; // Set tipeRecliningSeat
        this.fasilitasLayanan = fasilitasLayanan; // Set fasilitasLayanan
    }

    // Getter dan Setter
    public String getTipeRecliningSeat() { return tipeRecliningSeat; } // Ambil tipeRecliningSeat
    public void setTipeRecliningSeat(String tipeRecliningSeat) { this.tipeRecliningSeat = tipeRecliningSeat; } // Ubah tipeRecliningSeat

    public String getFasilitasLayanan() { return fasilitasLayanan; } // Ambil fasilitasLayanan
    public void setFasilitasLayanan(String fasilitasLayanan) { this.fasilitasLayanan = fasilitasLayanan; } // Ubah fasilitasLayanan

    @Override
    public double hitungHargaTotal() {
        return getHargaDasar() + 50000; // Harga dasar + biaya tambahan eksekutif
    }
}