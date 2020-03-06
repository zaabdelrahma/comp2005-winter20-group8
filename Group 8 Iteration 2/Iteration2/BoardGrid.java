import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import java.awt.*;

// Class for the intialisation of the game board GUI
public class BoardGrid extends JPanel{

    private int xcoord, ycoord, xcolumn, yrow;

    public BoardGrid(int xcoord, int ycoord, int xcolumn, int yrow){
        super();
        this.setSize(20,20);
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.xcolumn = xcolumn;
        this.yrow = yrow;
    }

    public void backgroundSet(int visual){
        this.setBackground(Color.WHITE);
        this.setBorder(new LineBorder(Color.lightGray));
        
        if(visual == 1){ // If "Color" is chosen, it will only feature shapes and colors, no text
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
                            Icon shapes = new Icon(1); // Declaring a new JPanel instance of the class Icon to add shapes and color to this particular panel
                            shapes.setPreferredSize(new Dimension(20,30));
                            this.add(shapes);
                            break;
                        case 12:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 3, 0, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes1 = new Icon(2);
                            shapes1.setPreferredSize(new Dimension(30,30));
                            this.add(shapes1);
                            break;
                    }
                    break;
                case 2:
                    switch(this.yrow){
                        case 1:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes = new Icon(3);
                            shapes.setPreferredSize(new Dimension(30,30));
                            this.add(shapes);
                            break;
                        case 14:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes1 = new Icon(4);
                            shapes1.setPreferredSize(new Dimension(30,30));
                            this.add(shapes1);
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
                            shapes.setPreferredSize(new Dimension(30,30));
                            this.add(shapes);
                            break;
                        
                    }
                    break;
                case 4:
                    switch(this.yrow){
                        case 6:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes = new Icon(6);
                            shapes.setPreferredSize(new Dimension(30,30));
                            this.add(shapes);
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
                            shapes.setPreferredSize(new Dimension(30,30));
                            this.add(shapes);
                            break;
                    }
                    break;
                case 6:
                    switch(this.yrow){
                        case 2:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes = new Icon(8);
                            shapes.setPreferredSize(new Dimension(30,30));
                            this.add(shapes);
                            break;
                        case 11:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 0, 0, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes1 = new Icon(9);
                            shapes1.setPreferredSize(new Dimension(30,30));
                            this.add(shapes1);
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
                            shapes.setPreferredSize(new Dimension(30,30));
                            this.add(shapes);
                            break;
                        case 5:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 3, 0, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes1 = new Icon(11);
                            shapes1.setPreferredSize(new Dimension(30,30));
                            this.add(shapes1);
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
                            Icon shapes = new Icon(11);
                            shapes.setPreferredSize(new Dimension(30,30));
                            this.add(shapes);
                            break;
                    }
                    break;
                case 11:
                    switch(this.yrow){
                        case 13:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 3, 0, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes = new Icon(12);
                            shapes.setPreferredSize(new Dimension(30,30));
                            this.add(shapes);
                            break;
                    }
                    break;
                case 12:
                    switch(this.yrow){
                        case 6:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes = new Icon(13);
                            shapes.setPreferredSize(new Dimension(30,30));
                            this.add(shapes);
                            break;
                    }
                    break;
                case 13:
                    switch(this.yrow){
                        case 8:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes = new Icon(14);
                            shapes.setPreferredSize(new Dimension(30,30));
                            this.add(shapes);
                            break;
                    }
                    break;
                case 14:
                    switch(this.yrow){
                        case 2:
                            this.setBorder(new CompoundBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes = new Icon(15);
                            shapes.setPreferredSize(new Dimension(30,30));
                            this.add(shapes);
                            break;
                        case 14:
                            this.setBorder(new CompoundBorder(new MatteBorder(3, 0, 0, 3, Color.BLACK), new LineBorder(Color.lightGray)));
                            Icon shapes1 = new Icon(16);
                            shapes1.setPreferredSize(new Dimension(30,30));
                            this.add(shapes1);
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
        else if(visual == 0){ // If "Text" was chosen, it will only feature letters but not shapes or colors
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
        else{ // Features both colored shapes and letters
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

}