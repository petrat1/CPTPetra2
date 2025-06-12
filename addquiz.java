import arc.Console;
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

        arc.TextOutputFile fileQuiz = new arc.TextOutputFile(strFileName, true);
        arc.TextOutputFile fileQuizList = new arc.TextOutputFile("quizzes.txt", true);

        fileQuizList.println(strQuizName);

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

            fileQuiz.println(strQuestion);
            fileQuiz.println(strA);
            fileQuiz.println(strB);
            fileQuiz.println(strC);
            fileQuiz.println(strD);
            fileQuiz.println(strCorrect);
        }

        setGraphics();
        drawTitle("QUIZ SAVED");
        con.setDrawFont(new Font("Arial", Font.PLAIN, 18));
        con.setDrawColor(Color.WHITE);
        con.println("\nQuiz saved as: " + strFileName);
        con.println("Quiz name also added to quizzes.txt!");

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
        int intTitleWidth = con.getDrawFontMetrics().stringWidth(strTitle);
        con.drawString(strTitle, (800 - intTitleWidth) / 2, 80);
    }
}



