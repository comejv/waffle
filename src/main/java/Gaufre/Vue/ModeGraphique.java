package Gaufre.Vue;

import Gaufre.Modele.Gaufre;
import Gaufre.Modele.IA;
import Gaufre.Modele.Coup;

public class ModeGraphique {
    Gaufre gaufre;
    IA ia;
    int nbJoueurs;

    public ModeGraphique(Gaufre g) {
        gaufre = g;
    }
    
    public void setIA(IA ia) {
        this.ia = ia;
    }
    public Gaufre getGaufre() {
        return gaufre;
    }

    public void setNbJoueurs(int nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
    }

    public void lancer() {
        InterfaceGraphique.demarrer(this);
    }

    public void jouer(int x, int y) {
        Coup coup = new Coup(x, y);
        if (gaufre.jouer(coup)) {
            if (nbJoueurs == 1) {
                try { // On attend un peu avant que l'IA joue
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Coup coupIA = ia.coupSuivant();
                gaufre.jouer(coupIA);
            }
        }
    }


    public void reset() {
        gaufre.reinitialiser();
    }

    public void annuler() {
        gaufre.dejouer();
        if (nbJoueurs == 1) { // Si on joue contre l'IA on annule 2 fois

            gaufre.dejouer();
        }
    }

    public void refaire() {
        gaufre.rejouer();
        if (nbJoueurs == 1) { // Si on joue contre l'IA on rejoue 2 fois
            gaufre.rejouer();
        }
    }


    public boolean peutAnnuler() {
        return gaufre.estDejouable();
    }

    public boolean peutRefaire() {
        return gaufre.estRejouable();
    }
    
    public boolean estFini() {
        return (gaufre.estFinie() != null);
    }
    
}