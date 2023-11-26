package fr.btn.sdbm;

import fr.btn.sdbm.metier.*;
import fr.btn.sdbm.service.ArticleBean;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    private MainApp mainApp;
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

    @FXML
    private void handleModifyClicked() {

        Article selectedArticle = articlesTable.getSelectionModel().getSelectedItem();
        if(selectedArticle == null) return;
        Article originalVer = createACopyOfArticle(selectedArticle);

        boolean isOkClicked = mainApp.showArticleNewEditDialog("Modifier Article N°" + selectedArticle.getId(), selectedArticle);
        if(isOkClicked) {
            boolean isUpdated = bean.updateArticle(selectedArticle, originalVer);
            if(isUpdated)
                showArticleDetail(selectedArticle);
            else showArticleDetail(originalVer);
        }
    }
    @FXML
    private void handleNewClicked() {
        Article newArticle = new Article();
        boolean isOkClicked = mainApp.showArticleNewEditDialog("New Article", newArticle);

        if(isOkClicked) {
            boolean isPosted = bean.postArticle(newArticle);
            if(isPosted)
                showArticleDetail(newArticle);
        }
    }

    @FXML
    private void handleDeleteClicked() {

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

       setDefaultSelect();

    }

    public void setResultCount(int count) {
        this.resultCount.setText(Integer.toString(count));
    }

    private void setDefaultSelect() {
        this.couleurSearchBox.getSelectionModel().selectFirst();
        this.fabricantSearchBox.getSelectionModel().selectFirst();
        this.contenanceSearchBox.getSelectionModel().selectFirst();
        this.continentSearchBox.getSelectionModel().selectFirst();
        this.paysSearchBox.getSelectionModel().selectFirst();
        this.marqueSearchBox.getSelectionModel().selectFirst();
        this.typeSearchBox.getSelectionModel().selectFirst();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private Article createACopyOfArticle(Article original) {
        Article newArticle = new Article(original.getId(), original.getNomArticle(),
                original.getVolume(), original.getTitrage(), original.getPrixAchat(),
                original.getStock(), original.getCouleur(), original.getType(), original.getMarque(),
                original.getFabricant(), original.getPays(), original.getContinent());

        newArticle.setIdCouleur(original.getIdCouleur());
        newArticle.setIdType(original.getIdType());
        newArticle.setIdMarque(original.getIdMarque());
        newArticle.setIdFabricant(original.getIdFabricant());
        newArticle.setIdPays(original.getIdPays());
        newArticle.setIdContinent(original.getIdContinent());

        return newArticle;
    }
}
