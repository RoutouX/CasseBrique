package PackPhysique.Component;

import PackPhysique.Component.ComponentThread.ThreadDeplacementRaquette;
import PackPhysique.Map;

public class Raquette extends Component{

    private static double sizeX = 200;
    private static double sizeY = 20;
    private static boolean isMovable = false;
    private static boolean onHitComponentEvent = false;

    private ThreadDeplacementRaquette threadDeplacementRaquette;

    public Raquette(Map map, double x, double y) {
        super(map, x, y, sizeX, sizeY, isMovable, onHitComponentEvent);
    }

    @Override
    public void run(){
        super.run();
        threadDeplacementRaquette = new ThreadDeplacementRaquette(this);
        threadDeplacementRaquette.start();
    }

    @Override
    public void terminate(){
        super.terminate();
        threadDeplacementRaquette.terminate();
    }




    public static double getSizeX() {
        return sizeX;
    }

    public static double getSizeY() {
        return sizeY;
    }
}
