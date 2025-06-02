import arc.*;

public class mainmenu {
    public static void main(String[] args) {
        Console con = new Console();
        displayMenu(con);
    }

    public static void displayMenu(Console con) {
        int intChoice = 0;
        String strInput;

        while (intChoice != 4) {
            con.clear();
            con.println("MULTIPLE CHOICE GAME");
            con.println("1. Play Game");
            con.println("2. View Leaderboard");
            con.println("3. Add Quiz");
            con.println("4. Quit");
            con.print("Enter your choice (1–4): ");

            strInput = con.readLine();

            // Check if input is one character and digit
            if (strInput.length() == 1 && 
                strInput.charAt(0) >= '1' && strInput.charAt(0) <= '4') {

                intChoice = strInput.charAt(0) - '0'; // convert char digit to int

                if (intChoice == 1) {
                    con.println("You chose to play a game.");
                } else if (intChoice == 2) {
                    con.println("Viewing leaderboard...");
                } else if (intChoice == 3) {
                    con.println("Adding a new quiz...");
                } else if (intChoice == 4) {
                    con.println("Goodbye!");
                }
            } else {
                con.println("Invalid input. Please enter a number from 1 to 4.");
                intChoice = 0; // keep looping
            }

            con.println();
            con.println("Press enter to continue...");
            con.readLine();
        }
    }
}

		
		
