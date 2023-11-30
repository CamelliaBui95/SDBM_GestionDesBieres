package fr.btn.sdbm;

import fr.btn.sdbm.metier.Vendre;
import fr.btn.sdbm.metier.VenteSerie;
import fr.btn.sdbm.service.VendreBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

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
    @FXML
    private VBox trancheAnneeCol;
    private String[] months = {"Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre"};
    private ObservableList<String> monthsForChart = FXCollections.observableArrayList(months);
    @FXML
    private LineChart<String, Number> venteLineChart;
    @FXML
    private CategoryAxis xAxis;
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
    private int currentYearForDisplay;
    private boolean chartClicked = true;

    private SortedList<Vendre> sortedVentesDesArticles;
    @FXML
    private void initialize() {
        venteSeries = FXCollections.observableArrayList();

        anneeCol.setCellValueFactory(cell -> cell.getValue().anneeProperty().asString());
        idCol.setCellValueFactory(cell -> cell.getValue().idArticleProperty().asString());
        nomArticleCol.setCellValueFactory(cell -> cell.getValue().nomArticleProperty());
        qteCol.setCellValueFactory(cell -> cell.getValue().quantiteProperty().asString());
        totaleCol.setCellValueFactory(cell -> cell.getValue().totaleProperty());

        xAxis.setCategories(monthsForChart);

        box2014.setOnAction(e -> {
            constructLineChartPerLot();
        });

        box2019.setOnAction(e -> {
            constructLineChartPerLot();
        });

    }

    public void setVendreBean(VendreBean vendreBean) {
        this.vendreBean = vendreBean;
        venteSeries.addAll(this.vendreBean.getVenteSeries());

        sortedVentesDesArticles = this.vendreBean.getSortedVentesDesArticles();
        sortedVentesDesArticles.comparatorProperty().bind(venteDesArticlesView.comparatorProperty());
        venteDesArticlesView.setItems(this.sortedVentesDesArticles);

        setUpDefaultDisplay();
    }

    private void constructSeries(VenteSerie venteSerie) {
        XYChart.Series series = new XYChart.Series();
        series.setName(venteSerie.getName());

        venteLineChart.getData().add(series);
        int seriesIndex = venteLineChart.getData().size() - 1;

        int serieAnnee = Integer.parseInt(venteSerie.getName());
        for(int i = 0; i < months.length; i++) {
            if(venteSerie.getVenteSerieData().get(months[i]) > 0) {
                XYChart.Data data = new XYChart.Data<>(months[i], venteSerie.getVenteSerieData().get(months[i]));

                series.getData().add(data);
                data.getNode().setOnMouseClicked(e -> {
                    setDetails(data.getXValue() + " " + venteSerie.getName(), data.getYValue().toString(), venteSerie.getCA());
                    updateChartUI(serieAnnee, seriesIndex);
                });
                data.getNode().setCursor(Cursor.HAND);
            }
        }

        series.getNode().setOnMouseClicked(e -> {
            updateVenteDesArticlesView(serieAnnee);
            updateChartUI(serieAnnee, seriesIndex);
        });
        series.getNode().setCursor(Cursor.HAND);
    }

    private void setDetails(String period, String qteTotale, String ca) {
        this.periodeText.setText(period);
        this.qteTotaleText.setText(qteTotale);
        this.caText.setText(ca);
    }
//pressedProperty on titrage slider

    private void constructLineChartPerLot() {
        if(!box2014.isSelected() && !box2019.isSelected()) {
            venteLineChart.getData().clear();
            return;
        }

        int from = 0;
        int to = venteSeries.size();
        if(box2014.isSelected() && !box2019.isSelected()) {
            updateVenteDesArticlesView(2018);
            to = 5;
        }
        else if(!box2014.isSelected() && box2019.isSelected()) {
            updateVenteDesArticlesView(2022);
            from = 5;
        }
        else if(box2014.isSelected() && box2019.isSelected())
            updateVenteDesArticlesView(2022);

        venteLineChart.getData().clear();

        for(int i = from; i < to; i++)
           constructSeries(venteSeries.get(i));
    }

    private void setUpDefaultDisplay() {
        currentYearForDisplay = Integer.parseInt(venteSeries.get(venteSeries.size() - 1).getName());
        vendreBean.getVentesByYearForArticles(currentYearForDisplay);
        // initialize chart with recent data
        for(int i = 5; i < 9; i++)
            constructSeries(venteSeries.get(i));
        box2019.setSelected(true);
    }

    private void updateVenteDesArticlesView(int annee) {
        if(currentYearForDisplay == annee)
            return;

        currentYearForDisplay = annee;
        vendreBean.getVentesByYearForArticles(currentYearForDisplay);
    }

    private void updateSeriesUI(int selectedSeriesIndex) {
        XYChart.Series<String, Number> selectedSeries = venteLineChart.getData().get(selectedSeriesIndex);
        selectedSeries.nodeProperty().get().setStyle("-fx-opacity: 1;");

        for(int j = 0; j < selectedSeries.getData().size(); j++) {
            XYChart.Data<String, Number> data = selectedSeries.getData().get(j);
            data.getNode().setStyle("-fx-opacity: 1;");
        }

        for(int i = 0; i < venteLineChart.getData().size(); i++) {
            XYChart.Series<String, Number> series = venteLineChart.getData().get(i);
            if(!series.equals(selectedSeries)) {
                series.nodeProperty().get().setStyle("-fx-opacity: 0.1;");

                for(int j = 0; j < series.getData().size(); j++) {
                    XYChart.Data<String, Number> data = series.getData().get(j);
                    data.getNode().setStyle("-fx-opacity: 0.1;");
                }
            }
        }

        chartClicked = false;
    }

    @FXML
    private void handleShowAllTrends() {
        if(chartClicked)
            return;

        for(int i = 0; i < venteLineChart.getData().size(); i++) {
            XYChart.Series<String, Number> series = venteLineChart.getData().get(i);
            series.nodeProperty().get().setStyle("-fx-opacity: 1;");

            for(int j = 0; j < series.getData().size(); j++) {
                XYChart.Data<String, Number> data = series.getData().get(j);
                data.getNode().setStyle("-fx-opacity: 1;");

            }
        }

        chartClicked = true;
    }

    private void updateChartUI(int annee, int selectedSeriesIndex) {
        updateVenteDesArticlesView(annee);
        updateSeriesUI(selectedSeriesIndex);
    }
}

/*ObservableList<XYChart.Series> seriesList = FXCollections.observableArrayList();
        for(int i = from; i < to; i++) {
            VenteSerie venteSerie = venteSeries.get(i);
            XYChart.Series series = constructSeries(venteSerie);
            seriesList.add(series);
        }

        venteLineChart.getData().addAll(seriesList);

        /*for(int i = 0; i < venteLineChart.getData().size(); i++) {
            int index = i;
            XYChart.Series series = (XYChart.Series) venteLineChart.getData().get(index);
            for(int j = 0 ; j < series.getData().size(); j++) {
                XYChart.Data data = (XYChart.Data)series.getData().get(j);
                data.getNode().setOnMouseClicked(e -> setDetails(data.getXValue() + " " + venteSeries.get(index).getName(), data.getYValue().toString(), venteSeries.get(index).getCA()));
                data.getNode().setCursor(Cursor.HAND);
            }
            series.getNode().setOnMouseClicked(e -> vendreBean.getVentesByYearForArticles(Integer.parseInt(venteSeries.get(index).getName())));
            series.getNode().setCursor(Cursor.HAND);
        }*/
