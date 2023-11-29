package fr.btn.sdbm.service;

import fr.btn.sdbm.MainViewController;
import fr.btn.sdbm.dao.*;
import fr.btn.sdbm.metier.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class ArticleBean {
    private ObservableList<Article> articles;
    private FilteredList<Article> filteredArticles;
    private SortedList<Article> sortedArticles;
    private ObservableList<Couleur> couleurs;
    private FilteredList<Couleur> filteredCouleurs;
    private ObservableList<Type> types;
    private FilteredList<Type> filteredTypes;
    private ObservableList<Continent> continents;
    private FilteredList<Continent> filteredContinents;
    private ObservableList<Pays> pays;
    private FilteredList<Pays> filteredPays;
    private ObservableList<Fabricant> fabricants;
    private FilteredList<Fabricant> filteredFabricants;
    private ObservableList<Marque> marques;
    private FilteredList<Marque> filteredMarques;
    private ObservableList<Volume> volumes;
    private FilteredList<Volume> filteredVolumes;

    private String searchedArticle;
    private ArticleDAO articleDAO;
    private CouleurDAO couleurDAO;
    private TypeDAO typeDAO;
    private ContinentDAO continentDAO;
    private PaysDAO paysDAO;
    private MarqueDAO marqueDAO;
    private FabricantDAO fabricantDAO;
    private VolumeDAO volumeDAO;
    private ArticleSearch articleSearch;
    private Pays paysSearch;
    private Marque marqueSearch;

    private MainViewController controller;
    public ArticleBean() {
        this.articleDAO = new ArticleDAO();
        this.couleurDAO = new CouleurDAO();
        this.typeDAO = new TypeDAO();
        this.continentDAO = new ContinentDAO();
        this.paysDAO = new PaysDAO();
        this.fabricantDAO = new FabricantDAO();
        this.marqueDAO = new MarqueDAO();
        this.volumeDAO = new VolumeDAO();
        this.articleSearch = new ArticleSearch();

        ArrayList<Continent> continentList= continentDAO.getAll();
        paysDAO.setContinents(continentList);
        ArrayList<Pays> paysList = paysDAO.getAll();
        ArrayList<Fabricant> fabricantsList = fabricantDAO.getAll();
        marqueDAO.setFabricants(fabricantsList);
        marqueDAO.setPays(paysList);

        articles = FXCollections.observableArrayList();
        couleurs = FXCollections.observableArrayList();
        types = FXCollections.observableArrayList();
        continents = FXCollections.observableArrayList();
        pays = FXCollections.observableArrayList();
        fabricants = FXCollections.observableArrayList();
        marques = FXCollections.observableArrayList();
        volumes = FXCollections.observableArrayList();

        articles.addAll(articleDAO.getAll());

        types.addAll(typeDAO.getAll());
        types.add(0,new Type(0, "Type Biere (" + types.size() + ")"));

        couleurs.addAll(couleurDAO.getAll());
        couleurs.add(0,new Couleur(0, "Couleur (" + couleurs.size() + ")"));

        fabricants.addAll(fabricantsList);
        fabricants.add(0,new Fabricant(0, "Fabricant (" + fabricants.size() + ")"));

        continents.addAll(continentList);
        continents.add(0,new Continent(0, "Continent (" + continents.size() + ")"));

        pays.addAll(paysList);
        pays.add(0,new Pays(0, "Pays (" + pays.size() + ")", null));

        marques.addAll(marqueDAO.getAll());
        marques.add(0,new Marque(0, "Marque (" + marques.size() + ")", null, null));

        volumes.addAll(volumeDAO.getAll());
        volumes.add(0,new Volume(0, "Contenance (" + volumes.size() + ")"));

        filteredArticles = new FilteredList<>(articles, null);
        sortedArticles = new SortedList<>(filteredArticles);
        filteredCouleurs = new FilteredList<>(couleurs, null);
        filteredTypes = new FilteredList<>(types, null);
        filteredPays = new FilteredList<>(pays, null);
        filteredContinents = new FilteredList<>(continents, null);
        filteredFabricants = new FilteredList<>(fabricants, null);
        filteredMarques = new FilteredList<>(marques, null);
        filteredVolumes = new FilteredList<>(volumes, null);
    }
    public SortedList<Article> getSortedArticles() {
        return sortedArticles;
    }

    public FilteredList<Couleur> getFilteredCouleurs() {
        return filteredCouleurs;
    }

    public FilteredList<Type> getFilteredTypes() {
        return filteredTypes;
    }

    public FilteredList<Marque> getFilteredMarques() {
        return filteredMarques;
    }

    public FilteredList<Continent> getFilteredContinents() {
        return filteredContinents;
    }

    public FilteredList<Pays> getFilteredPays() {
        return filteredPays;
    }

    public FilteredList<Fabricant> getFilteredFabricants() {
        return filteredFabricants;
    }

    public FilteredList<Volume> getFilteredVolumes() {
        return filteredVolumes;
    }

    public boolean filterPredicateForArticles(Article article) {
        boolean articleMatched = article.getNomArticle().toLowerCase().contains(searchedArticle);
        return articleMatched;
    }

    public void setFilteredPredicateArticles(String searchedArticle) {
        this.searchedArticle = searchedArticle.toLowerCase();
        filteredArticles.setPredicate(this::filterPredicateForArticles);
        controller.setResultCount(filteredArticles.size());
    }

    public void populatePays(Continent continent) {
        if(paysSearch == null)
            paysSearch = new Pays();

        if(continent == null)
            return;
        if(paysSearch.getContinent() != null && paysSearch.getContinent().equals(continent))
            return;

        paysSearch.setContinent(continent);
        this.pays.setAll(paysDAO.getLike(paysSearch));
        this.pays.add(0,new Pays(0, "Pays (" + pays.size() + ")", null));
    }

    public void populateMarques(Fabricant fabricant) {
        if(marqueSearch == null)
            marqueSearch = new Marque();

        if(fabricant == null) return;

        if(marqueSearch.getFabricant() != null && marqueSearch.getFabricant().equals(fabricant)) return;

        marqueSearch.setFabricant(fabricant);
        this.marques.setAll(marqueDAO.getLike(marqueSearch));
        this.marques.add(0,new Marque(0, "Marque (" + marques.size() + ")", null, null));
    }

    public void getArticlesByCouleur(Couleur newCouleur) {
        if(articleSearch.getCouleur() != null && articleSearch.getCouleur().equals(newCouleur))
            return;
        if(newCouleur != null) {
            articleSearch.setCouleur(newCouleur);
            articles.setAll(articleDAO.getLike(articleSearch));
            controller.setResultCount(articles.size());
        }
    }
    public void getArticlesByType(Type newType) {
        if(articleSearch.getType() != null && articleSearch.getType().equals(newType))
            return;
        if(newType != null) {
            articleSearch.setType(newType);
            articles.setAll(articleDAO.getLike(articleSearch));
            controller.setResultCount(articles.size());
        }
    }

    public void getArticlesByMarque(Marque newMarque) {
        if(articleSearch.getMarque() != null && articleSearch.getMarque().equals(newMarque))
            return;
        if(newMarque != null) {
            articleSearch.setMarque(newMarque);
            articles.setAll(articleDAO.getLike(articleSearch));
            controller.setResultCount(articles.size());
        }
    }

    public void getArticlesByFabricant(Fabricant newFabricant) {
        if(articleSearch.getFabricant() != null && articleSearch.getFabricant().equals(newFabricant))
            return;
        if(newFabricant != null) {
            articleSearch.setFabricant(newFabricant);
            articles.setAll(articleDAO.getLike(articleSearch));
            controller.setResultCount(articles.size());
        }
    }

    public void getArticlesByContinent(Continent newContinent) {
        if(articleSearch.getContinent() != null && articleSearch.getContinent().equals(newContinent))
            return;
        if(newContinent != null) {
            articleSearch.setContinent(newContinent);
            articles.setAll(articleDAO.getLike(articleSearch));
            controller.setResultCount(articles.size());
        }
    }

    public void getArticlesByPays(Pays newPays) {
        if(articleSearch.getPays() != null && articleSearch.getPays().equals(newPays))
            return;
        if(newPays != null) {
            articleSearch.setPays(newPays);
            articles.setAll(articleDAO.getLike(articleSearch));
            controller.setResultCount(articles.size());
        }
    }

    public void getArticlesByVolume(Volume newVolume) {
        if(articleSearch.getVolume() != null && articleSearch.getVolume().equals(newVolume))
            return;
        if(newVolume != null) {
            articleSearch.setVolume(newVolume);
            articles.setAll(articleDAO.getLike(articleSearch));
            controller.setResultCount(articles.size());
        }
    }
    public void getArticlesByTitrage(float min, float max, boolean changeInMin, boolean changeInMax) {
        Titrage currentTitrage = articleSearch.getTitrage();

        if(currentTitrage.getMin() == min && currentTitrage.getMax() == max)
            return;
        if(changeInMin)
            currentTitrage.setMin(min);

        if(changeInMax)
            currentTitrage.setMax(max);

        articleSearch.setTitrage(currentTitrage);
        articles.setAll(articleDAO.getLike(articleSearch));
        controller.setResultCount(articles.size());
    }

    public boolean updateArticle(Article selectedArticle, Article originalVer) {
        boolean isUpdated = articleDAO.update(selectedArticle);
        if(!isUpdated) {
            int index = articles.indexOf(selectedArticle);
            articles.set(index, originalVer);
        }
        return isUpdated;
    }
    public Article postArticle(Article article) {
        boolean isPosted = articleDAO.post(article);
        Article insertedArticle = new Article();
        if(isPosted) {
            articles.setAll(articleDAO.getAll());
            insertedArticle = articles.get(articles.size() - 1);
        }

        return insertedArticle;
    }

    public Article deleteArticle(Article article) {
        boolean isDeleted = articleDAO.delete(article);
        Article first = null;
        if(isDeleted) {
            articles.setAll(articleDAO.getAll());
            first = articles.get(0);
        }
        return first;
    }

    public void setMainViewController(MainViewController controller) {
        this.controller = controller;
        controller.setResultCount(articles.size());
    }

}
