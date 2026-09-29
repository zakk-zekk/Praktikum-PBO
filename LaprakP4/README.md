# Laporan Praktikum PBO — LaprakP4

## Sistem Perpustakaan Mini Berbasis Java


---

## Daftar Isi

1. [Identitas Mahasiswa](#1-identitas-mahasiswa)
2. [Latar Belakang dan Deskripsi Sistem](#2-latar-belakang-dan-deskripsi-sistem)
3. [Tujuan Pembelajaran dan Ruang Lingkup](#3-tujuan-pembelajaran-dan-ruang-lingkup)
4. [Arsitektur Proyek dan Struktur Package](#4-arsitektur-proyek-dan-struktur-package)
5. [Pemetaan Konsep OOP dan Fitur Bahasa Java](#5-pemetaan-konsep-oop-dan-fitur-bahasa-java)
6. [Bedah Kode Sumber dan Penjelasan Sintaks Detail](#6-bedah-kode-sumber-dan-penjelasan-sintaks-detail)
   - [6.1 Package `library.model`](#61-package-librarymodel)
   - [6.2 Package `library.exception`](#62-package-libraryexception)
   - [6.3 Package `library.service`](#63-package-libraryservice)
   - [6.4 Package `library.main`](#64-package-librarymain)
7. [Alur Bisnis dan Mekanisme Validasi](#7-alur-bisnis-dan-mekanisme-validasi-flow-logic)
8. [Panduan Kompilasi dan Eksekusi Program](#8-panduan-kompilasi-dan-eksekusi-program)
9. [Analisis Hasil Pengujian dan Output Sistem](#9-analisis-hasil-pengujian-dan-output-sistem)
10. [Kesimpulan](#10-kesimpulan)

---

## 1. Identitas Mahasiswa

| Keterangan | Isi |
|---|---|
| Nama | **Febrian Zaki Hidayatulloh** |
| NIM | L0325045 |
| Kelas | B Informatika PSDKU |
| Mata Kuliah | Pemrograman Berorientasi Objek (PBO) |
| Praktikum | Praktikum PBO — Laprak P4 |
| Topik | Sistem Perpustakaan Mini menggunakan Java |
| Repository | `zakk-zekk/Praktikum-PBO` |
| Folder | `LaprakP4` |
| Bahasa | Java |

---

## 2. Latar Belakang dan Deskripsi Sistem

### 2.1 Latar Belakang

Perpustakaan membutuhkan pengelolaan data buku, anggota, serta aktivitas peminjaman dan pengembalian secara teratur. Jika data dicatat secara manual, beberapa masalah dapat muncul, antara lain:

1. Sulit mengetahui apakah sebuah buku sedang tersedia atau sedang dipinjam.
2. Riwayat peminjaman mudah terlewat atau tercatat ganda.
3. Pencarian buku berdasarkan judul atau kategori menjadi lambat.
4. Batas maksimal peminjaman anggota tidak selalu terpantau.
5. Informasi buku paling populer dan anggota paling aktif sulit dihitung secara konsisten.
6. Kesalahan input dan kondisi bisnis tidak valid tidak ditangani secara terstruktur.

Folder `LaprakP4` menyelesaikan masalah tersebut melalui aplikasi konsol sederhana berbasis Java. Aplikasi ini memodelkan buku dan anggota sebagai objek, memusatkan operasi perpustakaan di dalam service, menggunakan collection untuk menyimpan data, dan menggunakan custom exception untuk melaporkan kondisi yang tidak valid.

### 2.2 Deskripsi Sistem

Sistem yang dibuat adalah **Sistem Perpustakaan Mini**. Program dijalankan melalui terminal dan menyediakan menu interaktif berikut:

1. Tambah buku.
2. Menampilkan daftar buku.
3. Mencari buku berdasarkan judul atau kategori.
4. Meminjam buku.
5. Mengembalikan buku.
6. Menampilkan laporan perpustakaan.
7. Menampilkan daftar anggota.
8. Keluar dari program.

Pada saat pertama kali dijalankan, program membuat objek `LibraryService`, kemudian menambahkan empat buku awal:

- `Laut Bercerita` — Leila S. Chudori — 2017 — Novel.
- `Janji` — Tere Liye — 2021 — Novel.
- `Hujan` — Tere Liye — 2016 — Novel.
- `Tentang Kamu` — Tere Liye — 2016 — Novel.

Program juga membuat dua anggota awal:

- `M001` — Febrian Zaki.
- `M002` — Agis Lentera.

Seluruh data disimpan di memori selama program berjalan. Belum terdapat database, file persistence, GUI, autentikasi, atau API. Oleh karena itu, data kembali ke kondisi awal setiap kali program dijalankan ulang.

### 2.3 Karakteristik Sistem

- **Jenis aplikasi:** aplikasi console/CLI.
- **Paradigma:** Object-Oriented Programming.
- **Penyimpanan:** collection Java di memory.
- **Input:** `Scanner` dari keyboard.
- **Output:** `System.out.println` dan `System.out.print`.
- **Pencarian:** pencocokan substring tanpa membedakan huruf besar-kecil.
- **Batas peminjaman:** maksimal tiga buku aktif per anggota.
- **Status buku:** tersedia atau dipinjam.
- **Laporan:** jumlah buku, anggota, total transaksi, buku terpopuler, anggota aktif, kategori populer, dan jumlah buku per kategori.

---

## 3. Tujuan Pembelajaran dan Ruang Lingkup

### 3.1 Tujuan Pembelajaran

Praktikum ini bertujuan untuk:

1. Memahami pembuatan class dan object dalam Java.
2. Menerapkan enkapsulasi melalui atribut `private` dan method akses.
3. Memisahkan tanggung jawab program menggunakan package.
4. Menggunakan constructor untuk menginisialisasi object.
5. Mengelola data menggunakan `ArrayList`, `HashMap`, dan `Map`.
6. Menerapkan pencarian dan pengolahan data collection.
7. Memahami relasi antar-object, khususnya relasi anggota dengan daftar buku pinjaman.
8. Membuat dan menggunakan custom checked exception.
9. Menerapkan `try-catch` untuk menangani kesalahan saat runtime.
10. Menggunakan perulangan, percabangan, `switch-case`, assertion, dan operator ternary.
11. Merancang alur bisnis sederhana yang memiliki validasi dan aturan transaksi.
12. Membuat laporan agregat berdasarkan data transaksi.

### 3.2 Ruang Lingkup

Fitur yang tercakup:

- Menambah buku ke koleksi.
- Menambah anggota ke koleksi.
- Melihat seluruh buku.
- Mencari buku berdasarkan judul atau kategori.
- Mencari anggota berdasarkan ID.
- Meminjam buku dengan batas maksimal tiga buku aktif.
- Menolak peminjaman buku yang sedang dipinjam.
- Mengembalikan buku yang benar-benar tercatat sebagai pinjaman anggota.
- Menghitung statistik penggunaan perpustakaan.
- Menampilkan data anggota beserta buku yang sedang dipinjam.

Batasan implementasi:

- Tidak ada penyimpanan permanen.
- Tidak ada penghapusan atau pengubahan data buku dan anggota melalui menu.
- Tidak ada validasi duplikasi ID anggota atau judul buku.
- Tidak ada tanggal pinjam, tanggal kembali, denda, atau jatuh tempo.
- `MemberNotFoundException.java` tidak ditemukan di folder aktual; pencarian anggota masih menggunakan `throws Exception` umum.
- Kesalahan input angka ditangani oleh `Exception` umum sehingga pesan error masih bergantung pada Java.
- `totalPinjaman` menghitung seluruh transaksi peminjaman yang pernah dilakukan selama satu sesi, bukan jumlah buku yang sedang dipinjam.

---

## 4. Arsitektur Proyek dan Struktur Package

### 4.1 Struktur Folder Aktual

```text
LaprakP4/
└── library/
    ├── exception/
    │   ├── BookAlreadyBorrowedException.java
    │   ├── BookNotFoundException.java
    │   └── BorrowLimitExceededException.java
    ├── main/
    │   └── MainApp.java
    ├── model/
    │   ├── Book.java
    │   └── Member.java
    └── service/
        └── LibraryService.java
```

### 4.2 Peran Setiap Layer

#### `library.model`

Berisi representasi data dan perilaku dasar domain perpustakaan. `Book` merepresentasikan buku, sedangkan `Member` merepresentasikan anggota beserta daftar pinjamannya.

#### `library.exception`

Berisi exception khusus untuk kondisi bisnis tertentu. Exception dibuat sebagai class terpisah agar error dapat diberi nama dan pesan yang jelas.

#### `library.service`

Berisi aturan bisnis utama. `LibraryService` menjadi pusat pengelolaan buku, anggota, transaksi, pencarian, validasi, dan laporan.

#### `library.main`

Berisi titik masuk aplikasi. `MainApp` membuat data awal, menampilkan menu, membaca input, memanggil service, dan menampilkan hasil atau pesan error.

### 4.3 Arsitektur Berlapis Sederhana

Alur dependensi program dapat digambarkan sebagai berikut:

```text
Pengguna
   │ input keyboard
   ▼
MainApp (library.main)
   │ memanggil operasi
   ▼
LibraryService (library.service)
   ├── mengelola Book dan Member
   ├── menggunakan exception bisnis
   └── membuat laporan
       │
       ▼
Book / Member (library.model)
```

`MainApp` tidak mengubah collection secara langsung. Ia menyerahkan pekerjaan kepada `LibraryService`. Pemisahan ini membuat kode lebih mudah dibaca dan memungkinkan logika layanan dikembangkan tanpa menulis ulang seluruh menu.

---

## 5. Pemetaan Konsep OOP dan Fitur Bahasa Java

### 5.1 Class dan Object

`Book`, `Member`, `LibraryService`, dan exception merupakan class. Object dibuat menggunakan keyword `new`, misalnya:

```java
Book buku = new Book("Laut Bercerita", "Leila S. Chudori", 2017, "Novel");
Member member = new Member("M001", "Febrian Zaki");
LibraryService library = new LibraryService();
```

Setiap object memiliki state sendiri. Dua object `Book` dapat memiliki judul dan status berbeda, sedangkan setiap `Member` memiliki daftar pinjaman masing-masing.

### 5.2 Enkapsulasi

Atribut pada `Book` dan `Member` dideklarasikan `private`. Akses diberikan melalui method seperti `getJudul()`, `getNama()`, `isStatusKetersediaan()`, dan `getJumlahPinjaman()`.

Enkapsulasi mencegah class lain mengubah state secara sembarangan. Sebagai contoh, status buku diubah melalui `setStatusKetersediaan`, bukan dengan mengakses atribut langsung dari `MainApp`.

### 5.3 Abstraksi

`LibraryService` menyembunyikan detail pencarian dan transaksi. `MainApp` cukup memanggil:

```java
library.pinjamBuku(idPinjam, judulPinjam);
```

Caller tidak perlu mengetahui bagaimana anggota dicari, bagaimana status buku diubah, bagaimana jumlah pinjaman diperbarui, atau bagaimana kategori dicatat.

### 5.4 Pewarisan dan Polimorfisme

Pewarisan terlihat pada custom exception:

```java
public class BookNotFoundException extends Exception
```

Ketiga exception bisnis mewarisi `Exception`, sehingga dapat digunakan sebagai checked exception dan ditangani oleh `try-catch`. Polimorfisme juga terlihat ketika object exception diperlakukan sebagai tipe `Exception` pada penanganan error umum.

Namun, model `Book` dan `Member` tidak menggunakan inheritance. Tidak ada superclass domain atau interface pada implementasi saat ini.

### 5.5 Composition/Aggregation

`Member` mempunyai `ArrayList<Book>` bernama `daftarPinjaman`. Ini menunjukkan hubungan antara anggota dan buku yang sedang dipinjam. `LibraryService` juga mengagregasi daftar `Book` dan `Member`.

### 5.6 Collection Framework

- `ArrayList<Book>`: daftar buku yang terurut berdasarkan urutan penambahan.
- `ArrayList<Member>`: daftar anggota.
- `HashMap<String, Integer>`: riwayat jumlah peminjaman berdasarkan kategori.
- `Map.Entry<String, Integer>`: cara iterasi pasangan key-value pada map.

### 5.7 Fitur Java yang Digunakan

- `package` untuk namespace.
- `import` untuk menggunakan class dari package lain.
- Constructor.
- `private`, `public`, `boolean`, `int`, dan `String`.
- `for` klasik dan enhanced `for`.
- `if-else` dan `switch-case`.
- `try-catch`.
- `throws` dan `throw`.
- `assert` untuk prasyarat internal.
- `String.toLowerCase()` dan `String.contains()` untuk pencarian.
- `equalsIgnoreCase()` untuk pencarian ID anggota.
- Operator ternary untuk menampilkan status.
- `Character.toUpperCase()` untuk kode kategori.
- `Scanner` untuk input console.

---

## 6. Bedah Kode Sumber dan Penjelasan Sintaks Detail

### 6.1 Package `library.model`

#### 6.1.1 `Book.java`

Class `Book` memiliki atribut:

```java
private String judul;
private String penulis;
private int tahunTerbit;
private String kategori;
private boolean statusKetersediaan;
private int jumlahDipinjam;
```

Empat atribut pertama menyimpan identitas buku. `statusKetersediaan` menunjukkan apakah buku dapat dipinjam. `jumlahDipinjam` menyimpan jumlah historis peminjaman buku.

Constructor menerima data inti buku:

```java
public Book(String judul, String penulis, int tahunTerbit, String kategori) {
    this.judul = judul;
    this.penulis = penulis;
    this.tahunTerbit = tahunTerbit;
    this.kategori = kategori;
    this.statusKetersediaan = true;
    this.jumlahDipinjam = 0;
}
```

Keyword `this` membedakan atribut object dari parameter constructor. Setiap buku baru otomatis tersedia dan belum pernah dipinjam.

Getter menyediakan akses baca. Method boolean diberi nama `isStatusKetersediaan()`, mengikuti pola penamaan umum Java untuk nilai boolean. Method `setStatusKetersediaan` dipakai service saat peminjaman atau pengembalian.

Method `tambahJumlahDipinjam()` menggunakan operator increment `++`. Nilai ini tidak dikurangi ketika buku dikembalikan karena yang dicatat adalah total historis peminjaman.

Method `tampilkanInfo()` menampilkan seluruh detail buku. Baris:

```java
char kodeKategori = Character.toUpperCase(kategori.charAt(0));
```

mengambil karakter pertama kategori lalu mengubahnya menjadi huruf besar. Untuk kategori `Novel`, hasilnya adalah `N`. Operator ternary:

```java
statusKetersediaan ? "Tersedia" : "Dipinjam"
```

mengubah nilai boolean menjadi teks yang mudah dibaca.

**Catatan validasi:** `kategori.charAt(0)` akan error jika kategori berupa string kosong. Program belum memiliki validasi input kosong sebelum method tersebut dipanggil.

#### 6.1.2 `Member.java`

Class `Member` memiliki:

```java
private String id;
private String nama;
private ArrayList<Book> daftarPinjaman;
```

Constructor menginisialisasi daftar pinjaman dengan:

```java
this.daftarPinjaman = new ArrayList<>();
```

Diamond operator `<>` memungkinkan compiler menyimpulkan tipe `Book` dari deklarasi variabel.

Method `tambahPinjaman` menambahkan object `Book` ke daftar. Method `hapusPinjaman` menghapus object tersebut. `ArrayList.remove(Object)` menggunakan kesamaan object untuk menentukan elemen yang dihapus. Karena object buku yang sama disimpan ke daftar anggota, penghapusan dapat dilakukan ketika pengembalian.

`getJumlahPinjaman()` mengembalikan `daftarPinjaman.size()`. Nilai inilah yang digunakan untuk membatasi peminjaman maksimal tiga buku.

`tampilkanInfo()` menggunakan `isEmpty()` untuk memilih apakah harus menampilkan pesan `Belum memiliki pinjaman.` atau melakukan iterasi pada daftar buku.

**Catatan desain:** method `getDaftarPinjaman()` mengembalikan `ArrayList` internal secara langsung. Secara desain yang lebih aman, method tersebut dapat mengembalikan view read-only atau salinan agar collection tidak dimodifikasi sembarang dari luar class.

---

### 6.2 Package `library.exception`

Ketiga file exception memiliki pola serupa:

```java
public class BookNotFoundException extends Exception {
    public BookNotFoundException(String pesan) {
        super(pesan);
    }
}
```

`extends Exception` membuat exception tersebut menjadi checked exception. Constructor menerima pesan dalam bahasa Indonesia dan meneruskannya ke constructor superclass dengan `super(pesan)`.

#### 6.2.1 `BookNotFoundException`

Digunakan oleh `cariBuku` jika tidak ada buku yang judul atau kategorinya cocok dengan kata kunci.

#### 6.2.2 `BookAlreadyBorrowedException`

Digunakan saat buku yang diminta memiliki `statusKetersediaan == false`.

#### 6.2.3 `BorrowLimitExceededException`

Digunakan ketika jumlah pinjaman anggota sudah mencapai tiga buku.

#### 6.2.4 Catatan File Anggota

Berdasarkan isi folder aktual, tidak terdapat file `MemberNotFoundException.java`. Method `cariMember` masih menggunakan:

```java
public Member cariMember(String id) throws Exception
```

dan melempar `new Exception(...)`. Jika ingin konsisten, dapat dibuat exception khusus `MemberNotFoundException extends Exception`, lalu digunakan pada method tersebut.

---

### 6.3 Package `library.service`

`LibraryService` adalah inti sistem. Field utamanya:

```java
private ArrayList<Book> daftarBuku;
private ArrayList<Member> daftarMember;
private HashMap<String, Integer> riwayatKategori;
private int totalPinjaman;
```

Constructor membuat ketiga collection dalam keadaan kosong dan mengatur `totalPinjaman` ke nol.

#### 6.3.1 Menambah Data

` tambahBuku(Book buku)` dan `tambahMember(Member member)` menggunakan `add()` untuk memasukkan object ke collection. Saat ini belum ada pengecekan `null`, duplikasi, atau kelengkapan atribut.

#### 6.3.2 Pencarian Satu Buku

`cariBuku` mengubah kata kunci menjadi lowercase, lalu membandingkannya dengan judul dan kategori yang juga diubah menjadi lowercase. `contains()` membuat pencarian bersifat substring. Contoh, kata kunci `li` dapat menemukan judul yang mengandung `li`.

Jika ditemukan, method langsung mengembalikan buku pertama. Jika tidak, method melempar `BookNotFoundException` dengan pesan informatif.

#### 6.3.3 Pencarian Banyak Buku

`cariSemuaBuku` menggunakan logika pencarian yang sama, tetapi memasukkan semua kecocokan ke `hasil`. Jika tidak ada hasil, method mengembalikan `ArrayList` kosong, bukan exception. Perbedaan ini sesuai kebutuhan menu: menu cari dapat menampilkan `Buku tidak ditemukan.` tanpa masuk ke alur error.

#### 6.3.4 Pencarian Anggota

`cariMember` menggunakan `equalsIgnoreCase` sehingga ID `m001` dan `M001` dianggap sama. Bila tidak ditemukan, method melempar `Exception` umum.

#### 6.3.5 Peminjaman Buku

Urutan operasi `pinjamBuku` adalah:

1. Cari anggota berdasarkan ID.
2. Pastikan object anggota tidak null menggunakan assertion.
3. Pastikan ID anggota tidak kosong menggunakan assertion.
4. Cek batas maksimal tiga pinjaman.
5. Cari buku berdasarkan judul/kata kunci.
6. Cek status ketersediaan buku.
7. Ubah status buku menjadi tidak tersedia.
8. Tambahkan total historis peminjaman buku.
9. Masukkan buku ke daftar pinjaman anggota.
10. Tambah `totalPinjaman` global.
11. Perbarui `riwayatKategori`.
12. Cetak pesan berhasil.

Pembaruan map dilakukan dengan pola `containsKey`, `get`, lalu `put`. Jika kategori belum ada, nilainya dibuat satu. Jika sudah ada, nilainya dinaikkan satu.

#### 6.3.6 Pengembalian Buku

`kembalikanBuku` mencari anggota dan buku terlebih dahulu. Kemudian program memastikan buku tersebut memang berada pada daftar pinjaman anggota. Jika tidak, exception dilempar dan status tidak diubah.

Jika valid, buku dihapus dari daftar anggota dan statusnya dikembalikan menjadi tersedia. `totalPinjaman` tidak dikurangi karena field tersebut merupakan jumlah seluruh transaksi peminjaman yang pernah terjadi.

#### 6.3.7 Tampilan Daftar

`tampilkanDaftarBuku` menangani kondisi collection kosong lebih dahulu. Jika ada data, method menggunakan indeks agar dapat menampilkan nomor urut buku, lalu memanggil `buku.tampilkanInfo()`.

`tampilkanDaftarMember` menggunakan enhanced `for` dan memanggil `member.tampilkanInfo()`.

#### 6.3.8 Laporan Perpustakaan

Method `laporanPerpustakaan` menghasilkan beberapa statistik:

- `daftarBuku.size()` untuk jumlah buku.
- `daftarMember.size()` untuk jumlah anggota.
- `totalPinjaman` untuk total transaksi peminjaman.
- Buku terpopuler dengan membandingkan `jumlahDipinjam` terbesar.
- Anggota paling aktif dengan membandingkan jumlah pinjaman aktif.
- Kategori populer dari `riwayatKategori`.
- Jumlah buku per kategori dari `daftarBuku`.

Variabel sementara seperti `bukuTerpopuler`, `memberAktif`, dan `kategoriPopuler` dimulai dari `null`. Object pertama menjadi kandidat awal, kemudian kandidat diganti apabila ditemukan nilai yang lebih besar.

Apabila terdapat nilai yang sama, program mempertahankan object yang ditemukan lebih dahulu karena perbandingan hanya menggunakan operator `>`.

---

### 6.4 Package `library.main`

`MainApp` memiliki method `main`, yaitu entry point Java:

```java
public static void main(String[] args)
```

Program membuat `Scanner` dan service, lalu mengisi data awal. Setelah itu, perulangan utama berjalan selama `pilihan != 8`.

Input menu dibaca menggunakan:

```java
pilihan = input.nextInt();
input.nextLine();
```

`nextLine()` setelah `nextInt()` diperlukan untuk membersihkan newline yang tersisa pada buffer input. Tanpa itu, pembacaan judul atau teks berikutnya dapat langsung membaca baris kosong.

`switch (pilihan)` memetakan angka menu ke operasi service. Menu tambah buku membaca judul, penulis, tahun, dan kategori lalu membuat object `Book`. Menu pencarian menggunakan `cariSemuaBuku` dan melakukan iterasi hasil.

Penanganan error dilakukan dengan dua blok:

```java
catch (BookNotFoundException e) { ... }
catch (Exception e) { ... }
```

Catch khusus ditulis lebih dahulu agar exception yang lebih spesifik dapat ditangani sebelum catch `Exception` yang umum. Namun, dalam implementasi ini keduanya pada dasarnya mencetak prefix `ERROR:` dan pesan exception.

Pada catch umum, `input.nextLine()` dipanggil untuk membersihkan input yang tersisa setelah kesalahan input. Program menutup scanner dengan `input.close()` setelah loop selesai.

---

## 7. Alur Bisnis dan Mekanisme Validasi (Flow Logic)

### 7.1 Alur Umum Program

```text
Mulai
  │
  ├── Buat Scanner
  ├── Buat LibraryService
  ├── Tambahkan data buku awal
  ├── Tambahkan data anggota awal
  │
  ▼
Tampilkan menu
  │
  ├── Baca pilihan
  ├── Jalankan operasi sesuai pilihan
  ├── Tangani exception jika ada
  │
  ├── Jika pilihan 8 → tampilkan pesan selesai → selesai
  └── Selain 8 → kembali ke menu
```

### 7.2 Flow Peminjaman

```text
Input ID anggota dan judul buku
          │
          ▼
Cari anggota
  ├── tidak ada → error
  └── ditemukan
          │
          ▼
Jumlah pinjaman >= 3?
  ├── ya → BorrowLimitExceededException
  └── tidak
          │
          ▼
Cari buku
  ├── tidak ada → BookNotFoundException
  └── ditemukan
          │
          ▼
Buku tersedia?
  ├── tidak → BookAlreadyBorrowedException
  └── ya
          │
          ▼
Ubah status, tambah counter, simpan ke anggota,
perbarui statistik kategori, tampilkan berhasil
```

### 7.3 Flow Pengembalian

```text
Input ID anggota dan judul buku
          │
          ▼
Cari anggota dan buku
  ├── salah satu tidak ada → error
  └── keduanya ditemukan
          │
          ▼
Buku ada di daftar pinjaman anggota?
  ├── tidak → error
  └── ya
          │
          ▼
Hapus dari daftar pinjaman
Ubah status menjadi tersedia
Tampilkan berhasil
```

### 7.4 Ringkasan Validasi

| Kondisi | Mekanisme | Dampak |
|---|---|---|
| Buku tidak ditemukan | `BookNotFoundException` | Transaksi dibatalkan |
| Buku sedang dipinjam | `BookAlreadyBorrowedException` | Peminjaman ditolak |
| Anggota meminjam 3 buku | `BorrowLimitExceededException` | Peminjaman ditolak |
| Anggota tidak ditemukan | `Exception` umum | Operasi dibatalkan |
| Pengembalian oleh anggota yang salah | `Exception` umum | Status buku tidak berubah |
| Menu tidak tersedia | `default` pada `switch` | Pesan informasi |
| Input bukan angka | `Exception` umum | Error ditampilkan, input dibersihkan |
| Data anggota null atau ID kosong | `assert` | Hanya aktif jika assertions diaktifkan |

### 7.5 Catatan tentang `assert`

Assertion bukan pengganti validasi input pengguna. Secara default, Java menjalankan program tanpa mengaktifkan assertion. Agar aktif, program harus dijalankan dengan opsi `-ea`. Validasi aturan bisnis seperti ID kosong sebaiknya menggunakan `if` dan exception agar selalu berjalan.

---

## 8. Panduan Kompilasi dan Eksekusi Program

### 8.1 Prasyarat

- Java Development Kit (JDK), disarankan JDK 8 atau lebih baru.
- Terminal atau command prompt.
- Struktur package harus tetap sesuai dengan struktur folder.

Periksa instalasi:

```bash
java -version
javac -version
```

### 8.2 Kompilasi dari Folder `LaprakP4`

Masuk ke folder:

```bash
cd LaprakP4
```

Buat folder output, kemudian compile seluruh source:

```bash
mkdir -p out
javac -d out library/model/*.java library/exception/*.java library/service/*.java library/main/*.java
```

Opsi `-d out` menempatkan file `.class` ke struktur package di dalam folder `out`.

### 8.3 Menjalankan Program

```bash
java -cp out library.main.MainApp
```

Untuk mengaktifkan assertion:

```bash
java -ea -cp out library.main.MainApp
```

### 8.4 Kompilasi dan Eksekusi Tanpa Folder Output

Alternatif sederhana:

```bash
cd LaprakP4
javac library/model/*.java library/exception/*.java library/service/*.java library/main/*.java
java library.main.MainApp
```

Cara ini menghasilkan file `.class` di dekat source. Untuk repository yang rapi, cara dengan `-d out` lebih disarankan.

### 8.5 Contoh Sesi Dasar

```text
Pilih menu: 2
# Menampilkan empat buku awal

Pilih menu: 4
ID Anggota: M001
Judul Buku: Laut Bercerita
Buku berhasil dipinjam.

Pilih menu: 7
# Menampilkan M001 dengan satu pinjaman aktif

Pilih menu: 5
ID Anggota: M001
Judul Buku: Laut Bercerita
Buku berhasil dikembalikan.

Pilih menu: 8
Program selesai. Terima kasih.
```

---

## 9. Analisis Hasil Pengujian dan Output Sistem

### 9.1 Pengujian Menampilkan Daftar Buku

**Langkah:** pilih menu `2`.

**Hasil yang diharapkan:** empat buku awal ditampilkan. Setiap buku memuat judul, penulis, tahun terbit, kategori, kode kategori `N`, status `Tersedia`, dan total dipinjam `0`.

**Analisis:** data awal berhasil dimasukkan melalui `tambahBuku`. Karena semua object dibuat oleh constructor `Book`, status awal setiap buku tersedia.

### 9.2 Pengujian Pencarian Berdasarkan Judul

**Input:** menu `3`, kata kunci `hujan`.

**Hasil:** buku `Hujan` ditemukan.

**Analisis:** kedua string diubah menjadi lowercase, sehingga pencarian tidak sensitif terhadap kapitalisasi. `contains` memungkinkan pencarian sebagian judul.

### 9.3 Pengujian Pencarian Berdasarkan Kategori

**Input:** menu `3`, kata kunci `novel`.

**Hasil:** seluruh buku awal ditemukan karena seluruhnya berkategori `Novel`.

**Analisis:** pencarian tidak hanya memeriksa judul, tetapi juga `getKategori()`.

### 9.4 Pengujian Buku Tidak Ditemukan

**Input:** menu `3`, kata kunci yang tidak ada, misalnya `komik`.

**Hasil:**

```text
Buku tidak ditemukan.
```

**Analisis:** menu menggunakan `cariSemuaBuku`, yang mengembalikan list kosong sehingga program menampilkan pesan khusus tanpa melempar exception.

### 9.5 Pengujian Peminjaman Berhasil

**Input:** anggota `M001`, buku `Laut Bercerita`.

**Hasil:**

```text
Buku berhasil dipinjam.
```

**Perubahan state:**

- Status buku menjadi `Dipinjam`.
- `jumlahDipinjam` buku bertambah satu.
- Buku masuk ke daftar pinjaman `M001`.
- `totalPinjaman` bertambah satu.
- Counter kategori `Novel` bertambah satu.

### 9.6 Pengujian Peminjaman Buku yang Sama

**Langkah:** pinjam `Laut Bercerita` lagi sebelum dikembalikan.

**Hasil:**

```text
ERROR: Buku sedang dipinjam.
```

**Analisis:** service menolak transaksi sebelum menambahkan buku ke daftar anggota untuk kedua kalinya.

### 9.7 Pengujian Batas Maksimal Peminjaman

**Langkah:** pinjam tiga buku berbeda untuk `M001`, lalu coba pinjam buku keempat.

**Hasil:**

```text
ERROR: Anggota sudah meminjam 3 buku. Batas maksimal tercapai.
```

**Analisis:** pengecekan batas dilakukan sebelum pencarian buku. Dengan demikian, ketika batas sudah tercapai, transaksi langsung dihentikan.

### 9.8 Pengujian Pengembalian Berhasil

**Langkah:** kembalikan buku yang memang ada pada daftar pinjaman anggota.

**Hasil:**

```text
Buku berhasil dikembalikan.
```

**Perubahan state:** buku dihapus dari daftar pinjaman anggota dan statusnya menjadi `Tersedia`. Counter historis `jumlahDipinjam` tetap, karena buku tersebut tetap pernah dipinjam.

### 9.9 Pengujian Pengembalian oleh Anggota yang Salah

**Langkah:** anggota `M002` mencoba mengembalikan buku yang dipinjam `M001`.

**Hasil:**

```text
ERROR: Anggota tersebut tidak sedang meminjam buku ini.
```

**Analisis:** validasi `contains(buku)` menjaga agar anggota lain tidak dapat mengubah transaksi milik anggota pertama.

### 9.10 Pengujian Laporan

Misalnya terjadi satu transaksi peminjaman `Laut Bercerita`, lalu dikembalikan. Laporan akan tetap mencatat:

- Jumlah total buku: 4.
- Jumlah total anggota: 2.
- Jumlah total peminjaman: 1.
- Buku paling sering dipinjam: `Laut Bercerita`.
- Jumlah dipinjam: 1.
- Kategori populer: `Novel`.
- Jumlah peminjaman kategori: 1.
- Jumlah buku per kategori: `Novel : 4 buku`.

Perlu dibedakan antara **total transaksi historis** dan **jumlah pinjaman aktif**. Pengembalian mengurangi pinjaman aktif anggota, tetapi tidak mengurangi `totalPinjaman` dan `jumlahDipinjam`.

### 9.11 Evaluasi Kualitas Implementasi

Kekuatan:

- Struktur package jelas.
- Tanggung jawab service cukup terpusat.
- Custom exception membuat beberapa error bisnis mudah dipahami.
- Pencarian mendukung judul dan kategori.
- Laporan menyediakan beberapa agregasi berguna.
- Status buku dan daftar pinjaman diperbarui secara konsisten pada alur normal.

Hal yang dapat ditingkatkan:

1. Membuat `MemberNotFoundException` khusus.
2. Mengganti `throws Exception` umum dengan exception yang lebih spesifik.
3. Menambahkan validasi string kosong, tahun tidak valid, dan object null.
4. Mencegah duplikasi ID anggota dan data buku.
5. Memisahkan class tampilan/menu dari service agar service tidak mencetak langsung ke console.
6. Menggunakan `List<Book>` dan `List<Member>` pada deklarasi field agar bergantung pada interface.
7. Mengembalikan collection read-only dari getter.
8. Menambahkan unit test otomatis.
9. Mempertimbangkan `HashMap<String, Member>` untuk pencarian anggota berdasarkan ID yang lebih efisien.
10. Menggunakan `Locale.ROOT` saat normalisasi lowercase untuk konsistensi lintas locale.
11. Menambahkan file `README.md` ini sebagai dokumentasi proyek.
12. Menambahkan persistence jika data harus bertahan setelah program ditutup.

---

## 10. Kesimpulan

Folder `LaprakP4` merupakan implementasi aplikasi perpustakaan mini berbasis Java console yang menerapkan prinsip dasar Pemrograman Berorientasi Objek. Sistem memodelkan buku dan anggota sebagai object, menggunakan service sebagai pusat aturan bisnis, memisahkan exception ke package khusus, dan menyediakan class utama untuk interaksi pengguna.

Konsep OOP yang terlihat paling kuat adalah enkapsulasi, abstraksi, relasi antar-object, serta pewarisan pada custom exception. Program juga mempraktikkan penggunaan `ArrayList`, `HashMap`, constructor, getter/setter, perulangan, percabangan, `switch-case`, `try-catch`, `throw`, `throws`, assertion, dan operator ternary.

Secara fungsional, program sudah mampu menjalankan proses inti perpustakaan: menambah dan menampilkan buku, mencari buku, mendaftarkan anggota melalui data awal, meminjam, mengembalikan, membatasi jumlah pinjaman, serta menghasilkan laporan statistik. Mekanisme validasi mencegah buku dipinjam ganda, mencegah anggota melewati batas tiga buku, dan mencegah pengembalian oleh anggota yang tidak memiliki buku tersebut.

Implementasi ini masih bersifat dasar karena data hanya disimpan di memory dan beberapa validasi masih menggunakan `Exception` umum. Walaupun demikian, struktur package dan pemisahan model-service-main sudah menjadi fondasi yang baik untuk pengembangan lebih lanjut, seperti penambahan database, unit testing, autentikasi, denda keterlambatan, tanggal transaksi, GUI, atau REST API.

Dengan demikian, `LaprakP4` berhasil menunjukkan bagaimana masalah dunia nyata dapat diterjemahkan menjadi class, object, collection, service, exception, serta alur bisnis yang dapat diuji dalam program Java.
