package PackPhysique.Component.ComponentThread;

import PackPhysique.Component.Component;

public class ThreadComponentOnHitComponentEvent extends Thread {

    private Component component;/** Le composent a qui appartien le thread */
    private boolean running = true;/** boolean qui permet l'arret de la boucle */
    private static double speedLoop = 50; /** Delay entre chaque occurance de la boucle (ms) */


    /**
     *      ThreadComponentOnHitComponentEvent
     * @param component
     *
     * Constructeur de la classe, definit le composent a qui appartien le thread
     */
    public ThreadComponentOnHitComponentEvent(Component component) {
        this.component = component;
    }


    /**
     *      run
     * défini la boucle du thread
     */
    @Override
    public void run() {
        while (running) { /** boucle du thread, sarrette lorsque runnnig passe a false */
            try {/** Essay de mettre en pause le thread */
                sleep((long) speedLoop);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Component[] components = component.getMap().getListComponent();/** Recupere la liste des composent de la map */
            for (Component mapComponent : components) {/** Pour chaque compossent */
                if (component.getHitBox().isHit(mapComponent.getHitBox())) { /** Vérifie si la hit box du component et celle des component de la map ce touche */
                    component.onHitEvent(mapComponent);/** Si oui on genere l'evenement on hit event et l'onn envoie le component */
                }
            }
        }
    }


    /**
     *      terminate
     * stop la répétitionn de la boucle
     */
    public void terminate(){
        running = false;
    }
}