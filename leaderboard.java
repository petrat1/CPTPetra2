import arc.*;
import java.awt.Color;
import java.awt.Font;

public class leaderboard {

    public static void showLeaderboard(Console conMain) {
        Console con = new Console("Leaderboard", 800, 700);

        Font fontTitle = new Font("Arial Black", Font.BOLD, 30);
        Font fontEntry = new Font("Arial", Font.PLAIN, 20);

        con.setDrawColor(new Color(25, 25, 112)); 
        con.fillRect(0, 0, 800, 700);
        con.setDrawFont(fontTitle);
        con.setDrawColor(new Color(255, 215, 0)); 
        con.drawString("LEADERBOARD", 160, 50);

        arc.TextInputFile fileRead = new arc.TextInputFile("leaderboard.txt");
        String[][] strLeaderboard = new String[100][3];
        int intCount = 0;

        while (fileRead.eof() != true) {
            String strName = fileRead.readLine();
            String strQuiz = fileRead.readLine();
            String strScore = fileRead.readLine();

            if (strName != null && strQuiz != null && strScore != null) {
                strLeaderboard[intCount][0] = strName;
                strLeaderboard[intCount][1] = strQuiz;
                strLeaderboard[intCount][2] = strScore;
                intCount++;
            }
        }

        for (int intI = 0; intI < intCount - 1; intI++) {
            for (int intJ = 0; intJ < intCount - 1; intJ++) {
                int intScore1 = Integer.parseInt(strLeaderboard[intJ][2]);
                int intScore2 = Integer.parseInt(strLeaderboard[intJ + 1][2]);

                if (intScore1 < intScore2) {
                    String strTempName = strLeaderboard[intJ][0];
                    String strTempQuiz = strLeaderboard[intJ][1];
                    String strTempScore = strLeaderboard[intJ][2];

                    strLeaderboard[intJ][0] = strLeaderboard[intJ + 1][0];
                    strLeaderboard[intJ][1] = strLeaderboard[intJ + 1][1];
                    strLeaderboard[intJ][2] = strLeaderboard[intJ + 1][2];

                    strLeaderboard[intJ + 1][0] = strTempName;
                    strLeaderboard[intJ + 1][1] = strTempQuiz;
                    strLeaderboard[intJ + 1][2] = strTempScore;
                }
            }
        }

        con.setDrawFont(fontEntry);
        con.setDrawColor(Color.WHITE);
        int intY = 120;

        for (int intI = 0; intI < intCount && intI < 5; intI++) {
            String strLine = (intI + 1) + ". " + strLeaderboard[intI][0] + " - " +
                             strLeaderboard[intI][1] + " - " +
                             strLeaderboard[intI][2] + "%";
            con.drawString(strLine, 50, intY);
            intY += 40; 
        }

        con.setDrawColor(Color.LIGHT_GRAY);
        con.repaint();
        con.sleep(100);
        con.readLine();
    }
}












