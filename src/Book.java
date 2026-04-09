public class Book {
    // New Private Fields
    private String bookName;
    private String bookAuthor;
    private boolean isAvailable;

    //Constructor
    public Book(String bookName, String bookAuthor) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.isAvailable = true; // default: book starts available
    }

    //Getters
    public String getBookName(){
        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    //Setter
    public void setAvailablity(boolean available){
        this.isAvailable = available;
    }

    //Abstraction Methods

    //Borrow the book
    public void borrowBook() {
        isAvailable = false;
    }

    // Return the book
    public void returnBook() {
        isAvailable = true;
    }

}
