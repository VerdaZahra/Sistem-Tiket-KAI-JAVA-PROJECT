package com.kai.services; // Menandakan lokasi package/folder services

import com.kai.models.Kereta; // Import abstract model Kereta
import com.kai.models.KeretaEkonomi; // Import model KeretaEkonomi
import com.kai.models.KeretaEksekutif; // Import model KeretaEksekutif
import com.kai.models.KeretaLuxury; // Import model KeretaLuxury
import com.kai.models.Penumpang; // Import class Penumpang
import com.kai.models.Tiket; // Import class Tiket

import java.io.BufferedReader; // Import class pembaca file
import java.io.BufferedWriter; // Import class penulis file
import java.io.File; // Import class pengelola objek file
import java.io.FileReader; // Import class pembaca stream file
import java.io.FileWriter; // Import class penulis stream file
import java.io.IOException; // Import exception error file I/O
import java.util.ArrayList; // Import ArrayList untuk simpan daftar objek sementara

public class FileService {
    private static final String FILE_TIKET = "data_tiket.txt"; // Nama file penyimpanan tiket
    private static final String FILE_PENUMPANG = "data_penumpang.txt"; // Nama file penyimpanan akun penumpang
    private static final String FILE_KERETA = "kereta.txt"; // Nama file penyimpanan data tipe kereta

    // --- MANAJEMEN PENUMPANG ---
    public static void simpanPenumpang(Penumpang p) { // Method menulis akun penumpang baru ke file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PENUMPANG, true))) { // Buka file dengan mode append (tambah)
            writer.write(p.toFileFormat()); // Tulis baris string penumpang
            writer.newLine(); // Buat baris baru di file txt
        } catch (IOException e) { // Tangkap error jika gagal akses file
            System.out.println("[Error FileService] Gagal menyimpan akun penumpang: " + e.getMessage()); // Cetak pesan error
        }
    }

    public static ArrayList<Penumpang> bacaSemuaPenumpang() { // Method membaca daftar seluruh penumpang dari file
        ArrayList<Penumpang> list = new ArrayList<>(); // Buat tempat penampungan list kosong
        File file = new File(FILE_PENUMPANG); // Menunjuk objek file
        if (!file.exists()) return list; // Jika file belum ada, langsung kembalikan list kosong

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PENUMPANG))) { // Buka file untuk dibaca
            String baris; // Variabel penampung teks per baris
            while ((baris = reader.readLine()) != null) { // Looping sampai baris terakhir file
                String[] data = baris.split(";"); // Pecah baris berdasarkan tanda titik koma
                if (data.length >= 8) { // Pastikan kolom minimal ada 8 data
                    Penumpang p = new Penumpang(data[0], data[1], data[2], data[3], data[4], data[5], "TKT-" + data[0], "Reguler", data[6], data[7]); // Buat objek penumpang
                    list.add(p); // Masukkan penumpang ke daftar list
                }
            }
        } catch (IOException e) { // Tangkap error pembacaan
            System.out.println("[Error FileService] Gagal membaca akun penumpang: " + e.getMessage()); // Tampilkan info error
        }
        return list; // Kembalikan list penumpang
    }

    // --- MANAJEMEN TIKET ---
    public static void simpanTiket(Tiket tiket) { // Method menyimpan tiket baru ke file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_TIKET, true))) { // Buka file tiket mode append
            writer.write(tiket.toFileFormat()); // Tulis format data tiket
            writer.newLine(); // Pindah baris baru
            System.out.println("\n[FileService] Data tiket berhasil disimpan ke file " + FILE_TIKET); // Tampilkan info sukses
        } catch (IOException e) { // Tangkap error file
            System.out.println("\n[Error FileService] Gagal menyimpan data tiket: " + e.getMessage()); // Tampilkan pesan gagal
        }
    }

    public static void bacaSemuaTiket() { // Method membaca dan mencetak seluruh isi file tiket
        File file = new File(FILE_TIKET); // Buat penunjuk file
        if (!file.exists()) { // Cek jika file belum dibuat
            System.out.println("\n[FileService] Belum ada transaksi tiket yang tersimpan."); // Info tiket kosong
            return; // Keluar dari method
        }

        System.out.println("\n=== REKAP TIKET DARI FILE DATA ==="); // Header rekap tiket
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_TIKET))) { // Buka file reader
            String baris; // Penampung baris
            int counter = 1; // Angka urutan rekap
            while ((baris = reader.readLine()) != null) { // Baca file baris demi baris
                String[] data = baris.split(";"); // Pecah teks berdasarkan titik koma
                if (data.length >= 9) { // Pastikan format kolom cukup (9 data)
                    System.out.printf("%d. Kode: %s | Penumpang: %s | Kereta: %s | Kursi: %s | Harga: Rp %.2f | Status: %s\n",
                            counter++, data[0], data[6], data[8], data[4], Double.parseDouble(data[5]), data[1]); // Cetak baris rekap
                }
            }
        } catch (IOException e) { // Tangkap error I/O
            System.out.println("\n[Error FileService] Gagal membaca file: " + e.getMessage()); // Tampilkan error
        } catch (NumberFormatException e) { // Tangkap error konversi angka
            System.out.println("\n[Error FileService] Format angka pada file tidak valid: " + e.getMessage()); // Tampilkan error format
        }
    }

    public static boolean isKursiSudahBooked(String namaKereta, String nomorKursi) { // Method cek ketersediaan kursi
        File file = new File(FILE_TIKET); // Penunjuk file tiket
        if (!file.exists()) return false; // Jika belum ada file, berarti kursi masih kosong

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_TIKET))) { // Read file
            String baris; // Penampung teks
            while ((baris = reader.readLine()) != null) { // Loop seluruh transaksi
                String[] data = baris.split(";"); // Pecah teks baris
                if (data.length >= 9) { // Cek kelengkapan kolom
                    String keretaDiFile = data[8].trim(); // Ambil nama kereta dari file
                    String kursiDiFile = data[4].trim(); // Ambil nomor kursi dari file

                    if (keretaDiFile.equalsIgnoreCase(namaKereta.trim()) && 
                        kursiDiFile.equalsIgnoreCase(nomorKursi.trim())) { // Jika kereta dan nomor kursi cocok
                        return true; // Berarti kursi sudah terpesan
                    }
                }
            }
        } catch (IOException e) { // Tangkap error file
            System.out.println("[Error FileService] Gagal membaca file validasi kursi: " + e.getMessage()); // Tampilkan error
        }
        return false; // Kursi belum terpakai
    }

    // --- MANAJEMEN TIPE KERETA ---
    public static ArrayList<Kereta> bacaSemuaKereta() { // Method membaca seluruh tipe kereta dari file
        ArrayList<Kereta> listKereta = new ArrayList<>(); // Buat penampung list kereta
        File file = new File(FILE_KERETA); // Penunjuk file kereta.txt

        if (!file.exists()) return listKereta; // Jika file belum ada, kembalikan list kosong

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_KERETA))) { // Buka file reader
            String baris; // Penampung teks per baris
            while ((baris = reader.readLine()) != null) { // Loop baca file per baris
                if (baris.trim().isEmpty()) continue; // Abaikan jika ada baris kosong
                String[] data = baris.split(";"); // Pecah data berdasarkan tanda titik koma
                if (data.length >= 10) { // Cek kelengkapan atribut kereta
                    String tipe = data[0].toUpperCase(); // Tipe kelas kereta (EKONOMI/EKSEKUTIF/LUXURY)

                    if (tipe.equals("EKONOMI")) {
                        listKereta.add(new KeretaEkonomi(data[1], data[2], data[3], data[4], data[5], data[6], Double.parseDouble(data[7]), data[8], data[9]));
                    } else if (tipe.equals("EKSEKUTIF")) {
                        listKereta.add(new KeretaEksekutif(data[1], data[2], data[3], data[4], data[5], data[6], Double.parseDouble(data[7]), data[8], data[9]));
                    } else if (tipe.equals("LUXURY")) {
                        listKereta.add(new KeretaLuxury(data[1], data[2], data[3], data[4], data[5], data[6], Double.parseDouble(data[7]), data[8], Boolean.parseBoolean(data[9])));
                    }
                }
            }
        } catch (IOException e) { // Tangkap error akses file
            System.out.println("[Error FileService] Gagal membaca data kereta: " + e.getMessage());
        } catch (NumberFormatException e) { // Tangkap error konversi harga
            System.out.println("[Error FileService] Format harga pada file kereta tidak valid: " + e.getMessage());
        }
        return listKereta; // Kembalikan list data kereta
    }

    public static void simpanKereta(Kereta k, String tipe, String attr1, String attr2) { // Method menyimpan tipe kereta baru
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_KERETA, true))) { // Buka file mode append
            String dataLine = String.format("%s;%s;%s;%s;%s;%s;%s;%.1f;%s;%s",
                    tipe.toUpperCase(),
                    k.getNamaKereta(),
                    k.getStasiunAsal(),
                    k.getStasiunTujuan(),
                    k.getWaktuBerangkat(),
                    k.getWaktuTiba(),
                    k.getDurasi(),
                    k.getHargaDasar(),
                    attr1,
                    attr2
            );
            writer.write(dataLine); // Tulis format baris ke file
            writer.newLine(); // Pindah baris baru
            System.out.println("\n[FileService] Data Kereta Baru berhasil disimpan ke file " + FILE_KERETA);
        } catch (IOException e) { // Tangkap error penyimpanan
            System.out.println("\n[Error FileService] Gagal menyimpan data kereta: " + e.getMessage());
        }
    }
}