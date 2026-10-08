package com.kai.models; // Menandakan lokasi package/folder models

// Class abstrak utama yang menampung data dasar manusia
public abstract class Person {
    private String kode; // Variabel unik penanda ID (Primary Key)
    private String nama; // Variabel menyimpan nama
    private String noHP; // Variabel menyimpan nomor telepon

    public Person() {} // Constructor kosong

    // Constructor untuk isi data langsung saat objek dibuat
    public Person(String kode, String nama, String noHP) {
        this.kode = kode; // Set data kode
        this.nama = nama; // Set data nama
        this.noHP = noHP; // Set data noHP
    }

    // Getter dan Setter untuk mengambil dan mengubah data
    public String getKode() { return kode; } // Ambil kode
    public void setKode(String kode) { this.kode = kode; } // Ubah kode

    public String getNama() { return nama; } // Ambil nama
    public void setNama(String nama) { this.nama = nama; } // Ubah nama

    public String getNoHP() { return noHP; } // Ambil noHP
    public void setNoHP(String noHP) { this.noHP = noHP; } // Ubah noHP
}