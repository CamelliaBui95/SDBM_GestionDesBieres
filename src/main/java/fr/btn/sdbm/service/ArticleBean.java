package fr.btn.sdbm.service;

import fr.btn.sdbm.dao.ArticleDAO;
import fr.btn.sdbm.dao.CouleurDAO;
import fr.btn.sdbm.dao.TypeDAO;
import fr.btn.sdbm.metier.Article;
import fr.btn.sdbm.metier.Couleur;
import fr.btn.sdbm.metier.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class ArticleBean {
    private ObservableList<Article> articles;
    private FilteredList<Article> filteredArticles;
    private SortedList<Article> sortedArticles;
    private ObservableList<Couleur> couleurs;
    private FilteredList<Couleur> filteredCouleurs;
    private ObservableList<Type> types;
    private FilteredList<Type> filteredTypes;

    private String searchedArticle;

    private String searchedCouleur;

    private ArticleDAO articleDAO;
    private CouleurDAO couleurDAO;
    private TypeDAO typeDAO;

    public ArticleBean() {
        this.articleDAO = new ArticleDAO();
        this.couleurDAO = new CouleurDAO();
        this.typeDAO = new TypeDAO();

        articles = FXCollections.observableArrayList();
        couleurs = FXCollections.observableArrayList();
        types = FXCollections.observableArrayList();

        types.addAll(typeDAO.getAll());
        couleurs.addAll(couleurDAO.getAll());
        articles.addAll(articleDAO.getAll());

        filteredArticles = new FilteredList<>(articles, null);
        sortedArticles = new SortedList<>(filteredArticles);
        filteredCouleurs = new FilteredList<>(couleurs, null);
        filteredTypes = new FilteredList<>(types, null);
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

    public boolean filterPredicateForArticles(Article article) {
        boolean articleMatched = article.getNomArticle().toLowerCase().contains(searchedArticle);
        return articleMatched;
    }

    public void setFilteredPredicateArticles(String searchedArticle) {
        this.searchedArticle = searchedArticle;
        filteredArticles.setPredicate(this::filterPredicateForArticles);
    }

    public boolean filterPredicateForCouleurs(Couleur couleur) {
        boolean couleurMatched = couleur.getNomCouleur().toLowerCase().contains(searchedCouleur);
        return couleurMatched;
    }

    public void setFilteredPredicateCouleurs(String searchedCouleur) {
        this.searchedCouleur = searchedCouleur;
        filteredCouleurs.setPredicate(this::filterPredicateForCouleurs);
    }
}
