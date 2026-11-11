package app;

import model.Mahasiswa;
import model.NilaiInvalidException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final String NAMA_FILE = "laporan_nilai.txt";

    public static void main(String[] args) {
        ArrayList<Mahasiswa> daftar = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        try {
            int jumlah = bacaJumlah(scanner);

            // Input data mahasiswa
            int i = 1;
            while (i <= jumlah) {
                System.out.println("\n--- Data Mahasiswa ke-" + i + " ---");
                System.out.print("Nama  : ");
                String nama = scanner.nextLine();
                System.out.print("NIM   : ");
                String nim = scanner.nextLine();
                System.out.print("Nilai : ");
                String inputNilai = scanner.nextLine();

                try {
                    if (nama.trim().isEmpty()) {
                        throw new IllegalArgumentException("Nama tidak boleh kosong!");
                    }
                    if (nim.trim().isEmpty()) {
                        throw new IllegalArgumentException("NIM tidak boleh kosong!");
                    }
                    double nilai = Double.parseDouble(inputNilai.trim());

                    Mahasiswa m = new Mahasiswa(nama, nim, nilai);
                    daftar.add(m);
                    i++; // lanjut hanya jika data valid
                } catch (NilaiInvalidException e) {
                    System.out.println("[ERROR] " + e.getMessage() + " Silakan ulangi.");
                } catch (NumberFormatException e) {
                    System.out.println("[ERROR] Nilai harus berupa angka! Silakan ulangi.");
                } catch (IllegalArgumentException e) {
                    System.out.println("[ERROR] " + e.getMessage() + " Silakan ulangi.");
                } catch (AssertionError e) {
                    System.out.println("[ASSERT] " + e.getMessage() + " Silakan ulangi.");
                }
            }
        } finally {
            scanner.close();
        }

        tampilkanDanSimpan(daftar);
    }

    private static int bacaJumlah(Scanner scanner) {
        while (true) {
            System.out.print("Masukkan jumlah mahasiswa: ");
            try {
                int n = Integer.parseInt(scanner.nextLine().trim());
                if (n > 0) {
                    return n;
                }
                System.out.println("[ERROR] Jumlah harus lebih dari 0.");
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Masukkan bilangan bulat!");
            }
        }
    }

    private static void tampilkanDanSimpan(ArrayList<Mahasiswa> daftar) {
        double total = 0;
        int lulus = 0;
        int tidakLulus = 0;

        // Looping for-each untuk menghitung statistik
        for (Mahasiswa m : daftar) {
            total += m.getNilai();
            if (m.isLulus()) {
                lulus++;
            } else {
                tidakLulus++;
            }
        }
        double rataRata = daftar.size() > 0 ? total / daftar.size() : 0;

        // Bangun isi laporan
        StringBuilder sb = new StringBuilder();
        sb.append("=========== LAPORAN NILAI MAHASISWA ===========\n");
        sb.append(String.format("%-4s %-20s %-12s %-8s %-6s %-12s%n",
                "No", "Nama", "NIM", "Nilai", "Grade", "Status"));
        sb.append("-".repeat(66)).append("\n");

        int no = 1;
        for (Mahasiswa m : daftar) {
            sb.append(String.format("%-4d %-20s %-12s %-8.2f %-6s %-12s%n",
                    no++, m.getNama(), m.getNim(), m.getNilai(), m.getGrade(), m.getStatus()));
        }

        sb.append("-".repeat(66)).append("\n");
        sb.append("Jumlah Mahasiswa : ").append(daftar.size()).append("\n");
        sb.append(String.format("Rata-rata Nilai  : %.2f%n", rataRata));
        sb.append("Jumlah Lulus     : ").append(lulus).append("\n");
        sb.append("Jumlah Tdk Lulus : ").append(tidakLulus).append("\n");
        sb.append("Batas Kelulusan  : ").append(Mahasiswa.FINAL_PASSING_GRADE).append("\n");

        String laporan = sb.toString();
        System.out.println("\n" + laporan);

        // File I/O dengan try-with-resources
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NAMA_FILE))) {
            writer.write(laporan);
            System.out.println("Laporan berhasil disimpan ke " + NAMA_FILE);
        } catch (IOException e) {
            System.out.println("[ERROR] Gagal menulis file: " + e.getMessage());
        }
    }
}
