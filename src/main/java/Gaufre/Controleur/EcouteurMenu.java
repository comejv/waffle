package Gaufre.Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Gaufre.Vue.InterfaceGraphique;
import Gaufre.Modele.IAaleatoire;
//import Gaufre.Modele.IAcoupGagnant;

public class EcouteurMenu implements ActionListener {
    private InterfaceGraphique vue;

    public EcouteurMenu(InterfaceGraphique vue) {
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        switch (evt.getActionCommand()) {
            case "Jeu1J":
                vue.setEtat(vue.JEU);
                vue.getMG().setNbJoueurs(1);
                vue.getMG().setIA(new IAaleatoire());
                break;
            case "Jeu2J":
                vue.setEtat(vue.JEU);
                vue.getMG().setNbJoueurs(2);
                break;
            case "Quitter":
                System.exit(0);
                break;
            default:
                throw new UnsupportedOperationException("Bouton du menu non supporté");
        }
    }

}
