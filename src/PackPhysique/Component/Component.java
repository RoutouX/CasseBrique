package PackPhysique.Component;

import PackPhysique.Component.ComponentThread.ThreadComponentOnHitComponentEvent;
import PackPhysique.Component.ComponentThread.ThreadComponentTakeMove;
import PackPhysique.Map;

import java.awt.*;

public class Component extends Thread{
    //Dependance
    private Map map;

    //Proprieter
    private HitBox hitBox;
    private double vitesseX = 0;    //  cm/s
    private double vitesseY = 0;    //  cm/s


    //Parametre
    private boolean isMovable = false;
    private boolean onHitComponentEvent = false;


    //Thread
    private ThreadComponentTakeMove takeMove = null;
    private ThreadComponentOnHitComponentEvent threadOnHitEvent = null;


    //Graphics

    public Component(Map map, double x, double y, double sizeX, double sizeY, boolean isMovable, boolean onHitComponentEvent) {
        this.map = map;
        this.hitBox = new HitBox(x, y, sizeX, sizeY);

        this.isMovable = isMovable;
        this.onHitComponentEvent = onHitComponentEvent;
    }

    @Override
    public void run() {
        super.run();
        if (isMovable == true) {
            activeTakeMove();
        }
        if (onHitComponentEvent == true){
            activeOnHitComponentEvent();
        }
    }



    public void activeTakeMove(){
        isMovable = true;
        takeMove = new ThreadComponentTakeMove(this);
        takeMove.start();
    }
    public void stopTakeMove(){
        isMovable = false;
        takeMove.terminate();
        takeMove = null;
    }
    public void activeOnHitComponentEvent(){
        onHitComponentEvent = true;
        threadOnHitEvent = new ThreadComponentOnHitComponentEvent(this);
        threadOnHitEvent.start();
    }
    public void stopOnHitComponentEvent(){
        onHitComponentEvent = false;
        threadOnHitEvent.terminate();
        threadOnHitEvent = null;
    }


    public void terminate(){
        if (takeMove != null){
            stopTakeMove();
        }
        if (threadOnHitEvent != null){
            stopOnHitComponentEvent();
        }
        vitesseX = 0;
        vitesseY = 0;
    }


    public void onStopXEvent(Component component){}
    public void onStopYEvent(Component component){}
    public void onHitEvent(Component component){}


    public Graphics drawComponentSkin(Graphics g){
        //g = this.skin.drawImage(g, this);
        return g;
    }

    public void setVitesseX(double vitesseX) {
        this.vitesseX = vitesseX;
    }

    public void setVitesseY(double vitesseY) {
        this.vitesseY = vitesseY;
    }

    public double getVitesseX() {
        return vitesseX;
    }

    public double getVitesseY() {
        return vitesseY;
    }

    public double getX(){
        return hitBox.getX();
    }

    public double getY(){
        return hitBox.getY();
    }

    public HitBox getHitBox() {
        return hitBox;
    }

    public Map getMap() {
        return map;
    }



}
