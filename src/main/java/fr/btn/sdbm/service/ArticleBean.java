package fr.btn.sdbm.service;

import fr.btn.sdbm.dao.ArticleDAO;
import fr.btn.sdbm.metier.Article;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class ArticleBean {
    private ObservableList<Article> articles;
    private FilteredList<Article> filteredArticles;
    private SortedList<Article> sortedArticles;

    private String searchedArticle;

    private ArticleDAO articleDAO;

    public ArticleBean(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
        this.articles = FXCollections.observableArrayList();
        articles.addAll(this.articleDAO.getAll());
        filteredArticles = new FilteredList<>(articles, null);
        sortedArticles = new SortedList<>(filteredArticles);
    }
    public SortedList<Article> getSortedArticles() {
        return sortedArticles;
    }

    public boolean filterPredicate(Article article) {
        boolean articleMatched = article.getNomArticle().toLowerCase().contains(searchedArticle);
        return articleMatched;
    }

    public void setFilteredPredicate(String searchedArticle) {
        this.searchedArticle = searchedArticle;
        filteredArticles.setPredicate(this::filterPredicate);
    }
}
