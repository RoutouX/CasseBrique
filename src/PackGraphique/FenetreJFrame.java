package PackGraphique;

import javax.swing.*;
import java.awt.*;

public class FenetreJFrame extends JFrame {

    MoteurGraphique moteurGraphique; // invoker
    GraphicsJPanel graphicsJPanel; // invoked

    //Propriéter
    private int sizeXScreen;
    private int sizeYScreen;

    public FenetreJFrame(MoteurGraphique moteurGraphique) throws HeadlessException {
        this.moteurGraphique = moteurGraphique;

        graphicsJPanel = new GraphicsJPanel(this);

        this.add(graphicsJPanel);

        Dimension dimension;
        switch (System.getProperty("os.name")) {
            case "Linux":
                //Tol
                this.setSize(new Dimension(1300, 700));
                dimension = getSize();
                this.sizeXScreen = dimension.width;
                this.sizeYScreen = dimension.height;

                this.setDefaultCloseOperation(EXIT_ON_CLOSE);

                this.setVisible(true);

                break;
            case "Windows":
                this.setExtendedState(JFrame.MAXIMIZED_BOTH);
                this.setUndecorated(true);

                this.setDefaultCloseOperation(EXIT_ON_CLOSE);

                this.setVisible(true);


                dimension = getSize();
                this.sizeXScreen = dimension.width;
                this.sizeYScreen = dimension.height;
                break;
            default:
                System.out.println("Can't now OS");
                System.exit(1);
        }

        System.out.println("Size screen X = " + sizeXScreen);
        System.out.println("Size screen Y = " + sizeYScreen);
    }

    public int getSizeXScreen() {
        return sizeXScreen;
    }

    public int getSizeYScreen() {
        return sizeYScreen;
    }

    public MoteurGraphique getMoteurGraphique() {
        return moteurGraphique;
    }
}
