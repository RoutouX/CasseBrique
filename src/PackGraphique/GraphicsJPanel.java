package PackGraphique;

import javax.swing.*;
import java.awt.*;
import PackPhysique.Component.Component;

public class GraphicsJPanel extends JPanel {

    private FenetreJFrame fenetreJFrame;

    public GraphicsJPanel(FenetreJFrame fenetreJFrame) {
        this.fenetreJFrame = fenetreJFrame;
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g = drawHitBoxes(g);
    }

    //public Graphics drawComponentSkin(Graphics g){
        //Component[] componentsList = fenetreJFrame.getRunGraphics().getRunGame().getRunPhysique().getMap().getListComponent();
        //for (Component component: componentsList){
            //g = component.drawComponentSkin(g);
        //}
        //return g;
    //}

    public Graphics drawHitBoxes(Graphics g){
        Component[] componentsList = fenetreJFrame.getMoteurGraphique().getRunGame().getMoteurPhysique().getMap().getListComponent();
        for (Component component: componentsList){
            g = component.getHitBox().drawHitBox(g);
        }
        return g;
    }
}
