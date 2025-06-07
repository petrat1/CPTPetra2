import arc.*;
import java.awt.Color;
import java.awt.Font;

public class mainmenu {
    public static void main(String[] args) {
        Console con = new Console("Multiple Choice Game", 600, 600);
        displayMenu(con);
    }

    public static void displayMenu(Console con) {
        Font fontTitle = new Font("Arial Black", Font.BOLD, 36);
        Font fontOption = new Font("Arial", Font.PLAIN, 24);
        Font fontInput = new Font("Arial", Font.ITALIC, 20);

        while (true) {
            con.clear();

            // Background
            con.setDrawColor(new Color(30, 30, 60));
            con.fillRect(0, 0, 600, 600);

            // Title
            con.setDrawFont(fontTitle);
            con.setDrawColor(new Color(255, 215, 0)); // gold
            String strTitle = "MULTIPLE CHOICE GAME";
            int intTitleWidth = con.getDrawFontMetrics().stringWidth(strTitle);
            con.drawString(strTitle, (600 - intTitleWidth) / 2, 100);

            // Menu Options
            String[] strOptions = {
                "P. Play Game",
                "V. View Leaderboard",
                "A. Add Quiz",
                "Q. Quit"
            };

            int intRectX = 150;
            int intRectWidth = 300;
            int intRectHeight = 50;
            int intStartY = 180;
            int intGap = 20;

            con.setDrawFont(fontOption);

            for (int intI = 0; intI < strOptions.length; intI++) {
                int intY = intStartY + intI * (intRectHeight + intGap);

                // Draw option button
                con.setDrawColor(new Color(70, 130, 180)); // steel blue
                con.fillRoundRect(intRectX, intY, intRectWidth, intRectHeight, 20, 20);

                // Draw text
                con.setDrawColor(Color.WHITE);
                int intTextY = intY + 35;
                con.drawString(strOptions[intI], intRectX + 20, intTextY);
            }

            // Input Prompt
            con.setDrawFont(fontInput);
            con.setDrawColor(Color.WHITE);
            String strPrompt = "Enter your choice (P, V, A, Q): ";
            con.drawString(strPrompt, intRectX, intStartY + strOptions.length * (intRectHeight + intGap) + 40);

            con.repaint();

            // Get user input
            String strInput = con.readLine().trim().toUpperCase();

            // Handle input
            if (strInput.length() == 1) {
                char charChoice = strInput.charAt(0);
                if (charChoice == 'P') {
                    playgame game = new playgame(); // ✅ launches in a new Console window
                    game.start();
                } else if (charChoice == 'V') {
                    con.clear();
                    con.println("Viewing leaderboard...");
                    // You could add code here to show leaderboard contents if you want
                    con.println("\nPress enter to return to main menu...");
                    con.readLine();
                } else if (charChoice == 'A') {
                    con.clear();
                    con.println("Adding a new quiz...");
                    // Add quiz code could go here
                    con.println("\nPress enter to return to main menu...");
                    con.readLine();
                } else if (charChoice == 'Q') {
                    con.clear();
                    con.println("Goodbye!");
                    break;
                } else {
                    con.clear();
                    con.println("Invalid input. Please enter P, V, A, or Q.");
                    con.println("\nPress enter to continue...");
                    con.readLine();
                }
            } else {
                con.clear();
                con.println("Invalid input. Please enter P, V, A, or Q.");
                con.println("\nPress enter to continue...");
                con.readLine();
            }
        }
    }
}




