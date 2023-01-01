import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * View klassen ritar, tillsammans med DrawView klassen, upp spelet
 */
public class View extends JFrame{
    JLabel label;
    DrawView r = new DrawView();


    public void setModel(Model m){
        r.setModel(m);
    }
    //Skapar skärmen
    public View(String s) {
        super(s);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLocation(500, 30);
        label = new JLabel("Asteroids");
        r.setBackground(Color.BLACK);
        r.add(label);
    }

    //kallar på metod i drawview för repaint. Gör allt i spelet synligt.
    public void updateView(){
        r.updateView();
        add(r);
        setVisible(true);
        }

    public static void main(String[] args) {
        new View("Key Listener Tester");
    }
}