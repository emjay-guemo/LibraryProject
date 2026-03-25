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

            if (lib.currentDay == 7) {
                System.out.println("Halfway of life in the library");
                
            }
            // simulate a random event
            int randomNumber = Rand.randomInt(0, 51); // could generate 0, 1... 25... up to 50.


            switch (randomNumber) {


            }
            Input.waitForUserToPressEnter("Press Enter to simulate the next day.");
            System.out.println("----- The End! -----");
            }
        }

}