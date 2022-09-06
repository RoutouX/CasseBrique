package PackGraphique;

import java.io.IOException;

public class ResourceRaquette extends Resource{

    private static String path = "\\asset\\Raquette.png";

    public ResourceRaquette() throws IOException {
        super(path);
    }
}
