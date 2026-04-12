// Loan class represents a single borrowing event in the library
// It stores all information related to one borrowed book and handles logic like overdue fees

public class Loan {

    // -------------------------------
    // Private Fields (Encapsulation)
    // -------------------------------
    private String memberName;   // Name of the member who borrowed the book
    private int memberID;        // ID of the member

    private String bookName;     // Name of the borrowed book

    private int dayBorrowed;     // Day the book was borrowed
    private int daysKept;        // How long the book has been kept

    private int maxDays;         // Max days before overdue
    private int feePerDay;       // Fee charged per overdue day

    private boolean returned;    // Whether the book has been returned


    // -------------------------------
    // Constructor (Abstraction)
    // -------------------------------
    public Loan(String memberName, int memberID, String bookName, int dayBorrowed) {
        this.memberName = memberName;
        this.memberID = memberID;
        this.bookName = bookName;
        this.dayBorrowed = dayBorrowed;

        this.daysKept = 0;
        this.returned = false;

        this.maxDays = 3;     // default rule
        this.feePerDay = 5;   // default rule
    }


    // -------------------------------
    // Getter Methods (Controlled Access)
    // -------------------------------
    public String getMemberName() {
        return memberName;
    }

    public int getMemberID() {
        return memberID;
    }

    public String getBookName() {
        return bookName;
    }

    public int getDayBorrowed() {
        return dayBorrowed;
    }

    public int getDaysKept() {
        return daysKept;
    }

    public boolean isReturned() {
        return returned;
    }

    public int getMaxDays() {
        return maxDays;
    }

    public int getFeePerDay() {
        return feePerDay;
    }


    // -------------------------------
    // Setters
    // -------------------------------
    public void setReturned(boolean returned) {
        this.returned = returned;
    }


    // -------------------------------
    // Abstraction Methods
    // -------------------------------

    // Increases how long the book has been kept
    public void updateDay() {
        daysKept++;
    }

    // Determines if the book is overdue
    public boolean isOverdue() {
        return daysKept > maxDays;
    }

    // Calculates overdue fee
    public int calculateFee() {
        if (!isOverdue()) return 0;

        int overdueDays = daysKept - maxDays;
        return overdueDays * feePerDay;
    }

    // Determines chance of returning book based on time kept
    public int getReturnChance() {
        if (daysKept == 1) return 5;
        else if (daysKept == 2) return 10;
        else if (daysKept == 3) return 20;
        else if (daysKept == 4) return 30;
        else if (daysKept == 5) return 45;
        else return 65;
    }
}