import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// This class renders the Setup Page use case and the GUI is implemented in accordance with the UI Prototype Sketch

public class GameSetup extends JFrame implements ActionListener {

    private JLabel ricochet, newgame, visual_settings, board_settings, numberofplayer;
    private JPanel main, top, middle, bottom, input, boardsettings, visualsettings, ricochet_label, top_buttons, newgame_label;
    private JButton resume, exit, help, startgame;
    private JRadioButton text, color, text_color, simpleboard, complexboard;
    private JComboBox numberofusers;
    private ButtonGroup bs, vs;

    public GameSetup(){
        super("Setting up game");
        this.setSize(500,800);

        Integer[] users = {1,2,3,4};

        //Sections where all JLabels are declared
        ricochet = new JLabel("RICOCHET");
        ricochet.setBorder(BorderFactory.createLineBorder(Color.black));
        ricochet.setFont(new Font("Helvetica", Font.BOLD, 30));
        ricochet.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        ricochet.setHorizontalAlignment(SwingConstants.CENTER);
        numberofplayer = new JLabel("Enter the number of players ");
        newgame = new JLabel("New Game");
        newgame.setBorder(BorderFactory.createLineBorder(Color.black));
        newgame.setFont(new Font("Helvetica", Font.BOLD, 30));
        newgame.setBackground(Color.RED);
        newgame.setOpaque(true);
        newgame.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        newgame.setHorizontalAlignment(SwingConstants.CENTER);
        visual_settings = new JLabel("Choose your visual settings");
        board_settings = new JLabel("Choose your board settings");

        top_buttons = new JPanel();
        main = new JPanel();
        top = new JPanel();
        middle = new JPanel();
        bottom = new JPanel();
        //JPanel for the user input section
        input = new JPanel();
        //JPanel for the Visual Settings section
        visualsettings = new JPanel();
        //JPanel for the Board Settngs section
        boardsettings = new JPanel();

        //Both ButtonGroup declared to incorporate the RadioButtons for both Visual Settings and Board Settings respectively
        this.vs = new ButtonGroup();
        this.bs = new ButtonGroup();

        resume = new JButton("Resume");
        resume.addActionListener(this);
        exit = new JButton("Exit");
        exit.addActionListener(this);
        help = new JButton("Help");
        help.addActionListener(this);
        startgame = new JButton("Start Game");
        startgame.addActionListener(this);
        startgame.setAlignmentX(Component.CENTER_ALIGNMENT);

        // RadioButton options for Visual settings
        text = new JRadioButton("Text");
        text.setActionCommand("Text");
        color = new JRadioButton("Color");
        color.setActionCommand("Color");
        text_color = new JRadioButton("Text & Color");
        text_color.setActionCommand("tnc");

        // RadioButton options for Board settings
        simpleboard = new JRadioButton("Simple Board");
        simpleboard.setActionCommand("simple");
        complexboard = new JRadioButton("Complex Board");
        complexboard.setActionCommand("complex");

        //This combo box is for player(s) to choose the number of human player(s)
        numberofusers = new JComboBox(users);

        // Board Settings' RadioButtons are added to the ButtonGroup to ensure no duplicate selection
        bs.add(simpleboard);
        bs.add(complexboard);

        // Visual Setting's RadioButtons are added to the ButtonGroup to ensure no duplicate selection
        vs.add(text);
        vs.add(color);
        vs.add(text_color);

        // Panel that features the Resume, Exit, and Help buttons
        top_buttons.setLayout(new FlowLayout());
        top_buttons.add(resume);
        top_buttons.add(exit);
        top_buttons.add(help);
        
        // Top part of the frame that holds the title of the game and the three buttons in the "top_buttons" JPanel
        top.setLayout(new GridLayout(2,1,0,0));
        top.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        top.add(ricochet);
        top.add(top_buttons);

        // Panel for input of players 
        input.setLayout(new FlowLayout());
        input.add(numberofplayer);
        input.add(numberofusers);

        // Panel for visual settings selection
        visualsettings.setLayout(new GridLayout(4,1,0,0));
        visualsettings.setBorder(BorderFactory.createLineBorder(Color.black));
        visualsettings.add(visual_settings);
        visualsettings.add(text);
        visualsettings.add(color);
        visualsettings.add(text_color);

        // Panel for board settings selection
        boardsettings.setLayout(new GridLayout(3,1,0,0));
        boardsettings.setBorder(BorderFactory.createLineBorder(Color.black));
        boardsettings.add(board_settings);
        boardsettings.add(simpleboard);
        boardsettings.add(complexboard);

        // The middle part of the GUI frame that features User input and selections
        middle.setLayout(new GridLayout(4,1,10,10));
        middle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        middle.add(newgame);
        middle.add(input);
        middle.add(visualsettings);
        middle.add(boardsettings);

        // Setting up the main JPanel 
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.add(top);
        main.add(Box.createRigidArea(new Dimension(5, 20)));
        main.add(middle);
        main.add(Box.createRigidArea(new Dimension(5, 20)));
        main.add(startgame);

        getContentPane().add(main);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("Start Game")){
            // Get the visual settings selection
            String visualoption = this.vs.getSelection().getActionCommand();
            // Get the board settings selection
            String boardoption = this.bs.getSelection().getActionCommand();
            // Get the number of human player(s)
            Integer o = (Integer)numberofusers.getSelectedItem();
            int playersselection = o.intValue();
            if(boardoption.equals("simple")){ // 0 represents Simple settings
                if(visualoption.equals("Text")){ // 0 represents Text settings
                    BoardSetup b = new BoardSetup(16, 16, playersselection, 0, 0); //BoardSetup(int x, int y, int players, int visualoption, int difficulty)
                }
                else if(visualoption.equals("Color")){ // 1 represents Color settings
                    BoardSetup b = new BoardSetup(16, 16, playersselection, 1, 0);
                }
                else{ // 2 represents Text and Color settings
                    BoardSetup b = new BoardSetup(16, 16, playersselection, 2, 0);
                }
            }
            else{ // 1 represents complex settings
                if(visualoption.equals("Text")){
                    BoardSetup b = new BoardSetup(16, 16, playersselection, 0, 1);
                }
                else if(visualoption.equals("Color")){
                    BoardSetup b = new BoardSetup(16, 16, playersselection, 1, 1);
                }
                else{
                    BoardSetup b = new BoardSetup(16, 16, playersselection, 2, 1);
                }
            }
        }
    }
}
