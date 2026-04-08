public class Member {

    //Private Fields Now
    private String name;
    private int memberID;

    //Constructor
    public Member(String name, int memberID) {
        this.name = name;
        this.memberID = memberID;
    }

    //Getters
    public String getName(){
        return name;
    }

    public int getMemberID(){
        return memberID;
    }

    //Simple methods that we use in library to display all the current members
    // We abstracted the one method at first into two because before, our method built and printed
    // But now we seperated the two resposiblities
    private String getFormattedInfo() {
        return "ID: " + memberID + ", Name: " + name;
    }

    public void displayInfo() {
        System.out.println(getFormattedInfo());
    }
}

