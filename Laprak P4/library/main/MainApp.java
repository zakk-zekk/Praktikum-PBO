package library.main;

import java.util.ArrayList;
import java.util.Scanner;

import library.model.Book;
import library.model.Member;
import library.service.LibraryService;
import library.exception.BookNotFoundException;

public class MainApp {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        LibraryService library = new LibraryService();

        library.tambahBuku(
                new Book(
                        "Laut Bercerita",
                        "Leila S. Chudori",
                        2017,
                        "Novel"
                )
        );

        library.tambahBuku(
                new Book(
                        "Janji",
                        "Tere Liye",
                        2021,
                        "Novel"
                )
        );

        library.tambahBuku(
                new Book(
                        "Hujan",
                        "Tere Liye",
                        2016,
                        "Novel"
                )
        );

        library.tambahBuku(
                new Book(
                        "Tentang Kamu",
                        "Tere Liye",
                        2016,
                        "Novel"
                )
        );

        library.tambahMember(
                new Member("M001", "Febrian Zaki")
        );

        library.tambahMember(
                new Member("M002", "Agis Lentera")
        );

        int pilihan = 0;

        while (pilihan != 8) {

            System.out.println("\n==============================");
            System.out.println("   SISTEM PERPUSTAKAAN MINI");
            System.out.println("==============================");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Daftar Buku");
            System.out.println("3. Cari Buku");
            System.out.println("4. Pinjam Buku");
            System.out.println("5. Kembalikan Buku");
            System.out.println("6. Laporan Perpustakaan");
            System.out.println("7. Daftar Anggota");
            System.out.println("8. Keluar");
            System.out.println("==============================");

            try {

                System.out.print("Pilih menu: ");
                pilihan = input.nextInt();
                input.nextLine();

                switch (pilihan) {

                    case 1:

                        System.out.println("\n===== TAMBAH BUKU =====");

                        System.out.print("Judul: ");
                        String judul = input.nextLine();

                        System.out.print("Penulis: ");
                        String penulis = input.nextLine();

                        System.out.print("Tahun Terbit: ");
                        int tahun = input.nextInt();
                        input.nextLine();

                        System.out.print("Kategori: ");
                        String kategori = input.nextLine();

                        Book bukuBaru = new Book(
                                judul,
                                penulis,
                                tahun,
                                kategori
                        );

                        library.tambahBuku(bukuBaru);

                        System.out.println(
                                "Buku berhasil ditambahkan."
                        );

                        break;

                    case 2:

                        library.tampilkanDaftarBuku();

                        break;

                    case 3:

                        System.out.println("\n===== CARI BUKU =====");

                        System.out.print(
                                "Masukkan judul atau kategori: "
                        );

                        String keyword = input.nextLine();

                        ArrayList<Book> hasil =
                                library.cariSemuaBuku(keyword);

                        if (hasil.isEmpty()) {

                            System.out.println(
                                    "Buku tidak ditemukan."
                            );

                        } else {

                            System.out.println(
                                    "Buku yang ditemukan:"
                            );

                            for (Book buku : hasil) {

                                System.out.println();

                                buku.tampilkanInfo();
                            }
                        }

                        break;

                    case 4:

                        System.out.println("\n===== PEMINJAMAN =====");

                        System.out.print("ID Anggota: ");
                        String idPinjam = input.nextLine();

                        System.out.print("Judul Buku: ");
                        String judulPinjam = input.nextLine();

                        library.pinjamBuku(
                                idPinjam,
                                judulPinjam
                        );

                        break;

                    case 5:

                        System.out.println("\n===== PENGEMBALIAN =====");

                        System.out.print("ID Anggota: ");
                        String idKembali = input.nextLine();

                        System.out.print("Judul Buku: ");
                        String judulKembali = input.nextLine();

                        library.kembalikanBuku(
                                idKembali,
                                judulKembali
                        );

                        break;

                    case 6:

                        library.laporanPerpustakaan();

                        break;

                    case 7:

                        library.tampilkanDaftarMember();

                        break;

                    case 8:

                        System.out.println(
                                "Program selesai. Terima kasih."
                        );

                        break;

                    default:

                        System.out.println(
                                "Pilihan menu tidak tersedia."
                        );
                }

            } catch (BookNotFoundException e) {

                System.out.println(
                        "ERROR: " + e.getMessage()
                );

            } catch (Exception e) {

                System.out.println(
                        "ERROR: " + e.getMessage()
                );

                input.nextLine();
            }
        }

        input.close();
    }
}