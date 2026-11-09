package Pert6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LaporanNilai {

    static final String NAMA_FILE = "laporan_nilai.txt";
    static final double BATAS_LULUS = 70.0;

    // Menentukan status kelulusan
    static String tentukanStatus(double nilai) {
        return nilai >= BATAS_LULUS ? "Lulus" : "Tidak Lulus";
    }

    // Langkah 1: baca input konsol dan tulis ke file
    static void inputDanTulisFile(Scanner sc) throws IOException {
        System.out.println("=== LAPORAN NILAI SISWA ===");
        System.out.println("---------------------------");
        System.out.print("Masukkan jumlah siswa: ");
        int jumlah = Integer.parseInt(sc.nextLine().trim());

        double totalNilai = 0;
        int jumlahLulus = 0;
        int jumlahTidakLulus = 0;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NAMA_FILE))) {
            writer.write("=== LAPORAN NILAI SISWA ===");
            writer.newLine();
            writer.newLine();

            for (int i = 1; i <= jumlah; i++) {
                System.out.println("\nData siswa ke-" + i);
                System.out.print("Nama  : ");
                String nama = sc.nextLine().trim();

                System.out.print("Nilai : ");
                double nilai = Double.parseDouble(sc.nextLine().trim());

                String status = tentukanStatus(nilai);
                totalNilai += nilai;
                if (status.equals("Lulus")) {
                    jumlahLulus++;
                } else {
                    jumlahTidakLulus++;
                }

                writer.write(i + ". Nama   : " + nama);
                writer.newLine();
                writer.write("   Nilai  : " + nilai);
                writer.newLine();
                writer.write("   Status : " + status);
                writer.newLine();
                writer.newLine();
            }

            double rataRata = (jumlah > 0) ? totalNilai / jumlah : 0;

            writer.write("---------------------------");
            writer.newLine();
            writer.write("RINGKASAN");
            writer.newLine();
            writer.write(String.format("Rata-rata nilai kelas : %.2f", rataRata));
            writer.newLine();
            writer.write("Jumlah siswa lulus    : " + jumlahLulus);
            writer.newLine();
            writer.write("Jumlah tidak lulus    : " + jumlahTidakLulus);
            writer.newLine();
        }
        System.out.println("\nData berhasil disimpan ke file.");
    }

    // Langkah 2: baca file baris demi baris dan tampilkan ke konsol
    static void bacaDanTampilkanFile() throws IOException {
        System.out.println("\n=== ISI LAPORAN NILAI SISWA ===\n");
        try (BufferedReader reader = new BufferedReader(new FileReader(NAMA_FILE))) {
            String baris;
            while ((baris = reader.readLine()) != null) {
                System.out.println(baris);
            }
        }
    }

    public static void main(String[] args) {
        // Langkah 3: exception handling + resource management
        try (Scanner sc = new Scanner(System.in)) {
            inputDanTulisFile(sc);
            bacaDanTampilkanFile();
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan I/O: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid, harus berupa angka: " + e.getMessage());
        }
    }
}