package com.kai.models; // Menandakan lokasi package/folder models

import com.kai.interfaces.Login; // Import interface Login
import com.kai.services.FileService; // Import FileService untuk membaca riwayat tiket dari file

public class Admin extends Person implements Login {
    private String username; // Variabel username admin
    private String password; // Variabel password admin

    public Admin() { // Constructor kosong
        super(); // Memanggil constructor class induk Person
    }

    // Constructor isi data admin
    public Admin(String kode, String nama, String noHP, String username, String password) {
        super(kode, nama, noHP); // Mengirim data dasar ke class induk Person
        this.username = username; // Set username
        this.password = password; // Set password
    }

    @Override
    public void signUp() { // Implementasi daftar admin
        System.out.println("\n[SISTEM] Admin " + getNama() + " didaftarkan ke sistem."); // Pesan info
    }

    @Override
    public boolean signIn(String inputUsername, String inputPassword) { // Implementasi cek login admin
        if (this.username.equalsIgnoreCase(inputUsername) && this.password.equals(inputPassword)) { // Cek kesesuaian login
            System.out.println("\n[SISTEM] Login Admin berhasil! Selamat datang, Petugas " + getNama() + "."); // Pesan sukses
            return true; // Login valid
        }
        return false; // Login invalid
    }

    // Method sesuai Class Diagram untuk menampilkan seluruh rekap transaksi tiket
    public void lihatSemuaTiket() {
        FileService.bacaSemuaTiket(); // Memanggil fungsi pembacaan file tiket dari FileService
    }

    // Getter dan Setter username & password
    public String getUsername() { return username; } // Ambil username
    public void setUsername(String username) { this.username = username; } // Ubah username

    public String getPassword() { return password; } // Ambil password
    public void setPassword(String password) { this.password = password; } // Ubah password
}