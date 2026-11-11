package model;

public class Mahasiswa {

    // Konstanta standar kelulusan
    public static final double FINAL_PASSING_GRADE = 70.0;

    private String nama;   // reference type
    private String nim;    // reference type
    private double nilai;  // primitive type

    public Mahasiswa(String nama, String nim, double nilai) throws NilaiInvalidException {
        // Merapikan nama & NIM dengan method String
        this.nama = nama.trim().toUpperCase();
        this.nim = nim.trim();

        // Assertion: NIM tidak boleh kosong (jalankan dengan: java -ea)
        assert !this.nim.isEmpty() : "NIM tidak boleh kosong!";

        // Validasi NIM hanya berisi angka menggunakan Character.isDigit()
        for (char c : this.nim.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("NIM hanya boleh berisi angka!");
            }
        }

        // Validasi nilai dengan custom exception
        if (nilai < 0 || nilai > 100) {
            throw new NilaiInvalidException(
                    "Nilai " + nilai + " tidak valid! Nilai harus antara 0 sampai 100.");
        }
        this.nilai = nilai;
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public double getNilai() {
        return nilai;
    }

    /** Memetakan nilai ke grade huruf (A, B, C, D) dengan if-else. */
    public String getGrade() {
        if (nilai >= 85) {
            return "A";
        } else if (nilai >= 75) {
            return "B";
        } else if (nilai >= 65) {
            return "C";
        } else {
            return "D";
        }
    }

    /** Status kelulusan berdasarkan FINAL_PASSING_GRADE. */
    public boolean isLulus() {
        return nilai >= FINAL_PASSING_GRADE;
    }

    public String getStatus() {
        return isLulus() ? "LULUS" : "TIDAK LULUS";
    }
}
