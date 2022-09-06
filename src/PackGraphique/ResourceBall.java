package PackGraphique;

import java.io.IOException;

public class ResourceBall extends Resource{

    private static String path = "\\asset\\Balle.png";

    public ResourceBall() throws IOException {
        super(path);
    }
}
