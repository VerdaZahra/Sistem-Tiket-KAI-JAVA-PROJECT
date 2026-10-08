package com.kai.models; // Menandakan lokasi package/folder models

// Class khusus Kereta Luxury turunan dari Kereta
public class KeretaLuxury extends Kereta {
    private String layananSleeper; // Variabel detail tempat tidur sleeper
    private boolean aksesLounge; // Variabel penanda dapat akses lounge VIP atau tidak

    public KeretaLuxury() { super(); } // Constructor kosong

    // Constructor isi data kereta luxury
    public KeretaLuxury(String namaKereta, String stasiunAsal, String stasiunTujuan, String waktuBerangkat, String waktuTiba, String durasi, double hargaDasar, String layananSleeper, boolean aksesLounge) {
        super(namaKereta, stasiunAsal, stasiunTujuan, waktuBerangkat, waktuTiba, durasi, hargaDasar); // Kirim data ke class induk Kereta
        this.layananSleeper = layananSleeper; // Set layananSleeper
        this.aksesLounge = aksesLounge; // Set aksesLounge
    }

    // Getter dan Setter
    public String getLayananSleeper() { return layananSleeper; } // Ambil layananSleeper
    public void setLayananSleeper(String layananSleeper) { this.layananSleeper = layananSleeper; } // Ubah layananSleeper

    public boolean isAksesLounge() { return aksesLounge; } // Ambil status aksesLounge
    public void setAksesLounge(boolean aksesLounge) { this.aksesLounge = aksesLounge; } // Ubah aksesLounge

    @Override
    public double hitungHargaTotal() {
        double biayaSleeper = 100000.0; // Surcharge layanan tempat tidur sleeper
        double biayaLounge = this.aksesLounge ? 50000.0 : 0.0; // Biaya akses lounge jika bernilai true
        // Rumus: Harga Dasar + Biaya Sleeper + Lounge Access
        return getHargaDasar() + biayaSleeper + biayaLounge;
    }
}