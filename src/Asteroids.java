import	java.awt.event.*;
import java.util.TimerTask;
import java.util.Timer;

/**
 * Asteroids klassen är programmets "Control" del i MVC modellen.
 * Beskriver hur Model och View är kopplade och reagerar på input från tangenterna
 */
public class Asteroids
    implements KeyListener {
    Model m = new Model();
    View view = new View("");
    /**
     *  Asteroids konstruktor tar startpositioner för det som ska ritas från Model och skickar in i View.
     *  Har även programmets huvudloop som kollar de uppdaterade värdena från Model till View.
     */
    public Asteroids(){
        view.setModel(m);
        m.initializePos();
        m.initializeRock();
        m.xPos();
        m.yPos();
        view.updateView();

        view.addKeyListener(this);

        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            public void run(){
                if (!m.checkCrash().equals("crash")) {
                    if (m.getStartMove() == "moving") {
                        m.moveShip("");
                    }
                    m.otherSideX();
                    m.otherSideY();
                    m.yPos();
                    m.xPos();
                    m.updateRockSpeed();
                    m.initializeRock();
                    m.rockCollision();
                }
                    view.updateView();
            }
        }, 0, 10);
    }


    //kallar på passande metod beroende på vilken tangent som är nedtryckt
    public void keyPressed(KeyEvent e) {
        if (!m.checkCrash().equals("crash")) {

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                m.rotateShip("right");
                m.yPos();
                m.xPos();
                view.updateView();
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                m.rotateShip("left");
                m.yPos();
                m.xPos();
                view.updateView();
            }

            if (e.getKeyCode() == KeyEvent.VK_UP) {
                m.startMove();
                m.moveShip("u");
                m.moveShip("");
                m.yPos();
                m.xPos();
                view.updateView();
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                m.addShot();
            }
        }
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(1);
            }
        }


    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args){
        new Asteroids();
    }
}