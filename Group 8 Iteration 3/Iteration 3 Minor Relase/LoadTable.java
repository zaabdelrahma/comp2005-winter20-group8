import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.HashSet;
import java.util.ArrayList;
import java.io.*;

// This class takes in the saved data as parameters and the datas are then used to initialise the saved board
// This class behaves very similarly to the BoardSetup class, the only difference being that certain values are fixed instead of randomised
public class LoadTable extends JFrame implements ActionListener, MouseListener{
    
    private JPanel maingame, bottom_panel, titlepanel, boardpanel, userpanel, playerturn, timer, chipdeck, scoreboard_panel, robotselect, arrows;
    private Icon chip;
    private ArrayList<Integer> targetlist;
    private ArrayList<RobotPosition> robotpost;
    private HashMap<String, Integer> scoreboard;
    private String savedtime,currentplayer;
    private int visualoption, difficulty, x, y;
    private JTable scoreboardtable;
    private JButton savegame, exitgame, hint, startclock, resetclock, pauseclock, up, down, left, right;
    private JLabel whos_turn, which_chip, timer_label, scoreboard_label, taketurn, time, visual_option, timesup;
    private BoardGrid[][] grid;
    private int paint, targetchip, currentrobot;
    private String lexicon;
    private HashMap<Integer, Integer> player_time;
    private HashMap<String, Integer> computer;
    private Random rand;
    private Timer newtimer;
    private ArrayList<ArrayList<Integer>> rp;
    private ArrayList<String> robottext;

    public LoadTable(int targetchip, ArrayList<Integer> targetlist, ArrayList<RobotPosition> robotpost, HashMap<String, Integer> scoreboard, String savedtime, String currentplayer, int visualoption, int difficulty, int x, int y, HashMap<Integer, Integer> player_time){
        super("Ricochet Robot");
        this.setSize(3000,1000);

        this.targetchip = targetchip;
        this.targetlist = targetlist;
        this.robotpost = robotpost;
        this.scoreboard = scoreboard;
        this.savedtime = savedtime;
        this.currentplayer = currentplayer;
        this.visualoption = visualoption;
        this.difficulty = difficulty;
        this.x = x;
        this.y = y;
        this.lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.rp = new ArrayList<>();
        this.robottext = new ArrayList<>();
        this.player_time = player_time;

        String[] scoreboard_header = { "Player", "Score" };
        DefaultTableModel dtm = new DefaultTableModel(scoreboard_header, 0);
        scoreboardtable = new JTable(dtm);
        dtm.addRow(scoreboard_header);

        for (Map.Entry<String, Integer> entry : scoreboard.entrySet()) {
            dtm.addRow(new Object[] { entry.getKey(), entry.getValue() });
        }

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

        whos_turn = new JLabel(currentplayer);
        whos_turn.setFont(new Font("Helvetica", Font.BOLD, 20));
        whos_turn.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        whos_turn.setHorizontalAlignment(SwingConstants.CENTER);
        which_chip = new JLabel("Chip Deck");
        timer_label = new JLabel("Timer");
        scoreboard_label = new JLabel("Scoreboard");
        taketurn = new JLabel("Take a turn");
        // Set the "time" JLabel to display the time for each round
        String countdown = secondsToString(Integer.valueOf(savedtime));
        time = new JLabel(countdown);
        timesup = new JLabel();

        hint = new JButton("Hint");
        savegame = new JButton("Save");
        exitgame = new JButton("Exit");
        startclock = new JButton("Start");
        pauseclock = new JButton("Pause");
        resetclock = new JButton("Reset");
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
                    if (visualoption == 0) {
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
                    } else if (visualoption == 1) {
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
                    } else {
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
        visual_option = new JLabel();

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
        scoreboard_panel.add(scoreboardtable);

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
            newtimer = new Timer(1000, new ActionListener() {
                int secondsPassed = Integer.valueOf(savedtime);

                @Override
                public void actionPerformed(ActionEvent aev) {
                    // For each second, the "time" JLabel will update itself
                    time.setText(secondsToString(secondsPassed));
                    secondsPassed--;
                    if (secondsPassed == -1) {
                        newtimer.stop();
                        timesup.setText("Time's up");
                        time.setText(secondsToString(60));
                    }
                }
            });
            newtimer.start();
        } else if (click.equals(resetclock)) { // If "Reset" is chosen, timer will stop and goes back to 01:00
                                               // Changes player's turn
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
                String s = scoreboardtable.getValueAt(i, 0).toString();
                String next = scoreboardtable.getValueAt(i + 1, 0).toString();
                if (whos_turn.getText().equals("Player 1's turn") && s.equals("1") && next.equals("2")) {
                    whos_turn.setText("Player 2's turn");
                }
                else if(whos_turn.getText().equals("Player 2's turn") && s.equals("2") && next.equals("3")){
                    whos_turn.setText("Player 3's turn");
                }
                else if(whos_turn.getText().equals("Player 3's turn") && s.equals("3") && next.equals("4")){
                    whos_turn.setText("Player 4's turn");
                }
                else if(whos_turn.getText().equals("Player 4's turn") && scoreboardtable.getValueAt(1,0).toString().equals("1")){
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

        } else if (click.equals(pauseclock)) { // If "Pause" is chosen, timer will stop and the system will record the
                                               // time and record it to the current player
            newtimer.stop();
            int finaltime = Integer.parseInt(time.getText().substring(3, 5));
            savedtime = Integer.toString(finaltime);
            if (whos_turn.getText().equals("Player 1's turn")) {
                player_time.replace(1, finaltime);
            } else if (whos_turn.getText().equals("Player 2's turn")) {
                player_time.replace(2, finaltime);
            } else if (whos_turn.getText().equals("Player 3's turn")) {
                player_time.replace(3, finaltime);
            } else{
                player_time.replace(4, finaltime);
            }

        } else if (click.equals(savegame)) {
            newtimer.stop(); // Similar to the save method in BoardSetup
            File file = new File("targetchip.txt");
            try {
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
            try{
                FileOutputStream fos1 = new FileOutputStream(file1);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos1));

                bw.write(savestring);
                bw.close();
            }
            catch (Exception e1){
                e1.printStackTrace();
            }

            File file2 = new File("robotposition.txt");
            try{
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
            try{
                FileOutputStream fos3 = new FileOutputStream(file3);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos3));

                for(int i = 1; i < 5; i++){
                    StringBuilder sb1 = new StringBuilder();
                    sb1.append(scoreboardtable.getValueAt(i, 0).toString() + "," + scoreboardtable.getValueAt(i,1).toString());
                    bw.write(sb1.toString());
                    bw.newLine();
                }
                bw.close();
            }catch(Exception e1){
                e1.printStackTrace();
            }

            File file4 = new File("timer.txt");
            try{
                FileOutputStream fos4 = new FileOutputStream(file4);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos4));

                String remainingtime = time.getText().substring(0, 2);
                if(remainingtime.equals("00")){
                    remainingtime = time.getText().substring(3,5);
                }
                bw.write(remainingtime);
                bw.close();
            }catch(Exception e1){
                e1.printStackTrace();
            }

            File file5 = new File("currentplayer.txt");
            try{
                FileOutputStream fos5 = new FileOutputStream(file5);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos5));

                bw.write(whos_turn.getText());
                bw.close();
            }catch(Exception e1){
                e1.printStackTrace();
            }

            File file6 = new File("playertime.txt");
            try{
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
                        sb1.append(scoreboardtable.getValueAt(i, 0).toString() + "," + scoreboardtable.getValueAt(i,1).toString());
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


            for(int i = 0; i < robotpost.size(); i++){
                if(robotpost.get(i).returnrobotx() == xco && robotpost.get(i).returnroboty() == yco){
                    currentrobot = i;
                    if(i == 0){
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


            up.addActionListener(new ActionListener(){
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
                                    switch(currentrobot){
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
                                    grid[16][16].setBackground(Color.white);
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