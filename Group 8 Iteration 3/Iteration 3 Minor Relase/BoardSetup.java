import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.io.*;

public class BoardSetup extends JFrame implements ActionListener, MouseListener {

    private JPanel maingame, bottom_panel, titlepanel, boardpanel, userpanel, playerturn, timer, chipdeck, scoreboard_panel, robotselect, arrows;
    private Icon chip;
    private JTable scoreboard;
    private JButton savegame, exitgame, hint, startclock, resetclock, pauseclock, up, down, left, right;
    private JLabel whos_turn, which_chip, timer_label, scoreboard_label, taketurn, time, visual_option, timesup;
    private BoardGrid[][] grid;
    private int players, hour, minutes, visualoption, difficulty, pausetime, x, y, paint, targetchip, robottextx, robottexty, robottextcount, currentrobot, secondspassed;
    private String lexicon;
    private HashMap<Integer, Integer> player_score, player_time;
    private HashMap<String, Integer> computer;
    private Random rand;
    private Timer newtimer;
    private ArrayList<Integer> targetlist;
    private ArrayList<ArrayList<Integer>> rp;
    private ArrayList<RobotPosition> robotpost;
    private ArrayList<String> robottext;

    public BoardSetup(int x, int y, int players, int visualoption, int difficulty) {
        super("Ricochet Robot");
        this.setSize(3000, 1000);

        this.players = players;
        this.secondspassed = 60;
        this.visualoption = visualoption;
        this.difficulty = difficulty;
        this.x = x;
        this.y = y;
        // String created to replace the shapes with letters
        this.lexicon = "ABCDEFGHIJKLMNOPQ";
        this.targetlist = new ArrayList<>();
        this.rp = new ArrayList<>();
        this.robotpost = new ArrayList<>();
        this.robottext = new ArrayList<>();

        robottext.add("Alpha");
        robottext.add("Beta");
        robottext.add("Charlie");
        robottext.add("Delta");

        rand = new Random();

        for (int i = 1; i < 18; i++) {
            int target = rand.nextInt(17);
            while (targetlist.contains(target)) {
                target = rand.nextInt(17);
            }
            targetlist.add(target);
        }

        for (int i = 0; i < targetlist.size(); i++) {
            if (targetlist.get(i) == 0) {
                targetlist.set(i, 17);
            }
        }

        // Different hashmaps to be read and written on the scoreboard
        player_score = new HashMap<>();
        computer = new HashMap<>();
        // Hashmap to record the time each player took for their round
        player_time = new HashMap<>();

        this.targetchip = rand.nextInt(17);

        // Setting up the scoreboard table
        String[] scoreboard_header = { "Player", "Score" };
        DefaultTableModel dtm = new DefaultTableModel(scoreboard_header, 0);
        scoreboard = new JTable(dtm);
        dtm.addRow(scoreboard_header);

        // Initialising the number of computer players
        if (players == 1) {
            computer.put("Computer 1", 0);
            computer.put("Computer 2", 0);
            computer.put("Computer 3", 0);
        } else if (players == 2) {
            computer.put("Computer 1", 0);
            computer.put("Computer 2", 0);
        } else if (players == 3) {
            computer.put("Computer 1", 0);
        }

        // For each entry in the "player_score" hashmap, it will be shown in the
        // scoreboard table
        for (int i = 0; i < players; i++) {
            player_score.put(i + 1, 0);
            for (Map.Entry<Integer, Integer> entry : player_score.entrySet()) {
                dtm.addRow(new Object[] { entry.getKey(), entry.getValue() });
            }
            player_score.clear();
        }
        for (Map.Entry<String, Integer> entry : computer.entrySet()) {
            dtm.addRow(new Object[] { entry.getKey(), entry.getValue() });
        }

        for (int i = 0; i < 4; i++) {
            player_time.put((i + 1), 0);
        }

        // All JPanels are declared here
        boardpanel = new JPanel();
        boardpanel.setLayout(new GridLayout(x, y));
        userpanel = new JPanel();
        bottom_panel = new JPanel();
        maingame = new JPanel();
        titlepanel = new JPanel();
        playerturn = new JPanel();
        timer = new JPanel();
        chipdeck = new JPanel();
        scoreboard_panel = new JPanel();
        // "chip" is declared as an instance of class "Icon" so that shapes and colours
        // and be added
        chip = new Icon(targetchip);
        chip.setPreferredSize(new Dimension(100, 100));
        robotselect = new JPanel();
        robotselect.setLayout(new GridLayout(0, 4));
        arrows = new JPanel();
        arrows.setLayout(new GridLayout(0, 4));

        // JLabels are declared here
        whos_turn = new JLabel("Player 1's turn");
        whos_turn.setFont(new Font("Helvetica", Font.BOLD, 20));
        whos_turn.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        whos_turn.setHorizontalAlignment(SwingConstants.CENTER);
        which_chip = new JLabel("Chip Deck");
        timer_label = new JLabel("Timer");
        scoreboard_label = new JLabel("Scoreboard");
        taketurn = new JLabel("Take a turn");
        // Set the "time" JLabel to display the time for each round
        String countdown = secondsToString(60);
        time = new JLabel(countdown);
        timesup = new JLabel();

        // Setting random letter as the destination chip
        int i = rand.nextInt(lexicon.length());
        String s = Character.toString(lexicon.charAt(i));

        visual_option = new JLabel(s);

        hint = new JButton("Hint");
        savegame = new JButton("Save");
        exitgame = new JButton("Exit");
        startclock = new JButton("Start");
        pauseclock = new JButton("Pause");
        resetclock = new JButton("Reset");

        // New JButtons for the direction control
        up = new JButton("Up");
        up.setSize(10, 10);
        down = new JButton("Down");
        down.setSize(10, 10);
        left = new JButton("Left");
        left.setSize(10, 10);
        right = new JButton("Right");
        right.setSize(10, 10);

        hint.addActionListener(this);
        savegame.addActionListener(this);
        exitgame.addActionListener(this);
        startclock.addActionListener(this);
        pauseclock.addActionListener(this);
        resetclock.addActionListener(this);

        // Setting up the four robot pieces with randomised x and y coordinates
        for (int robot = 0; robot < 4; robot++) {
            ArrayList<Integer> robotp = new ArrayList<>();
            int robotx = rand.nextInt(x);
            int roboty = rand.nextInt(y);

            robotp.add(robotx);
            robotp.add(roboty);

            rp.add(robotp);
        }

        for (int r = 0; r < rp.size(); r++) {
            int robotx = rp.get(r).get(0);
            int roboty = rp.get(r).get(1);

            RobotPosition robotpos = new RobotPosition(robotx, roboty);
            robotpost.add(robotpos);
        }

        // If "Text" option is chosen, robot pieces will be words instead of colored pieces
        if (visualoption == 0) {
            for (int column = 0; column < 4; column++) {
                RobotPosition temp = new RobotPosition(robotpost.get(column).returnrobotx(),
                        robotpost.get(column).returnroboty());
                temp.colorrobottext(column);
                temp.addMouseListener(this);
                robotselect.add(temp);
            }
        } else if (visualoption == 1) {
            for (int column = 0; column < 4; column++) {
                RobotPosition temp = new RobotPosition(robotpost.get(column).returnrobotx(),
                        robotpost.get(column).returnroboty());
                temp.colourrobot(column);
                temp.addMouseListener(this);
                robotselect.add(temp);
            }
        }

        // Setting up the main game board using a 2D array instance of the class
        // "BoardGrid"
        grid = new BoardGrid[x][y];
        // Goes through the first if statement if "Simple Board" is chosen
        if (difficulty == 0) {
            for (int column = 0; column < x; column++) {
                for (int row = 0; row < y; row++) {
                    grid[column][row] = new BoardGrid(x, y, column, row);
                    if (visualoption == 0) { // "Text" option displays robots in word form
                        if (column == robotpost.get(0).returnrobotx() && row == robotpost.get(0).returnroboty()) {
                            grid[column][row].setrobottext(1);
                        } else if (column == robotpost.get(1).returnrobotx()
                                && row == robotpost.get(1).returnroboty()) {
                            grid[column][row].setrobottext(2);
                        } else if (column == robotpost.get(2).returnrobotx()
                                && row == robotpost.get(2).returnroboty()) {
                            grid[column][row].setrobottext(3);
                        } else if (column == robotpost.get(3).returnrobotx()
                                && row == robotpost.get(3).returnroboty()) {
                            grid[column][row].setrobottext(4);
                        }
                    } else if (visualoption == 1) { // "Color" option displays robots in colored graphics form
                        if (column == robotpost.get(0).returnrobotx() && row == robotpost.get(0).returnroboty()) {
                            grid[column][row].setrobot(18);
                        } else if (column == robotpost.get(1).returnrobotx()
                                && row == robotpost.get(1).returnroboty()) {
                            grid[column][row].setrobot(19);
                        } else if (column == robotpost.get(2).returnrobotx()
                                && row == robotpost.get(2).returnroboty()) {
                            grid[column][row].setrobot(20);
                        } else if (column == robotpost.get(3).returnrobotx()
                                && row == robotpost.get(3).returnroboty()) {
                            grid[column][row].setrobot(21);
                        }
                    } else { // "Text and Color" option displays robots in both colored and word form
                        if (column == robotpost.get(0).returnrobotx() && row == robotpost.get(0).returnroboty()) {
                            grid[column][row].setrobottext(1);
                            grid[column][row].setrobot(18);
                        } else if (column == robotpost.get(1).returnrobotx()
                                && row == robotpost.get(1).returnroboty()) {
                            grid[column][row].setrobottext(2);
                            grid[column][row].setrobot(19);
                        } else if (column == robotpost.get(2).returnrobotx()
                                && row == robotpost.get(2).returnroboty()) {
                            grid[column][row].setrobottext(3);
                            grid[column][row].setrobot(20);
                        } else if (column == robotpost.get(3).returnrobotx()
                                && row == robotpost.get(3).returnroboty()) {
                            grid[column][row].setrobottext(4);
                            grid[column][row].setrobot(21);
                        }
                    }
                    grid[column][row].backgroundSet(visualoption, targetlist);
                    grid[column][row].addMouseListener(this);

                    boardpanel.add(grid[column][row]);
                }
            }
        } else { // Goes through this statement if "Complex Board" is chosen
            for (int column = 0; column < x; column++) {
                for (int row = 0; row < y; row++) {
                    grid[column][row] = new BoardGrid(x, y, column, row);
                    grid[column][row].backgroundSetComplex();

                    boardpanel.add(grid[column][row]);
                }
            }
        }

        // The top part of the main game frame that gives player(s) options to ask for
        // hints, save game, or exit game
        titlepanel.setPreferredSize(new Dimension(3000, 100));
        titlepanel.setLayout(new FlowLayout());
        titlepanel.add(taketurn);
        titlepanel.add(hint);
        titlepanel.add(savegame);
        titlepanel.add(exitgame);

        // Panel that shows the destination chip or letter
        chipdeck.setLayout(new GridLayout(3, 1));
        chipdeck.add(which_chip);
        chipdeck.add(chip);
        chipdeck.add(visual_option);

        // If "Text" option is chosen, the chip display will be hidden
        if (visualoption == 0) {
            chip.setVisible(false);
        } else if (visualoption == 1) { // If "Color" option is chosen, the letter display will be hidden
            visual_option.setVisible(false);
        }

        // Setting up the timer part
        timer.setLayout(new GridLayout(5, 1));
        timer.add(timer_label);
        timer.add(time);
        timer.add(startclock);
        timer.add(pauseclock);
        timer.add(resetclock);

        arrows.add(up);
        arrows.add(down);
        arrows.add(left);
        arrows.add(right);

        playerturn.setLayout(new GridLayout(3, 1));
        playerturn.add(whos_turn);
        playerturn.add(robotselect);
        playerturn.add(arrows);

        // Scoreboard
        scoreboard_panel.setLayout(new GridLayout(2, 1));
        scoreboard_panel.add(scoreboard_label);
        scoreboard_panel.add(scoreboard);

        // The information panel of the game
        userpanel.setLayout(new GridLayout(4, 1, 20, 20));
        userpanel.add(playerturn);
        userpanel.add(chipdeck);
        userpanel.add(timer);
        userpanel.add(scoreboard_panel);

        // The main game panel that features the board and the player information
        bottom_panel.setLayout(new BoxLayout(bottom_panel, BoxLayout.X_AXIS));
        bottom_panel.add(boardpanel);
        bottom_panel.add(userpanel);

        // Setting up the full frame
        maingame.setLayout(new BoxLayout(maingame, BoxLayout.Y_AXIS));
        maingame.add(titlepanel);
        maingame.add(bottom_panel);

        getContentPane().add(maingame);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    // Converting seconds to "MM:ss" format
    private String secondsToString(int pTime) {
        final int min = pTime / 60;
        final int sec = pTime - (min * 60);

        final String strMin = placeZeroIfNeede(min);
        final String strSec = placeZeroIfNeede(sec);
        return String.format("%s:%s", strMin, strSec);
    }

    // Properly format the minute string with leading zeroes if needed
    private String placeZeroIfNeede(int number) {
        return (number >= 10) ? Integer.toString(number) : String.format("0%s", Integer.toString(number));
    }

    public void actionPerformed(ActionEvent e) {
        Object click = e.getSource();

        if (click.equals(startclock)) {
            // Set up a swing timer for the time countdown for the round with one second
            // delay
            Container cont = this.getContentPane();
            newtimer = new Timer(1000, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent aev) {
                    // For each second, the "time" JLabel will update itself 
                    time.setText(secondsToString(secondspassed));
                    secondspassed--;
                    if (secondspassed == -1) { // Displays a message indicating that the time is up and changes the player's turn
                        newtimer.stop();
                        JOptionPane.showMessageDialog(cont, "Time's Up!");
                        time.setText(secondsToString(60));
                        for (int i = 0; i < 5; i++) {
                            String s = scoreboard.getValueAt(i, 0).toString();
                            String next = scoreboard.getValueAt(i + 1, 0).toString();
                            if (whos_turn.getText().equals("Player 1's turn") && s.equals("1") && next.equals("2")) {
                                whos_turn.setText("Player 2's turn");
                            }
                            else if(whos_turn.getText().equals("Player 2's turn") && s.equals("2") && next.equals("3")){
                                whos_turn.setText("Player 3's turn");
                            }
                            else if(whos_turn.getText().equals("Player 3's turn") && s.equals("3") && next.equals("4")){
                                whos_turn.setText("Player 4's turn");
                            }
                        }
                    }
                }
            });
            newtimer.start();
        } else if (click.equals(resetclock)) { // If "Reset" is chosen, timer will stop and goes back to 01:00
                                               // Changes the player's turn 
            newtimer.stop();
            time.setText(secondsToString(60));
            for (int column = 0; column < x; column++) {
                for (int row = 0; row < y; row++) {
                    if (!grid[column][row].getBackground().equals(Color.WHITE)) {
                        grid[column][row].setBackground(Color.WHITE);
                    }
                }
            }
            for (int i = 0; i < 5; i++) {
                String s = scoreboard.getValueAt(i, 0).toString();
                String next = scoreboard.getValueAt(i + 1, 0).toString();
                if (whos_turn.getText().equals("Player 1's turn") && s.equals("1") && next.equals("2")) {
                    whos_turn.setText("Player 2's turn");
                }
                else if(whos_turn.getText().equals("Player 2's turn") && s.equals("2") && next.equals("3")){
                    whos_turn.setText("Player 3's turn");
                }
                else if(whos_turn.getText().equals("Player 3's turn") && s.equals("3") && next.equals("4")){
                    whos_turn.setText("Player 4's turn");
                }
                else if(whos_turn.getText().equals("Player 4's turn") && scoreboard.getValueAt(1,0).toString().equals("1")){
                    // Changes back to Player 1's turn and allocates a new target chip
                    whos_turn.setText("Player 1's turn");
                    int newtargetchip = rand.nextInt(17);
                    while(newtargetchip == this.targetchip){
                        newtargetchip = rand.nextInt(17);
                    }
                    this.targetchip = newtargetchip;
                    chip = new Icon(targetchip);
                    chip.setPreferredSize(new Dimension(100,100));
                }
            }
            

        } else if (click.equals(pauseclock)) { // If "Pause" is chosen, timer will stop and the system will record the time and record it to the current player
            newtimer.stop();
            int finaltime = Integer.parseInt(time.getText().substring(3, 5));
            secondspassed = finaltime;
            if (whos_turn.getText().equals("Player 1's turn")) {
                player_time.replace(1, finaltime);
            } else if (whos_turn.getText().equals("Player 2's turn")) {
                player_time.replace(2, finaltime);
            } else if (whos_turn.getText().equals("Player 3's turn")) {
                player_time.replace(3, finaltime);
            } else {
                player_time.replace(4, finaltime);
            }

        } else if (click.equals(savegame)) { // Saves sections which contains data used to resume the game
            newtimer.stop();
            File file = new File("targetchip.txt");
            try { // Saves the target chip data
                FileOutputStream fos = new FileOutputStream(file);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

                bw.write(Integer.toString(targetchip));
                bw.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i<targetlist.size(); i++){
                sb.append(targetlist.get(i));
            }
            String savestring = sb.toString();
            File file1 = new File("targetlist.txt");
            try{ // Saves the icon in form of list
                FileOutputStream fos1 = new FileOutputStream(file1);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos1));

                bw.write(savestring);
                bw.close();
            }
            catch (Exception e1){
                e1.printStackTrace();
            }

            File file2 = new File("robotposition.txt");
            try{ // Saves the current position for all four robot
                FileOutputStream fos2 = new FileOutputStream(file2);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos2));

                for(int i = 0; i<robotpost.size(); i++){
                    StringBuilder sb1 = new StringBuilder();
                    sb1.append(robotpost.get(i).returnrobotx() + "," + robotpost.get(i).returnroboty());
                    bw.write(sb1.toString());
                    bw.newLine();
                }
                bw.close();
            } catch(Exception e1){
                e1.printStackTrace();
            }

            File file3 = new File("scoreboard.txt");
            try{ // Saves the current state of the scoreboard
                FileOutputStream fos3 = new FileOutputStream(file3);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos3));

                for(int i = 1; i < 5; i++){
                    StringBuilder sb1 = new StringBuilder();
                    sb1.append(scoreboard.getValueAt(i, 0).toString() + "," + scoreboard.getValueAt(i,1).toString());
                    bw.write(sb1.toString());
                    bw.newLine();
                }
                bw.close();
            }catch(Exception e1){
                e1.printStackTrace();
            }

            File file4 = new File("timer.txt");
            try{ // Saves the remaining time when the game is saved 
                FileOutputStream fos4 = new FileOutputStream(file4);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos4));

                String remainingtime = time.getText().substring(0, 2);
                if(remainingtime.equals("00")){
                    remainingtime = time.getText().substring(3,5);
                }
                else if(remainingtime.equals("01")){
                    remainingtime = "60";
                }
                bw.write(remainingtime);
                bw.close();
            }catch(Exception e1){
                e1.printStackTrace();
            }

            File file5 = new File("currentplayer.txt");
            try{ // Saves the current player data 
                FileOutputStream fos5 = new FileOutputStream(file5);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos5));

                bw.write(whos_turn.getText());
                bw.close();
            }catch(Exception e1){
                e1.printStackTrace();
            }

            File file6 = new File("playertime.txt");
            try{ // Saves the playertime HashMap, recording the time used for each player before saving 
                FileOutputStream fos6 = new FileOutputStream(file6);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos6));

                for(Map.Entry<Integer, Integer> entry: player_time.entrySet()){
                    StringBuilder sb1 = new StringBuilder();
                    sb1.append(entry.getKey() + "," + entry.getValue());
                    bw.write(sb1.toString());
                    bw.newLine();
                }
                bw.close();
            }catch(Exception e1){
                e1.printStackTrace();
            }

            File file7 = new File("selections.txt");
            try{ // Saves the selected visual option and difficulty 
                FileOutputStream fos7 = new FileOutputStream(file7);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos7));

                StringBuilder sb1 = new StringBuilder();
                sb1.append(visualoption + "," + difficulty);
                bw.write(sb1.toString());
                bw.close();
            }catch(Exception e1){
                e1.printStackTrace();
            }
            
            // Displays a message prompting users to continue or exit the game
            int cont = JOptionPane.showConfirmDialog(this.getContentPane(), "Saved, continue game?", "Game Saved", JOptionPane.YES_NO_OPTION);
            if(cont == JOptionPane.NO_OPTION){
                JOptionPane.showMessageDialog(this.getContentPane(), "Goodbye");
                System.exit(0);
            }
            else{
                newtimer.start();
            }
        }
        else if(click.equals(exitgame)){ // Gives user the option to save the game before exiting
            newtimer.stop();
            int save = JOptionPane.showConfirmDialog(this.getContentPane(), "Do you want to save the game before exiting?", "Exit Game", JOptionPane.YES_NO_OPTION);
            if(save == JOptionPane.YES_OPTION){
                File file = new File("targetchip.txt");
                try { // Saves the target chip data
                    FileOutputStream fos = new FileOutputStream(file);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
    
                    bw.write(Integer.toString(targetchip));
                    bw.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
    
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i<targetlist.size(); i++){
                    sb.append(targetlist.get(i));
                }
                String savestring = sb.toString();
                File file1 = new File("targetlist.txt");
                try{ // Saves the icon in form of list
                    FileOutputStream fos1 = new FileOutputStream(file1);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos1));
    
                    bw.write(savestring);
                    bw.close();
                }
                catch (Exception e1){
                    e1.printStackTrace();
                }
    
                File file2 = new File("robotposition.txt");
                try{ // Saves the current position for all four robot
                    FileOutputStream fos2 = new FileOutputStream(file2);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos2));
    
                    for(int i = 0; i<robotpost.size(); i++){
                        StringBuilder sb1 = new StringBuilder();
                        sb1.append(robotpost.get(i).returnrobotx() + "," + robotpost.get(i).returnroboty());
                        bw.write(sb1.toString());
                        bw.newLine();
                    }
                    bw.close();
                } catch(Exception e1){
                    e1.printStackTrace();
                }
    
                File file3 = new File("scoreboard.txt");
                try{ // Saves the current state of the scoreboard
                    FileOutputStream fos3 = new FileOutputStream(file3);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos3));
    
                    for(int i = 1; i < 5; i++){
                        StringBuilder sb1 = new StringBuilder();
                        sb1.append(scoreboard.getValueAt(i, 0).toString() + "," + scoreboard.getValueAt(i,1).toString());
                        bw.write(sb1.toString());
                        bw.newLine();
                    }
                    bw.close();
                }catch(Exception e1){
                    e1.printStackTrace();
                }
    
                File file4 = new File("timer.txt");
                try{ // Saves the remaining time when the game is saved 
                    FileOutputStream fos4 = new FileOutputStream(file4);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos4));
    
                    String remainingtime = time.getText().substring(0, 2);
                    if(remainingtime.equals("00")){
                        remainingtime = time.getText().substring(3,5);
                    }
                    else if(remainingtime.equals("01")){
                        remainingtime = "60";
                    }
                    bw.write(remainingtime);
                    bw.close();
                }catch(Exception e1){
                    e1.printStackTrace();
                }
    
                File file5 = new File("currentplayer.txt");
                try{ // Saves the current player data 
                    FileOutputStream fos5 = new FileOutputStream(file5);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos5));
    
                    bw.write(whos_turn.getText());
                    bw.close();
                }catch(Exception e1){
                    e1.printStackTrace();
                }
    
                File file6 = new File("playertime.txt");
                try{ // Saves the playertime HashMap, recording the time used for each player before saving 
                    FileOutputStream fos6 = new FileOutputStream(file6);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos6));
    
                    for(Map.Entry<Integer, Integer> entry: player_time.entrySet()){
                        StringBuilder sb1 = new StringBuilder();
                        sb1.append(entry.getKey() + "," + entry.getValue());
                        bw.write(sb1.toString());
                        bw.newLine();
                    }
                    bw.close();
                }catch(Exception e1){
                    e1.printStackTrace();
                }
    
                File file7 = new File("selections.txt");
                try{ // Saves the selected visual option and difficulty 
                    FileOutputStream fos7 = new FileOutputStream(file7);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos7));
    
                    StringBuilder sb1 = new StringBuilder();
                    sb1.append(visualoption + "," + difficulty);
                    bw.write(sb1.toString());
                    bw.close();
                }catch(Exception e1){
                    e1.printStackTrace();
                }
            }
            else if(save == JOptionPane.NO_OPTION){
                JOptionPane.showMessageDialog(this.getContentPane(), "Goodbye");
                System.exit(0);
            }
        }
    }

    public void mousePressed(MouseEvent me){
        Object click = me.getSource();


        if(click instanceof RobotPosition){
            int xco = ((RobotPosition) click).returnrobotx();
            int yco = ((RobotPosition) click).returnroboty();


            for(int i = 0; i < robotpost.size(); i++){ // Cross reference the selected robot piece with the coordinate of the robot piece on the board 
                if(robotpost.get(i).returnrobotx() == xco && robotpost.get(i).returnroboty() == yco){
                    currentrobot = i;
                    if(i == 0){  // If matched, assigns the "paint" with the graphics value
                        paint = 18;
                    }
                    else if(i == 1){
                        paint = 19;
                    }
                    else if(i == 2){
                        paint = 20;
                    }
                    else if(i == 3){
                        paint = 21;
                    }
                }
            }


            ((RobotPosition) click).setBackground(Color.black);

            // In each nested ActionListener, the JLabels "UR","UL","DR","DL","Mid","Right","Down" are used to represent the walls of the board
            // Whilst "R" is used to represent the robot pieces
            // "Yes" represents the board tiles in which it's okay to move across

            up.addActionListener(new ActionListener(){ // Nested ActionListener for "up" button
                public void actionPerformed(ActionEvent e){
                    String jc;
                    outerloop:
                    for(int column = xco; column >= 0 ; column--){
                        for(Component c : grid[column-1][yco].getComponents()){
                            if(c instanceof JLabel){
                                JLabel label = (JLabel)c;
                                jc = label.getText();
                                if(jc.equals("UR") || jc.equals("UL")){ 
                                    grid[column-1][yco].setrobot(paint);
                                    grid[xco][yco].setrobot(0);
                                    for(int ci = column-1; ci <= xco; ci++){
                                        grid[ci][yco].setBackground(new Color(255,255,153));
                                    }
                                    ((RobotPosition) click).x = column-1;
                                    ((RobotPosition) click).setBackground(Color.WHITE);
                                    switch(currentrobot){ // Changes the coordinate values of the selected robot
                                        case 0:
                                            robotpost.get(0).x = column-1;
                                            break;
                                        case 1:
                                            robotpost.get(1).x = column-1;
                                            break;
                                        case 2:
                                            robotpost.get(2).x = column-1;
                                            break;
                                        case 3:
                                            robotpost.get(3).x = column-1;
                                            break;
                                        default: break;
                                    }
                                    grid[16][16].setBackground(Color.white); // To prevent collisions from happening
                                    break outerloop;
                                }
                                else if(jc.equals("DR") || jc.equals("DL") || jc.equals("Down") || jc.equals("Mid") || jc.equals("R")){
                                    grid[column][yco].setrobot(paint);
                                    grid[xco][yco].setrobot(0);
                                    for(int ci = column; ci <= xco; ci++){
                                        grid[ci][yco].setBackground(new Color(255,255,153));
                                    }
                                    ((RobotPosition) click).x = column;
                                    ((RobotPosition) click).setBackground(Color.WHITE);
                                    switch(currentrobot){
                                        case 0:
                                            robotpost.get(0).x = column;
                                            break;
                                        case 1:
                                            robotpost.get(1).x = column;
                                            break;
                                        case 2:
                                            robotpost.get(2).x = column;
                                            break;
                                        case 3:
                                            robotpost.get(3).x = column;
                                            break;
                                        default: break;
                                    }
                                    grid[16][16].setBackground(Color.white);
                                    break outerloop;
                                }
                                else if(jc.equals("Yes")){
                                    grid[column][yco].setBackground(new Color(255, 255, 153));
                                    grid[xco][yco].setrobot(0);
                                    grid[xco][yco].setBackground(new Color(255,255,153));

                                    if(column == 1){
                                        grid[0][yco].setBackground(new Color(255,255,153));
                                        grid[0][yco].setrobot(paint);
                                        
                                    }
                                    switch(currentrobot){
                                        case 0:
                                            robotpost.get(0).x = 0;
                                            break;
                                        case 1:
                                            robotpost.get(1).x = 0;
                                            break;
                                        case 2:
                                            robotpost.get(2).x = 0;
                                            break;
                                        case 3:
                                            robotpost.get(3).x = 0;
                                            break;
                                        default: break;
                                    };
                                    ((RobotPosition) click).x = 0;
                                    ((RobotPosition) click).setBackground(Color.WHITE);
                                }
                            }
                        }
                        
                    }
                }
            });

            down.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                    String jc;
                    outerloop:
                    for(int column = xco; column <= x ; column++){
                        for(Component c : grid[column+1][yco].getComponents()){
                            if(c instanceof JLabel){
                                JLabel label = (JLabel)c;
                                jc = label.getText();
                                if(jc.equals("UR") || jc.equals("UL") || jc.equals("R") || jc.equals("Mid")){
                                    grid[column][yco].setrobot(paint);
                                    grid[xco][yco].setrobot(0);
                                    for(int ci = column; ci >= xco; ci--){
                                        grid[ci][yco].setBackground(new Color(255,255,153));
                                    }
                                    ((RobotPosition) click).x = column;
                                    ((RobotPosition) click).setBackground(Color.WHITE);
                                    switch(currentrobot){
                                        case 0:
                                            robotpost.get(0).x = column;
                                            break;
                                        case 1:
                                            robotpost.get(1).x = column;
                                            break;
                                        case 2:
                                            robotpost.get(2).x = column;
                                            break;
                                        case 3:
                                            robotpost.get(3).x = column;
                                            break;
                                        default: break;
                                    };
                                    grid[16][16].setBackground(Color.white);
                                    break outerloop;
                                }
                                else if(jc.equals("DR") || jc.equals("DL") || jc.equals("Down")){
                                    grid[column+1][yco].setrobot(paint);
                                    grid[xco][yco].setrobot(0);
                                    for(int ci = column+1; ci <= xco; ci++){
                                        grid[ci][yco].setBackground(new Color(255,255,153));
                                    }
                                    ((RobotPosition) click).x = column+1;
                                    ((RobotPosition) click).setBackground(Color.WHITE);
                                    switch(currentrobot){
                                        case 0:
                                            robotpost.get(0).x = column+1;
                                            break;
                                        case 1:
                                            robotpost.get(1).x = column+1;
                                            break;
                                        case 2:
                                            robotpost.get(2).x = column+1;
                                            break;
                                        case 3:
                                            robotpost.get(3).x = column+1;
                                            break;
                                        default: break;
                                    };
                                    grid[16][16].setBackground(Color.white);
                                    break outerloop;
                                }
                                else if(jc.equals("Yes")){
                                    grid[column][yco].setBackground(new Color(255, 102, 102));
                                    grid[xco][yco].setrobot(0);
                                    grid[xco][yco].setBackground(new Color(255,102,102));

                                    if(column == x-2){
                                        grid[x-1][yco].setBackground(new Color(255,102,102));
                                        grid[x-1][yco].setrobot(paint);
                                    }
                                    switch(currentrobot){
                                        case 0:
                                            robotpost.get(0).x = x-1;
                                            break;
                                        case 1:
                                            robotpost.get(1).x = x-1;
                                            break;
                                        case 2:
                                            robotpost.get(2).x = x-1;
                                            break;
                                        case 3:
                                            robotpost.get(3).x = x-1;
                                            break;
                                        default: break;
                                    };
                                    ((RobotPosition) click).x = x-1;
                                    ((RobotPosition) click).setBackground(Color.WHITE);

                                }
                            }
                        }
                    }
                    
                }
            });

            right.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                    String jc;
                    outerloop:
                    for(int column = yco; column <= y ; column++){
                        for(Component c : grid[xco][column+1].getComponents()){
                            if(c instanceof JLabel){
                                JLabel label = (JLabel)c;
                                jc = label.getText();
                                if(jc.equals("UL") || jc.equals("DL") || jc.equals("Mid") || jc.equals("R")){
                                    grid[xco][column].setrobot(paint);
                                    grid[xco][yco].setrobot(0);
                                    for(int ci = column; ci >= yco; ci--){
                                        grid[xco][ci].setBackground(new Color(51,204,255));
                                    }
                                    ((RobotPosition) click).y = column;
                                    ((RobotPosition) click).setBackground(Color.WHITE);
                                    switch(currentrobot){
                                        case 0:
                                            robotpost.get(0).y = column;
                                            break;
                                        case 1:
                                            robotpost.get(1).y = column;
                                            break;
                                        case 2:
                                            robotpost.get(2).y = column;
                                            break;
                                        case 3:
                                            robotpost.get(3).y = column;
                                            break;
                                        default: break;
                                    };
                                    grid[16][16].setBackground(Color.white);
                                    break outerloop;
                                }
                                else if(jc.equals("UR") || jc.equals("DR") || jc.equals("Right")){
                                    grid[xco][column+1].setrobot(paint);
                                    grid[xco][yco].setrobot(0);
                                    for(int ci = column+1; ci >= yco; ci--){
                                        grid[xco][ci].setBackground(new Color(51,204,255));
                                    }
                                    switch(currentrobot){
                                        case 0:
                                            robotpost.get(0).y = column+1;
                                            break;
                                        case 1:
                                            robotpost.get(1).y = column+1;
                                            break;
                                        case 2:
                                            robotpost.get(2).y = column+1;
                                            break;
                                        case 3:
                                            robotpost.get(3).y = column+1;
                                            break;
                                        default: break;
                                    };
                                    ((RobotPosition) click).setBackground(Color.WHITE);
                                    ((RobotPosition) click).y = column+1;
                                    grid[16][16].setBackground(Color.white);
                                    break outerloop;
                                }
                                else if(jc.equals("Yes")){
                                    grid[xco][column].setBackground(new Color(51, 204, 255));
                                    grid[xco][yco].setrobot(0);
                                    grid[xco][yco].setBackground(new Color(51,204,255));

                                    if(column == y-2){
                                        grid[xco][y-1].setBackground(new Color(51,204,255));
                                        grid[xco][y-1].setrobot(paint);
                                    }
                                    switch(currentrobot){
                                        case 0:
                                            robotpost.get(0).y = y-1;
                                            break;
                                        case 1:
                                            robotpost.get(1).y = y-1;
                                            break;
                                        case 2:
                                            robotpost.get(2).y = y-1;
                                            break;
                                        case 3:
                                            robotpost.get(3).y = y-1;
                                            break;
                                        default: break;
                                    }
                                    ((RobotPosition) click).y = y-1;
                                    ((RobotPosition) click).setBackground(Color.WHITE);

                                }
                            }
                        }
                    }
                    
                }
            });

            left.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                    String jc;
                    outerloop:
                    for(int column = yco; column >= 0; column--){
                        for(Component c : grid[xco][column-1].getComponents()){
                            if(c instanceof JLabel){
                                JLabel label = (JLabel)c;
                                jc = label.getText();
                                if(jc.equals("UL") || jc.equals("DL")){
                                    grid[xco][column-1].setrobot(paint);
                                    grid[xco][yco].setrobot(0);
                                    for(int ci = column-1; ci <= yco; ci++){
                                        grid[xco][ci].setBackground(new Color(102,255,102));
                                    }
                                    switch(currentrobot){
                                        case 0:
                                            robotpost.get(0).y = column-1;
                                            break;
                                        case 1:
                                            robotpost.get(1).y = column-1;
                                            break;
                                        case 2:
                                            robotpost.get(2).y = column-1;
                                            break;
                                        case 3:
                                            robotpost.get(3).y = column-1;
                                            break;
                                        default: break;
                                    }
                                    ((RobotPosition) click).setBackground(Color.WHITE);
                                    ((RobotPosition) click).y = column-1;
                                    grid[16][16].setBackground(Color.white);
                                    break outerloop;
                                }
                                else if(jc.equals("UR") || jc.equals("DR") || jc.equals("Mid") || jc.equals("Right") || jc.equals("R")){
                                    grid[xco][column].setrobot(paint);
                                    grid[xco][yco].setrobot(0);
                                    for(int ci = column; ci <= yco; ci++){
                                        grid[xco][ci].setBackground(new Color(102,255,102));
                                    }
                                    switch(currentrobot){
                                        case 0:
                                            robotpost.get(0).y = column;
                                            break;
                                        case 1:
                                            robotpost.get(1).y = column;
                                            break;
                                        case 2:
                                            robotpost.get(2).y = column;
                                            break;
                                        case 3:
                                            robotpost.get(3).y = column;
                                            break;
                                        default: break;
                                    }
                                    ((RobotPosition) click).setBackground(Color.WHITE);
                                    ((RobotPosition) click).y = column;
                                    grid[16][16].setBackground(Color.white);
                                    break outerloop;
                                }
                                else if(jc.equals("Yes")){
                                    grid[xco][column].setBackground(new Color(102, 255, 102));
                                    grid[xco][yco].setrobot(0);
                                    grid[xco][yco].setBackground(new Color(102,255,102));

                                    if(column == 1){
                                        grid[xco][0].setBackground(new Color(102,255,102));
                                        grid[xco][0].setrobot(paint);
                                    }
                                    switch(currentrobot){
                                        case 0:
                                            robotpost.get(0).y = 0;
                                            break;
                                        case 1:
                                            robotpost.get(1).y = 0;
                                            break;
                                        case 2:
                                            robotpost.get(2).y = 0;
                                            break;
                                        case 3:
                                            robotpost.get(3).y = 0;
                                            break;
                                        default: break;
                                    }
                                    ((RobotPosition) click).y = 0;
                                    ((RobotPosition) click).setBackground(Color.WHITE);

                                }
                            }
                        }
                    }
                }
            });
        }
    }

    public void mouseClicked(MouseEvent arg0){}
    public void mouseEntered(MouseEvent arg0){}
	public void mouseExited(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
    
}