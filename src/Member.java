public class Member {

    String name;
    int memberID;

    public Member(String name, int memberID) {
        this.memberID = memberID;
        this.name = name;
    }

    //Simple method that we use in library to display all the current members
    public void displayInfo() {
        System.out.println("ID: " + memberID + ", Name: " + name);
    }
}

