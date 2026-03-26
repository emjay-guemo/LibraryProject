// Loan class holds all info and data about loans and fees
public class Loan {

    String memberName;  // Who borrowed the book
    int MemberID;

    int dayBorrowed;    //What day they took the book
    int daysKept;   // How many days they borrowed it

    int maxDays = 3;    // Maximum days they can keep the book without getting fined
    int feePerDay = 5;  // Fee per extra day

    boolean returned;   // Whether book is returned or not
}
