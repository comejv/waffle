package Gaufre.Vue;

import Gaufre.Modele.Gaufre;
import Gaufre.Modele.IA;
import Gaufre.Modele.Coup;

public class ModeGraphique {
    private Gaufre gaufre;
    private IA ia;
    private int nbJoueurs;

    public ModeGraphique(Gaufre g) {
        gaufre = g;
    }

    public void setIA(IA ia) {
        this.ia = ia;
        ia.init(gaufre);
    }

    public Gaufre getGaufre() {
        return gaufre;
    }

    public int getNbJoueurs() {
        return nbJoueurs;
    }

    public void setNbJoueurs(int nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
    }

    public void lancer() {
        InterfaceGraphique.demarrer(this);
    }

    public boolean jouer(int l, int c) {
        Coup coup = new Coup(l, c);
        boolean res;
        if (res = gaufre.jouer(coup)) {
            if (nbJoueurs == 1) {
            }
        }
        return res;
    }

    public Coup jouerIA() {
        Coup coupIA = ia.coupSuivant();
        gaufre.jouer(coupIA);
        return coupIA;
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
        return gaufre.estDejouable() && (gaufre.estFinie() == null);
    }

    public boolean peutRefaire() {
        return gaufre.estRejouable();
    }

    public boolean estFini() {
        return (gaufre.estFinie() != null);
    }

    public void plus() {
            gaufre.resize(gaufre.getNbLignes() + 1, gaufre.getNbColonnes() + 1);
    }
    
    public void moins() {
        if (gaufre.getNbLignes() > 1) {
            gaufre.resize(gaufre.getNbLignes() - 1, gaufre.getNbColonnes() - 1);
        }
    }

    public void resize(int l, int c) {
        gaufre.resize(l, c);
    }

}
