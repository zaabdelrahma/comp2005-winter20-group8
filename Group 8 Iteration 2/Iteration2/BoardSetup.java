import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;


public class BoardSetup extends JFrame implements ActionListener{

    private JPanel maingame, bottom_panel, titlepanel, boardpanel, userpanel, playerturn, timer, chipdeck, scoreboard_panel;
    private Icon chip;
    private JTable scoreboard;
    private JButton savegame, exitgame, hint, startclock, resetclock, pauseclock;
    private JLabel whos_turn, which_chip, timer_label, scoreboard_label, taketurn, time, visual_option, timesup;
    private BoardGrid[][] grid; 
    private int players, hour, minutes, visualoption, pausetime;
    private String lexicon;
    private HashMap<Integer, Integer> player_score, player_time;
    private HashMap<String, Integer> computer;
    private Random rand;
    private Timer newtimer;

    public BoardSetup(int x, int y, int players, int visualoption, int difficulty){
        super("Ricochet Robot");
        this.setSize(3000,1000);

        this.players = players;
        this.hour = 1;
        this.minutes = 0;
        this.visualoption = visualoption;
        // String created to replace the shapes with letters
        this.lexicon = "ABCDEFGHIJKLMNOPQ";


        rand = new Random();

        // Different hashmaps to be read and written on the scoreboard
        player_score = new HashMap<>();
        computer = new HashMap<>();
        // Hashmap to record the time each player took for their round 
        player_time = new HashMap<>();

        // Setting up the scoreboard table
        String[] scoreboard_header = {"Player", "Score"};
        DefaultTableModel dtm = new DefaultTableModel(scoreboard_header,0);
        scoreboard = new JTable(dtm);
        dtm.addRow(scoreboard_header);

        // Initialising the number of computer players
        if(players == 1){
            computer.put("Computer 1", 0);
            computer.put("Computer 2", 0);
            computer.put("Computer 3", 0);
        }
        else if(players == 2){
            computer.put("Computer 1", 0);
            computer.put("Computer 2", 0);
        }
        else if(players == 3){
            computer.put("Computer 1", 0);
        }

        // For each entry in the "player_score" hashmap, it will be shown in the scoreboard table
        for(int i = 0; i < players; i++){
            player_score.put(i+1 , 0);
            for(Map.Entry<Integer, Integer> entry: player_score.entrySet()) {
                dtm.addRow(new Object[] {entry.getKey(), entry.getValue()});
            }
            player_score.clear();
        }
        for(Map.Entry<String, Integer> entry : computer.entrySet()) {
            dtm.addRow(new Object[] {entry.getKey(), entry.getValue()});
        }

        for(int i = 0; i < 4; i++){
            player_time.put((i+1), 0);
        }


        // All JPanels are declared here
        boardpanel = new JPanel();
        boardpanel.setLayout(new GridLayout(x,y));
        userpanel = new JPanel();
        bottom_panel = new JPanel();
        maingame = new JPanel();
        titlepanel = new JPanel();
        playerturn = new JPanel();
        timer = new JPanel();
        chipdeck = new JPanel();
        scoreboard_panel = new JPanel();
        // "chip" is declared as an instance of class "Icon" so that shapes and colours and be added 
        chip = new Icon(0);
        chip.setPreferredSize(new Dimension(100,100));

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

        hint.addActionListener(this);
        savegame.addActionListener(this);
        exitgame.addActionListener(this);
        startclock.addActionListener(this);
        pauseclock.addActionListener(this);
        resetclock.addActionListener(this);

        // Setting up the main game board using a 2D array instance of the class "BoardGrid"
        grid = new BoardGrid[x][y];

        // Goes through the first if statement if "Simple Board" is chosen
        if(difficulty == 0){
            for(int column = 0; column < x; column++){
                for(int row = 0; row < y; row++){
                    grid[column][row] = new BoardGrid(x, y, column, row);
                    grid[column][row].backgroundSet(visualoption);
    
                    boardpanel.add(grid[column][row]);
                }
            }
        }
        else{ // Goes through this statement if "Compelx Board" is chosen
            for(int column = 0; column < x; column++){
                for(int row = 0; row < y; row++){
                    grid[column][row] = new BoardGrid(x, y, column, row);
                    grid[column][row].backgroundSetComplex();
    
                    boardpanel.add(grid[column][row]);
                }
            }
        }

        

        // The top part of the main game frame that gives player(s) options to ask for hints, save game, or exit game
        titlepanel.setPreferredSize(new Dimension(3000,100));
        titlepanel.setLayout(new FlowLayout());
        titlepanel.add(taketurn);
        titlepanel.add(hint);
        titlepanel.add(savegame);
        titlepanel.add(exitgame);

        // Panel that shows the destination chip or letter
        chipdeck.setLayout(new GridLayout(3,1));
        chipdeck.add(which_chip);
        chipdeck.add(chip);
        chipdeck.add(visual_option);

        // If "Text" option is chosen, the chip display will be hidden
        if(visualoption == 0){
            chip.setVisible(false);
        }
        else if(visualoption == 1){ // If "Color" option is chosen, the letter display will be hidden
            visual_option.setVisible(false);
        }

        // Setting up the timer part
        timer.setLayout(new GridLayout(5,1));
        timer.add(timer_label);
        timer.add(time);
        timer.add(startclock);
        timer.add(pauseclock);
        timer.add(resetclock);

        playerturn.setLayout(new GridLayout(2,1));
        playerturn.add(whos_turn);
        playerturn.add(timesup);

        // Scoreboard 
        scoreboard_panel.setLayout(new GridLayout(2,1));
        scoreboard_panel.add(scoreboard_label);
        scoreboard_panel.add(scoreboard);

        // The information panel of the game
        userpanel.setLayout(new GridLayout(4,1,20,20));
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
        final int min = pTime/60;
        final int sec = pTime-(min*60);
    
        final String strMin = placeZeroIfNeede(min);
        final String strSec = placeZeroIfNeede(sec);
        return String.format("%s:%s",strMin,strSec);
    }

    // Properly format the minute string with leading zeroes if needed
    private String placeZeroIfNeede(int number) {
        return (number >=10)? Integer.toString(number):String.format("0%s",Integer.toString(number));
    }


    public void actionPerformed(ActionEvent e){
        Object click = e.getSource();

        if(click.equals(startclock)){
                // Set up a swing timer for the time countdown for the round with one second delay
                newtimer = new Timer(1000, new ActionListener(){
                int secondsPassed = 60;
                @Override
                public void actionPerformed(ActionEvent aev){
                    // For each second, the "time" JLabel will update itself
                    time.setText(secondsToString(secondsPassed));
                    secondsPassed--;
                    if(secondsPassed == -1){
                        newtimer.stop();
                        timesup.setText("Time's up");
                        time.setText(secondsToString(60));
                    }
                }
            });
            newtimer.start();
        }
        else if(click.equals(resetclock)){ // If "Reset" is chosen, timer will stop and goes to 0, displaying the message
            newtimer.stop();
            time.setText(secondsToString(0));
            JOptionPane.showMessageDialog(this.getContentPane(), "No cheating");
            time.setText(secondsToString(60));
        }
        else if(click.equals(pauseclock)){ // If "Pause" is chosen, timer will stop and the system will record the time and record it to the current player
            newtimer.stop();
            int finaltime = Integer.parseInt(time.getText().substring(3,5));
            if(whos_turn.getText().equals("Player 1's turn")){
                player_time.putIfAbsent(1,finaltime);
            }
            else if(whos_turn.getText().equals("Player 2's turn")){
                player_time.putIfAbsent(2, finaltime);
            }
            else if(whos_turn.getText().equals("Player 3's turn")){
                player_time.putIfAbsent(3, finaltime);
            }
            else{
                player_time.putIfAbsent(4, finaltime);
            }

        }
    }
    
    
}
