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
                path = path.replace('\\', '/');
                break;
            case "Windows 10":
                path = path.replace('/', '\\');
                break;
            default:
                System.out.println("2");
                System.exit(1);
        }
        return path;
    }

    public BufferedImage getImage() {
        return imageOriginal;
    }
}
