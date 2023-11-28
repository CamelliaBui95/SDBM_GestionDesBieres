package fr.btn.sdbm;

import fr.btn.sdbm.metier.Vendre;
import fr.btn.sdbm.metier.VenteSerie;
import fr.btn.sdbm.service.VendreBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class VenteStatisticsController {
    private VendreBean vendreBean;
    private ObservableList<VenteSerie> venteSeries;
    @FXML
    private TableView<Vendre> venteDesArticlesView;
    @FXML
    private TableColumn<Vendre, String> anneeCol;
    @FXML
    private TableColumn<Vendre, String> idCol;
    @FXML
    private TableColumn<Vendre, String> nomArticleCol;
    @FXML
    private TableColumn<Vendre, String> qteCol;
    @FXML
    private TableColumn<Vendre, String> totaleCol;
    private String[] months = {"Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre"};
    @FXML
    private LineChart venteLineChart;
    @FXML
    private Label periodeText;
    @FXML
    private Label qteTotaleText;
    @FXML
    private Label caText;
    @FXML
    private CheckBox box2014;
    @FXML
    private CheckBox box2019;

    private SortedList<Vendre> sortedVentesDesArticles;
    @FXML
    private void initialize() {
        venteSeries = FXCollections.observableArrayList();
        //venteLineChart.setCreateSymbols(false);

        anneeCol.setCellValueFactory(cell -> cell.getValue().anneeProperty().asString());
        idCol.setCellValueFactory(cell -> cell.getValue().idArticleProperty().asString());
        nomArticleCol.setCellValueFactory(cell -> cell.getValue().nomArticleProperty());
        qteCol.setCellValueFactory(cell -> cell.getValue().quantiteProperty().asString());
        totaleCol.setCellValueFactory(cell -> cell.getValue().totaleProperty());


    }

    public void setVendreBean(VendreBean vendreBean) {
        this.vendreBean = vendreBean;
        venteSeries.addAll(this.vendreBean.getVenteSeries());

        sortedVentesDesArticles = this.vendreBean.getSortedVentesDesArticles();
        sortedVentesDesArticles.comparatorProperty().bind(venteDesArticlesView.comparatorProperty());
        venteDesArticlesView.setItems(this.sortedVentesDesArticles);

        /*for(int i = 5; i < 9; i++)
            constructLineChart(venteSeries.get(i));*/
        box2014.setOnAction(e -> {
            if(box2014.isSelected())
                constructLineChartPerLot(0, 5);
            else clearSeries(0, 5);
        });

        box2019.setOnAction(e -> {
            if(box2019.isSelected())
                constructLineChartPerLot(5, 9);
            else clearSeries(5, 9);
        });

    }

    private void constructSeries(VenteSerie venteSerie) {
        XYChart.Series series = new XYChart.Series();
        series.setName(venteSerie.getName());
        venteLineChart.getData().add(series);

        for(int i = 0; i < months.length; i++) {
            if(venteSerie.getVenteSerieData().get(months[i]) > 0) {
                XYChart.Data data = new XYChart.Data<>(months[i], venteSerie.getVenteSerieData().get(months[i]));

                series.getData().add(data);
                data.getNode().setOnMouseClicked(e -> setDetails(data.getXValue() + " " + venteSerie.getName(), data.getYValue().toString(), venteSerie.getCA()));
                data.getNode().setCursor(Cursor.HAND);

            }
        }

        series.getNode().setOnMouseClicked(e -> vendreBean.getVentesByYearForArticles(Integer.parseInt(venteSerie.getName())));
        series.getNode().setCursor(Cursor.HAND);
    }

    private void setDetails(String period, String qteTotale, String ca) {
        this.periodeText.setText(period);
        this.qteTotaleText.setText(qteTotale);
        this.caText.setText(ca);
    }
//pressedProperty on titrage slider

    @FXML
    private void handleBoxChecked(Event e) {

    }

    private void constructLineChartPerLot(int from, int to) {
        if(box2014.isSelected() && box2019.isSelected()) {
            from = 0;
            to = 9;
        }

        for(int i = from; i < to; i++)
            constructSeries(venteSeries.get(i));

    }

    private void clearSeries(int from, int to) {
        if(venteLineChart.getData().size() == to)
            venteLineChart.getData().remove(from, to);
        else if(venteLineChart.getData().size() == from - 1)
            venteLineChart.getData().remove(0, from - 1);
    }
}
