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
        String[] topMemberData = {"", "0"};
        String[] mostBorrowedData = {"", "0"};

        // Run simulation for 14 days
        while (lib.getCurrentDay() < 14) {

            // Move to next day
            lib.increaseCurrentDay();

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

                System.out.println(" ");
                lib.addRandomMember();

            } else {
                System.out.println(" ");
                System.out.println("No new members joined today.");
            }

            //----------------------
            // New Book Stored in library simulation
            //----------------------

            if (randomNewBook == 0) {
                System.out.println(" ");
                lib.addRandomBook();
            } else {
                System.out.println(" ");
                System.out.println("No new books were added to the library today");
            }

            // -------------------------------
            // Borrow Book Section
            // -------------------------------

            lib.processBorrowing(loans, topMemberData, mostBorrowedData);

            // Daily Loan Updates
            System.out.println("\n--- Loan Updates ---");

            for (Loan loan : loans) {

                // Only update if book not returned
                if (!loan.isReturned()) {

                    loan.updateDay();

                    int chance = loan.getReturnChance();

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
                        if (loan.isOverdue()) {

                            int fee = loan.calculateFee();

                            System.out.println("Overdue! " + loan.getMemberName() +
                                    " owes $" + fee);
                            totalFees += fee;
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

        System.out.println("Most Active Member: " + topMemberData[0] +
                " (" + topMemberData[1] + " loans)");

        System.out.println("Most Borrowed Book: " + mostBorrowedData[0] +
                " (" + mostBorrowedData[1] + " times)");

        System.out.println("System successfully simulated 14 days of library activity.");

        // -------------------------------
        // Total Money Generated by the Library After the 14 Days
        // -------------------------------
        System.out.println("\nTotal Overdue Fees Collected: $" + totalFees);

        // End Message!
        System.out.println("----- The End! -----");
    }
}