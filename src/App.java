import com.kai.models.Admin; // Mengimpor kelas Admin dari package models
import com.kai.models.Kereta; // Mengimpor kelas induk Kereta dari package models
import com.kai.models.KeretaEkonomi; // Mengimpor kelas KeretaEkonomi dari package models
import com.kai.models.KeretaEksekutif; // Mengimpor kelas KeretaEksekutif dari package models
import com.kai.models.KeretaLuxury; // Mengimpor kelas KeretaLuxury dari package models
import com.kai.models.Penumpang; // Mengimpor kelas Penumpang dari package models
import com.kai.models.Tiket; // Mengimpor kelas Tiket dari package models
import com.kai.services.FileService; // Mengimpor kelas layanan FileService dari package services

import java.util.ArrayList; // Mengimpor ArrayList untuk pemrosesan koleksi data
import java.util.Scanner; // Mengimpor Scanner untuk membaca input pengguna

public class App { // Deklarasi kelas utama App
    public static void main(String[] args) { // Method utama sebagai titik awal eksekusi program
        Scanner scanner = new Scanner(System.in); // Membuat objek Scanner untuk membaca input dari keyboard

        // Membuat instance awal akun Admin utama sistem
        Admin adminUtama = new Admin("ADM01", "Verda Az", "081234567890", "admin", "admin123"); 

        boolean systemRunning = true; // Variable boolean penanda status berjalannya loop utama sistem

        while (systemRunning) { // Perulangan selama variabel systemRunning bernilai true
            System.out.println("\n=================================================="); 
            System.out.println("   SELAMAT DATANG DI SISTEM TIKET KAI           "); 
            System.out.println("=================================================="); 
            System.out.println("1. Login Penumpang"); 
            System.out.println("2. Register Penumpang Baru"); 
            System.out.println("3. Login Admin"); 
            System.out.println("4. Keluar Sistem"); 
            System.out.print("Pilih menu utama (1-4): "); 

            try { // Blok try untuk menangkap potensi kesalahan input non-angka
                int menuAwal = scanner.nextInt(); // Membaca input angka pilihan menu utama
                scanner.nextLine(); // Membersihkan sisa karakter newline dari buffer scanner

                switch (menuAwal) { // Percabangan switch untuk memilih alur berdasarkan input menu awal
                    case 1: // Kasus jika pengguna memilih angka 1 (Login Penumpang)
                        System.out.println("\n--- LOGIN PENUMPANG ---"); 
                        System.out.print("Username: "); 
                        String usnPen = scanner.nextLine(); 
                        System.out.print("Password: "); 
                        String passPen = scanner.nextLine(); 

                        ArrayList<Penumpang> listPenumpang = FileService.bacaSemuaPenumpang(); // Membaca daftar semua penumpang dari file
                        Penumpang activePenumpang = null; // Menyiapkan variabel penampung penumpang yang berhasil login

                        for (Penumpang p : listPenumpang) { // Iterasi setiap data penumpang dalam 
                            if (p.signIn(usnPen, passPen)) { // Memeriksa kecocokan username dan password via method signIn
                                activePenumpang = p; // Menyimpan objek penumpang yang cocok ke activePenumpang
                                break; // Hentikan perulangan karena akun penumpang sudah ditemukan
                            } 
                        } 

                        if (activePenumpang != null) { // Jika akun penumpang berhasil ditemukan/terverifikasi
                            System.out.println("\n[SISTEM] Login Penumpang berhasil! Selamat datang, " + activePenumpang.getNama() + "."); // Menampilkan pesan sukses login
                            ArrayList<Kereta> daftarKeretaTerbaru = FileService.bacaSemuaKereta(); // Membaca data kereta paling baru dari file
                            menuPenumpang(scanner, activePenumpang, adminUtama, daftarKeretaTerbaru); // Mengarahkan ke tampilan menu khusus penumpang
                        } else { // Jika data username atau password penumpang tidak cocok
                            System.out.println("[GAGAL] Username atau Password Penumpang salah!"); // Menampilkan pesan error kegagalan login
                        } 
                        break; 

                    case 2: // Kasus jika pengguna memilih angka 2 
                        System.out.println("\n--- REGISTRASI PENUMPANG BARU ---"); 
                        System.out.print("Nama Lengkap      : "); 
                        String nama = scanner.nextLine(); 
                        System.out.print("No HP             : "); 
                        String noHP = scanner.nextLine(); 
                        System.out.print("Email             : "); 
                        String email = scanner.nextLine(); 
                        System.out.print("Tipe ID (KTP/SIM) : "); 
                        String tipeID = scanner.nextLine(); 
                        System.out.print("No Identitas (NIK): "); 
                        String noID = scanner.nextLine(); 
                        System.out.print("Buat Username     : "); 
                        String newUsn = scanner.nextLine(); 
                        System.out.print("Buat Password     : "); 
                        String newPass = scanner.nextLine(); 

                        String kodeBaru = "P00" + (listPenumpangCount() + 1); // Membuat format kode unik untuk penumpang baru
                        Penumpang penumpangBaru = new Penumpang(kodeBaru, nama, noHP, email, tipeID, noID, "TKT-" + kodeBaru, "Reguler", newUsn, newPass); // Membentuk objek Penumpang baru

                        penumpangBaru.signUp(); // Memanggil method pemrosesan pendaftaran akun penumpang
                        FileService.simpanPenumpang(penumpangBaru); // Menyimpan objek penumpang baru ke dalam penyimpanan file
                        break; // Keluar dari struktur switch case 2

                    case 3: // Kasus jika pengguna memilih angka 3 Login Admin
                        System.out.println("\n--- LOGIN ADMIN ---"); 
                        System.out.print("Username Admin: "); 
                        String usnAdm = scanner.nextLine(); 
                        System.out.print("Password Admin: "); 
                        String passAdm = scanner.nextLine(); 

                        if (adminUtama.signIn(usnAdm, passAdm)) { // Memeriksa validitas kredensial admin via method signIn
                            menuAdmin(scanner, adminUtama); // Mengarahkan ke menu khusus admin jika otentikasi sukses
                        } else { 
                            System.out.println("[GAGAL] Username atau Password Admin salah!"); 
                        } 
                        break; 

                    case 4: // Kasus jika pengguna memilih angka 4 (Keluar Sistem)
                        systemRunning = false; // Mengubah flag systemRunning menjadi false untuk menghentikan perulangan menu
                        System.out.println("\nTerima kasih telah menggunakan Sistem Tiket KAI!"); 
                        break; // Keluar dari struktur switch case 4

                    default: // Kasus jika angka pilihan yang dimasukkan tidak berada di rentang 1-4
                        System.out.println("\nPilihan menu tidak valid!"); // Menampilkan pesan peringatan opsi tidak ada
                } 
            } catch (Exception e) { // Menangkap jika terjadi kegagalan/exception tipe data pada blok try
                System.out.println("\nError: Input menu harus berupa angka!"); 
                scanner.nextLine(); 
            } 
        } 
        scanner.close(); 
    } 

    private static int listPenumpangCount() { // Method pembantu statis untuk menghitung jumlah total penumpang terdaftar
        return FileService.bacaSemuaPenumpang().size(); // Mengembalikan nilai ukuran/jumlah baris data penumpang dari file
    } 

    // --- MENU KHUSUS PENUMPANG SETELAH LOGIN ---
    private static void menuPenumpang(Scanner scanner, Penumpang penumpang, Admin admin, ArrayList<Kereta> daftarKereta) { // Method menu khusus penumpang
        boolean loggedIn = true; // Variable penanda status alur login menu penumpang
        while (loggedIn) { // Perulangan sub-menu penumpang selama bernilai true
            System.out.println("\n--- MENU PENUMPANG (" + penumpang.getNama() + ") ---"); // Header menu penumpang dengan menampilkan nama akun
            System.out.println("1. Pesan Tiket Kereta"); // Opsi menu pemesanan tiket kereta
            System.out.println("2. Logout"); // Opsi menu logout kembali ke menu utama
            System.out.print("Pilih opsi (1-2): "); // Meminta input nomor opsi menu penumpang

            try { // Blok try untuk penanganan error input menu penumpang
                int p = scanner.nextInt(); // Membaca nomor pilihan menu dari penumpang
                scanner.nextLine(); // Membersihkan karakter newline dari buffer scanner

                if (p == 1) { // Jika penumpang memilih menu pesan tiket kereta
                    if (daftarKereta.isEmpty()) { // Memeriksa ketersediaan data kereta dalam sistem
                        System.out.println("\n[INFO] Belum ada jadwal kereta yang tersedia."); // Peringatan data kereta kosong
                        continue; // Kembali ke awal loop perulangan sub-menu penumpang
                    } 

                    System.out.println("\n--- PILIH KERETA DAN KELAS ---"); // Tampilan header daftarkan kereta
                    for (int i = 0; i < daftarKereta.size(); i++) { // Iterasi pengulangan mencetak setiap data daftar kereta
                        Kereta k = daftarKereta.get(i); // Mengambil objek Kereta indeks ke-i
                        String tipeKelas = k.getClass().getSimpleName().replace("Kereta", ""); // Ekstraksi nama kelas turunan kereta
                        
                        System.out.printf("%d. %s [%s] (%s -> %s) - Rp %.2f\n", 
                                (i + 1), k.getNamaKereta(), tipeKelas, k.getStasiunAsal(), k.getStasiunTujuan(), k.hitungHargaTotal()); // Mencetak detail jadwal dan harga total hasil polimorfisme
                    } 
                    
                    System.out.print("Pilih Kereta (1-" + daftarKereta.size() + "): "); // Meminta pilihan indeks nomor kereta
                    int pilihKereta = scanner.nextInt(); // Membaca nomor kereta pilihan penumpang
                    scanner.nextLine(); // Membersihkan buffer masukan dari scanner

                    if (pilihKereta < 1 || pilihKereta > daftarKereta.size()) { // Validasi rentang nomor pilihan kereta
                        System.out.println("\nPilihan kereta tidak valid!"); // Peringatan indeks pilihan out of range
                        continue; // Kembali ke awal perulangan sub-menu penumpang
                    } 

                    Kereta keretaPilihan = daftarKereta.get(pilihKereta - 1); // Mengambil instance Kereta berdasarkan indeks terpilih

                    System.out.print("Masukkan Nomor Kursi (misal: 12A): "); // Meminta masukan teks nomor kursi penumpang
                    String kursi = scanner.nextLine().trim().toUpperCase(); // Membaca nomor kursi dan dikonversi ke format kapital

                    if (FileService.isKursiSudahBooked(keretaPilihan.getNamaKereta(), kursi)) { // Pengecekan ketersediaan kursi via FileService
                        System.out.println("\n[PROSES GAGAL] Kursi " + kursi + " untuk " + keretaPilihan.getNamaKereta() + " SUDAH DIPESAN!"); // Peringatan jika kursi sudah terisi
                        continue; // Kembali ke awal perulangan menu penumpang
                    } 

                    Tiket tiketBaru = new Tiket(
                            "KAI-" + (System.currentTimeMillis() % 10000), // Generate kode tiket unik berdasarkan stempel waktu sistem
                            "Lunas", // Inisialisasi status pemesanan tiket
                            "KAI Access", // Inisialisasi channel asal pemesanan
                            "2026-09-29", // Inisialisasi tanggal transaksi pembelian tiket
                            kursi, // Memasukkan variabel string nomor kursi
                            keretaPilihan.hitungHargaTotal(), // Mengisi nilai atribut harga jual menggunakan penerapan polimorfisme hitungHargaTotal
                            penumpang, // Memasukkan instance objek Penumpang pemesan tiket
                            admin, // Memasukkan instance objek Admin pengelola sistem
                            keretaPilihan // Memasukkan instance objek Kereta pilihan
                    ); 

                    System.out.println("\n[BERHASIL] Pemesanan Sukses!"); // Menampilkan konfirmasi keberhasilan pemesanan
                    tiketBaru.cetakTiket(); // Memanggil method pencetakan tampilan rincian tiket
                    tiketBaru.cetakStruk(); // Memanggil method pencetakan tampilan bukti struk pembayaran

                    FileService.simpanTiket(tiketBaru); // Menyimpan objek data tiket baru ke file teks transaksi
                } else if (p == 2) { // Jika penumpang memilih menu logout
                    loggedIn = false; // Mengubah status loggedIn menjadi false untuk keluar loop menu penumpang
                    System.out.println("\nBerhasil Logout dari akun Penumpang."); // Menampilkan pesan konfirmasi berhasil logout
                } else { // Jika nomor menu pilihan penumpang tidak bernilai 1 maupun 2
                    System.out.println("Pilihan tidak valid!"); // Menampilkan pesan peringatan opsi salah
                } // Akhir struktur percabangan menu penumpang
            } catch (Exception e) { // Menangkap exception jika pengguna memasukkan karakter non-angka
                System.out.println("\nError: Input harus berupa angka!"); // Menampilkan peringatan format masukan input
                scanner.nextLine(); // Membersihkan masukan input error dari buffer scanner
            } 
        } 
    } 

    // --- MENU KHUSUS ADMIN SETELAH LOGIN ---
    private static void menuAdmin(Scanner scanner, Admin admin) { // Method penanganan menu khusus admin
        boolean loggedIn = true; 
        while (loggedIn) { 
            System.out.println("\n--- MENU ADMIN ---"); // Header tampilan menu admin
            System.out.println("1. Lihat Seluruh Transaksi Tiket File"); // Opsi menu untuk melihat seluruh log transaksi tiket dari file
            System.out.println("2. Tambah Data Tipe Kereta Baru"); // Opsi menu untuk memasukkan data kereta baru
            System.out.println("3. Logout"); // Opsi menu logout dari akun admin
            System.out.print("Pilih opsi (1-3): "); // Meminta masukan nomor pilihan dari admin

            try { 
                int p = scanner.nextInt(); // Membaca nomor pilihan menu admin
                scanner.nextLine(); // Membersihkan buffer newline pada scanner

                if (p == 1) { // Jika admin memilih opsi 1
                    admin.lihatSemuaTiket(); // Memanggil method pada class Admin untuk membaca seluruh data tiket di file
                } else if (p == 2) { // Jika admin memilih opsi 2 (Tambah Data Kereta Baru)
                    System.out.println("\n--- INPUT DATA TIPE KERETA BARU ---"); 
                    System.out.println("Pilih Tipe Kelas Kereta:"); 
                    System.out.println("1. Ekonomi"); 
                    System.out.println("2. Eksekutif"); 
                    System.out.println("3. Luxury"); 
                    System.out.print("Pilihan (1-3): "); 
                    int tipePilihan = scanner.nextInt(); 
                    scanner.nextLine(); 

                    System.out.print("Nama Kereta     : "); 
                    String nama = scanner.nextLine(); 
                    System.out.print("Stasiun Asal    : "); 
                    String asal = scanner.nextLine(); 
                    System.out.print("Stasiun Tujuan  : "); 
                    String tujuan = scanner.nextLine(); 
                    System.out.print("Waktu Berangkat : "); 
                    String berangkat = scanner.nextLine(); 
                    System.out.print("Waktu Tiba      : "); 
                    String tiba = scanner.nextLine(); 
                    System.out.print("Durasi          : "); 
                    String durasi = scanner.nextLine(); 
                    System.out.print("Harga Dasar     : "); 
                    double harga = scanner.nextDouble(); 
                    scanner.nextLine(); 

                    if (tipePilihan == 1) { // Percabangan jika tipe kereta yang dipilih adalah Ekonomi
                        System.out.print("Jenis Kursi (misal: Split Seat 2-2) : "); 
                        String jenis = scanner.nextLine(); 
                        System.out.print("Posisi Kursi (misal: EKO-CA)        : "); 
                        String posisi = scanner.nextLine(); 

                        KeretaEkonomi ke = new KeretaEkonomi(nama, asal, tujuan, berangkat, tiba, durasi, harga, jenis, posisi); // Instansiasi objek KeretaEkonomi
                        FileService.simpanKereta(ke, "EKONOMI", jenis, posisi); // Menyimpan objek data KeretaEkonomi baru ke file

                    } else if (tipePilihan == 2) { // Percabangan jika tipe kereta yang dipilih adalah Eksekutif
                        System.out.print("Tipe Reclining Seat (misal: Reclining 45-deg) : "); // Meminta input atribut tipe reclining seat
                        String recl = scanner.nextLine(); // Membaca teks tipe reclining seat
                        System.out.print("Fasilitas Layanan (misal: Wi-Fi & Snack)      : "); // Meminta input atribut fasilitas layanan
                        String fas = scanner.nextLine(); // Membaca teks fasilitas layanan

                        KeretaEksekutif keks = new KeretaEksekutif(nama, asal, tujuan, berangkat, tiba, durasi, harga, recl, fas); // Instansiasi objek KeretaEksekutif
                        FileService.simpanKereta(keks, "EKSEKUTIF", recl, fas); // Menyimpan objek data KeretaEksekutif baru ke file

                    } else if (tipePilihan == 3) { // Percabangan jika tipe kereta yang dipilih adalah Luxury
                        System.out.print("Layanan Sleeper (misal: Sleeper Bed 180-deg) : "); // Meminta input atribut layanan sleeper
                        String sleep = scanner.nextLine(); 
                        System.out.print("Akses Lounge VIP (true/false)                : "); // Meminta input status akses lounge VIP
                        boolean lounge = scanner.nextBoolean(); // Membaca input berjenis tipe boolean
                        scanner.nextLine(); 

                        KeretaLuxury kl = new KeretaLuxury(nama, asal, tujuan, berangkat, tiba, durasi, harga, sleep, lounge); // Instansiasi objek KeretaLuxury
                        FileService.simpanKereta(kl, "LUXURY", sleep, String.valueOf(lounge)); // Menyimpan objek KeretaLuxury ke file dengan nilai boolean lounge dikonversi ke String
                    } else { // Jika opsi angka tipe pilihan kereta bukan 1, 2, atau 3
                        System.out.println("[GAGAL] Tipe kelas kereta tidak valid!"); 
                    } 

                } else if (p == 3) { // Jika admin memilih opsi 3 (Logout)
                    loggedIn = false; // Mengubah flag loggedIn admin menjadi false untuk keluar dari menu admin
                    System.out.println("\nBerhasil Logout dari akun Admin."); // Menampilkan pesan sukses logout admin
                } else { 
                    System.out.println("Pilihan tidak valid!"); 
                } 
            } catch (Exception e) { // Menangkap exception jika input pada menu admin terjadi mismatch tipe data
                System.out.println("\nError: Input tidak valid!"); 
                scanner.nextLine(); 
            } 
        } 
    } 
} 