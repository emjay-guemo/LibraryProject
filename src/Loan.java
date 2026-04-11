// Loan class holds all info and data about loans and fees
public class Loan {

    private String memberName;  // Who borrowed the book
    private int memberID;

    private int dayBorrowed;    //What day they took the book
    private int daysKept;   // How many days they borrowed it

    private String bookName; // stores the name of the borrowed book

    int maxDays = 3;    // Maximum days they can keep the book without getting fined
    int feePerDay = 5;  // Fee per extra day

    private int memberBookCount; // Tracks how many books this member has currently taken out

    private boolean returned;// Whether book is returned or not

    // Setter Methods //
    public void storeMemberName(String memberName){
        this.memberName = memberName;
    }

    public void storeMemId(int memberID){
        this.memberID = memberID;
    }

    public void setReturned(boolean returned){
        this.returned = returned;
    }

    public void storeBookName(String bookName){
        this.bookName = bookName;
    }

    public void storeDayBorrowed(int dayBorrowed){
        this.dayBorrowed = dayBorrowed;
    }

    public void storeDaysKept(int daysKept){
        this.daysKept = daysKept;
    }

    public void storeMemberBookCount(int memberBookCount){
        this.memberBookCount = memberBookCount;
    }

    // Getter Methods //
    public String getMemberName(){
        return memberName;
    }

    public int getMembID(){
        return memberID;
    }

    public boolean isReturned(){
        return returned;
    }

    public String getBookName(){
        return bookName;
    }

    public int getDayBorrowed(){
        return dayBorrowed;
    }

    public int getDaysKept() {
        return daysKept;
    }

    public int getMemberBookCount() {
        return memberBookCount;
    }

    // other methods
    public void increaseDaysKept(int daysKept){
        this.daysKept++;
    } // increases the days that the member has the book for
}