import java.util.LinkedList;
import java.util.Random;

public class Rock {
    private LinkedList<Double> rockList = new LinkedList<Double>();
    private LinkedList<Double> rockStartPosList = new LinkedList<Double>();
    private LinkedList<Double> rockSpeedList =  new LinkedList<Double>();
    private LinkedList<Double> rockDirection = new LinkedList<Double>();
    private boolean drawOnce = false;

    public void setRockLists(LinkedList<Double> rList, LinkedList<Double> rStartPosList ,LinkedList<Double> rSpeedList,LinkedList<Double> rDir){
        this.rockList = rList;
        this.rockStartPosList = rStartPosList;
        this.rockSpeedList = rSpeedList;
        this.rockDirection = rDir;
    }

    public LinkedList<Double> getRockList(){
        return this.rockList;
    }
    public LinkedList<Double> getRockStartList(){
        return this.rockStartPosList;
    }
    public LinkedList<Double> getRockSpeedList(){
        return this.rockSpeedList;
    }
    public LinkedList<Double> getRockDirList(){
        return this.rockDirection;
    }

    //initierar stenarnas position och skickar tillbaka nya position
    public LinkedList<Double> initializeRock() {
        if (this.drawOnce == false) {
            for (int i = 0 ; i<4; i++){
                this.rockSpeedList.add(0.4);
                this.rockSpeedList.add(0.4);
                this.rockSpeedList.add(0.0);
            }
            Random rand = new Random();
            this.rockStartPosList.add((double)rand.nextInt((400 - 100) + 1) + 100);
            this.rockStartPosList.add((double)rand.nextInt((400 - 100) + 1) + 100);
            this.rockStartPosList.add(0.0);
            this.rockStartPosList.add((double)rand.nextInt((400 - 100) + 1) + 100);
            this.rockStartPosList.add((double)rand.nextInt((900 - 600) + 1) + 600);
            this.rockStartPosList.add(0.0);
            this.rockStartPosList.add((double)rand.nextInt((900 - 600) + 1) + 600);
            this.rockStartPosList.add((double)rand.nextInt((400 - 100) + 1) + 100);
            this.rockStartPosList.add(0.0);
            this.rockStartPosList.add((double)rand.nextInt((900 - 600) + 1) + 600);
            this.rockStartPosList.add((double)rand.nextInt((900 - 600) + 1) + 600);
            this.rockStartPosList.add(0.0);
            this.rockList.addAll(this.rockStartPosList);
            for(int i = 0; i<rockList.size(); i+=3) {
                this.rockList.set(i + 2, 50.0);
            }
            for(int i = 0; i<this.rockStartPosList.size(); i++){
                this.rockDirection.add(0.3);
            }
            drawOnce = true;
        }
        for (int i = 0 ; i<this.rockList.size(); i= i+3){
            double x = this.rockList.get(i);
            double y = this.rockList.get(i+1);
            if ((int)x>1051 || (int)x<-51 || (int)y>1051 || (int)y<-51) {
                this.rockSpeedList.set(i, 0.4);
            }
        }
        updateRockSpeed();
        for(int i = 0; i<rockList.size(); i+=3) {
            this.rockList.set(i, this.rockStartPosList.get(i) + this.rockSpeedList.get(i));
            this.rockList.set(i+1, this.rockStartPosList.get(i+1) + this.rockSpeedList.get(i+1));
        }
        return rockList;
    }

    //uppdaterar stenens hastighet
    public void updateRockSpeed(){
        for (int i=0; i<this.rockSpeedList.size(); i=i+3) {
            this.rockSpeedList.set(i, this.rockSpeedList.get(i) + this.rockDirection.get(i));
            this.rockSpeedList.set(i+1, this.rockSpeedList.get(i+1) + this.rockDirection.get(i+1));
        }
    }



    //gör så skeppet och stenarna kommer ut på andra x sidan vid kollision med kanten av skärmen
    public void otherRockSideX(){
        for (int i = 0; i<this.rockStartPosList.size(); i += 3){
            if (this.rockList.get(i) > 1051){
                this.rockStartPosList.set(i, 0.0);
                this.rockList.set(i, 0.0);
                this.rockSpeedList.set(i, 0.4);
            }
            if (this.rockList.get(i) < -51){
                this.rockStartPosList.set(i, 1000.0);
                this.rockList.set(i, 1000.0);
                this.rockSpeedList.set(i, 0.4);
            }
        }
    }

    public void otherRockSideY(){
        for (int i = 0 ; i<this.rockStartPosList.size(); i += 3){
            if (this.rockList.get(i+1) > 1051){
                this.rockStartPosList.set(i+1, 0.0);
                this.rockList.set(i+1, 0.0);
                this.rockSpeedList.set(i+1, 0.4);
            }
            if (this.rockList.get(i+1) < -51){
                this.rockStartPosList.set(i+1, 1000.0);
                this.rockList.set(i+1, 1000.0);
                this.rockSpeedList.set(i+1, 0.4);
            }
        }
    }
}