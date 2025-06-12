import arc.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

public class mainmenu {
	public static void main(String[] args) {
		Console con = new Console("Multiple Choice Game", 800, 700);
		BufferedImage imgLogo = con.loadImage("logo.png");
		displayMenu(con, imgLogo);
	}

	public static void displayMenu(Console con, BufferedImage imgLogo) {
		Font fontTitle = new Font("Arial Black", Font.BOLD, 36);
		Font fontOption = new Font("Arial", Font.PLAIN, 24);
		Font fontInput = new Font("Arial", Font.ITALIC, 20);

		while (true) {
			con.clear();

			con.setDrawColor(new Color(30, 30, 60));
			con.fillRect(0, 0, 800, 700);
			con.drawImage(imgLogo, -50, 90);

			con.setDrawFont(fontTitle);
			con.setDrawColor(new Color(255, 215, 0));
			String strTitle = "MULTIPLE CHOICE GAME";
			int intTitleWidth = con.getDrawFontMetrics().stringWidth(strTitle);
			con.drawString(strTitle, (800 - intTitleWidth) / 2, 100);

			String[] strOptions = {
				"(P)lay Game",
				"(V)iew Leaderboard",
				"(A)dd Quiz",
				"(H)elp",
				"(Q)uit"
			};

			int intStartY = 200;
			int intRectX = 250;
			int intRectWidth = 300;
			int intRectHeight = 50;
			int intGap = 20;

			con.setDrawFont(fontOption);
			for (int intI = 0; intI < strOptions.length; intI++) {
				int intY = intStartY + intI * (intRectHeight + intGap);
				con.setDrawColor(new Color(70, 130, 180));
				con.fillRoundRect(intRectX, intY, intRectWidth, intRectHeight, 20, 20);
				con.setDrawColor(Color.WHITE);
				con.drawString(strOptions[intI], intRectX + 20, intY + 10); 
			}

			con.setDrawFont(fontInput);
			con.setDrawColor(Color.WHITE);
			con.drawString("Enter your choice (P, V, A, H, Q, S):", intRectX, intStartY + strOptions.length * (intRectHeight + intGap) + 40);
			con.repaint();

			String strInput = con.readLine().trim().toUpperCase();
			if (strInput.length() == 1) {
				char chrChoice = strInput.charAt(0);
				if (chrChoice == 'P') {
					playgame game = new playgame();
						game.start();
				} else if (chrChoice == 'V') {
					leaderboard.showLeaderboard(con);
				} else if (chrChoice == 'A') {
					addquiz quizAdder = new addquiz(); 
					quizAdder.addQuiz();               
				} else if (chrChoice == 'H') {
					con.clear();
					con.setDrawColor(new Color(30, 30, 60));
					con.fillRect(0, 0, 800, 700);

					con.setDrawFont(fontTitle);
					con.setDrawColor(new Color(255, 215, 0));
					String strHelpTitle = "MULTIPLE CHOICE GAME HELP";
					int intHelpTitleWidth = con.getDrawFontMetrics().stringWidth(strHelpTitle);
					con.drawString(strHelpTitle, (800 - intHelpTitleWidth) / 2, 100);

					con.setDrawFont(fontOption);
					con.setDrawColor(Color.WHITE);
					int intHelpY = 160;
					con.drawString("- Choose P to play the game.", 50, intHelpY);
					con.drawString("- Choose V to view the leaderboard.", 50, intHelpY + 40);
					con.drawString("- Choose A to add a new quiz.", 50, intHelpY + 80);
					con.drawString("- Choose Q to quit the game.", 50, intHelpY + 160);
					con.drawString("- Choose the secret S option for a surprise!", 50, intHelpY + 200);

					con.setDrawFont(fontInput);
					con.setDrawColor(Color.WHITE);
					con.drawString("Press Enter to return to the main menu.", 50, intHelpY + 260);
					con.repaint();

					con.readLine();
				} else if (chrChoice == 'S') {
					con.clear();
					con.println("Here's a joke for you:");
					con.println("Why did the strawberry cry?");
					con.println("He found himself in a jam.");
					con.println("\nPress Enter to return to the main menu...");
					con.readLine();
				} else if (chrChoice == 'Q') {
					con.clear();
					System.exit(0);
				} else {
					con.clear();
					con.println("Invalid input. Please enter P, V, A, H, S, or Q.");
					con.println("\nPress enter to continue...");
					con.readLine();
				}
			}
		}
	}
}










