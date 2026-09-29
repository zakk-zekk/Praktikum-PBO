package library.exception;

public class BookNotFoundException extends Exception {

    public BookNotFoundException(String pesan) {
        super(pesan);
    }
}