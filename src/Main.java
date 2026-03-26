// Simulation
public class Main {
    public static void main(String[] args) {
        //Welcome Messages!

        Library lib = new Library();
        System.out.println(" ");
        System.out.println("----- 14 Days in the Life of a Library -----");

        while (lib.currentDay < 14) {
            // Advance time
            lib.currentDay = lib.currentDay + 1;
            System.out.println("\n--- Day " + lib.currentDay + " ---");
            System.out.println(" ");

            if (lib.currentDay == 7) {
                System.out.println("Halfway of life in the library");
            }

            // simulate a random event
            // Simulate a 25% chance of a new member joining the library
            int randomMember = Rand.randomInt(0, 4); // could generate 0, 1, 2, or 3
            int randomBook = Rand.randomInt(0,1); // could generate 0 or 1
            int newBook = Rand.randomInt(0,2); // could generate 0, 1 or 2

            if (randomMember == 0){ // 25% chance

                int newID = lib.members.size() + 1;
                String newName = "Member" + newID;

                Member newMember = new Member(newName, newID);
                lib.members.add(newMember);

                System.out.println(newName + " joined the library!");
            }
            else{
                System.out.println("No new members joined today.");
            }

            if (randomBook == 0){ // 50% of someone taking out a book
                // Member selectedMemb = members[]

            }

            lib.displayMembers();
            Input.waitForUserToPressEnter("Press Enter to simulate the next day.");
        }


        System.out.println("----- The End! -----");
    }
}
