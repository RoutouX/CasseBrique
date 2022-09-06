package PackGraphique;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Resource {
    private BufferedImage imageOriginal;

    public Resource(String path) throws IOException {
        this.imageOriginal = ImageIO.read(new File(System.getProperty("user.dir") + pathForMyOs(path)));
    }

    private String pathForMyOs(String path){
        switch (System.getProperty("os.name")) {
            case "Linux":
                path.replace("\\", "/");
                break;
            case "Windows":
                path.replace("/", "\\");
                break;
        }
        return path;
    }

    public BufferedImage getImage() {
        return imageOriginal;
    }
}
