import java.util.ArrayList;

// Represents the entire library system
class Library {

    int currentDay; // Tracks the current day in the simulation

    ArrayList<Member> members; // Stores all library members
    ArrayList<Book> books;     // Stores all books in the library
    ArrayList<String> names = new ArrayList<>(); //Stores all names for possible members
    ArrayList<Book> bookPool;
    // List of possible book titles and authors
    String[] titles = {
            "The Bible",
            "Harry Potter and The Sorcerer's Stone",
            "The Hunger Games",
            "The Godfather",
            "The Lord of The Rings",
            "The Hobit"
    };

    String[] authors = {
            "over 40 different authors",
            "J.K Rowling",
            "Suzanne Collins",
            "Mario Puzo",
            "J.R.R Tolkien",
            "J.R.R. Tolkien"
    };


    public Library() {

        // Initialize lists
        members = new ArrayList<>();
        books = new ArrayList<>();
        bookPool = new ArrayList<>();
        // -------------------------------
        // Add Starting Members
        // -------------------------------
        members.add(new Member("Justice", 1));
        members.add(new Member("MJ", 2));
        members.add(new Member("Mr. Rempel", 3));

        // Member name pool
        names.add("Jurell");
        names.add("Michael Jordan");
        names.add("Kros");
        names.add("Christiano Ronaldo");
        names.add("Jordan MANANSALA");
        names.add("Mr. Beckett");
        names.add("Teo");
        names.add("Elon Musk");
        names.add("Akeil");
        names.add("Donald Trump");

        // --------------------------
        // List of books that can be added to the library inventory;
        // --------------------------

        bookPool.add(new Book("A Long Walk to Water", "Linda Sue Park"));
        bookPool.add(new Book("Dune", "Frank Herbert"));
        bookPool.add(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
        bookPool.add(new Book("Diary of a Wimpy Kid", "Jeff Kinney"));
        bookPool.add(new Book("Percy Jackson & The Olympians: The Lightning Thief", "Rick Riordan"));

        // -------------------------------
        // Add Randomized Starting Books
        // -------------------------------

        // Total number of books in the library (between 35 and 100)
        int totalBooks = Rand.randomInt(35, 101);


        // Add random books to the library
        for (int i = 0; i < totalBooks; i++) {

            // Pick a random book from the list
            int randomIndex = Rand.randomInt(0, titles.length);

            String bookName = titles[randomIndex];
            String bookAuthor = authors[randomIndex];

            // Create new book object
            Book newBook = new Book(bookName, bookAuthor);

            // Mark book as available when added
            newBook.isAvailable = true;

            // Add book to library collection
            books.add(newBook);
        }

        // -------------------------------
        // Full Welcome Message with Rules!
        // -------------------------------

        System.out.println("\n===== WELCOME TO THE LIBRARY SYSTEM (by MJ and Justice) =====");
        System.out.println(" ");
        System.out.println("----- 14 Days in the Life of a Library -----");
        System.out.println(" ");

        System.out.println("\nRULES:");
        System.out.println(" ");

        System.out.println("- Members can join the library for FREE!");
        System.out.println("- Each member can borrow a maximum of 3 books at a time.");
        System.out.println("- Each book can be kept for a maximum of 3 days.");
        System.out.println("- After 3 days, the book is considered OVERDUE.");
        System.out.println("- Overdue books cost $5 per day until returned.");
        System.out.println(" ");

        // Shows how many copies of each book exist at the beginning
        System.out.println("\n===== INITIAL BOOK INVENTORY =====");

        for (int i = 0; i < titles.length; i++) {

            String currentTitle = titles[i];
            String currentAuthor = authors[i];

            int count = 0;

            for (Book b : books) {
                if (b.bookName.equals(currentTitle)) {
                    count++;
                }
            }

            System.out.println(currentTitle + " by " + currentAuthor + ": " + count + " copies");
        }
        System.out.println(" ");
    }

    // -------------------------------
    // Display Members
    // -------------------------------
    public void displayMembers() {
        System.out.println(" ");
        System.out.println("Current Library Members:");

        for (Member m : members) {
            m.displayInfo();
        }
    }
}