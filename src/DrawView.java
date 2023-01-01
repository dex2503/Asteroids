import java.awt.*;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 * DrawView klassen ritar, tillsammans med View klassen, upp spelet
 */
public class DrawView extends JPanel {
    private Model m;


    //Tilldelar instansvariabel m med infon från objektet Model m i Asteroidsklassen
    public void setModel(Model m){
        this.m = m;
    }


    public void updateView(){
        this.repaint();
    }


    //ritar upp allt
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[] xs = new int[3];
        for (int i=0; i<3; ++i) {
            xs[i] = (int) m.xPos()[i];
        }
        int[] ys = new int[3];
        for (int i=0; i<3; ++i) {
            ys[i] = (int) m.yPos()[i];
        }

        g.setColor(Color.RED);
        g.fillPolygon(xs,ys,3);

        g.setColor(Color.GRAY);

        
        for(int i = 0; i < m.getRockList().size(); i = i+3) {
                double makeIntRockX = m.getRockList().get(i);
                double makeIntRockY = m.getRockList().get(i + 1);
                double makeSize = m.getRockList().get(i + 2);
                g.fillOval((int) makeIntRockX, (int) makeIntRockY, (int) makeSize, (int) makeSize);
        }
        g.setColor(Color.GREEN);

        if (!m.checkCrash().equals("crash")) {
            LinkedList<Double> bulletArr = m.moveShot();
            for (int i = 0; i < bulletArr.size(); i = i + 2) {
                double makeIntX = bulletArr.get(i);
                int x = (int) makeIntX;
                double makeIntY = bulletArr.get(i + 1);
                int y = (int) makeIntY;
                g.fillOval(x, y, 10, 10);
            }
        }
        if (m.checkCrash().equals("crash")){
            int fontSize = 100;
            g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
            g.setColor(Color.blue);
            g.drawString("Game Over!",200,450);
        }
    }

    public static void main(String[] args) {

    }
}