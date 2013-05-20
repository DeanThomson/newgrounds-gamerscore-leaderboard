package gamerscoreLeaderboard;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A model of the leaderboard.
 * @author Dean Thomson [deanthomson92@gmail.com]
 */
public class Leaderboard {
    
    private ArrayList<User> leaderboard;
    
    public Leaderboard() {
        leaderboard = new ArrayList();
    }
    
    /**
     * Add a user to the leaderboard.
     * @param user The user to be added.
     */
    public void addUser(User user) {
        if(leaderboard == null)
            leaderboard = new ArrayList();
        leaderboard.add(user);
    }
    
    /**
     * Edit the details of a user on the leaderboard.
     * @param name The name of the user to edit.
     * @param newName The user's new name.
     * @param newGamertag The user's new gamertag.
     * @return Indicates whether the edit was successful.
     */
    public void editUser(String name, String newName, String newGamertag) {
        for(User user : leaderboard) {
            if(user.getName().equals(name)) {
                user.setName(newName);
                user.setGamertag(newGamertag);
                return;
            }
        }
    }
    
    /**
     * Deletes a user from the leaderboard.
     * @param name The name of the user to be deleted.
     */
    public void deleteUser(String name) {
        int i = 0;
        for(User user : leaderboard) {
            if(user.getName().equals(name)) {
                leaderboard.remove(i);
                return;
            }
            i++;
        }
    }
    
    /**
     * Returns the leaderboard.
     * @return ArrayList<User> containing the leaderboard.
     */
    public ArrayList<User> getLeaderboard() {
        return leaderboard;
    }
    
    /**
     * Formats the leaderboard and adds it to the system's clipboard.
     */
    public void toClipboard() {
        sort();
        String output = "";
        
        for(User user: leaderboard) {
            output += user.toString() + "\n";
        }
        
        output += getTopScorers();
        
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection strSel = new StringSelection(output);
        clipboard.setContents(strSel, strSel);
    }
    
    /**
     * Sorts the leaderboard.
     */
    private void sort() {
        Collections.sort(leaderboard, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return u1.getCurrentScore() - u2.getCurrentScore();
            }
        });
        
        for(int i=leaderboard.size()-1; i>=0; i--) {
            leaderboard.get(i).setCurrentRank(leaderboard.size() - i);
        }
        
        Collections.sort(leaderboard, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return u1.getCurrentRank()- u2.getCurrentRank();
            }
        });
    }
    
    /**
     * Produces a formatted string of the top scorers.
     * @return A formatted string of the top scorers.
     */
    private String getTopScorers() {
        Collections.sort(leaderboard, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return u1.getScoreDiff()- u2.getScoreDiff();
            }
        });
        
        String output = "\nTop Whores:\n";
        int pos = 1;
        for(int i=leaderboard.size()-1; i>leaderboard.size()-6; i--) {
            output += leaderboard.get(i).toTopScorerString(pos) + "\n";
            pos++;
        }
        
        return output;
    }
    
    /**
     * Loads a previous leaderboard from a user selected file. If a valid file is selected, leaderboard will be re-written.
     */
    public void load() {
        FileHandler fh = new FileHandler();
        ArrayList<User> newLeaderboard = fh.importFile();
        if(newLeaderboard != null) {
            leaderboard.clear();
            leaderboard = newLeaderboard;
        }
    }
    
    /**
     * Saves the current leaderboard to a user selected file.
     */
    public void save() {
        FileHandler fh = new FileHandler();
        fh.exportFile(leaderboard);
    }
    
    /**
     * Fetches the updates scores for the leaderboard.
     * @return An ArrayList of the names of the failed users.
     */
    public ArrayList<String> update() {
        try {
            //LeaderboardUpdater updater = new LeaderboardUpdater(leaderboard);
            //leaderboard = updater.run();
            //ArrayList<String> failedUsers = updater.getFailedUsers();
            LeaderboardUpdater updater = new LeaderboardUpdater(leaderboard);
            updater.start();
            updater.join();
            sort();
            return updater.getFailedUsers();
        } 
        catch (InterruptedException ex) {
            Logger.getLogger(Leaderboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}