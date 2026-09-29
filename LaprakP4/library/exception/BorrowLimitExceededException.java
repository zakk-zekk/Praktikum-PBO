package library.exception;

public class BorrowLimitExceededException extends Exception {

    public BorrowLimitExceededException(String pesan) {
        super(pesan);
    }
}