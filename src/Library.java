import java.util.ArrayList;

// Represents the entire library system
class Library {

    // -------------------------------
    // Private Fields (Encapsulation)
    // -------------------------------
    private int currentDay; // Tracks the current day in the simulation

    ArrayList<Member> members; // Stores all library members
    ArrayList<Book> books;     // Stores all books in the library

    ArrayList<String> names = new ArrayList<>(); // Pool of possible member names
    ArrayList<Book> bookPool; // Pool of books that can be added

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


    // -------------------------------
    // Constructor
    // -------------------------------
    public Library() {

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
// List of books that can be added to the library inventory
// --------------------------

        bookPool.add(new Book("Percy Jackson & The Olympians: The Lightning Thief", "Rick Riordan"));
        bookPool.add(new Book("Percy Jackson & The Olympians: The Sea of Monsters", "Rick Riordan"));
        bookPool.add(new Book("Percy Jackson & The Olympians: The Titan's Curse", "Rick Riordan"));
        bookPool.add(new Book("Harry Potter and the Chamber of Secrets", "J.K Rowling"));
        bookPool.add(new Book("Harry Potter and the Prisoner of Azkaban", "J.K Rowling"));
        bookPool.add(new Book("Harry Potter and the Goblet of Fire", "J.K Rowling"));
        bookPool.add(new Book("Harry Potter and the Order of the Phoenix", "J.K Rowling"));
        bookPool.add(new Book("Harry Potter and the Half-Blood Prince", "J.K Rowling"));
        bookPool.add(new Book("Harry Potter and the Deathly Hallows", "J.K Rowling"));

        bookPool.add(new Book("The Hobbit", "J.R.R. Tolkien"));
        bookPool.add(new Book("The Fellowship of the Ring", "J.R.R. Tolkien"));
        bookPool.add(new Book("The Two Towers", "J.R.R. Tolkien"));
        bookPool.add(new Book("The Return of the King", "J.R.R. Tolkien"));

        bookPool.add(new Book("The Hunger Games", "Suzanne Collins"));
        bookPool.add(new Book("Catching Fire", "Suzanne Collins"));
        bookPool.add(new Book("Mockingjay", "Suzanne Collins"));

        bookPool.add(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
        bookPool.add(new Book("To Kill a Mockingbird", "Harper Lee"));
        bookPool.add(new Book("1984", "George Orwell"));
        bookPool.add(new Book("Animal Farm", "George Orwell"));

        bookPool.add(new Book("The Maze Runner", "James Dashner"));
        bookPool.add(new Book("The Scorch Trials", "James Dashner"));
        bookPool.add(new Book("The Death Cure", "James Dashner"));

        bookPool.add(new Book("Dune", "Frank Herbert"));
        bookPool.add(new Book("The Godfather", "Mario Puzo"));
        bookPool.add(new Book("Diary of a Wimpy Kid", "Jeff Kinney"));
        bookPool.add(new Book("A Long Walk to Water", "Linda Sue Park"));


        // -------------------------------
        // Add Randomized Starting Books
        // -------------------------------
        int totalBooks = Rand.randomInt(35, 101);

        for (int i = 0; i < totalBooks; i++) {

            int randomIndex = Rand.randomInt(0, titles.length);

            Book newBook = new Book(titles[randomIndex], authors[randomIndex]);

            newBook.setAvailablity(true);

            books.add(newBook);
        }

        // -------------------------------
        // Welcome Message
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

        // -------------------------------
        // Initial Inventory Display
        // -------------------------------
        System.out.println("\n===== INITIAL BOOK INVENTORY =====");

        for (int i = 0; i < titles.length; i++) {

            String currentTitle = titles[i];
            String currentAuthor = authors[i];

            int count = 0;

            for (Book b : books) {
                if (b.getBookName().equals(currentTitle)) {
                    count++;
                }
            }

            System.out.println(currentTitle + " by " + currentAuthor + ": " + count + " copies");
        }
        System.out.println(" ");
    }


    // -------------------------------
    // Day Control Methods
    // -------------------------------
    public int getCurrentDay() {
        return currentDay;
    }

    public void increaseCurrentDay() {
        currentDay++;
    }


    // -------------------------------
    // MEMBER LOGIC (ABSTRACTION)
    // -------------------------------

    // Adds a random member from the name pool
    public void addRandomMember() {

        int newID = members.size() + 1;

        int nameIndex = Rand.randomInt(0, names.size());
        String newName = names.get(nameIndex);

        names.remove(nameIndex);

        Member newMember = new Member(newName, newID);
        members.add(newMember);

        System.out.println(newName + " joined the library!");
    }


    // -------------------------------
    // BOOK LOGIC (ABSTRACTION)
    // -------------------------------

    // Adds a random book from the book pool into the library
    public void addRandomBook() {

        int bookIndex = Rand.randomInt(0, bookPool.size());
        Book storedNewBook = bookPool.get(bookIndex);

        Book storedBook = new Book(
                storedNewBook.getBookName(),
                storedNewBook.getBookAuthor()
        );

        books.add(storedBook);

        System.out.println(storedBook.getBookName() +
                " by " + storedBook.getBookAuthor() +
                " stored in the library!");
    }


    // -------------------------------
    // BORROWING LOGIC
    // Handles all borrowing behavior for members
    // -------------------------------
    public void processBorrowing(ArrayList<Loan> loans,
                                 String[] topMemberData,
                                 String[] mostBorrowedData) {

        // Loop through each member in the library
        for (Member selectedMember : members) {

            // -------------------------------
            // STEP 1: Count how many books this member currently has
            // -------------------------------
            int currentBooks = 0;

            for (Loan l : loans) {
                if (l.getMemberID() == selectedMember.getMemberID() && !l.isReturned()) {
                    currentBooks++;
                }
            }

            // -------------------------------
            // STEP 2: Only allow borrowing if under limit (max 3 books)
            // -------------------------------
            if (currentBooks < 3) {

                // 25% chance to attempt borrowing
                int chance = Rand.randomInt(0, 4);

                if (chance == 1) {

                    // -------------------------------
                    // STEP 3: Find an available book
                    // -------------------------------
                    Book selectedBook = null;

                    for (Book b : books) {
                        if (b.isAvailable()) {
                            selectedBook = b;
                            break;
                        }
                    }

                    // -------------------------------
                    // STEP 4: Create loan if book exists
                    // -------------------------------
                    if (selectedBook != null) {

                        // Mark book as borrowed
                        selectedBook.borrowBook();

                        // Create new Loan object (using constructor)
                        Loan loan = new Loan(
                                selectedMember.getName(),
                                selectedMember.getMemberID(),
                                selectedBook.getBookName(),
                                currentDay
                        );

                        loans.add(loan);

                        // -------------------------------
                        // Track MOST ACTIVE MEMBER
                        // -------------------------------
                        int memberLoanCount = 0;

                        for (Loan l : loans) {
                            if (l.getMemberID() == selectedMember.getMemberID()) {
                                memberLoanCount++;
                            }
                        }

                        if (memberLoanCount > Integer.parseInt(topMemberData[1])) {
                            topMemberData[0] = selectedMember.getName();
                            topMemberData[1] = String.valueOf(memberLoanCount);
                        }

                        // -------------------------------
                        // Track MOST BORROWED BOOK
                        // -------------------------------
                        int bookCount = 0;

                        for (Loan l : loans) {
                            if (l.getBookName().equals(selectedBook.getBookName())) {
                                bookCount++;
                            }
                        }

                        if (bookCount > Integer.parseInt(mostBorrowedData[1])) {
                            mostBorrowedData[0] = selectedBook.getBookName();
                            mostBorrowedData[1] = String.valueOf(bookCount);
                        }

                        // Display borrowing message
                        System.out.println(selectedMember.getName() +
                                " borrowed \"" + selectedBook.getBookName() + "\"");
                    }
                }
            }
        }
    }


    // -------------------------------
    // DISPLAY METHODS
    // -------------------------------
    public void displayMembers() {
        System.out.println(" ");
        System.out.println("Current Library Members:");

        for (Member m : members) {
            m.displayInfo();
        }
    }
}