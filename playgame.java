import arc.*;
import java.awt.Color;
import java.awt.Font;
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

		con.setDrawColor(Color.WHITE);
		con.setDrawFont(new Font("Arial", Font.PLAIN, 20));
		con.drawString("Enter your name:", 280, 300);
		String strPlayerName = con.readLine();
		System.out.println("Debug: Player name entered: " + strPlayerName);


		TextInputFile fileQuizList = new TextInputFile("quizzes.txt");
		String[] strQuizzes = new String[100];
		int intQuizCount = 0;

		while (fileQuizList.eof() != true) {
			String strLine = fileQuizList.readLine();
			if (strLine != null && !strLine.equals("")) {
				strQuizzes[intQuizCount] = strLine.trim();
				intQuizCount++;
            }
        }

		setGraphics();
		con.setDrawColor(Color.WHITE);
		con.setDrawFont(new Font("Arial", Font.PLAIN, 20));
		con.drawString("Available quizzes:", 280, 100);

		for (int intI = 0; intI < intQuizCount; intI++) {
			con.drawString((intI + 1) + ". " + strQuizzes[intI], 300, 140 + intI * 30);
        }

		con.drawString("Type the name of the quiz file you want to play (ex: countries.txt):", 150, 140 + intQuizCount * 30 + 20);
		String strChosenQuiz = con.readLine().trim().toLowerCase();
		System.out.println("Debug: Chosen quiz file: " + strChosenQuiz);

		String[][] strQuiz = loadQuizFile(strChosenQuiz);
		if (strQuiz == null) {
			con.println("Error loading quiz file.");
			return;
        }

		for (int intI = 0; intI < strQuiz.length; intI++) {
			strQuiz[intI][6] = Integer.toString(rand.nextInt(100) + 1);
        }

		shuffleArray(strQuiz);
		playQuiz(strPlayerName, strChosenQuiz, strQuiz);
    }

	public String[][] loadQuizFile(String strFileName) {
		ArrayList<String[]> listLines = new ArrayList<>();
		TextInputFile fileQuiz = new TextInputFile(strFileName);

		while (fileQuiz.eof() != true) {
			String strQuestion = fileQuiz.readLine();
			String strA = fileQuiz.readLine();
            String strB = fileQuiz.readLine();
            String strC = fileQuiz.readLine();
            String strD = fileQuiz.readLine();
            String strAnswer = fileQuiz.readLine();

if(strQuestion == null || strA == null || strB == null || strC == null || strD == null || strAnswer == null){
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

        return listLines.toArray(new String[0][0]);
    }

    public void shuffleArray(String[][] strArr) {
        for (int intI = strArr.length - 1; intI > 0; intI--) {
            int intJ = rand.nextInt(intI + 1);
            String[] strTemp = strArr[intI];
            strArr[intI] = strArr[intJ];
            strArr[intJ] = strTemp;
        }
    }

    public void playQuiz(String strPlayerName, String strQuizName, String[][] strQuiz) {
        int intScore = 0;
        boolean boolCheatActive = strPlayerName.equalsIgnoreCase("statitan");
        int intTotalQuestions = strQuiz.length;

        for (int intI = 0; intI < intTotalQuestions; intI++) {
            con.clear();
            setGraphics();

            int intPercentSoFar = (intI == 0) ? (boolCheatActive ? 50 : 0) : (intScore * 100) / intI;

            con.setDrawColor(Color.WHITE);
            con.setDrawFont(new Font("Arial", Font.PLAIN, 20));
            con.drawString("Player: " + strPlayerName + "   Quiz: " + strQuizName + "   Score: " + intPercentSoFar + "%", 20, 40);

            con.setDrawFont(new Font("Arial", Font.PLAIN, 18));
            con.drawString((intI + 1) + ": " + strQuiz[intI][0], 20, 100);
            con.drawString(strQuiz[intI][1], 20, 140);
            con.drawString(strQuiz[intI][2], 20, 180);
            con.drawString(strQuiz[intI][3], 20, 220);
            con.drawString(strQuiz[intI][4], 20, 260);

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

        TextOutputFile fileLeaderboard = new TextOutputFile("leaderboard.txt", true);
        fileLeaderboard.println(strPlayerName);
        fileLeaderboard.println(strQuizName);
        fileLeaderboard.println(Integer.toString(intPercent));

        con.setDrawFont(new Font("Arial", Font.ITALIC, 18));
        con.readLine();
    }

    public void setGraphics() {
        con.setDrawColor(new Color(30, 30, 60));
        con.fillRect(0, 0, 800, 700);
    }
}






