public class Book {
    public String bookName;
    public String bookAuthor;

    public Book(String bookName, String bookAuthor){
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
    }

    public String toString(){
        return bookName + "by " + bookAuthor;
    }
}
