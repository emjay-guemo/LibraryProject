// Simulation
public class Main {
    public static void main(String[] args) {

        Library lib = new Library();

        while (true) {

            // Advance time
            lib.currentDay = lib.currentDay + 1;
            System.out.println("\n--- Day " + lib.currentDay + " ---");

            // simulate a random event
            int randomNumber = Rand.randomInt(0, 4); // could generate 0, 1, 2, or 3

            switch (randomNumber) {

            }

            Input.waitForUserToPressEnter("Press Enter to simulate the next day.");
        }
    }
}