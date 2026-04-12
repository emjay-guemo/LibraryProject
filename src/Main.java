import java.util.ArrayList;

// Simulation
public class Main {
    public static void main(String[] args) {

        // Create the library (members + books are initialized inside)
        Library lib = new Library();

        // List to track all active loans
        ArrayList<Loan> loans = new ArrayList<>();

        int totalFees = 0; // tracks total money earned from overdue books

        // -------------------------------
        // Tracking for Statistics
        // -------------------------------
        String topMember = "";
        int topMemberLoans = 0;

        String mostBorrowedBook = "";
        int mostBorrowedCount = 0;

        // Run simulation for 14 days
        while (lib.getCurrentDay() < 14) {

            // Move to next day
            lib.increaseCurrentDay(0);

            System.out.println("\n--- Day " + lib.getCurrentDay() + " ---");
            System.out.println(" ");

            // Midway message
            if (lib.getCurrentDay() == 7) {
                System.out.println("It has now officially been 7 days in the Life of a Library");
            }

            // 25% chance for new member
            int randomMember = Rand.randomInt(0, 4);

            // 33% of new book that can be added to a library
            int randomNewBook = Rand.randomInt(0, 2);

            // -------------------------------
            // Member Join Event
            // -------------------------------
            if (randomMember == 0) {

                int newID = lib.members.size() + 1;
                // Pick random name from available pool
                int nameIndex = Rand.randomInt(0, lib.names.size());
                String newName = lib.names.get(nameIndex);

                lib.names.remove(nameIndex);

                Member newMember = new Member(newName, newID);
                lib.members.add(newMember);

                System.out.println(" ");
                System.out.println(newMember.getName() + " joined the library!");
            } else {
                System.out.println(" ");
                System.out.println("No new members joined today.");
            }

            //----------------------
            // New Book Stored in library simulation
            //----------------------

            if (randomNewBook == 0){
                int newBook = lib.books.size() + 1;
                // Picking random book from the available pool
                int bookIndex = Rand.randomInt(0, lib.bookPool.size());
                Book storedNewBook = lib.bookPool.get(bookIndex);

                // removes the book and author from being chosen again
                lib.bookPool.remove(bookIndex);

                Book storedBook = new Book(storedNewBook.getBookName(), storedNewBook.getBookAuthor());
                lib.bookPool.add(storedBook);

                System.out.println(" ");
                System.out.println(storedBook.getBookName() +  "by " + storedBook.getBookAuthor() + " stored in the library!");
            }
            else {
                System.out.println(" ");
                System.out.println("No new books was added to the library today");
            }

            // -------------------------------
            // Borrow Book Section
            // -------------------------------
            // Each member has a 25% chance to borrow a book BUT can only have a maximum of 3 books at once

            for (Member selectedMember : lib.members) {

                // -------------------------------
                // STEP 1:Count books
                // -------------------------------
                int currentBooks = 0;

                for (Loan l : loans) {
                    if (l.getMembID() == selectedMember.getMemberID() && !l.isReturned()) {
                        currentBooks++;
                    }
                }

                // -------------------------------
                // STEP 2: Check if member can borrow
                // -------------------------------
                if (currentBooks < 3) {

                    // 25% chance to attempt borrowing
                    int chance = Rand.randomInt(0, 4);

                    if (chance == 1) {

                        // -------------------------------
                        // STEP 3: Find available book
                        // -------------------------------
                        Book selectedBook = null;

                        for (Book b : lib.books) {
                            if (b.isAvailable()) {
                                selectedBook = b;
                                break;
                            }
                        }

                        // -------------------------------
                        // STEP 4: Create loan if book exists
                        // -------------------------------
                        if (selectedBook != null) {

                            selectedBook.borrowBook();

                            Loan loan = new Loan();

                            loan.storeMemberName(selectedMember.getName());
                            loan.storeMemId(selectedMember.getMemberID());

                            loan.storeBookName(selectedBook.getBookName());

                            loan.storeDayBorrowed(lib.getCurrentDay());
                            loan.storeDaysKept(0);
                            loan.setReturned(false);

                            loans.add(loan);

                            // -------------------------------
                            // Track most active member
                            // -------------------------------
                            int memberLoanCount = 0;

                            for (Loan l : loans) {
                                if (l.getMembID() == selectedMember.getMemberID()) {
                                    memberLoanCount++;
                                }
                            }

                            if (memberLoanCount > topMemberLoans) {
                                topMemberLoans = memberLoanCount;
                                topMember = selectedMember.getName();
                            }

                            // -------------------------------
                            // Track most borrowed book
                            // -------------------------------
                            int bookCount = 0;

                            for (Loan l : loans) {
                                if (l.getBookName().equals(selectedBook.getBookName())) {
                                    bookCount++;
                                }
                            }

                            if (bookCount > mostBorrowedCount) {
                                mostBorrowedCount = bookCount;
                                mostBorrowedBook = selectedBook.getBookName();
                            }

                            System.out.println(selectedMember.getName() + " borrowed \"" + selectedBook.getBookName() + "\"");
                        }
                    }
                }
            }

            // Daily Loan Updates
            System.out.println("\n--- Loan Updates ---");

            for (Loan loan : loans) {

                // Only update if book not returned
                if (!loan.isReturned()) {

                    loan.increaseDaysKept(-1); // Makes sure the loan starts at day 0 instead of day 1.

                    int chance;

                    // Increasing probability of return as they have the book longer
                    if (loan.getDaysKept() == 1) chance = 5;
                    else if (loan.getDaysKept() == 2) chance = 10;
                    else if (loan.getDaysKept() == 3) chance = 20;
                    else if (loan.getDaysKept() == 4) chance = 30;
                    else if(loan.getDaysKept() == 5) chance = 45;
                    else chance = 65;

                    int roll = Rand.randomInt(1, 101);

                    // BOOK RETURNED
                    if (roll <= chance) {

                        loan.setReturned(true);

                        // Find the book and make it available again
                        for (Book b : lib.books) {
                            if (b.getBookName().equals(loan.getBookName()) && !b.isAvailable()) {
                                b.returnBook();
                                break;
                            }
                        }

                        System.out.println(loan.getMemberName() + " returned \"" + loan.getBookName() + "\"");

                    } else {

                        // Show how long they have had the book
                        System.out.println(loan.getMemberName() +
                                " has \"" + loan.getBookName() +
                                "\" for " + loan.getDaysKept() + " days");

                        // OVERDUE CHECK
                        if (loan.getDaysKept() > loan.getMaxDays()) {

                            int overdueDays = loan.getDaysKept() - loan.getMaxDays();
                            int fee = overdueDays * loan.getFeePerDay();

                            System.out.println("Overdue! " + loan.getMemberName() +
                                    " owes $" + fee);
                            totalFees += fee; // add daily fee to total
                        }
                    }
                }
            }

            // Display members at end of day
            lib.displayMembers();

            // Pause simulation
            Input.waitForUserToPressEnter("Press Enter to simulate the next day.");
        }

        // -------------------------------
        // Final Summary after 14 days!
        // -------------------------------

        System.out.println("\n===== FINAL LIBRARY SUMMARY =====");

        // -------------------------------
        // Total Members
        // -------------------------------
        System.out.println("Total Members: " + lib.members.size());

        // -------------------------------
        // Book Availability
        // -------------------------------
        int borrowedBooks = 0;

        // -------------------------------
        // Final Book Inventory Summary
        // -------------------------------
        // This section displays how many copies of each book are left in the library after the 14-day simulation.

        System.out.println("\n===== FINAL BOOK INVENTORY =====");

        // Loop through all possible book types in the library (6 total)
        for (int i = 0; i < lib.titles.length; i++) {

            // Get the current book title and author from the library arrays
            String currentTitle = lib.titles[i];
            String currentAuthor = lib.authors[i];

            // Counter for available copies of this book
            int availableCount = 0;

            // Loop through every book object in the library inventory
            for (Book b : lib.books) {

                // Check if the book matches this title AND is still available
                if (b.getBookName().equals(currentTitle) && b.isAvailable()) {
                    availableCount++;
                }
            }

            // Print final result for this book type
            System.out.println(" - " + currentTitle + " by " + currentAuthor +
                    ": " + availableCount + " available");
        }

        // -------------------------------
        // Loan Statistics
        // -------------------------------
        int totalLoans = loans.size();
        int returnedLoans = 0;
        int activeLoans = 0;

        for (Loan loan : loans) {
            if (loan.isReturned()) {
                returnedLoans++;
            } else {
                activeLoans++;
            }
        }

        System.out.println("\nTotal Loans Created: " + totalLoans);
        System.out.println("Books Returned: " + returnedLoans);
        System.out.println("Books Still Borrowed: " + activeLoans);
        System.out.println(" ");

        // -------------------------------
        // Statistics
        // -------------------------------
        System.out.println("\n===== LIBRARY STATISTICS =====");

        System.out.println("Most Active Member: " + topMember +
                " (" + topMemberLoans + " loans)");

        System.out.println("Most Borrowed Book: " + mostBorrowedBook +
                " (" + mostBorrowedCount + " times)");

        System.out.println("System successfully simulated 14 days of library activity.");

        // -------------------------------
        // Total Money Generated by the Library After the 14 Days
        // -------------------------------
        System.out.println("\nTotal Overdue Fees Collected: $" + totalFees);

        // End Message!
        System.out.println("----- The End! -----");
    }
}