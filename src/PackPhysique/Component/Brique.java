package PackPhysique.Component;

import PackPhysique.Component.Component;
import PackPhysique.Map;

public class Brique extends Component {

    private static boolean isMovable = false;
    private static boolean onHitComponentEvent = false;

    private static double sizeX = 200;
    private static double sizeY = 50;

    public Brique(Map map, double x, double y) {
        super(map, x, y, sizeX, sizeY, isMovable, onHitComponentEvent);
    }

    public static double getSizeX() {
        return sizeX;
    }

    public static double getSizeY() {
        return sizeY;
    }
}
