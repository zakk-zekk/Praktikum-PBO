package library.model;

import java.util.ArrayList;

public class Member {
    private String id;
    private String nama;
    private ArrayList<Book> daftarPinjaman;

    public Member(String id, String nama) {
        this.id = id;
        this.nama = nama;
        this.daftarPinjaman = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public ArrayList<Book> getDaftarPinjaman() {
        return daftarPinjaman;
    }

    public void tambahPinjaman(Book buku) {
        daftarPinjaman.add(buku);
    }

    public void hapusPinjaman(Book buku) {
        daftarPinjaman.remove(buku);
    }

    public int getJumlahPinjaman() {
        return daftarPinjaman.size();
    }

    public void tampilkanInfo() {
        System.out.println("ID Anggota : " + id);
        System.out.println("Nama       : " + nama);
        System.out.println("Jumlah Pinjaman: " + daftarPinjaman.size());

        if (daftarPinjaman.isEmpty()) {
            System.out.println("Belum memiliki pinjaman.");
        } else {
            for (Book buku : daftarPinjaman) {
                System.out.println("- " + buku.getJudul());
            }
        }
    }
}