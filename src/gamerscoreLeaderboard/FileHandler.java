package gamerscoreLeaderboard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Class used for importing/exporting leaderboards from/to the users file system.
 * @author Dean Thomson [deanthomson92@gmail.com]
 */
public class FileHandler {

    public FileHandler() {
    }

    /**
     * Allows the user to select an input file.
     * @return The user selected input file.
     */
    private File getImportFile() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
        chooser.setFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);
       
        int returnVal = chooser.showOpenDialog(null);
        
        try {
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                return chooser.getSelectedFile();
            }
            else {
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    /**
     * Reads the imported file and converts the data to a Leaderboard.
     * @return The Leaderboard.
     */
    public ArrayList<User> importFile() {
        File file = getImportFile();
        if (file == null) {
            return null;
        }

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String read = br.readLine();
            ArrayList<User> leaderboard = new ArrayList();

            String name, gamertag;
            int ppos, pscore;

            while (read != null) {
                StringTokenizer st = new StringTokenizer(read, ",");
                name = st.nextToken();
                gamertag = st.nextToken();
                ppos = Integer.parseInt(st.nextToken());
                pscore = Integer.parseInt(st.nextToken());

                User user = new User(name, gamertag);
                user.setPreviousScore(pscore);
                user.setPreviousRank(ppos);

                leaderboard.add(user);
                read = br.readLine();
            }
            br.close();
            return leaderboard;
        } 
       catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    /**
     * Allows the user to select an export file.
     * @return The user selected export file.
     */
    private File getExportFile() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV file", "csv");
        chooser.setFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);
        int returnVal = chooser.showSaveDialog(null);
        try {
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                return chooser.getSelectedFile();
            }
            else {
                return null;
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid File!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    /**
     * Exports the leaderboard to a file.
     * @param leaderboard The leaderboard to export.
     * @return Returns a boolean to indicate whether or not it was a success.
     */
    public boolean exportFile(ArrayList<User> leaderboard) {
        FileOutputStream out;
        try {
            File file = getExportFile();
            
            if(file == null) return false;
            
            String fileString = file.toString();
            String extension = fileString.substring(fileString.length()-4);
            if(extension.equals(".csv")) {
                out = new FileOutputStream(file);
            }
            else {
                out = new FileOutputStream(file+".csv");
            }
            
            PrintStream ps = new PrintStream(out);
            for(User user : leaderboard) {
                String output = user.getName() + "," + user.getGamertag() + "," + user.getCurrentRank() + "," + user.getCurrentScore();
                ps.println(output);
            }
            ps.close();
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }
}
