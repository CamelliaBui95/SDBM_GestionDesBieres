package fr.btn.sdbm.metier;

public class Couleur {
    private int id;
    private String nomCouleur;
    public Couleur(int id, String nomCouleur) {
        this.id = id;
        this.nomCouleur = nomCouleur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCouleur() {
        return nomCouleur;
    }

    public void setNomCouleur(String nomCouleur) {
        this.nomCouleur = nomCouleur;
    }

    @Override
    public String toString() {
        return nomCouleur;
    }
}
