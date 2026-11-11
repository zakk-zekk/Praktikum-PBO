package model;

/**
 * Custom exception yang dilempar jika nilai kurang dari 0 atau lebih dari 100.
 */
public class NilaiInvalidException extends Exception {

    public NilaiInvalidException(String message) {
        super(message);
    }
}
