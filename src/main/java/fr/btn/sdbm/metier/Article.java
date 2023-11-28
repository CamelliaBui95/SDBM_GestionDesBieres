package fr.btn.sdbm.metier;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Article {
    private SimpleIntegerProperty id;
    private StringProperty nomArticle;
    private SimpleIntegerProperty volume;
    private SimpleFloatProperty titrage;
    private SimpleFloatProperty prixAchat;
    private SimpleIntegerProperty stock;
    private SimpleStringProperty couleur;
    private SimpleIntegerProperty idCouleur;
    private SimpleStringProperty type;
    private SimpleIntegerProperty idType;
    private SimpleStringProperty marque;
    private SimpleIntegerProperty idMarque;
    private SimpleStringProperty fabricant;
    private SimpleIntegerProperty idFabricant;
    private SimpleStringProperty pays;
    private SimpleIntegerProperty idPays;
    private SimpleStringProperty continent;
    private SimpleIntegerProperty idContinent;

    public Article(int id, String nomArticle, int volume, float titrage, float prixAchat, int stock, String couleur, String type, String marque, String fabricant, String pays, String continent) {
        this.id = new SimpleIntegerProperty(id);
        this.nomArticle = new SimpleStringProperty(nomArticle);
        this.volume = new SimpleIntegerProperty(volume);
        this.titrage = new SimpleFloatProperty(titrage);
        this.prixAchat = new SimpleFloatProperty(prixAchat);
        this.stock = new SimpleIntegerProperty(stock);
        this.couleur = new SimpleStringProperty(couleur);
        this.type = new SimpleStringProperty(type);
        this.marque = new SimpleStringProperty(marque);
        this.fabricant = new SimpleStringProperty(fabricant);
        this.pays = new SimpleStringProperty(pays);
        this.continent = new SimpleStringProperty(continent);

        this.idCouleur = new SimpleIntegerProperty(0);
        this.idMarque = new SimpleIntegerProperty(0);
        this.idFabricant = new SimpleIntegerProperty(0);
        this.idType = new SimpleIntegerProperty(0);
        this.idPays = new SimpleIntegerProperty(0);
        this.idContinent = new SimpleIntegerProperty(0);
    }

    public Article() {
        this.id = new SimpleIntegerProperty(0);
        this.nomArticle = new SimpleStringProperty("");
        this.volume = new SimpleIntegerProperty(0);
        this.titrage = new SimpleFloatProperty(0);
        this.prixAchat = new SimpleFloatProperty(0);
        this.stock = new SimpleIntegerProperty(0);
        this.couleur = new SimpleStringProperty("");
        this.type = new SimpleStringProperty("");
        this.marque = new SimpleStringProperty("");
        this.fabricant = new SimpleStringProperty("");
        this.pays = new SimpleStringProperty("");
        this.continent = new SimpleStringProperty("");

        this.idCouleur = new SimpleIntegerProperty(0);
        this.idMarque = new SimpleIntegerProperty(0);
        this.idFabricant = new SimpleIntegerProperty(0);
        this.idType = new SimpleIntegerProperty(0);
        this.idPays = new SimpleIntegerProperty(0);
        this.idContinent = new SimpleIntegerProperty(0);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNomArticle() {
        return nomArticle.get();
    }

    public StringProperty nomArticleProperty() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle.set(nomArticle);
    }

    public int getVolume() {
        return volume.get();
    }

    public SimpleIntegerProperty volumeProperty() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume.set(volume);
    }

    public float getTitrage() {
        return titrage.get();
    }

    public SimpleFloatProperty titrageProperty() {
        return titrage;
    }

    public void setTitrage(float titrage) {
        this.titrage.set(titrage);
    }

    public float getPrixAchat() {
        return prixAchat.get();
    }

    public SimpleFloatProperty prixAchatProperty() {
        return prixAchat;
    }

    public void setPrixAchat(float prixAchat) {
        this.prixAchat.set(prixAchat);
    }

    public int getStock() {
        return stock.get();
    }

    public SimpleIntegerProperty stockProperty() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    public String getCouleur() {
        return couleur.get();
    }

    public SimpleStringProperty couleurProperty() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur.set(couleur);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getMarque() {
        return marque.get();
    }

    public SimpleStringProperty marqueProperty() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque.set(marque);
    }

    public String getFabricant() {
        return fabricant.get();
    }

    public SimpleStringProperty fabricantProperty() {
        return fabricant;
    }

    public void setFabricant(String fabricant) {
        this.fabricant.set(fabricant);
    }

    public String getPays() {
        return pays.get();
    }

    public SimpleStringProperty paysProperty() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays.set(pays);
    }

    public String getContinent() {
        return continent.get();
    }

    public SimpleStringProperty continentProperty() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent.set(continent);
    }

    public int getIdCouleur() {
        return idCouleur.get();
    }

    public SimpleIntegerProperty idCouleurProperty() {
        return idCouleur;
    }

    public void setIdCouleur(int idCouleur) {
        this.idCouleur.set(idCouleur);
    }

    public int getIdType() {
        return idType.get();
    }

    public SimpleIntegerProperty idTypeProperty() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType.set(idType);
    }

    public int getIdMarque() {
        return idMarque.get();
    }

    public SimpleIntegerProperty idMarqueProperty() {
        return idMarque;
    }

    public void setIdMarque(int idMarque) {
        this.idMarque.set(idMarque);
    }

    public int getIdFabricant() {
        return idFabricant.get();
    }

    public SimpleIntegerProperty idFabricantProperty() {
        return idFabricant;
    }

    public void setIdFabricant(int idFabricant) {
        this.idFabricant.set(idFabricant);
    }

    public int getIdPays() {
        return idPays.get();
    }

    public SimpleIntegerProperty idPaysProperty() {
        return idPays;
    }

    public void setIdPays(int idPays) {
        this.idPays.set(idPays);
    }

    public int getIdContinent() {
        return idContinent.get();
    }

    public SimpleIntegerProperty idContinentProperty() {
        return idContinent;
    }

    public void setIdContinent(int idContinent) {
        this.idContinent.set(idContinent);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", nomArticle=" + nomArticle +
                ", volume=" + volume +
                ", titrage=" + titrage +
                ", id_Couleur=" + idCouleur +
                ", id_Type=" + idType +
                ", Stock=" + stock +
                '}';
    }
}
