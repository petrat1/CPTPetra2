import arc.*;
import java.util.*;
import java.io.*;  // keep this for PrintWriter and FileWriter only

public class playgame {
    public arc.Console con;  // explicitly use arc.Console here
    public Random rand = new Random();

    // Constructor takes the Console object created elsewhere (like main menu)
    public playgame(arc.Console con) {
        this.con = con;
    }

    public void start() {
        con.clear();
        con.println("Enter your name:");
        String strPlayerName = con.readLine();

        // Quizzes available (matching your file names)
        String[] strQuizzes = {"countries", "famousmovies", "foodandcooking", "inventors", "space"};
        con.println("\nAvailable quizzes:");
        for (int intI = 0; intI < strQuizzes.length; intI++) {
            con.println((intI + 1) + ". " + strQuizzes[intI]);
        }

        con.println("\nType the name of the quiz file you want to play (ex: countries):");
        String strChosenQuiz = con.readLine().trim().toLowerCase();

        // Load quiz data from file into 2D array
        String[][] strQuiz = loadQuizFile(strChosenQuiz);
        if (strQuiz == null) {
            con.println("Error loading quiz file! Returning to main menu...");
            return;
        }

        // Add random number 1–100 into last column of each row
        for (int intI = 0; intI < strQuiz.length; intI++) {
            strQuiz[intI][6] = Integer.toString(rand.nextInt(100) + 1);
        }

        // Shuffle questions
        shuffleArray(strQuiz);

        // Play the quiz
        playQuiz(strPlayerName, strChosenQuiz, strQuiz);
    }

    public String[][] loadQuizFile(String strFileName) {
        List<String[]> listLines = new ArrayList<>();
        try {
            TextInputFile quizFile = new TextInputFile(strFileName + ".txt");

            while (true) {
                // read 6 lines for one question set
                String strQuestion = quizFile.readLine();
                String strA = quizFile.readLine();
                String strB = quizFile.readLine();
                String strC = quizFile.readLine();
                String strD = quizFile.readLine();
                String strAnswer = quizFile.readLine();

                // Stop if any line is null (EOF or incomplete question)
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
                strRow[6] = "";  // placeholder for random number

                listLines.add(strRow);
            }
            quizFile.close();

        } catch (Exception e) {
            return null;
        }

        return listLines.toArray(new String[listLines.size()][]);
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

            int intPercentSoFar = (intI == 0) ? 0 : (intScore * 100 / intI);
            con.println("Player: " + strPlayerName + "   Quiz: " + strQuizName + "   Score: " + intPercentSoFar + "%\n");

            con.println("Q" + (intI + 1) + ": " + strQuiz[intI][0]);
            con.println("A. " + strQuiz[intI][1]);
            con.println("B. " + strQuiz[intI][2]);
            con.println("C. " + strQuiz[intI][3]);
            con.println("D. " + strQuiz[intI][4]);

            con.println("\nEnter your answer (A, B, C, D):");
            String strAnswer = con.readLine().trim().toUpperCase();

            if (strAnswer.equals(strQuiz[intI][5].toUpperCase())) {
                con.println("Correct!");
                intScore++;
            } else {
                con.println("Incorrect! Correct answer was: " + strQuiz[intI][5]);
            }

            con.println("\nPress Enter to continue...");
            con.readLine();
        }

        int intPercent = (intScore * 100) / intTotalQuestions;
        con.clear();
        con.println("Quiz finished!\nPlayer: " + strPlayerName + "\nQuiz: " + strQuizName + "\nScore: " + intPercent + "%");

        saveResultToLeaderboard(strPlayerName, strQuizName, intPercent);

        con.println("\nPress Enter to return to main menu...");
        con.readLine();
    }

    public void saveResultToLeaderboard(String strPlayerName, String strQuizName, int intPercent) {
        try (PrintWriter out = new PrintWriter(new FileWriter("leaderboard.txt", true))) {
            out.println(strPlayerName + "," + strQuizName + "," + intPercent + "%");
        } catch (IOException e) {
            con.println("Error saving leaderboard data.");
        }
    }
}





