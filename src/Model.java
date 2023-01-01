import java.util.LinkedList;
import java.util.Random;

/**
 * Klassen Model representerar asteroids centrala datastruktur och spellogiken
 */
public class Model {
    Spaceship ship = new Spaceship();
    Bullet b = new Bullet();
    Rock rock = new Rock();
    private double tempDirection = -0.3;
    private String startMove = "";


    //kollar om skeppet och stenarna har kolliderat. Returnar då "crash"
    public String checkCrash() {
        for (int i = 0; i < ship.xPos().length; i++) {
            for (int j = 0; j < rock.getRockList().size(); j +=3) {
                if ((rock.getRockList().get(j) < ship.xPos()[i] && ship.xPos()[i] < rock.getRockList().get(j) + rock.getRockList().get(j+2)) && (rock.getRockList().get(j+1) < ship.yPos()[i] && ship.yPos()[i] < rock.getRockList().get(j+1) + rock.getRockList().get(j+2)))
                    return "crash";
            }
        }
        return "";
    }

    //initierar stenarnas position och skickar tillbaka nya position
    public LinkedList<Double> initializeRock() {
        return rock.initializeRock();
    }

    //uppdaterar stenens hastighet
    public void updateRockSpeed(){
        rock.updateRockSpeed();
    }
    public String getStartMove(){
        return this.startMove;
    }
    public void startMove(){
        this.startMove = "moving";
    }

    public LinkedList<Double> getRockList(){
        return rock.getRockList();
    }

    //kollar om kula kolliderat med sten. tar då bort kulan och skapar en ny, mindre sten alternativt tar bort stenen beroende på dess storlek.
    public void rockCollision(){
        int tempBulletSize = b.getBulletList().size();
        outerloop:
            for(int j = 0;j<b.getBulletList().size(); j = j+2){
                for(int i = 0; i<rock.getRockList().size(); i = i+3){
                    if((rock.getRockList().get(i)  < b.getBulletList().get(j) && b.getBulletList().get(j)  < rock.getRockList().get(i) + 50) && (rock.getRockList().get(i+1)  < b.getBulletList().get(j+1) && b.getBulletList().get(j+1)  < rock.getRockList().get(i+1) + 50)){
                        double tempXStart = rock.getRockList().get(i);
                        double tempYStart = rock.getRockList().get(i+1);
                        double tempSize = rock.getRockList().get(i+2);

                        LinkedList <Double> tempBulletList = b.getBulletList();
                        LinkedList <Double> tempBulletDir = b.getBulletDirList();
                        LinkedList <Double> tempRockList = rock.getRockList();
                        LinkedList <Double> tempRockStartList = rock.getRockStartList();
                        LinkedList <Double> tempRockSpeedList = rock.getRockSpeedList();
                        LinkedList <Double> tempRockDirList = rock.getRockDirList();
                        tempBulletList.remove(j+1);
                        tempBulletList.remove(j);
                        b.setBulletList(tempBulletList);


                        tempBulletDir.remove(j+1);
                        tempBulletDir.remove(j);
                        b.setBulletDirList(tempBulletDir);
                        if (tempSize == 30) {
                            for(int b = 2; b>-1; b--) {
                                tempRockList.remove(i + b);
                                tempRockStartList.remove(i + b);
                                tempRockSpeedList.remove(i + b);
                                tempRockDirList.remove(i + b);
                                rock.setRockLists(tempRockList, tempRockStartList, tempRockSpeedList,tempRockDirList);
                            }
                        }
                        else {
                            if (tempSize == 40) {
                                tempRockList.set(i + 2, 30.0);
                            }
                            if (tempSize == 50) {
                                tempRockList.set(i + 2, 40.0);
                            }
                            tempRockList.set(i + 1, tempYStart);
                            tempRockList.set(i, tempXStart);
                            tempRockStartList.set(i, tempXStart);
                            tempRockStartList.set(i + 1, tempYStart);

                            for(int b = 2; b>-1; b--) {
                                tempRockSpeedList.set(i + b, 0.4);
                                tempRockDirList.set(i + b, 0.3);
                            }

                            for(int k = 0; k<2; k++) {
                                tempRockList.add(tempXStart);
                                tempRockList.add(tempYStart);
                                if (tempSize == 40) {
                                    tempRockList.add(30.0);
                                }
                                if (tempSize == 50) {
                                    tempRockList.add(40.0);
                                }
                                tempRockStartList.add(tempXStart);
                                tempRockStartList.add(tempYStart);
                                tempRockStartList.add(0.0);

                                for(int b = 2; b>-1; b--) {
                                    tempRockSpeedList.add(0.4);
                                }

                                tempRockDirList.add(-0.6);
                                tempRockDirList.add(this.tempDirection);
                                tempRockDirList.add(0.0);

                                this.tempDirection = -tempDirection;
                            }

                            rock.setRockLists(tempRockList, tempRockStartList, tempRockSpeedList,tempRockDirList);
                        }
                        if(tempBulletSize-2 == b.getBulletList().size()){
                            break outerloop;
                        }
                }
            }
        }
    }

    //uppdaterar kulans position och tar bort den om den når skärmens kant
    public LinkedList<Double> moveShot(){
            return b.moveShot();
    }

    //lägger till x och y position för kulan. Lägger också till x och y för hastighet i kulans hastighetslista.
    public void addShot(){
        b.addShot(ship.xPos(), ship.yPos());
    }


    //tar in string som väljer vilket håll skeppet ska roteras åt
    public void rotateShip(String r) {
      ship.rotateShip(r);
    }


    //gör så skeppet och stenarna kommer ut på andra x sidan vid kollision med kanten av skärmen
    public void otherSideX(){
        ship.otherShipSideX();

        rock.otherRockSideX();
    }


    //gör så skeppet och stenarna kommer ut på andra y sidan vid kollision med kanten av skärmen
    public void otherSideY(){
       ship.otherShipSideY();

        rock.otherRockSideY();
    }

    //rör skeppet framåt
    public void moveShip(String r){
       ship.moveShip(r);
    }


    //initierar startpostion för skeppet
    public void initializePos(){
       ship.initializePos();
    }
    //returnerar skeppets current x position
    public double[] xPos(){//Returnerar instanslista x. oklart om behövs
        return ship.xPos();
    }

    //returnerar skeppets current y position
    public double[] yPos(){//Returnerar instanslista y oklart om behövs
        return ship.yPos();
    }


    public static void main(String[] args) {

}
}
