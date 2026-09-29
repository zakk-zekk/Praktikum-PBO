package library.exception;

public class BookAlreadyBorrowedException extends Exception {

    public BookAlreadyBorrowedException(String pesan) {
        super(pesan);
    }
}