package PackPhysique.Component.ComponentThread;

import PackPhysique.Component.Component;
import PackPhysique.Component.HitBox;

public class ThreadComponentTakeMove extends Thread{

    private Component component;
    private boolean running = true;
    private static double speedLoop = 5; //ms

    public ThreadComponentTakeMove(Component component) {
        this.component = component;
    }

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
