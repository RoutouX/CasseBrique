package PackPhysique.Component.ComponentThread;

import java.awt.MouseInfo;
import PackPhysique.Component.Component;
import PackPhysique.Component.Raquette;

public class ThreadDeplacementRaquette extends Thread{
    private Raquette raquette;
    private boolean running = true;
    private static double speedLoop = 10; //ms


    public ThreadDeplacementRaquette(Raquette raquette) {
        this.raquette = raquette;
    }

    @Override
    public void run() {
        while (running) {
            try {
                sleep((long) speedLoop);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
            //System.out.println(mouseX);
            double newPosition = (mouseX - (Raquette.getSizeX()/2));
            raquette.getHitBox().setX(newPosition);
        }
    }

    public void terminate(){
        running = false;
    }
}
