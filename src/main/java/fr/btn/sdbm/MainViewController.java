package fr.btn.sdbm;

import fr.btn.sdbm.metier.*;
import fr.btn.sdbm.service.ArticleBean;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.controlsfx.control.RangeSlider;
import org.controlsfx.control.SearchableComboBox;

import java.util.ArrayList;

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
    private Label resultCount;
    @FXML
    private TextField searchField;
    @FXML
    private SearchableComboBox couleurSearchBox;
    @FXML
    private SearchableComboBox typeSearchBox;
    @FXML
    private SearchableComboBox paysSearchBox;
    @FXML
    private SearchableComboBox continentSearchBox;
    @FXML
    private SearchableComboBox fabricantSearchBox;
    @FXML
    private SearchableComboBox marqueSearchBox;
    @FXML
    private SearchableComboBox contenanceSearchBox;
    @FXML
    private RangeSlider titrageSlider;

    private SortedList<Article> articleSortedList;

    private ArticleBean bean;
    @FXML
    private void initialize() {
        this.idCol.setCellValueFactory(cell -> cell.getValue().idProperty().asString());
        this.nomArticleCol.setCellValueFactory(cell -> cell.getValue().nomArticleProperty());
        this.volumeCol.setCellValueFactory(cell -> cell.getValue().volumeProperty().asString());
        this.titrageCol.setCellValueFactory(cell -> cell.getValue().titrageProperty().asString());

        articlesTable.getSelectionModel().selectedItemProperty().addListener((ob, o, n) -> showArticleDetail(n));
        searchField.textProperty().addListener((ob, o, n) -> bean.setFilteredPredicateArticles(n));
        couleurSearchBox.valueProperty().addListener((ob, o, n) -> bean.getArticlesByCouleur((Couleur) n));
        typeSearchBox.valueProperty().addListener((ob, o, n) -> bean.getArticlesByType((Type) n));
        marqueSearchBox.valueProperty().addListener((ob, o, n) -> bean.getArticlesByMarque((Marque) n));
        fabricantSearchBox.valueProperty().addListener((ob, o, n) -> bean.getArticlesByFabricant((Fabricant) n));
        paysSearchBox.valueProperty().addListener((ob, o, n) -> bean.getArticlesByPays((Pays) n));
        continentSearchBox.valueProperty().addListener((ob, o, n) -> bean.getArticlesByContinent((Continent) n));
        contenanceSearchBox.valueProperty().addListener((ob, o, n) -> bean.getArticlesByVolume((Volume) n));

        titrageSlider.lowValueProperty().addListener((ob, o, n) -> {
            if(!titrageSlider.isLowValueChanging())
                bean.getArticlesByTitrage(n.floatValue(), 30, true, false);
        });
        titrageSlider.highValueProperty().addListener((ob, o, n) -> {
            if(!titrageSlider.isHighValueChanging())
                bean.getArticlesByTitrage(0, n.floatValue(), false, true);
        });

    }

    private void showArticleDetail(Article article) {
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
        this.bean.setMainViewController(this);
        this.articleSortedList = this.bean.getSortedArticles();
        this.articleSortedList.comparatorProperty().bind(articlesTable.comparatorProperty());
        articlesTable.setItems(this.articleSortedList);

       this.couleurSearchBox.setItems(this.bean.getFilteredCouleurs());
       this.typeSearchBox.setItems(this.bean.getFilteredTypes());
       this.paysSearchBox.setItems(this.bean.getFilteredPays());
       this.continentSearchBox.setItems(this.bean.getFilteredContinents());
       this.fabricantSearchBox.setItems(this.bean.getFilteredFabricants());
       this.marqueSearchBox.setItems(this.bean.getFilteredMarques());
       this.contenanceSearchBox.setItems(this.bean.getFilteredVolumes());

    }

    public void setResultCount(int count) {
        this.resultCount.setText(Integer.toString(count));
    }

}
