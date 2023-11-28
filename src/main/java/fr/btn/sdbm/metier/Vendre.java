package fr.btn.sdbm.metier;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Vendre {
    private SimpleIntegerProperty annee;
    private SimpleIntegerProperty numeroTicket;
    private SimpleIntegerProperty idArticle;
    private SimpleStringProperty nomArticle;
    private SimpleIntegerProperty quantite;
    private SimpleFloatProperty prixVente;
    private SimpleStringProperty totale;

    public Vendre(int annee, int idArticle, String nomArticle, int quantite, String totale) {
        this.annee = new SimpleIntegerProperty(annee);
        this.idArticle = new SimpleIntegerProperty(idArticle);
        this.nomArticle = new SimpleStringProperty(nomArticle);
        this.quantite = new SimpleIntegerProperty(quantite);
        this.totale = new SimpleStringProperty(totale);
    }

    public int getAnnee() {
        return annee.get();
    }

    public SimpleIntegerProperty anneeProperty() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee.set(annee);
    }

    public int getNumeroTicket() {
        return numeroTicket.get();
    }

    public SimpleIntegerProperty numeroTicketProperty() {
        return numeroTicket;
    }

    public void setNumeroTicket(int numeroTicket) {
        this.numeroTicket.set(numeroTicket);
    }

    public int getIdArticle() {
        return idArticle.get();
    }

    public SimpleIntegerProperty idArticleProperty() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle.set(idArticle);
    }

    public int getQuantite() {
        return quantite.get();
    }

    public SimpleIntegerProperty quantiteProperty() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite.set(quantite);
    }

    public float getPrixVente() {
        return prixVente.get();
    }

    public SimpleFloatProperty prixVenteProperty() {
        return prixVente;
    }

    public void setPrixVente(float prixVente) {
        this.prixVente.set(prixVente);
    }

    public String getNomArticle() {
        return nomArticle.get();
    }

    public SimpleStringProperty nomArticleProperty() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle.set(nomArticle);
    }

    public String getTotale() {
        return totale.get();
    }

    public SimpleStringProperty totaleProperty() {
        return totale;
    }

    public void setTotale(String totale) {
        this.totale.set(totale);
    }
}
