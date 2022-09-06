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

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setVisible(true);

        Dimension dimension = getSize();
        this.sizeXScreen = dimension.width;
        this.sizeYScreen = dimension.height;
        System.out.println(sizeXScreen);
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
