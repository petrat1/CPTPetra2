import arc.Console;
import arc.TextInputFile;
import java.awt.Color;
import java.awt.Font;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;

public class playgame {
    public Console con;
    public Random rand = new Random();

    public playgame() {
        con = new Console("Play Game", 800, 700); 
    }

    public void start() {
        setGraphics();

        con.println("Enter your name:");
        String strPlayerName = con.readLine();

        String[] strQuizzes = {"countries", "famousmovies", "foodandcooking", "inventors", "space"};
        con.println("\nAvailable quizzes:");
        for (int intI = 0; intI < strQuizzes.length; intI++) {
            con.println((intI + 1) + ". " + strQuizzes[intI]);
        }

        con.println("\nType the name of the quiz file you want to play (ex: countries):");
        String strChosenQuiz = con.readLine().trim().toLowerCase();

        String[][] strQuiz = loadQuizFile(strChosenQuiz);
        if (strQuiz == null) {
            con.println("Error loading quiz file! Returning to main menu...");
            return;
        }

        for (int intI = 0; intI < strQuiz.length; intI++) {
            strQuiz[intI][6] = Integer.toString(rand.nextInt(100) + 1);
        }

        shuffleArray(strQuiz);
        playQuiz(strPlayerName, strChosenQuiz, strQuiz);
    }

    public String[][] loadQuizFile(String strFileName) {
        java.util.List<String[]> listLines = new ArrayList<>();

        try {
            TextInputFile quizFile = new TextInputFile(strFileName + ".txt");

            while (true) {
                String strQuestion = quizFile.readLine();
                String strA = quizFile.readLine();
                String strB = quizFile.readLine();
                String strC = quizFile.readLine();
                String strD = quizFile.readLine();
                String strAnswer = quizFile.readLine();

                if (strQuestion == null || strA == null || strB == null || strC == null || strD == null || strAnswer == null) {
                    break;
                }

                String[] strRow = new String[7];
                strRow[0] = strQuestion;
                strRow[1] = strA;
                strRow[2] = strB;
                strRow[3] = strC;
                strRow[4] = strD;
                strRow[5] = strAnswer;
                strRow[6] = "";

                listLines.add(strRow);
            }

        } catch (Exception e) {
            return null;
        }

        return listLines.toArray(new String[0][0]);
    }

    public void shuffleArray(String[][] arr) {
        for (int intI = arr.length - 1; intI > 0; intI--) {
            int intJ = rand.nextInt(intI + 1);
            String[] strTemp = arr[intI];
            arr[intI] = arr[intJ];
            arr[intJ] = strTemp;
        }
    }

    public void playQuiz(String strPlayerName, String strQuizName, String[][] strQuiz) {
        int intScore = 0;
        int intTotalQuestions = strQuiz.length;

        for (int intI = 0; intI < intTotalQuestions; intI++) {
            con.clear();
            setGraphics();

            int intPercentSoFar = (intI == 0) ? 0 : (intScore * 100 / intI);
            con.setDrawColor(Color.WHITE);
            con.setDrawFont(new Font("Arial", Font.PLAIN, 20));
            con.drawString("Player: " + strPlayerName + "   Quiz: " + strQuizName + "   Score: " + intPercentSoFar + "%", 20, 40);

            con.setDrawFont(new Font("Arial", Font.PLAIN, 18));
            con.drawString("Q" + (intI + 1) + ": " + strQuiz[intI][0], 20, 100);
            con.drawString("A. " + strQuiz[intI][1], 20, 140);
            con.drawString("B. " + strQuiz[intI][2], 20, 180);
            con.drawString("C. " + strQuiz[intI][3], 20, 220);
            con.drawString("D. " + strQuiz[intI][4], 20, 260);

            con.setDrawFont(new Font("Arial", Font.ITALIC, 18));
            con.drawString("Enter your answer (A, B, C, D):", 20, 310);
            String strAnswer = con.readLine().trim().toUpperCase();

            con.clear();
            setGraphics();
            con.setDrawColor(Color.WHITE);
            con.setDrawFont(new Font("Arial", Font.PLAIN, 20));
            con.drawString("Player: " + strPlayerName + "   Quiz: " + strQuizName + "   Score: " + intPercentSoFar + "%", 20, 40);

            con.setDrawFont(new Font("Arial", Font.PLAIN, 18));
            if (strAnswer.equals(strQuiz[intI][5].toUpperCase())) {
                con.drawString("Correct!", 20, 100);
                intScore++;
            } else {
                con.drawString("Incorrect! Correct answer was: " + strQuiz[intI][5], 20, 100);
            }

            con.setDrawFont(new Font("Arial", Font.ITALIC, 18));
            con.drawString("Press Enter to continue...", 20, 150);
            con.readLine();
        }

        int intPercent = (intScore * 100) / intTotalQuestions;
        con.clear();
        setGraphics();
        con.setDrawColor(Color.WHITE);
        con.setDrawFont(new Font("Arial", Font.PLAIN, 20));
        con.drawString("Quiz finished!", 20, 40);
        con.drawString("Player: " + strPlayerName, 20, 80);
        con.drawString("Quiz: " + strQuizName, 20, 120);
        con.drawString("Score: " + intPercent + "%", 20, 160);

        saveResultToLeaderboard(strPlayerName, strQuizName, intPercent);

        con.setDrawFont(new Font("Arial", Font.ITALIC, 18));
        con.drawString("Press Enter to return to main menu...", 20, 220);
        con.readLine();
    }

    public void saveResultToLeaderboard(String strPlayerName, String strQuizName, int intPercent) {
        try (PrintWriter out = new PrintWriter(new FileWriter("leaderboard.txt", true))) {
            out.println(strPlayerName + "," + strQuizName + "," + intPercent + "%");
        } catch (IOException e) {
            con.println("Error saving leaderboard data.");
        }
    }

    public void setGraphics() {
        con.setDrawColor(new Color(30, 30, 60)); 
        con.fillRect(0, 0, 800, 700);
        con.setDrawColor(Color.WHITE);
        con.setDrawFont(new Font("Arial", Font.PLAIN, 18));
    }
}








