package com.kai.models; // Menandakan lokasi package/folder models

import com.kai.interfaces.Login; // Import interface Login

// Class Penumpang turunan dari Person dan memakai interface Login
public class Penumpang extends Person implements Login {
    private String email; // Variabel email
    private String tipeID; // Variabel jenis identitas (KTP/SIM)
    private String noID; // Variabel nomor NIK
    private String noTiket; // Variabel nomor tiket
    private String tipePenumpang; // Variabel tipe penumpang (Reguler/dll)
    private String username; // Variabel username login
    private String password; // Variabel password login

    public Penumpang() { // Constructor kosong
        super(); // Memanggil constructor dari class induk Person
    }

    // Constructor isi lengkap data penumpang
    public Penumpang(String kode, String nama, String noHP, String email, String tipeID, String noID, String noTiket, String tipePenumpang, String username, String password) {
        super(kode, nama, noHP); // Mengirim data dasar ke class induk Person
        this.email = email; // Set email
        this.tipeID = tipeID; // Set tipeID
        this.noID = noID; // Set noID
        this.noTiket = noTiket; // Set noTiket
        this.tipePenumpang = tipePenumpang; // Set tipePenumpang
        this.username = username; // Set username
        this.password = password; // Set password
    }

    @Override
    public void signUp() { // Implementasi cetak saat registrasi berhasil
        System.out.println("\n[SISTEM] Registrasi penumpang baru atas nama '" + getNama() + "' (Username: " + username + ") berhasil!"); // Pesan sukses
    }

    @Override
    public boolean signIn(String inputUsername, String inputPassword) { // Implementasi validasi login
        if (this.username.equalsIgnoreCase(inputUsername) && this.password.equals(inputPassword)) { // Cek cocok username & password
            System.out.println("\n[SISTEM] Login Penumpang berhasil! Selamat datang, " + getNama() + "."); // Pesan login sukses
            return true; // Mengembalikan nilai benar
        }
        return false; // Mengembalikan nilai salah jika gagal
    }

    public String toFileFormat() { // Mengubah data penumpang jadi string yang dipisah titik koma
        return getKode() + ";" + getNama() + ";" + getNoHP() + ";" + email + ";" + tipeID + ";" + noID + ";" + username + ";" + password; // Format simpan txt
    }

    // Getter dan Setter untuk tiap atribut
    public String getEmail() { return email; } // Ambil email
    public void setEmail(String email) { this.email = email; } // Ubah email

    public String getTipeID() { return tipeID; } // Ambil tipeID
    public void setTipeID(String tipeID) { this.tipeID = tipeID; } // Ubah tipeID

    public String getNoID() { return noID; } // Ambil noID
    public void setNoID(String noID) { this.noID = noID; } // Ubah noID

    public String getNoTiket() { return noTiket; } // Ambil noTiket
    public void setNoTiket(String noTiket) { this.noTiket = noTiket; } // Ubah noTiket

    public String getTipePenumpang() { return tipePenumpang; } // Ambil tipePenumpang
    public void setTipePenumpang(String tipePenumpang) { this.tipePenumpang = tipePenumpang; } // Ubah tipePenumpang

    public String getUsername() { return username; } // Ambil username
    public void setUsername(String username) { this.username = username; } // Ubah username

    public String getPassword() { return password; } // Ambil password
    public void setPassword(String password) { this.password = password; } // Ubah password
}