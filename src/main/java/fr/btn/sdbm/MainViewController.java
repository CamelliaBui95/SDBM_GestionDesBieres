package fr.btn.sdbm;

import fr.btn.sdbm.metier.*;
import fr.btn.sdbm.service.ArticleBean;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.controlsfx.control.RangeSlider;
import org.controlsfx.control.SearchableComboBox;

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
    private TableColumn<Article, String> stockCol;
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
    private Label stockText;
    @FXML
    private Label resultCount;
    @FXML
    private TextField searchField;
    @FXML
    private SearchableComboBox<Couleur> couleurSearchBox;
    @FXML
    private SearchableComboBox<Type> typeSearchBox;
    @FXML
    private SearchableComboBox<Pays> paysSearchBox;
    @FXML
    private SearchableComboBox<Continent> continentSearchBox;
    @FXML
    private SearchableComboBox<Fabricant> fabricantSearchBox;
    @FXML
    private SearchableComboBox<Marque> marqueSearchBox;
    @FXML
    private SearchableComboBox<String> contenanceSearchBox;
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
        this.stockCol.setCellValueFactory(cell -> cell.getValue().stockProperty().asString());

        articlesTable.getSelectionModel().selectedItemProperty().addListener((ob, o, n) -> showArticleDetail(n));

        searchField.textProperty().addListener((ob, o, n) -> bean.setFilteredPredicateArticles(n));
        couleurSearchBox.valueProperty().addListener((ob, o, n) -> bean.getArticlesByCouleur((Couleur) n));
        typeSearchBox.valueProperty().addListener((ob, o, n) -> bean.getArticlesByType((Type) n));
        marqueSearchBox.valueProperty().addListener((ob, o, n) -> bean.getArticlesByMarque((Marque) n));
        fabricantSearchBox.valueProperty().addListener((ob, o, n) -> {
            bean.getArticlesByFabricant((Fabricant) n);
            bean.populateMarques((Fabricant) n);
            marqueSearchBox.getSelectionModel().selectFirst();
        });
        paysSearchBox.valueProperty().addListener((ob, o, n) -> bean.getArticlesByPays((Pays) n));
        continentSearchBox.valueProperty().addListener((ob, o, n) ->{
            bean.getArticlesByContinent((Continent) n);
            bean.populatePays((Continent) n);
            paysSearchBox.getSelectionModel().selectFirst();
        });
        contenanceSearchBox.valueProperty().addListener((ob, o, n) -> {
            if(contenanceSearchBox.getSelectionModel().getSelectedIndex() == 0)
                bean.getArticlesByVolume("0");
            else
                bean.getArticlesByVolume(n);
        });

        titrageSlider.highValueProperty().set(30);

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

        idText.setText(java.lang.Integer.toString(article.getId()));
        libelleText.setText(article.getNomArticle());
        prixText.setText(Float.toString(article.getPrixAchat()));
        volumeText.setText(java.lang.Integer.toString(article.getVolume()));
        titrageText.setText(Float.toString(article.getTitrage()));
        marqueText.setText(article.getMarque());
        fabricantText.setText(article.getFabricant());
        paysText.setText(article.getPays());
        continentText.setText(article.getContinent());
        couleurText.setText(article.getCouleur());
        typeText.setText(article.getType());
        stockText.setText(java.lang.Integer.toString(article.getStock()));
    }

    @FXML
    private void handleModifyClicked() {
        Article selectedArticle = articlesTable.getSelectionModel().getSelectedItem();
        if(selectedArticle == null) return;
        Article originalVer = createACopyOfArticle(selectedArticle);

        boolean isOkClicked = mainApp.showArticleNewEditDialog("Modifier Article N°" + selectedArticle.getId(), selectedArticle);
        if(isOkClicked) {
            boolean isUpdated = bean.updateArticle(selectedArticle, originalVer);
            if(isUpdated) {
                ObservableList<String> volumes = bean.getVolumes();
                if(!volumes.contains(Integer.toString(selectedArticle.getVolume()))) {
                    //bean.getVolumes().set(0, "Contenance (" + volumes.size() + ")");
                    //bean.getVolumes().add(Integer.toString(selectedArticle.getVolume()));
                    bean.updateVolumeList();

                }
                showArticleDetail(selectedArticle);
            }
            else showArticleDetail(originalVer);
        }
    }
    @FXML
    private void handleNewClicked() {
        Article newArticle = new Article();
        boolean isOkClicked = mainApp.showArticleNewEditDialog("New Article", newArticle);

        if(isOkClicked) {
            Article insertedArticle = bean.postArticle(newArticle);
            if(insertedArticle != null) {
                showArticleDetail(insertedArticle);
                articlesTable.getSelectionModel().select(insertedArticle);
            }
        }
    }

    @FXML
    private void handleDeleteClicked() {
        Article article = articlesTable.getSelectionModel().getSelectedItem();
        Article first = bean.deleteArticle(article);
        if(first != null) {
            showArticleDetail(first);
            articlesTable.getSelectionModel().select(first);
        }
    }

    @FXML
    private void handleStatisticsClicked() {
        mainApp.showVenteStatistics();
    }

    public void setArticleBean(ArticleBean articleBean) {
        this.bean = articleBean;
        this.bean.setMainViewController(this);
        this.articleSortedList = this.bean.getSortedArticles();
        this.articleSortedList.comparatorProperty().bind(articlesTable.comparatorProperty());
        articlesTable.setItems(this.articleSortedList);

       this.couleurSearchBox.setItems(this.bean.getCouleurs());
       this.typeSearchBox.setItems(this.bean.getTypes());
       this.paysSearchBox.setItems(this.bean.getPays());
       this.continentSearchBox.setItems(this.bean.getContinents());
       this.fabricantSearchBox.setItems(this.bean.getFabricants());
       this.marqueSearchBox.setItems(this.bean.getMarques());
       this.contenanceSearchBox.setItems(this.bean.getVolumes());

       setDefaultSelect();

    }

    public void setResultCount(int count) {
        this.resultCount.setText(java.lang.Integer.toString(count));
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
