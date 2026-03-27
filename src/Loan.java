// Loan class holds all info and data about loans and fees
public class Loan {

    String memberName;  // Who borrowed the book
    int memberID;

    int dayBorrowed;    //What day they took the book
    int daysKept;   // How many days they borrowed it

    String bookName; // stores the name of the borrowed book

    int maxDays = 3;    // Maximum days they can keep the book without getting fined
    int feePerDay = 5;  // Fee per extra day

    int memberBookCount; // Tracks how many books this member has currently taken out

    boolean returned;   // Whether book is returned or not
}