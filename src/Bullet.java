import java.util.LinkedList;

public class Bullet {
    Spaceship s = new Spaceship();
    private LinkedList<Double> bulletList = new LinkedList<Double>();
    private LinkedList<Double> bulletDirList = new LinkedList<Double>();

    public LinkedList<Double> getBulletList(){
        return this.bulletList;
    }
    public LinkedList<Double> getBulletDirList(){
        return this.bulletDirList;
    }

    public void setBulletList(LinkedList<Double> posList){
         this.bulletList = posList;
    }
    public void setBulletDirList(LinkedList<Double> dirList){
         this.bulletDirList = dirList;
    }

    //lägger till x och y position för kulan. Lägger också till x och y för hastighet i kulans hastighetslista.
    public void addShot(double[] shipXpos, double[] shipYpos){
        double shotSpeed = 0.07;
        double yeetedXShot = shipXpos[0] + shotSpeed* s.distanceforBulletX();
        double yeetedYShot = shipYpos[0] + shotSpeed* s.distanceforBulletY();
        this.bulletList.add(yeetedXShot);
        this.bulletList.add(yeetedYShot);

        double tempCentroidX = (shipXpos[0] + shipXpos[1] + shipXpos[2]) / 3;
        double tempCentroidY = (shipYpos[0] + shipYpos[1] + shipYpos[2]) / 3;
        double tempFrontX = shipXpos[0] - tempCentroidX;
        double tempFrontY = shipYpos[0] - tempCentroidY;

        this.bulletDirList.add(tempFrontX);
        this.bulletDirList.add(tempFrontY);

    }

    //uppdaterar kulans position och tar bort den om den når skärmens kant
    public LinkedList<Double> moveShot(){
        for(int i = 0; i < this.bulletList.size(); i = i+2) {
            this.bulletList.set(i, this.bulletList.get(i) + this.bulletDirList.get(i)/10);
            this.bulletList.set(i+1, this.bulletList.get(i+1) +this.bulletDirList.get(i+1)/10);
            if(this.bulletList.get(i) > 1000 || this.bulletList.get(i) < 0 || this.bulletList.get(i+1) > 1000 ||this.bulletList.get(i+1) < 0){
                this.bulletList.remove(i+1);
                this.bulletDirList.remove(i+1);
                this.bulletList.remove(i);
                this.bulletDirList.remove(i);
            }
        }
        return this.bulletList;
    }
}
