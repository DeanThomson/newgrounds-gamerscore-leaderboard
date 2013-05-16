package gamerscoreLeaderboard;

import java.text.NumberFormat;

/**
 * Model of a user.
 * @author Dean Thomson [deanthomson92@gmail.com]
 */
public class User {
    
    private String name;
    private String gamertag;
    private int prank;
    private int pscore;
    private int crank;
    private int cscore;
    
    /**
     * Initialises a new user.
     * @param name The name of the user.
     * @param gamertag The gamertag of the user.
     */
    public User(String name, String gamertag) {
        this.name = name;
        this.gamertag = gamertag;
        this.prank = 0;
        this.pscore = 0;
    }
    
    /**
     * Updates the user's name.
     * @param name The users new name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the name of the user.
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Updates the user's gamertag.
     * @param gamertag  The users new gamertag.
     */
    public void setGamertag(String gamertag) {
        this.gamertag = gamertag;
    }
    
    /**
     * Gets the gamertag of the user.
     * @return The gamertag of the user.
     */
    public String getGamertag() {
        return gamertag;
    }
    
    /**
     * Updates the previous rank of the user.
     * @param prank The previous rank of the user.
     */
    public void setPreviousRank(int prank) {
        this.prank = prank;
    }
    
    /**
     * Gets the previous rank of the user.
     * @return The previous rank of the user.
     */
    public int getPreviousRank() {
        return prank;
    }
    
    /**
     * Updates the previous score of the user.
     * @param pscore The previous score of the user.
     */
    public void setPreviousScore(int pscore) {
        this.pscore = pscore;
    }
    
    /**
     * Gets the previous score of the user.
     * @return The previous score of the user.
     */
    public int getPreviousScore() {
        return pscore;
    }
    
    /**
     * Updates the current rank of the user.
     * @param crank The current rank of the user.
     */
    public void setCurrentRank(int crank) {
        this.crank = crank;
    }
    
    /**
     * Gets the current rank of the user.
     * @return The current rank of the user.
     */
    public int getCurrentRank() {
        return crank;
    }
    
    /**
     * Updates the current score of the user.
     * @param cscore The current score of the user.
     */
    public void setCurrentScore(int cscore) {
        this.cscore = cscore;
    }
    
    /**
     * Gets the current score of the user.
     * @return The current score of the user.
     */
    public int getCurrentScore() {
        return cscore;
    }
    
    /**
     * Calculates the difference in rank since the last update.
     * @return The difference in rank since the last update.
     */
    public int getRankDiff() {
        if(prank == 0)
            return 0;
        return crank - prank;
    }
    
    /**
     * Calculates the difference in score since the last update.
     * @return The difference in score since the last update
     */
    public int getScoreDiff() {
        if(pscore == 0)
            return 0;
        return cscore - pscore;
    }
    
    /**
     * Creates a string representation of the user for use on the leaderboard.
     * @return A string representation of the user.
     */
    @Override
    public String toString() {
        String out = "";
        String score = NumberFormat.getInstance().format(cscore);
        String url = "<a href=\""+name+".newgrounds.com\">"+name+"</a>";
        String scorediff = scoreDiffToString();
        String rankdiff = rankDiffToString();
        
        return out += crank + ") " + score + " - " + url + " - " + gamertag + " " + scorediff + " | " + rankdiff;
    }
    
    /**
     * Converts the score difference to a formatted string for use in print()
     * @return The score difference as a formatted string.
     */
    private String scoreDiffToString() {
        String scorediff;
        
        if (pscore == 0) {
            scorediff = "0";
        }
        else if(getScoreDiff() > 0) {
            scorediff = "+" + getScoreDiff();
        }
        else if(getScoreDiff() == 0) {
            scorediff = "" + getScoreDiff();
        }
        else {
            scorediff = "-" + getScoreDiff()*-1;
        }
        
        return scorediff;
    }
    
    /**
     * Converts the rank difference to a formatted string for use in toString()
     * @return The rank difference as a formatted string.
     */
    private String rankDiffToString() {
        String rankdiff;
        
        if (prank == 0) {
            rankdiff = "NEW";
        }
        else if(getRankDiff() > 0) {
            rankdiff = "-" + getRankDiff();
        }
        else if(getRankDiff() == 0) {
            rankdiff = "=";
        }
        else {
            rankdiff = "+" + getRankDiff() * -1;
        }
        
        return rankdiff;
    }
    
    /**
     * Creates a string representation of the user for the top scorer list.
     * @param pos The position of the user on the top scorer list.
     * @return A string representation of the user for the top scorer list.
     */
    public String toTopScorerString(int pos) {
        return "#" + pos + " - " + "<a href=\""+name+".newgrounds.com\">"+name+"</a>" + " - " + gamertag + " " + scoreDiffToString();
    }
    
    /**
     * Creates a string representation of the user's xbox.xom profile URL.
     * @return A string representation of the user's xbox.com profile URL.
     */
    public String getXboxURL() {
        return "https://live.xbox.com/en-GB/Profile?gamertag=" + gamertag.replaceAll(" ", "%20");
    }
}