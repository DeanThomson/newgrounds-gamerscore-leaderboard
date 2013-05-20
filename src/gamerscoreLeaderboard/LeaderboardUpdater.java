package gamerscoreLeaderboard;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Class for updating a leaderboard by fetching the users gamerscore from the web.
 * @author Dean Thomson [deanthomson92@gmail.com]
 */
public class LeaderboardUpdater extends Thread {

    private ArrayList<User> leaderboard = null;
    private ArrayList<String> failedUsers;

    public LeaderboardUpdater(ArrayList<User> leaderboard) {
        this.leaderboard = leaderboard;
    }
    
    /**
     * Starts the update process.
     * @return The updated leaderboard.
     */
    @Override
    public void run() {
        Document doc;
        for (User user : leaderboard) {
            try {
                doc = Jsoup.connect(user.getXboxURL()).timeout(10 * 1000).get();
                int score = Integer.parseInt(doc.select("div.gamerscore").last().text());
                user.setCurrentScore(score);
                System.out.println("Completed: " + user.getGamertag());
            } catch (IOException e) {
                failedUsers.add(user.getName());
            }
        }
    }

    /**
     * Used to return a list of users who could not be updated.
     * @return List of users who could not be updated.
     */
    public ArrayList<String> getFailedUsers() {
        return failedUsers;
    }
    
    public ArrayList<User> getUpdatedLeaderboard() {
        return leaderboard;
    }
    
}
