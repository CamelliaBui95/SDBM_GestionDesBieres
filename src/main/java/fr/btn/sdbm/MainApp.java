package fr.btn.sdbm;

import fr.btn.sdbm.dao.ArticleDAO;
import fr.btn.sdbm.metier.Article;
import fr.btn.sdbm.service.ArticleBean;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private ArticleBean bean;
    private Stage primaryStage;

    public MainApp() {
        bean = new ArticleBean();
    }
    @Override
    public void start(Stage stage) throws Exception {

        this.primaryStage = stage;
        this.primaryStage.setTitle("SDBM - Gestion Des Bières");
        initMainWindow(stage);
    }

    public void initMainWindow(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("MainView.fxml"));
            BorderPane pane = loader.load();
            MainViewController controller = loader.getController();
            controller.setArticleBean(bean);
            controller.setMainApp(this);

            Scene scene = new Scene(pane, 1200, 800);
            primaryStage.setTitle("SDBM - Gestion des Bières");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showArticleNewEditDialog(String dialogTitle, Article article) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ArticleNewEditDialog.fxml"));
            AnchorPane dialogBox = loader.load();
            ArticleNewEditDialogController controller = loader.getController();

            Scene scene = new Scene(dialogBox);
            Stage dialogStage = new Stage();

            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);

            dialogStage.setTitle(dialogTitle);
            dialogStage.setScene(scene);

            controller.setDialogStage(dialogStage);
            controller.setArticle(article);

            controller.setCouleurs(bean.getFilteredCouleurs());
            controller.setTypes(bean.getFilteredTypes());
            controller.setMarques(bean.getFilteredMarques());

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
