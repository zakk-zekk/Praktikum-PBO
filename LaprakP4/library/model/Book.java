package library.model;

public class Book {
    private String judul;
    private String penulis;
    private int tahunTerbit;
    private String kategori;
    private boolean statusKetersediaan;
    private int jumlahDipinjam;

    public Book(String judul, String penulis, int tahunTerbit, String kategori) {
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
        this.kategori = kategori;
        this.statusKetersediaan = true;
        this.jumlahDipinjam = 0;
    }

    public String getJudul() {
        return judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public int getTahunTerbit() {
        return tahunTerbit;
    }

    public String getKategori() {
        return kategori;
    }

    public boolean isStatusKetersediaan() {
        return statusKetersediaan;
    }

    public int getJumlahDipinjam() {
        return jumlahDipinjam;
    }

    public void setStatusKetersediaan(boolean statusKetersediaan) {
        this.statusKetersediaan = statusKetersediaan;
    }

    public void tambahJumlahDipinjam() {
        jumlahDipinjam++;
    }

    public void tampilkanInfo() {
        char kodeKategori = Character.toUpperCase(kategori.charAt(0));

        System.out.println("Judul       : " + judul);
        System.out.println("Penulis     : " + penulis);
        System.out.println("Tahun Terbit: " + tahunTerbit);
        System.out.println("Kategori    : " + kategori);
        System.out.println("Kode Kategori: " + kodeKategori);
        System.out.println("Status      : " +
                (statusKetersediaan ? "Tersedia" : "Dipinjam"));
        System.out.println("Total Dipinjam: " + jumlahDipinjam);
    }
}