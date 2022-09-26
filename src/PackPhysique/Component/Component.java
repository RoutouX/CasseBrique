package PackPhysique.Component;

import PackPhysique.Component.ComponentThread.ThreadComponentOnHitComponentEvent;
import PackPhysique.Component.ComponentThread.ThreadComponentTakeMove;
import PackPhysique.Map;

import java.awt.*;

public class Component extends Thread{
    //Dependance
    private Map map; /** Stock une referance de l'object qui la crée*/

    //Proprieter
    private HitBox hitBox; /** Stock la hit box de l'object */

    private Vitesse vitesse;/** Stock la vitesse de l'object */


    //Parametre
    private boolean isMovable = false; /** Le component peut bougé ? */
    private boolean onHitComponentEvent = false;/** Le component declanche l'event onHit lors de colision ? */


    //Thread
    private ThreadComponentTakeMove takeMove = null; /** Stock une referance vers le thread qui deplace l'object */
    private ThreadComponentOnHitComponentEvent threadOnHitEvent = null;/** Stock une referance vers le thread qui appelle l'envent on hit event */


    /**
     *      Component
     * @param map
     * @param x
     * @param y
     * @param sizeX
     * @param sizeY
     * @param isMovable
     * @param onHitComponentEvent
     *
     * Constructeur de la classe
     * Stock la referance de la map qui a crée le component
     * genere une nouvelle hitBox en fonction de la taille et la position en parametre
     *
     * definit les boolean qui active ou nom les thread de deplacement et onHit
     *
     * genere une vitesse de direction 0 et vitesse 0
     */
    public Component(Map map, double x, double y, double sizeX, double sizeY, boolean isMovable, boolean onHitComponentEvent) {
        this.map = map;
        this.hitBox = new HitBox(x, y, sizeX, sizeY);

        this.isMovable = isMovable;
        this.onHitComponentEvent = onHitComponentEvent;

        vitesse = new Vitesse(new Position(0, 0), 0);
    }


    /**
     *      run
     *démare les thread nesécaire a l'object (celon les boolean :isMovable, onHitComponentEvent)
     */
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


    /**
     *      activeTakeMove
     * Crée et démare le thread takeMove (celui qui déplace le component)
     */
    public void activeTakeMove(){
        isMovable = true;
        takeMove = new ThreadComponentTakeMove(this);
        takeMove.start();
    }

    /**
     *      stopTakeMove
     *  termine le thread takeMove pour qu'il aille dans le garbadge collector
     */
    public void stopTakeMove(){
        isMovable = false;
        takeMove.terminate();
        takeMove = null;
    }


    /**
     *      activeOnHitComponentEvent
     * Crée et démare le thread OnHitEvent (celui qui appelle L'evenement onHitEvennt)
     */
    public void activeOnHitComponentEvent(){
        onHitComponentEvent = true;
        threadOnHitEvent = new ThreadComponentOnHitComponentEvent(this);
        threadOnHitEvent.start();
    }

    /**
     *      stopOnHitComponentEvent
     *  termine le thread onHitComponent pour qu'il aille dans le garbadge collector
     */
    public void stopOnHitComponentEvent(){
        onHitComponentEvent = false;
        threadOnHitEvent.terminate();
        threadOnHitEvent = null;
    }


    /**
     *      terminate
     *  Met fin au deux thread pour que l'object aille dans le garbadge colector
     */
    public void terminate(){
        if (takeMove != null){
            stopTakeMove();
        }
        if (threadOnHitEvent != null){
            stopOnHitComponentEvent();
        }
    }


    /**
     * Les trois methose ci dessou sont appeler dans les thread, et ont pour but detre @Overide par les classe enfant de celle ci
     *
     */
    public void onStopXEvent(Component component){}
    public void onStopYEvent(Component component){}
    public void onHitEvent(Component component){}


    /**
     * Les methode ci dessous sont les get/set des variable
     */
    public double getVitesseX() {
        return vitesse.getSpeedX();
    }

    public double getVitesseY() {
        return vitesse.getSpeedY();
    }

    public Vitesse getVitesse(){return vitesse;}
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
