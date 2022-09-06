package PackPhysique.Component.ComponentThread;

import PackPhysique.Component.Component;

public class ThreadComponentOnHitComponentEvent extends Thread {

    private Component component;
    private boolean running = true;
    private static double speedLoop = 50; //ms


    public ThreadComponentOnHitComponentEvent(Component component) {
        this.component = component;
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
            for (Component mapComponent : components) {
                if (component.getHitBox().isHit(mapComponent.getHitBox())) {
                    component.onHitEvent(mapComponent);
                }
            }
        }
    }

    public void terminate(){
        running = false;
    }
}