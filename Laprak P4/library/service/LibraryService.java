package library.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import library.model.Book;
import library.model.Member;
import library.exception.BookNotFoundException;
import library.exception.BookAlreadyBorrowedException;
import library.exception.BorrowLimitExceededException;

public class LibraryService {

    private ArrayList<Book> daftarBuku;
    private ArrayList<Member> daftarMember;
    private HashMap<String, Integer> riwayatKategori;
    private int totalPinjaman;

    public LibraryService() {
        daftarBuku = new ArrayList<>();
        daftarMember = new ArrayList<>();
        riwayatKategori = new HashMap<>();
        totalPinjaman = 0;
    }

    public void tambahBuku(Book buku) {
        daftarBuku.add(buku);
    }

    public void tambahMember(Member member) {
        daftarMember.add(member);
    }

    public ArrayList<Book> getDaftarBuku() {
        return daftarBuku;
    }

    public ArrayList<Member> getDaftarMember() {
        return daftarMember;
    }

    public Book cariBuku(String kataKunci) throws BookNotFoundException {

        String keyword = kataKunci.toLowerCase();

        for (Book buku : daftarBuku) {

            String judul = buku.getJudul().toLowerCase();
            String kategori = buku.getKategori().toLowerCase();

            if (judul.contains(keyword) || kategori.contains(keyword)) {
                return buku;
            }
        }

        throw new BookNotFoundException(
                "Buku dengan kata kunci \"" + kataKunci + "\" tidak ditemukan."
        );
    }

    public ArrayList<Book> cariSemuaBuku(String kataKunci) {

        ArrayList<Book> hasil = new ArrayList<>();

        String keyword = kataKunci.toLowerCase();

        for (Book buku : daftarBuku) {

            String judul = buku.getJudul().toLowerCase();
            String kategori = buku.getKategori().toLowerCase();

            if (judul.contains(keyword) || kategori.contains(keyword)) {
                hasil.add(buku);
            }
        }

        return hasil;
    }

    public Member cariMember(String id) throws Exception {

        for (Member member : daftarMember) {

            if (member.getId().equalsIgnoreCase(id)) {
                return member;
            }
        }

        throw new Exception("Anggota dengan ID " + id + " tidak ditemukan.");
    }

    public void pinjamBuku(String idMember, String judulBuku)
            throws Exception {

        Member member = cariMember(idMember);

        assert member != null :
                "Data anggota tidak boleh kosong.";

        assert member.getId() != null &&
                !member.getId().trim().isEmpty() :
                "ID anggota tidak valid.";

        if (member.getJumlahPinjaman() >= 3) {
            throw new BorrowLimitExceededException(
                    "Anggota sudah meminjam 3 buku. Batas maksimal tercapai."
            );
        }

        Book buku = cariBuku(judulBuku);

        if (!buku.isStatusKetersediaan()) {
            throw new BookAlreadyBorrowedException(
                    "Buku sedang dipinjam."
            );
        }

        buku.setStatusKetersediaan(false);
        buku.tambahJumlahDipinjam();

        member.tambahPinjaman(buku);

        totalPinjaman++;

        String kategori = buku.getKategori();

        if (riwayatKategori.containsKey(kategori)) {
            riwayatKategori.put(
                    kategori,
                    riwayatKategori.get(kategori) + 1
            );
        } else {
            riwayatKategori.put(kategori, 1);
        }

        System.out.println("Buku berhasil dipinjam.");
    }

    public void kembalikanBuku(String idMember, String judulBuku)
            throws Exception {

        Member member = cariMember(idMember);
        Book buku = cariBuku(judulBuku);

        if (!member.getDaftarPinjaman().contains(buku)) {
            throw new Exception(
                    "Anggota tersebut tidak sedang meminjam buku ini."
            );
        }

        member.hapusPinjaman(buku);
        buku.setStatusKetersediaan(true);

        System.out.println("Buku berhasil dikembalikan.");
    }

    public void tampilkanDaftarBuku() {

        if (daftarBuku.isEmpty()) {
            System.out.println("Belum ada buku.");
            return;
        }

        System.out.println("\n===== DAFTAR BUKU =====");

        for (int i = 0; i < daftarBuku.size(); i++) {

            Book buku = daftarBuku.get(i);

            System.out.println("\nBuku ke-" + (i + 1));
            buku.tampilkanInfo();
        }
    }

    public void tampilkanDaftarMember() {

        if (daftarMember.isEmpty()) {
            System.out.println("Belum ada anggota.");
            return;
        }

        System.out.println("\n===== DAFTAR ANGGOTA =====");

        for (Member member : daftarMember) {
            member.tampilkanInfo();
            System.out.println();
        }
    }

    public void laporanPerpustakaan() {

        System.out.println("\n===== LAPORAN PERPUSTAKAAN =====");

        System.out.println("Jumlah total buku       : "
                + daftarBuku.size());

        System.out.println("Jumlah total anggota    : "
                + daftarMember.size());

        System.out.println("Jumlah total peminjaman : "
                + totalPinjaman);

        Book bukuTerpopuler = null;

        for (Book buku : daftarBuku) {

            if (bukuTerpopuler == null ||
                    buku.getJumlahDipinjam()
                    > bukuTerpopuler.getJumlahDipinjam()) {

                bukuTerpopuler = buku;
            }
        }

        if (bukuTerpopuler != null) {

            System.out.println("Buku paling sering dipinjam: "
                    + bukuTerpopuler.getJudul());

            System.out.println("Jumlah dipinjam: "
                    + bukuTerpopuler.getJumlahDipinjam());
        }

        Member memberAktif = null;

        for (Member member : daftarMember) {

            if (memberAktif == null ||
                    member.getJumlahPinjaman()
                    > memberAktif.getJumlahPinjaman()) {

                memberAktif = member;
            }
        }

        if (memberAktif != null) {

            System.out.println("Anggota paling aktif: "
                    + memberAktif.getNama());

            System.out.println("Jumlah pinjaman aktif: "
                    + memberAktif.getJumlahPinjaman());
        }

        String kategoriPopuler = null;
        int jumlahKategoriTerbesar = 0;

        for (Map.Entry<String, Integer> entry :
                riwayatKategori.entrySet()) {

            if (entry.getValue() > jumlahKategoriTerbesar) {

                jumlahKategoriTerbesar = entry.getValue();
                kategoriPopuler = entry.getKey();
            }
        }

        if (kategoriPopuler != null) {

            System.out.println("Kategori paling populer: "
                    + kategoriPopuler);

            System.out.println("Jumlah peminjaman: "
                    + jumlahKategoriTerbesar);
        }

        System.out.println("\n===== JUMLAH BUKU PER KATEGORI =====");

        HashMap<String, Integer> jumlahKategori =
                new HashMap<>();

        for (Book buku : daftarBuku) {

            String kategori = buku.getKategori();

            if (jumlahKategori.containsKey(kategori)) {

                jumlahKategori.put(
                        kategori,
                        jumlahKategori.get(kategori) + 1
                );

            } else {

                jumlahKategori.put(kategori, 1);
            }
        }

        for (Map.Entry<String, Integer> entry :
                jumlahKategori.entrySet()) {

            System.out.println(
                    entry.getKey() + " : "
                    + entry.getValue() + " buku"
            );
        }
    }
}