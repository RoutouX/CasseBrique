package PackPhysique.Component.ComponentThread;

import PackPhysique.Component.Component;
import PackPhysique.Component.HitBox;

public class ThreadComponentTakeMove extends Thread{

    private Component component;/** Le composent a qui appartien le thread */
    private boolean running = true;/** boolean qui permet l'arret de la boucle */
    private static double speedLoop = 5;/** Delay entre chaque occurance de la boucle (ms) */


    /**
     *      ThreadComponentTakeMove
     * @param component on envoie le composent qui ce deplace en x/y
     *
     * On recupere une referance vers l'object qui ce deplace
     */
    public ThreadComponentTakeMove(Component component) {
        this.component = component;
    }


    /**
     *      deplacementX
     * @param components
     */
    public void deplacementX(Component[] components) {
        if (component.getVitesseX() != 0) {
            double nextPositionX;
            nextPositionX = component.getHitBox().getX() + (component.getVitesseX() * (speedLoop / 1000));
            HitBox nextHitBox = new HitBox(nextPositionX, component.getHitBox().getY(), component.getHitBox().getSizeX(), component.getHitBox().getSizeY());

            //NullPointerException
            try {
                for (Component mapComponent : components) {
                    if (component.getId() != mapComponent.getId()) {
                        if (nextHitBox.isHit(mapComponent.getHitBox())) {
                            if (component.getVitesseX() > 0) {
                                nextPositionX = mapComponent.getHitBox().getX() - component.getHitBox().getSizeX();
                                component.getHitBox().setX(nextPositionX);
                                //component.setVitesseX(0);
                                component.onStopXEvent(mapComponent);
                                return;
                            } else {
                                nextPositionX = mapComponent.getHitBox().getX() + mapComponent.getHitBox().getSizeX();
                                component.getHitBox().setX(nextPositionX);
                                //component.setVitesseX(0);
                                component.onStopXEvent(mapComponent);
                                return;
                            }
                        }
                    }
                }
            } catch (NullPointerException e){

            }
            component.getHitBox().setX(nextPositionX);
            return;
        }
    }

    public void deplacementY(Component[] components) {
        if (component.getVitesseY() != 0) {
            double nextPositionY;
            nextPositionY = component.getHitBox().getY() + (component.getVitesseY() * (speedLoop / 1000));
            HitBox nextHitBox = new HitBox(component.getHitBox().getX(), nextPositionY, component.getHitBox().getSizeX(), component.getHitBox().getSizeY());
            //NullPointerException
            try {
            for (Component mapComponent : components) {
                if (mapComponent.getId() != component.getId()) {
                    if (nextHitBox.isHit(mapComponent.getHitBox())) {
                        if (component.getVitesseY() > 0) {
                            nextPositionY = mapComponent.getHitBox().getY() - component.getHitBox().getSizeY();
                            component.getHitBox().setY(nextPositionY);
                            //component.setVitesseY(0);
                            component.onStopYEvent(mapComponent);
                            return;
                        } else {
                            nextPositionY = mapComponent.getHitBox().getY() + mapComponent.getHitBox().getSizeY();
                            component.getHitBox().setY(nextPositionY);
                            //component.setVitesseY(0);
                            component.onStopYEvent(mapComponent);
                            return;
                        }
                    }
                }
            }
            } catch (NullPointerException e){

            }
            component.getHitBox().setY(nextPositionY);
            return;
        }
    }

    @Override
    public void run() {
        while (running) {
            try {
                sleep((long) speedLoop);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Component[] components = component.getMap().getListComponent();

            //Deplacement en X
            deplacementX(components);

            //Deplacement en Y
            deplacementY(components);
        }
    }

    public void terminate(){
        running = false;
    }
}
