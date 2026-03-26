public class Book {
    public String bookName;
    public String bookAuthor;
    public boolean isAvailable;

    public Book(String bookName, String bookAuthor){
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
    }

    public void displayBookInfo(){
        System.out.println("Book Title:" + bookName + ", Author: " + bookAuthor );
    }
}
