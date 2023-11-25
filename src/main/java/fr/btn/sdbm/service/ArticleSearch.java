package fr.btn.sdbm.service;

import fr.btn.sdbm.metier.*;

public class ArticleSearch {
    private String nomArticle = "";
    private Continent continent;
    private Couleur couleur;
    private Marque marque;
    private Fabricant fabricant;
    private Pays pays;
    private Type type;
    private Volume volume;

    private Titrage titrage = new Titrage(0, 30);

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Fabricant getFabricant() {
        return fabricant;
    }

    public void setFabricant(Fabricant fabricant) {
        this.fabricant = fabricant;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public Titrage getTitrage() {
        return titrage;
    }

    public void setTitrage(Titrage titrage) {
        this.titrage = titrage;
    }
}
