import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.util.HashSet;
import java.util.Random;
import java.util.ArrayList;
import java.awt.*;

// Class for the intialisation of the game board GUI
public class BoardGrid extends JPanel{

    private int xcoord, ycoord, xcolumn, yrow, count;
    private Random rand;

    public BoardGrid(int xcoord, int ycoord, int xcolumn, int yrow){
        super();
        this.setSize(20,20);
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.xcolumn = xcolumn;
        this.yrow = yrow;
        rand = new Random();
    }

    public void setrobot(int count){ // Setting up the robot pieces
        this.count = count;
        if(count == 0){
            for(Component c: this.getComponents()){ // Should the JLabel hold "R", it will be changed to "Yes" to allow other robot pieces to pass through
                if(c instanceof JLabel){
                    JLabel j = (JLabel)c;
                    String v = j.getText();
                    if(v.equals("R")){
                        j.setText("Yes");
                    }
                }
            }
        }
        else{
            JLabel robotlabel = new JLabel("R");
            this.add(robotlabel);
            robotlabel.setVisible(false);   
            repaint();
        }
        
    }

    // Setting the value of robots given "Text" option, to be further implemented
    public void setrobottext(int count){
        switch(count){
            case 1:
                this.add(new JLabel("Alpha"));
                break;
            case 2:
                this.add(new JLabel("Beta"));
                break;
            case 3:
                this.add(new JLabel("Charlie"));
                break;
            case 4:
                this.add(new JLabel("Delta"));
                break;
            default:break;
        }
    }


    public void backgroundSet(int visual, ArrayList<Integer> targetpiece){
        this.setBackground(Color.WHITE);
        this.setBorder(new LineBorder(Color.lightGray));
        JLabel jl = new JLabel("Yes"); //"Yes" Jlabel is added to ensure robots are able to move across the board
        this.add(jl);
        jl.setVisible(false);
        

        // Tiles with border will have JLabels in accordance to the walls
        // "UR" = Up Right
        // "UL" = Up Left
        // "DR" = Down Right
        // "DL" = Down Left
        // "Right" = Right
        // "Down" = Down
        // "Mid" = Four tiles in the middle of the board
        if(visual == 1){ // If "Color" is chosen, it will only feature shapes and colors, no text
            switch(this.xcolumn){
                case 0:
                    switch(this.yrow){
                        case 3:
                            this.setBorder(new CompoundBorder(new MatteBorder(0,0,0,3,Color.BLACK), new LineBorder(Color.lightGray)));
                            JLabel no = new JLabel("Right"); 
                            this.add(no);
                            no.setVisible(false);
                            break;
                        case 10:
                            this.setBorder(new CompoundBorder(new MatteBorder(0,0,0,3,Color.BLACK), new LineBorder(Color.lightGray)));
                            JLabel no1 = new JLabel("Right");
                            this.add(no1);
                            no1.setVisible(false);
                            break;
                    }
                    break;
                case 1:
                    switch(this.yrow){
                        case 5:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 0, 0, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            this.count = targetpiece.get(0);
                            repaint();
                            JLabel no = new JLabel("UR");
                            this.add(no);
                            no.setVisible(false);
                            break;
                        case 12:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 3, 0, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            // Icon shapes1 = new Icon(targetpiece.get(1));
                            // shapes1.setPreferredSize(new Dimension(30,30));
                            // this.add(shapes1);
                            this.count = targetpiece.get(1);
                            repaint();
                            JLabel no1 = new JLabel("UL");
                            this.add(no1);
                            no1.setVisible(false);
                            break;
                    }
                    break;
                case 2:
                    switch(this.yrow){
                        case 1:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            // Icon shapes = new Icon(targetpiece.get(2));
                            // shapes.setPreferredSize(new Dimension(30,30));
                            // this.add(shapes);
                            this.count = targetpiece.get(2);
                            repaint();
                            JLabel no = new JLabel("DL");
                            this.add(no);
                            no.setVisible(false);
                            break;
                        case 14:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            // Icon shapes1 = new Icon(targetpiece.get(3));
                            // shapes1.setPreferredSize(new Dimension(30,30));
                            // this.add(shapes1);
                            this.count = targetpiece.get(3);
                            repaint();
                            JLabel no1 = new JLabel("DL");
                            this.add(no1);
                            no1.setVisible(false);
                            break;
                    }
                    break;
                case 3:
                    switch(this.yrow){
                        case 0:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            JLabel no = new JLabel("Down");
                            this.add(no);
                            no.setVisible(false);
                            break;
                        case 8:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            // Icon shapes = new Icon(targetpiece.get(4));
                            // shapes.setPreferredSize(new Dimension(30,30));
                            // this.add(shapes);
                            this.count = targetpiece.get(4);
                            repaint();
                            JLabel no1 = new JLabel("DL");
                            this.add(no1);
                            no1.setVisible(false);
                            break;
                        
                    }
                    break;
                case 4:
                    switch(this.yrow){
                        case 6:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            // Icon shapes = new Icon(targetpiece.get(5));
                            // shapes.setPreferredSize(new Dimension(30,30));
                            // this.add(shapes);
                            this.count = targetpiece.get(5);
                            repaint();
                            JLabel no = new JLabel("DR");
                            this.add(no);
                            no.setVisible(false);
                            break;
                        case 15:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            JLabel no1 = new JLabel("Down");
                            this.add(no1);
                            no1.setVisible(false);
                            break;
                    }
                    break;
                case 5:
                    switch(this.yrow){
                        case 9:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            // Icon shapes = new Icon(targetpiece.get(6));
                            // shapes.setPreferredSize(new Dimension(30,30));
                            // this.add(shapes);
                            this.count = targetpiece.get(6);
                            repaint();
                            JLabel no = new JLabel("DR");
                            this.add(no);
                            no.setVisible(false);
                            break;
                    }
                    break;
                case 6:
                    switch(this.yrow){
                        case 2:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            // Icon shapes = new Icon(targetpiece.get(7));
                            // shapes.setPreferredSize(new Dimension(30,30));
                            // this.add(shapes);
                            this.count = targetpiece.get(7);
                            repaint();
                            JLabel no = new JLabel("DR");
                            this.add(no);
                            no.setVisible(false);
                            break;
                        case 11:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 0, 0, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            // Icon shapes1 = new Icon(targetpiece.get(8));
                            // shapes1.setPreferredSize(new Dimension(30,30));
                            // this.add(shapes1);
                            this.count = targetpiece.get(8);
                            repaint();
                            JLabel no1 = new JLabel("UR");
                            this.add(no1);
                            no1.setVisible(false);
                            break;
                    }
                    break;
                case 7:
                    switch(this.yrow){
                        case 7:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 3, 0, 0, Color.BLACK), null));
                            JLabel no = new JLabel("Mid");
                            this.add(no);
                            no.setVisible(false);
                            break;
                        case 8:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 0, 0, 3, Color.BLACK), null));
                            JLabel no1 = new JLabel("Mid");
                            this.add(no1);
                            no1.setVisible(false);
                            break;
                    }
                    break;
                case 8:
                    switch(this.yrow){
                        case 7:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK), null));
                            JLabel no = new JLabel("Mid");
                            this.add(no);
                            no.setVisible(false);
                            break;
                        case 8:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK), null));
                            JLabel no1 = new JLabel("Mid");
                            this.add(no1);
                            no1.setVisible(false);
                            break;
                    }
                    break;
                case 9:
                    switch(this.yrow){
                        case 1:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 0, 0, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            // Icon shapes = new Icon(targetpiece.get(9));
                            // shapes.setPreferredSize(new Dimension(30,30));
                            // this.add(shapes);
                            this.count = targetpiece.get(9);
                            repaint();
                            JLabel no = new JLabel("UR");
                            this.add(no);
                            no.setVisible(false);
                            break;
                        case 5:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 3, 0, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            // Icon shapes1 = new Icon(targetpiece.get(10));
                            // shapes1.setPreferredSize(new Dimension(30,30));
                            // this.add(shapes1);
                            this.count = targetpiece.get(10);
                            repaint();
                            JLabel no1 = new JLabel("UL");
                            this.add(no1);
                            no1.setVisible(false);
                            break;
                        case 15:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            JLabel no2 = new JLabel("Down");
                            this.add(no2);
                            no2.setVisible(false);
                            break;
                    }
                    break;
                case 10:
                    switch(this.yrow){
                        case 0:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            JLabel no = new JLabel("Down");
                            this.add(no);
                            no.setVisible(false);
                            break;
                        case 8:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            // Icon shapes = new Icon(targetpiece.get(11));
                            // shapes.setPreferredSize(new Dimension(30,30));
                            // this.add(shapes);
                            this.count = targetpiece.get(11);
                            repaint();
                            JLabel no1 = new JLabel("DR");
                            this.add(no1);
                            no1.setVisible(false);
                            break;
                    }
                    break;
                case 11:
                    switch(this.yrow){
                        case 13:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 3, 0, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            // Icon shapes = new Icon(targetpiece.get(12));
                            // shapes.setPreferredSize(new Dimension(30,30));
                            // this.add(shapes);
                            this.count = targetpiece.get(12);
                            repaint();
                            JLabel no = new JLabel("UL");
                            this.add(no);
                            no.setVisible(false);
                            break;
                    }
                    break;
                case 12:
                    switch(this.yrow){
                        case 6:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            // Icon shapes = new Icon(targetpiece.get(13));
                            // shapes.setPreferredSize(new Dimension(30,30));
                            // this.add(shapes);
                            this.count = targetpiece.get(13);
                            repaint();
                            JLabel no = new JLabel("DR");
                            this.add(no);
                            no.setVisible(false);
                            break;
                    }
                    break;
                case 13:
                    switch(this.yrow){
                        case 8:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            // Icon shapes = new Icon(targetpiece.get(14));
                            // shapes.setPreferredSize(new Dimension(30,30));
                            // this.add(shapes);
                            this.count = targetpiece.get(14);
                            repaint();
                            JLabel no = new JLabel("DL");
                            this.add(no);
                            no.setVisible(false);
                            break;
                    }
                    break;
                case 14:
                    switch(this.yrow){
                        case 2:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            // Icon shapes = new Icon(targetpiece.get(15));
                            // shapes.setPreferredSize(new Dimension(30,30));
                            // this.add(shapes);
                            this.count = targetpiece.get(15);
                            repaint();
                            JLabel no = new JLabel("DL");
                            this.add(no);
                            no.setVisible(false);
                            break;
                        case 14:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 0, 0, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            // Icon shapes1 = new Icon(targetpiece.get(16));
                            // shapes1.setPreferredSize(new Dimension(30,30));
                            // this.add(shapes1);
                            this.count = targetpiece.get(16);
                            repaint();
                            JLabel no1 = new JLabel("UR");
                            this.add(no1);
                            no1.setVisible(false);
                            break;
                    }
                    break;
                case 15:
                    switch(this.yrow){
                        case 5:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 0, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            JLabel no = new JLabel("Right");
                            this.add(no);
                            no.setVisible(false);
                            break;
                        case 11:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 0, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            JLabel no1 = new JLabel("Right");
                            this.add(no1);
                            no1.setVisible(false);
                            break;
                    }
                    break;
                default: break;
            }
        }
        else if(visual == 0){ // If "Text" was chosen, it will only feature letters but not shapes or colors, to be further implemented
            switch(this.xcolumn){
                case 0:
                    switch(this.yrow){
                        case 3:
                            this.setBorder(new CompoundBorder(new MatteBorder(0,0,0,3,Color.BLACK), new LineBorder(Color.lightGray)));
                            break;
                        case 10:
                            this.setBorder(new CompoundBorder(new MatteBorder(0,0,0,3,Color.BLACK), new LineBorder(Color.lightGray)));
                            break;
                    }
                    break;
                case 1:
                    switch(this.yrow){
                        case 5:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 0, 0, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            JLabel text = new JLabel("A"); // Declaring a new JLabel with letters to add to the panel 
                            text.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text);
                            break;
                        case 12:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 3, 0, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            JLabel text1 = new JLabel("B");
                            text1.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text1.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text1);
                            break;
                    }
                    break;
                case 2:
                    switch(this.yrow){
                        case 1:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            JLabel text = new JLabel("C");
                            text.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text);
                            break;
                        case 14:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            JLabel text1 = new JLabel("D");
                            text1.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text1.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text1);
                            break;
                    }
                    break;
                case 3:
                    switch(this.yrow){
                        case 0:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            break;
                        case 8:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            JLabel text = new JLabel("E");
                            text.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text);
                            break;
                        
                    }
                    break;
                case 4:
                    switch(this.yrow){
                        case 6:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            JLabel text = new JLabel("F");
                            text.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text);
                            break;
                        case 15:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            break;
                    }
                    break;
                case 5:
                    switch(this.yrow){
                        case 9:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            JLabel text = new JLabel("G");
                            text.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text);
                            break;
                    }
                    break;
                case 6:
                    switch(this.yrow){
                        case 2:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            JLabel text = new JLabel("H");
                            text.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text);
                            break;
                        case 11:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 0, 0, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            JLabel text1 = new JLabel("I");
                            text1.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text1.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text1);
                            break;
                    }
                    break;
                case 7:
                    switch(this.yrow){
                        case 7:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 3, 0, 0, Color.BLACK), null));
                            break;
                        case 8:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 0, 0, 3, Color.BLACK), null));
                            break;
                    }
                    break;
                case 8:
                    switch(this.yrow){
                        case 7:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK), null));
                            break;
                        case 8:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK), null));
                            break;
                    }
                    break;
                case 9:
                    switch(this.yrow){
                        case 1:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 0, 0, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            JLabel text = new JLabel("J");
                            text.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text);
                            break;
                        case 5:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 3, 0, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            JLabel text1 = new JLabel("K");
                            text1.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text1.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text1);
                            break;
                        case 15:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            break;
                    }
                    break;
                case 10:
                    switch(this.yrow){
                        case 0:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            break;
                        case 8:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            JLabel text = new JLabel("L");
                            text.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text);
                            break;
                    }
                    break;
                case 11:
                    switch(this.yrow){
                        case 13:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 3, 0, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            JLabel text = new JLabel("M");
                            text.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text);
                            break;
                    }
                    break;
                case 12:
                    switch(this.yrow){
                        case 6:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            JLabel text = new JLabel("N");
                            text.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text);
                            break;
                    }
                    break;
                case 13:
                    switch(this.yrow){
                        case 8:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            JLabel text = new JLabel("O");
                            text.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text);
                            break;
                    }
                    break;
                case 14:
                    switch(this.yrow){
                        case 2:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            JLabel text = new JLabel("P");
                            text.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text);
                            break;
                        case 14:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 0, 0, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            JLabel text1 = new JLabel("Q");
                            text1.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text1.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text1);
                            break;
                    }
                    break;
                case 15:
                    switch(this.yrow){
                        case 5:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 0, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            break;
                        case 11:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 0, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            break;
                    }
                    break;
                default: break;
            }
            
        }
        else{ // Features both colored shapes and letters, to be further implemented
            switch(this.xcolumn){
                case 0:
                    switch(this.yrow){
                        case 3:
                            this.setBorder(new CompoundBorder(new MatteBorder(0,0,0,3,Color.BLACK), new LineBorder(Color.lightGray)));
                            break;
                        case 10:
                            this.setBorder(new CompoundBorder(new MatteBorder(0,0,0,3,Color.BLACK), new LineBorder(Color.lightGray)));
                            break;
                    }
                    break;
                case 1:
                    switch(this.yrow){
                        case 5:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 0, 0, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes = new Icon(1); // Declares new instance of class Icon to add colored shapes
                            shapes.setPreferredSize(new Dimension(20,30));
                            this.add(shapes);
                            JLabel text = new JLabel("A"); // Declares new JLabel to add letter
                            text.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text);
                            break;
                        case 12:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 3, 0, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes1 = new Icon(2);
                            shapes1.setPreferredSize(new Dimension(20,30));
                            this.add(shapes1);
                            JLabel text1 = new JLabel("B");
                            text1.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text1.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text1);
                            break;
                    }
                    break;
                case 2:
                    switch(this.yrow){
                        case 1:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes = new Icon(3);
                            shapes.setPreferredSize(new Dimension(20,30));
                            this.add(shapes);
                            JLabel text = new JLabel("C");
                            text.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text);
                            break;
                        case 14:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes1 = new Icon(4);
                            shapes1.setPreferredSize(new Dimension(20,30));
                            this.add(shapes1);
                            JLabel text1 = new JLabel("D");
                            text1.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text1.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text1);
                            break;
                    }
                    break;
                case 3:
                    switch(this.yrow){
                        case 0:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            break;
                        case 8:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes = new Icon(5);
                            shapes.setPreferredSize(new Dimension(20,30));
                            this.add(shapes);
                            JLabel text = new JLabel("E");
                            text.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text);
                            break;
                        
                    }
                    break;
                case 4:
                    switch(this.yrow){
                        case 6:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes = new Icon(6);
                            shapes.setPreferredSize(new Dimension(20,30));
                            this.add(shapes);
                            JLabel text = new JLabel("F");
                            text.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text);
                            break;
                        case 15:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            break;
                    }
                    break;
                case 5:
                    switch(this.yrow){
                        case 9:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes = new Icon(7);
                            shapes.setPreferredSize(new Dimension(20,30));
                            this.add(shapes);
                            JLabel text = new JLabel("G");
                            text.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text);
                            break;
                    }
                    break;
                case 6:
                    switch(this.yrow){
                        case 2:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes = new Icon(8);
                            shapes.setPreferredSize(new Dimension(20,30));
                            this.add(shapes);
                            JLabel text = new JLabel("H");
                            text.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text);
                            break;
                        case 11:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 0, 0, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes1 = new Icon(9);
                            shapes1.setPreferredSize(new Dimension(20,30));
                            this.add(shapes1);
                            JLabel text1 = new JLabel("I");
                            text1.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text1.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text1);
                            break;
                    }
                    break;
                case 7:
                    switch(this.yrow){
                        case 7:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 3, 0, 0, Color.BLACK), null));
                            break;
                        case 8:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 0, 0, 3, Color.BLACK), null));
                            break;
                    }
                    break;
                case 8:
                    switch(this.yrow){
                        case 7:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK), null));
                            break;
                        case 8:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK), null));
                            break;
                    }
                    break;
                case 9:
                    switch(this.yrow){
                        case 1:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 0, 0, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes = new Icon(10);
                            shapes.setPreferredSize(new Dimension(20,30));
                            this.add(shapes);
                            JLabel text = new JLabel("J");
                            text.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text);
                            break;
                        case 5:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 3, 0, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes1 = new Icon(11);
                            shapes1.setPreferredSize(new Dimension(20,30));
                            this.add(shapes1);
                            JLabel text1 = new JLabel("K");
                            text1.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text1.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text1);
                            break;
                        case 15:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            break;
                    }
                    break;
                case 10:
                    switch(this.yrow){
                        case 0:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            break;
                        case 8:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes = new Icon(12);
                            shapes.setPreferredSize(new Dimension(20,30));
                            this.add(shapes);
                            JLabel text = new JLabel("L");
                            text.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text);
                            break;
                    }
                    break;
                case 11:
                    switch(this.yrow){
                        case 13:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 3, 0, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes = new Icon(13);
                            shapes.setPreferredSize(new Dimension(20,30));
                            this.add(shapes);
                            JLabel text = new JLabel("M");
                            text.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text);
                            break;
                    }
                    break;
                case 12:
                    switch(this.yrow){
                        case 6:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes = new Icon(14);
                            shapes.setPreferredSize(new Dimension(20,30));
                            this.add(shapes);
                            JLabel text = new JLabel("N");
                            text.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text);
                            break;
                    }
                    break;
                case 13:
                    switch(this.yrow){
                        case 8:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes1 = new Icon(15);
                            shapes1.setPreferredSize(new Dimension(20,30));
                            this.add(shapes1);
                            JLabel text = new JLabel("O");
                            text.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text);
                            break;
                    }
                    break;
                case 14:
                    switch(this.yrow){
                        case 2:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes = new Icon(16);
                            shapes.setPreferredSize(new Dimension(20,30));
                            this.add(shapes);
                            JLabel text = new JLabel("P");
                            text.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text);
                            break;
                        case 14:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 0, 0, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes1 = new Icon(17);
                            shapes1.setPreferredSize(new Dimension(20,30));
                            this.add(shapes1);
                            JLabel text1 = new JLabel("Q");
                            text1.setFont(new Font("Helvetica", Font.BOLD, 15));
                            text1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                            text1.setHorizontalAlignment(SwingConstants.CENTER);
                            this.add(text1);
                            break;
                    }
                    break;
                case 15:
                    switch(this.yrow){
                        case 5:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 0, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            break;
                        case 11:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 0, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            break;
                    }
                    break;
                default: break;
            }
        }

    }

    public void backgroundSetComplex(){
        //TODO Implement complex board in future implementation
    }

    public int getx(){
        return this.xcolumn;
    }

    public int gety(){
        return this.yrow;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        switch(count){
            case 0:
                break;
            case 1: 
                g.setColor(Color.blue);
                g.fillPolygon(new int[] {10, 25, 40}, new int[] {40, 5, 40}, 3);
                break;
            case 2:
                g.setColor(Color.GREEN);
                g.fillRect(10, 10, 30, 30);
                break;
            case 3:
                g.setColor(Color.pink);
                g.fillRect(10, 10, 30, 30);
                break;
            case 4:
                g.setColor(Color.pink);
                g.fillOval(10, 10, 30, 30);
                break;
            case 5:
                g.setColor(Color.black);
                g.fillOval(10, 10, 30, 30);
                break;
            case 6:
                g.setColor(Color.red);
                g.fillOval(10, 10, 30, 30);
                break;
            case 7:
                g.setColor(Color.blue);
                g.fillOval(10, 10, 30, 30);
                break;
            case 8:
                g.setColor(Color.green);
                g.fillOval(10, 10, 30, 30);
                break;
            case 9:
                g.setColor(Color.magenta);
                g.fillOval(10, 10, 30, 30);
                break;
            case 10:
                g.setColor(Color.blue);
                g.fillRect(10, 10, 30, 30);
                break;
            case 11:
                g.setColor(Color.yellow);
                g.fillOval(10, 10, 30, 30);
                break;
            case 12:
                g.setColor(Color.darkGray);
                g.fillOval(10, 10, 30, 30);
                break;
            case 13:
                g.setColor(Color.ORANGE);
                g.fillOval(10, 10, 30, 30);
                break;
            case 14:
                g.setColor(Color.cyan);
                g.fillOval(10, 10, 30, 30);
                break;
            case 15:
                g.setColor(Color.green);
                g.fillPolygon(new int[] {10, 25, 40}, new int[] {40, 5, 40}, 3);
                break;
            case 16:
                g.setColor(Color.yellow);
                g.fillPolygon(new int[] {10, 25, 40}, new int[] {40, 5, 40}, 3);
                break;
            case 17:
                g.setColor(Color.RED);
                g.fillRect(10,10,30,30);
                break;
            case 18:
                g.setColor(Color.BLUE);
                g.fillRect(15,10,15,25);
                g.fillRect(5,30,35,10);
                break;
            case 19:
                g.setColor(Color.RED);
                g.fillRect(15,10,15,25);
                g.fillRect(5,30,35,10);
                break;
            case 20:
                g.setColor(Color.BLACK);
                g.fillRect(15,10,15,25);
                g.fillRect(5,30,35,10);
                break;
            case 21:
                g.setColor(new Color(0,102,0));
                g.fillRect(15,10,15,25);
                g.fillRect(5,30,35,10);
            default: break;
        }
    }


}