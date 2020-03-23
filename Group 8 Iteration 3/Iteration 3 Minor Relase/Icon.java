import java.awt.*;
import javax.swing.*;

// Class for drawing
public class Icon extends JPanel{

    private int count;
    
    public Icon(int count){
        super.setPreferredSize(new Dimension(50,50));
        this.count = count;
        this.setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        
        // Draw different shapes and colours according to the count passed through as parameter
        // FillPolygon = Triangle
        // FillOval = Circle
        // FillRect = Squares
        switch(this.count){
            case 0:
                g.setColor(Color.ORANGE);
                g.fillOval(10, 10, 30, 30);
                break;
            case 1: 
                g.setColor(Color.blue);
                g.fillPolygon(new int[] {10, 25, 40}, new int[] {40, 5, 40}, 3);
                break;
            case 2:
                g.setColor(Color.GREEN);
                g.fillRect(0, 0, 30, 30);
                break;
            case 3:
                g.setColor(Color.pink);
                g.fillRect(0, 0, 30, 30);
                break;
            case 4:
                g.setColor(Color.pink);
                g.fillOval(5, 5, 20, 20);
                break;
            case 5:
                g.setColor(Color.black);
                g.fillOval(5, 5, 20, 20);
                break;
            case 6:
                g.setColor(Color.red);
                g.fillOval(5, 5, 20, 20);
                break;
            case 7:
                g.setColor(Color.blue);
                g.fillOval(5, 5, 20, 20);
                break;
            case 8:
                g.setColor(Color.green);
                g.fillOval(5, 5, 20, 20);
                break;
            case 9:
                g.setColor(Color.magenta);
                g.fillOval(5, 5, 20, 20);
                break;
            case 10:
                g.setColor(Color.blue);
                g.fillRect(0, 0, 30, 30);
                break;
            case 11:
                g.setColor(Color.yellow);
                g.fillOval(5, 5, 20, 20);
                break;
            case 12:
                g.setColor(Color.darkGray);
                g.fillOval(5, 5, 20, 20);
                break;
            case 13:
                g.setColor(Color.pink);
                g.fillOval(5, 5, 20, 20);
                break;
            case 14:
                g.setColor(Color.cyan);
                g.fillOval(5, 5, 20, 20);
                break;
            case 15:
                g.setColor(Color.green);
                g.fillPolygon(new int[] {0, 10, 20}, new int[] {30, 5, 30}, 3);
                break;
            case 16:
                g.setColor(Color.yellow);
                g.fillPolygon(new int[] {0, 10, 20}, new int[] {30, 5, 30}, 3);
                break;
            default: break;
        }
    }

    public int getcount(){
        return this.count;
    }
}