package Gaufre.Controleur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Graphics2D;

import Gaufre.Vue.InterfaceGraphique;
import Gaufre.Configuration.Config;
import Gaufre.Modele.Coup;

public class EcouteurSouris extends MouseAdapter {
    InterfaceGraphique ig;

    public EcouteurSouris(InterfaceGraphique ig) {
        this.ig = ig;
    }

    public void mouseClicked(MouseEvent e) {
        if (ig.getPlateau().contains(e.getX(), e.getY()) && !ig.getMG().estFini()) {
            int c = e.getX() / ig.getTailleCelluleX();
            int l = e.getY() / ig.getTailleCelluleY();
            Config.debug("Click sur case ", l, c);
            if (ig.getMG().jouer(l, c)) {
                if(ig.boolCoupInval){
                    ig.revertAfficahgeInval();
                }
                ig.mangeCellGaufre(l, c);
                ig.majInfo();
                // Peut-être à remplacer par un truc du genre if .contreIA()
                if (ig.getMG().getNbJoueurs() == 1 && !ig.getMG().estFini()) {
                    Coup coupIA = ig.getMG().jouerIA();
                    c = (int) coupIA.getPosition().getY();
                    l = (int) coupIA.getPosition().getX();
                    Config.debug("Coup IA : ", l, c);
                    ig.mangeCellGaufre(l, c);
                    ig.majInfo();
                }
                if (ig.getMG().estFini()) {
                    ig.getMG().getGaufre().estFinie().incrementScore();
                    ig.afficheGagnant();
                    ig.finPartie();
                }
            }
            else {
                ig.boolCoupInval=true;
                ig.coupInval();
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        return;
    }

    public void mouseReleased(MouseEvent e) {
        return;
    }

    public void mouseExited(MouseEvent e) {
        // Repeindre le jeu en enlevant le masque vert ou rouge
        Graphics2D g2d = (Graphics2D) ig.getGraphics();
        g2d.setColor(new Color(0, 0, 0, 0));
        g2d.fillRect(0, 0, ig.getPlateau().getWidth(), ig.getPlateau().getHeight());
    }
}
