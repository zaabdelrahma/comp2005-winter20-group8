import java.util.*;
import java.awt.*;
import javax.swing.*;

// This class is created for users to be able to click on individual robot pieces and move them accordingly
public class RobotPosition extends JPanel{

    public int x,y,rcount;

    public RobotPosition(int x, int y){
        super();
        this.setSize(30,30);
        this.setBackground(Color.white);
        this.x = x;
        this.y = y;
        
    }

    public void colourrobot(int count){ // "Color" option
        this.rcount = count;
        repaint();
    }

    public void colorrobottext(int newcount){ // "Text" option, to be further implemented
        switch(newcount){
            case 0:
                this.add(new JLabel("Alpha"));
                break;
            case 1:
                this.add(new JLabel("Beta"));
                break;
            case 2:
                this.add(new JLabel("Charlie"));
                break;
            case 3:
                this.add(new JLabel("Delta"));
                break;
            default:break;
        }
    }

    public int returnrobotx(){
        return this.x;
    }

    public int returnroboty(){
        return this.y;
    }

    @Override
    public void paintComponent(Graphics g){ // Setting up the color and shape for each robot piece
        super.paintComponent(g);

        switch(this.rcount){
            case 0:
                g.setColor(Color.BLUE);
                g.fillRect(15,10,15,25);
                g.fillRect(5,30,35,10);
                break;
            case 1:
                g.setColor(Color.RED);
                g.fillRect(15,10,15,25);
                g.fillRect(5,30,35,10);
                break;
            case 2:
                g.setColor(Color.BLACK);
                g.fillRect(15,10,15,25);
                g.fillRect(5,30,35,10);
                break;
            case 3:
                g.setColor(new Color(0,102,0));
                g.fillRect(15,10,15,25);
                g.fillRect(5,30,35,10);
            default: break;
        }
    }

}