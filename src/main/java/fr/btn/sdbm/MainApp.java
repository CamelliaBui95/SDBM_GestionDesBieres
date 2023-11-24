package fr.btn.sdbm;

import fr.btn.sdbm.dao.ArticleDAO;
import fr.btn.sdbm.metier.Article;
import fr.btn.sdbm.service.ArticleBean;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private ArticleDAO articleDAO;
    private ArticleBean bean;

    public MainApp() {
        articleDAO = new ArticleDAO();
        bean = new ArticleBean(articleDAO);
    }
    @Override
    public void start(Stage stage) throws Exception {
        initMainWindow(stage);
    }

    public void initMainWindow(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("MainView.fxml"));
            BorderPane pane = loader.load();
            MainViewController controller = loader.getController();
            controller.setArticleBean(bean);

            Scene scene = new Scene(pane, 1200, 800);
            primaryStage.setTitle("SDBM - Gestion des Bières");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
