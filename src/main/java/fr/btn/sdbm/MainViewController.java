package fr.btn.sdbm;

import fr.btn.sdbm.metier.Article;
import fr.btn.sdbm.service.ArticleBean;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainViewController {
    @FXML
    private TableView<Article> articlesTable;
    @FXML
    private TableColumn<Article, String> idCol;
    @FXML
    private TableColumn<Article, String> nomArticleCol;
    @FXML
    private TableColumn<Article, String> volumeCol;
    @FXML
    private TableColumn<Article, String> titrageCol;
    @FXML
    private Label idText;
    @FXML
    private Label libelleText;
    @FXML
    private Label prixText;
    @FXML
    private Label volumeText;
    @FXML
    private Label titrageText;
    @FXML
    private Label marqueText;
    @FXML
    private Label fabricantText;
    @FXML
    private Label paysText;
    @FXML
    private Label continentText;
    @FXML
    private Label couleurText;
    @FXML
    private Label typeText;
    @FXML
    private TextField searchField;
    private SortedList<Article> articleSortedList;

    private ArticleBean bean;
    @FXML
    private void initialize() {
        this.idCol.setCellValueFactory(cell -> cell.getValue().idProperty().asString());
        this.nomArticleCol.setCellValueFactory(cell -> cell.getValue().nomArticleProperty());
        this.volumeCol.setCellValueFactory(cell -> cell.getValue().volumeProperty().asString());
        this.titrageCol.setCellValueFactory(cell -> cell.getValue().titrageProperty().asString());

        articlesTable.getSelectionModel().selectedItemProperty().addListener((ob, o, n) -> showArticleDetail(n));
        searchField.textProperty().addListener((ob, o, n) -> bean.setFilteredPredicate(n));
    }

    public void showArticleDetail(Article article) {
        if(article == null)
            return;

        idText.setText(Integer.toString(article.getId()));
        libelleText.setText(article.getNomArticle());
        prixText.setText(Float.toString(article.getPrixAchat()));
        volumeText.setText(Integer.toString(article.getVolume()));
        titrageText.setText(Float.toString(article.getTitrage()));
        marqueText.setText(article.getMarque());
        fabricantText.setText(article.getFabricant());
        paysText.setText(article.getPays());
        continentText.setText(article.getContinent());
        couleurText.setText(article.getCouleur());
        typeText.setText(article.getType());
    }

    public void setArticleBean(ArticleBean articleBean) {
        this.bean = articleBean;
        this.articleSortedList = this.bean.getSortedArticles();
        this.articleSortedList.comparatorProperty().bind(articlesTable.comparatorProperty());
        articlesTable.setItems(this.articleSortedList);
    }

}
