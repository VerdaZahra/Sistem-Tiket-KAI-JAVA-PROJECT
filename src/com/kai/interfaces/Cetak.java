package com.kai.interfaces; // Menandakan lokasi package/folder interfaces

// Interface untuk template cetak dokumen
public interface Cetak {
    String PERUSAHAAN = "PT Kereta Api Indonesia (Persero)"; // Teks konstanta nama perusahaan

    void cetakTiket(); // Method tanpa isi untuk mencetak tiket
    void cetakStruk(); // Method tanpa isi untuk mencetak struk
}