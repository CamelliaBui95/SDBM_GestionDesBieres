package fr.btn.sdbm;

import fr.btn.sdbm.metier.Article;
import fr.btn.sdbm.service.ArticleBean;
import fr.btn.sdbm.service.VendreBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private ArticleBean articleBean;
    private VendreBean vendreBean;
    private Stage primaryStage;

    public MainApp() {
        articleBean = new ArticleBean();
        vendreBean = new VendreBean();
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
            controller.setArticleBean(articleBean);
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

            controller.setCouleurs(articleBean.getCouleurs());
            controller.setTypes(articleBean.getTypes());
            controller.setMarques(articleBean.getMarques());

            dialogStage.setResizable(false);
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showVenteStatistics() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("VenteStatistics.fxml"));
            AnchorPane pane = loader.load();
            VenteStatisticsController controller = loader.getController();
            controller.setVendreBean(vendreBean);

            Stage statisticStage = new Stage();

            statisticStage.initModality(Modality.WINDOW_MODAL);
            statisticStage.initOwner(primaryStage);
            statisticStage.setResizable(false);
            statisticStage.setTitle("Ventes 2014 - 2022");

            Scene scene = new Scene(pane);
            statisticStage.setScene(scene);

            statisticStage.show();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
