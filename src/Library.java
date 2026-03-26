import java.util.ArrayList;
import java.util.List;

//Represents the entire library system
class Library {

    int currentDay;
    ArrayList<Member> members;
    ArrayList<Book> books;

    public Library() {
        members = new ArrayList<>();
        books = new ArrayList<>();

        // Add starting members
        members.add(new Member("Justice", 1));
        members.add(new Member("MJ", 2));
        members.add(new Member("Bob", 3));

        // Add starting books
        books.add(new Book("The Bible", ""));
        books.add(new Book("Harry Potter and The Sorcerer's Stone ", "J.K Rowling"));
        books.add(new Book("The Hunger Games", "Suzanne Collins"));
        books.add(new Book("The Godfather", "Mario Puzo"));
        books.add(new Book("The Lord of The Rings", "J.R.R Tolkien"));

    }

    // Simple method that we use in main that displays members at the end of each day
    public void displayMembers() {
        System.out.println("Current Library Members:");

        for (Member m : members) {
            m.displayInfo();
        }
    }

    public void displayBooks(){
        System.out.println("Books in the library:");

        for (Book bk : books) {
            bk.displayBookInfo();
        }

    }
}
