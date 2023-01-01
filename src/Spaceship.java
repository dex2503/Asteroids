public class Spaceship {
    private double[] x = new double[3];
    private double[] y = new double[3];
    private double distanceFrontX = 0.0;
    private double distanceFrontY = 26.6;
    private double oldDistanceFrontX = 0.0;
    private double oldDistanceFrontY = 26.6;
    private double speed = 0.03;
    private double maxSpeed = 0.5;

    //initierar skeppets startposition
    public void initializePos(){
        this.x[0] = 500;
        this.x[1] = 490;
        this.x[2] = 510;
        this.y[0] = 440;
        this.y[1] = 480;
        this.y[2] = 480;
    }

    //rör skeppet framåt
    public void moveShip(String r){
        if(r.equals("u")) {
            double dacentroidX = (this.x[0] + this.x[1] + this.x[2]) / 3;
            double dacentroidY = (this.y[0] + this.y[1] + this.y[2]) / 3;
            this.distanceFrontX = this.x[0] - dacentroidX;
            this.distanceFrontY = this.y[0] - dacentroidY;
            speedShip();
        }

        double yeetedXFront = this.x[0] + this.speed * this.distanceFrontX;
        double yeetedYFront = this.y[0] + this.speed * this.distanceFrontY;

        double yeetedX1 = this.x[1] + this.speed*this.distanceFrontX;
        double yeetedY1 = this.y[1] + this.speed*this.distanceFrontY;
        double yeetedX2 = this.x[2] + this.speed*this.distanceFrontX;
        double yeetedY2 = this.y[2] + this.speed*this.distanceFrontY;

        this.x[0] = yeetedXFront;
        this.y[0] = yeetedYFront;
        this.x[1] = yeetedX1;
        this.y[1] = yeetedY1;
        this.x[2] = yeetedX2;
        this.y[2] = yeetedY2;

        this.oldDistanceFrontX = this.distanceFrontX;
        this.oldDistanceFrontY = this.distanceFrontY;
    }

    //accelererar, bromsar eller behåller skeppets fart
    public void speedShip(){
        if((this.oldDistanceFrontX > 0 && this.oldDistanceFrontY > 0 && this.distanceFrontX<0 && this.distanceFrontY<0) || (this.oldDistanceFrontX < 0 && this.oldDistanceFrontY > 0 && this.distanceFrontX>0 && this.distanceFrontY<0) || (this.oldDistanceFrontX > 0 && this.oldDistanceFrontY < 0 && this.distanceFrontX<0 && this.distanceFrontY>0)|| (this.oldDistanceFrontX < 0 && this.oldDistanceFrontY < 0 && this.distanceFrontX>0 && this.distanceFrontY>0)){
            this.speed = this.speed *0.6;
        }
        if((this.oldDistanceFrontX < 0 && this.oldDistanceFrontY < 0 && this.distanceFrontX<0 && this.distanceFrontY<0) ||(this.oldDistanceFrontX > 0 && this.oldDistanceFrontY > 0 && this.distanceFrontX>0 && this.distanceFrontY>0)||(this.oldDistanceFrontX < 0 && this.oldDistanceFrontY > 0 && this.distanceFrontX<0 && this.distanceFrontY>0) ||(this.oldDistanceFrontX > 0 && this.oldDistanceFrontY < 0 && this.distanceFrontX>0 && this.distanceFrontY<0)||(this.oldDistanceFrontX == this.distanceFrontX && this.oldDistanceFrontY == this.distanceFrontY)){
            if(this.speed >maxSpeed ){
                this.speed = maxSpeed;
            }
            else {
                this.speed = this.speed * 1.1;
            }
        }
    }

    //tar in string som väljer vilket håll skeppet ska roteras åt
    public void rotateShip(String r) {
        double x0 = this.x[0];
        double x1 = this.x[1];
        double x2 = this.x[2];
        double y0 = this.y[0];
        double y1 = this.y[1];
        double y2 = this.y[2];
        double centroidX = (x0+x1+x2)/3;
        double centroidY = (y0+y1+y2)/3;

        double distanceX1 = x1 - centroidX;
        double distanceY1 = y1 - centroidY;
        double distanceX2 = x2 - centroidX;
        double distanceY2 = y2 - centroidY;
        double distanceFrontX = x0 - centroidX;
        double distanceFrontY = y0 - centroidY;

        double angle = Math.toRadians(10);

        if (r.equals("right")) {
            angle = angle;
        }
        if (r.equals("left")) {
            angle = -angle;
        }

        double rotateSidesX1 = distanceX1 * Math.cos(angle) - distanceY1 * Math.sin(angle);
        double rotateSidesX2 = distanceX2 * Math.cos(angle) - distanceY2 * Math.sin(angle);
        double rotateFrontX = distanceFrontX * Math.cos(angle) - distanceFrontY * Math.sin(angle);
        double rotateSidesY1 = distanceX1 * Math.sin(angle) + distanceY1 * Math.cos(angle);
        double rotateSidesY2 = distanceX2 * Math.sin(angle) + distanceY2 * Math.cos(angle);
        double rotateFrontY = distanceFrontX * Math.sin(angle) + distanceFrontY * Math.cos(angle);

        this.x[0] = centroidX + rotateFrontX;
        this.x[1] = centroidX + rotateSidesX1;
        this.x[2] = centroidX + rotateSidesX2;
        this.y[0] = centroidY + rotateFrontY;
        this.y[1] = centroidY + rotateSidesY1;
        this.y[2] = centroidY + rotateSidesY2;
    }

    //gör så skeppet kommer ut på andra x sidan vid kollision med kanten av skärmen
    public void otherShipSideX(){
        for (int i = 0 ; i<3; i++) {
            if (this.x[i] > 1050 || this.x[i]<-50) {
                double tempDistance0 =this.x[i] - this.x[0];
                double tempDistance1 =this.x[i] - this.x[1];
                double tempDistance2 =this.x[i] - this.x[2];
                if (this.x[i] > 1050){
                    this.x[i] = 0;
                }
                if (this.x[i] < -50){
                    this.x[i] = 1000;
                }
                this.x[0] = this.x[i] - tempDistance0;
                this.x[1] = this.x[i] - tempDistance1;
                this.x[2] = this.x[i] - tempDistance2;
            }
        }
    }


    //gör så skeppet kommer ut på andra y sidan vid kollision med kanten av skärmen
    public void otherShipSideY(){
        for (int i = 0 ; i<3; i++) {
            if (this.y[i] > 1050 || this.y[i]<-50) {
                double tempDistance0 =this.y[i] - this.y[0];
                double tempDistance1 =this.y[i] - this.y[1];
                double tempDistance2 =this.y[i] - this.y[2];
                if (this.y[i] > 1050){
                    this.y[i] = 0;
                }
                if (this.y[i] < -50){
                    this.y[i] = 1000;
                }
                this.y[0] = this.y[i] - tempDistance0;
                this.y[1] = this.y[i] - tempDistance1;
                this.y[2] = this.y[i] - tempDistance2;
            }
        }
    }

    public double distanceforBulletX(){
        return this.distanceFrontX;
    }
    public double distanceforBulletY(){
        return this.distanceFrontY;
    }

    //returnerar skeppets current x position
    public double[] xPos(){//Returnerar instanslista x. oklart om behövs
        return this.x;
    }

    //returnerar skeppets current y position
    public double[] yPos(){//Returnerar instanslista y oklart om behövs
        return this.y;
    }
}
