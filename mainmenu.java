import arc.*;
import java.awt.Color;
import java.awt.Font;

public class mainmenu {
    public static void main(String[] args) {
        Console con = new Console("Multiple Choice Game", 800, 700);
        displayMenu(con);
    }

    public static void displayMenu(Console con) {
        Font fontTitle = new Font("Arial Black", Font.BOLD, 36);
        Font fontOption = new Font("Arial", Font.PLAIN, 24);
        Font fontInput = new Font("Arial", Font.ITALIC, 20);

        while (true) {
            con.clear();

            con.setDrawColor(new Color(30, 30, 60));
            con.fillRect(0, 0, 800, 700);

            con.setDrawFont(fontTitle);
            con.setDrawColor(new Color(255, 215, 0));
            String strTitle = "MULTIPLE CHOICE GAME";
            int intTitleWidth = con.getDrawFontMetrics().stringWidth(strTitle);
            con.drawString(strTitle, (800 - intTitleWidth) / 2, 100);

            String[] strOptions = {
                "P. Play Game",
                "V. View Leaderboard",
                "A. Add Quiz",
                "H. Help",
                "Q. Quit"
            };

            int intStartY = 180;
            int intRectX = 250;
            int intRectWidth = 300;
            int intRectHeight = 50;
            int intGap = 20;

            con.setDrawFont(fontOption);
            for (int i = 0; i < strOptions.length; i++) {
                int intY = intStartY + i * (intRectHeight + intGap);
                con.setDrawColor(new Color(70, 130, 180));
                con.fillRoundRect(intRectX, intY, intRectWidth, intRectHeight, 20, 20);
                con.setDrawColor(Color.WHITE);
                con.drawString(strOptions[i], intRectX + 30, intY + 35); 
            }

            con.setDrawFont(fontInput);
            con.setDrawColor(Color.WHITE);
            con.drawString("Enter your choice (P, V, A, H, Q):", intRectX, intStartY + strOptions.length * (intRectHeight + intGap) + 40);
            con.repaint();

            String strInput = con.readLine().trim().toUpperCase();
            if (strInput.length() == 1) {
                char chrChoice = strInput.charAt(0);
                if (chrChoice == 'P') {
                    playgame game = new playgame();
                    game.start();
                } else if (chrChoice == 'V') {
                    con.clear();
                    con.println("Viewing leaderboard...");
                    con.println("\nPress enter to return to main menu...");
                    con.readLine();
                } else if (chrChoice == 'A') {
                    addquiz quizAdder = new addquiz(); 
                    quizAdder.addQuiz();               
                } else if (chrChoice == 'H') {
                    con.clear();
                    con.setDrawColor(new Color(30, 30, 60));
                    con.fillRect(0, 0, 800, 700);

                    con.setDrawFont(fontTitle);
                    con.setDrawColor(new Color(255, 215, 0));
                    String helpTitle = "MULTIPLE CHOICE GAME HELP";
                    int helpTitleWidth = con.getDrawFontMetrics().stringWidth(helpTitle);
                    con.drawString(helpTitle, (800 - helpTitleWidth) / 2, 100);

                    con.setDrawFont(fontOption);
                    con.setDrawColor(Color.WHITE);
                    int helpY = 160;
                    con.drawString("- Choose P to play the game. Select a quiz you want to take, then once completed your name will be added to the leaderboard.", 50, helpY);
                    con.drawString("- Choose V to view the leaderboard.", 50, helpY + 40);
                    con.drawString("- Choose A to add a new quiz.", 50, helpY + 80);
                    con.drawString("- Choose H to view this help screen.", 50, helpY + 120);
                    con.drawString("- Choose Q to quit the game.", 50, helpY + 160);
                    con.drawString("- (Try the secret S option for a surprise!)", 50, helpY + 200);

                    con.setDrawFont(fontInput);
                    con.setDrawColor(Color.WHITE);
                    con.drawString("Press Enter to return to the main menu.", 50, helpY + 260);
                    con.repaint();

                    con.readLine();
                } else if (chrChoice == 'S') {
                    con.clear();
                    con.println("Here's a joke for you:");
                    con.println("Why don't scientists trust atoms?");
                    con.println("Because they make up everything!");
                    con.println("\nPress Enter to return to main menu...");
                    con.readLine();
                } else if (chrChoice == 'Q') {
                    con.clear();
                    con.println("Goodbye!");
                    break;
                } else {
                    con.clear();
                    con.println("Invalid input. Please enter P, V, A, H, or Q.");
                    con.println("\nPress enter to continue...");
                    con.readLine();
                }
            } else {
                con.clear();
                con.println("Invalid input. Please enter P, V, A, H, or Q.");
                con.println("\nPress enter to continue...");
                con.readLine();
            }
        }
    }
}










