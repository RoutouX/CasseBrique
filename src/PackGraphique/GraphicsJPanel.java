package PackGraphique;

import javax.swing.*;
import java.awt.*;

import PackPhysique.Component.Balle;
import PackPhysique.Component.Brique;
import PackPhysique.Component.Component;
import PackPhysique.Component.Raquette;

public class GraphicsJPanel extends JPanel {

    private FenetreJFrame fenetreJFrame;
    private Resource resourceBackGround;
    private Resource resourceBalle;
    private Resource resourceBrique;
    private Resource resourceRaquette;

    public GraphicsJPanel(FenetreJFrame fenetreJFrame) {
        this.fenetreJFrame = fenetreJFrame;
        try{
            resourceBackGround = new ResourceBackGround();
            resourceBalle = new ResourceBall();
            resourceBrique = new ResourceBrique();
            resourceRaquette = new ResourceRaquette();
        }catch (Exception e){
            System.exit(130);
        }
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //g = drawHitBoxes(g);
        g = drawComponentSkin(g);
    }

    public Graphics drawComponentSkin(Graphics g){
        g.drawImage(resourceBackGround.getImage(),0,0,null);
        Component[] componentsList = fenetreJFrame.getMoteurGraphique().getRunGame().getMoteurPhysique().getMap().getListComponent();
        for (Component component: componentsList){
            if (component.getClass() == Brique.class){
                g.drawImage(resourceBrique.getImage(), (int)component.getX(), (int)component.getY(), null);
            } else if (component.getClass() == Balle.class) {
                g.drawImage(resourceBalle.getImage(), (int)component.getX(), (int)component.getY(), null);
            } else if (component.getClass() == Raquette.class) {
                g.drawImage(resourceRaquette.getImage(), (int)component.getX(), (int)component.getY(), null);
            }
        }
        return g;
    }

    public Graphics drawHitBoxes(Graphics g){
        Component[] componentsList = fenetreJFrame.getMoteurGraphique().getRunGame().getMoteurPhysique().getMap().getListComponent();
        for (Component component: componentsList){
            g = component.getHitBox().drawHitBox(g);
        }
        return g;
    }
}
