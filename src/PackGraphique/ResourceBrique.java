package PackGraphique;

import java.io.IOException;

public class ResourceBrique extends Resource{

    private static String path = "\\asset\\Brique.png";

    public ResourceBrique() throws IOException {
        super(path);
    }
}
