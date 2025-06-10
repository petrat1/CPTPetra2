import arc.Console;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;

public class addquiz {
	public Console con;

	public addquiz() {
		con = new Console("Add Quiz", 800, 700); 
    }

	public void addQuiz() {
		setGraphics();
		drawTitle("ADD A NEW QUIZ");

		con.setDrawFont(new Font("Arial", Font.PLAIN, 18));
		con.setDrawColor(Color.WHITE);
		con.println("Enter a name for the new quiz (example: students):");
		String strQuizName = con.readLine().trim().toLowerCase();

		String strFileName = strQuizName + ".txt";

		try (
			PrintWriter quizFile = new PrintWriter(new FileWriter(strFileName));
			PrintWriter masterFile = new PrintWriter(new FileWriter("quizzes.txt", true))
        ) {
			masterFile.println(strQuizName);

			while (true) {
				setGraphics();
				drawTitle("ADD A NEW QUIZ");
				con.setDrawFont(new Font("Arial", Font.PLAIN, 18));
				con.setDrawColor(Color.WHITE);

				con.println("\nEnter a question (or type 'stop' to finish):");
                String strQuestion = con.readLine();
                if (strQuestion.equalsIgnoreCase("stop")) {
                    break;
                }

                con.println("Enter answer A:");
                String strA = con.readLine();

                con.println("Enter answer B:");
                String strB = con.readLine();

                con.println("Enter answer C:");
                String strC = con.readLine();

                con.println("Enter answer D:");
                String strD = con.readLine();

                con.println("Enter the correct answer letter (A, B, C, or D):");
                String strCorrect = con.readLine().trim().toUpperCase();

                quizFile.println(strQuestion);
                quizFile.println(strA);
                quizFile.println(strB);
                quizFile.println(strC);
                quizFile.println(strD);
                quizFile.println(strCorrect);
            }

            setGraphics();
            drawTitle("QUIZ SAVED");
            con.setDrawFont(new Font("Arial", Font.PLAIN, 18));
            con.setDrawColor(Color.WHITE);
            con.println("\n Quiz saved as: " + strFileName);
            con.println("Quiz name also added to quizzes.txt!");

        } catch (IOException e) {
            con.println("Error writing to file: " + e.getMessage());
        }

        con.println("\nPress Enter to return to main menu.");
        con.readLine();
    }

    public void setGraphics() {
        con.setDrawColor(new Color(30, 30, 60)); 
        con.fillRect(0, 0, 800, 700);
    }

    public void drawTitle(String strTitle) {
        Font fontTitle = new Font("Arial Black", Font.BOLD, 36);
        con.setDrawFont(fontTitle);
        con.setDrawColor(new Color(255, 215, 0));
        int titleWidth = con.getDrawFontMetrics().stringWidth(strTitle);
        con.drawString(strTitle, (800 - titleWidth) / 2, 80);
    }
}


