package fr.btn.sdbm;

import fr.btn.sdbm.metier.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.SearchableComboBox;

public class ArticleNewEditDialogController {
    @FXML
    private Label idArticle;
    @FXML
    private TextField libelleField;
    @FXML
    private TextField volumeField;
    @FXML
    private TextField titrageField;
    @FXML
    private SearchableComboBox couleurSearchBox;
    @FXML
    private SearchableComboBox typeSearchBox;
    @FXML
    private SearchableComboBox marqueSearchBox;
    @FXML
    private TextField prixField;
    @FXML
    private TextField stockField;
    @FXML
    private Button okBtn;
    private boolean isOkClicked = false;
    @FXML
    private Article article;
    @FXML
    private Stage dialogStage;

    @FXML
    private void initialize() {
        okBtn.setDisable(!isDataValid());

        libelleField.textProperty().addListener((obs, o, n) -> okBtn.setDisable(!isDataValid()));
        volumeField.textProperty().addListener((obs, o, n) -> okBtn.setDisable(!isDataValid()));
        titrageField.textProperty().addListener((obs, o, n) -> okBtn.setDisable(!isDataValid()));
        stockField.textProperty().addListener((obs, o, n) -> okBtn.setDisable(!isDataValid()));
        prixField.textProperty().addListener((obs, o, n) -> okBtn.setDisable(!isDataValid()));
        marqueSearchBox.valueProperty().addListener((obs, o, n) -> okBtn.setDisable(!isDataValid()));
    }

    public boolean isOkClicked() {
        return isOkClicked;
    }

    @FXML
    private void handleCancelClicked() {
        dialogStage.close();
    }

    @FXML
    private void handleOkClicked() {
        if(!isDataValid()) return;

        article.setNomArticle(libelleField.getText());
        article.setVolume(Integer.parseInt(volumeField.getText()));
        article.setTitrage(Float.parseFloat(titrageField.getText()));
        article.setStock(Integer.parseInt(stockField.getText()));
        article.setPrixAchat(Float.parseFloat(prixField.getText()));

        int couleurId = couleurSearchBox.getSelectionModel().getSelectedIndex();
        article.setIdCouleur(couleurId);
        String newCouleur = couleurId == 0 ? "" : couleurSearchBox.getSelectionModel().getSelectedItem().toString();
        article.setCouleur(newCouleur);

        int typeId = typeSearchBox.getSelectionModel().getSelectedIndex();
        article.setIdType(typeId);
        String newType = typeId == 0 ? "" : typeSearchBox.getSelectionModel().getSelectedItem().toString();
        article.setType(newType);

        int marqueId = marqueSearchBox.getSelectionModel().getSelectedIndex();
        article.setIdMarque(marqueId);
        String newMarque = marqueId == 0 ? "" : marqueSearchBox.getSelectionModel().getSelectedItem().toString();
        article.setMarque(newMarque);

        isOkClicked = true;
        dialogStage.close();
    }

    private boolean isDataValid() {
        boolean isLibelleValid = libelleField.getText() != null && !libelleField.getText().isEmpty();
        boolean isVolumeValid = volumeField.getText() != null && isValidInteger(volumeField.getText());
        boolean isTitrageValid = titrageField.getText() != null && isValidFloat(titrageField.getText());
        boolean isPrixValid = prixField.getText() != null && isValidFloat(prixField.getText()) && Float.parseFloat(prixField.getText()) > 0;
        boolean isStockValid = stockField.getText() != null && isValidInteger(stockField.getText());
        boolean isMarqueValid = marqueSearchBox.getSelectionModel().getSelectedIndex() > 0;

        return isLibelleValid && isVolumeValid && isTitrageValid && isPrixValid && isStockValid && isMarqueValid;
    }

    private boolean isValidInteger(String integerString) {
        try {
            Integer.parseInt(integerString);
            return true;
        } catch(Exception e) {
            //e.printStackTrace();
            return false;
        }
    }

    private boolean isValidFloat(String floatString) {
        try {
            Float.parseFloat(floatString);
            return true;
        } catch(Exception e) {
            //e.printStackTrace();
            return false;
        }
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setArticle(Article article) {
        if(article == null) return;

        this.article = article;

        String id = article.getId() == 0 ? "###" : Integer.toString(article.getId());
        idArticle.setText(id);
        libelleField.setText(article.getNomArticle());
        volumeField.setText(Integer.toString(article.getVolume()));
        titrageField.setText(Float.toString(article.getTitrage()));
        prixField.setText(Float.toString(article.getPrixAchat()));
        stockField.setText(Integer.toString(article.getStock()));
    }
    public void setCouleurs(ObservableList<Couleur> couleurs) {
        couleurSearchBox.setItems(couleurs);
        couleurSearchBox.getSelectionModel().select(article.getIdCouleur());
    }
    public void setTypes(ObservableList<Type> types) {
        typeSearchBox.setItems(types);
        typeSearchBox.getSelectionModel().select(article.getIdType());
    }
    public void setMarques(ObservableList<Marque> marques) {
        marqueSearchBox.setItems(marques);
        marqueSearchBox.getSelectionModel().select(article.getIdMarque());
    }


//mvc
//mcd
}
