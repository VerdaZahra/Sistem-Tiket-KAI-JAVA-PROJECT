package com.kai.interfaces; // Menandakan lokasi package/folder interfaces

// Interface untuk template sistem login dan registrasi
public interface Login {
    void signUp(); // Method tanpa isi untuk proses daftar akun
    boolean signIn(String username, String password); // Method tanpa isi untuk mengecek login
}