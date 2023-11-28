package fr.btn.sdbm;

import fr.btn.sdbm.dao.VendreDAO;
import fr.btn.sdbm.metier.VenteSerie;
import fr.btn.sdbm.service.VendreBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class VenteStatisticsController {
    private VendreBean vendreBean;
    private ObservableList<VenteSerie> venteSeries;

    @FXML
    private LineChart venteLineChart;
    @FXML
    private void initialize() {
        venteSeries = FXCollections.observableArrayList();

    }

    public void setVendreBean(VendreBean vendreBean) {
        this.vendreBean = vendreBean;
        venteSeries.addAll(this.vendreBean.getVenteSeries());

        for(int i = 5; i < 9; i++)
            constructLineChart(venteSeries.get(i));

    }

    private void constructLineChart(VenteSerie venteSerie) {
        XYChart.Series series = new XYChart.Series();
        series.setName(venteSerie.getName());

        series.getData().add(new XYChart.Data<>("Jan", venteSerie.getVenteSerieData().get("Janvier")));
        series.getData().add(new XYChart.Data<>("Feb", venteSerie.getVenteSerieData().get("Fevrier")));
        series.getData().add(new XYChart.Data<>("March", venteSerie.getVenteSerieData().get("Mars")));
        series.getData().add(new XYChart.Data<>("April", venteSerie.getVenteSerieData().get("Avril")));
        series.getData().add(new XYChart.Data<>("May", venteSerie.getVenteSerieData().get("Mai")));
        series.getData().add(new XYChart.Data<>("June", venteSerie.getVenteSerieData().get("Juin")));
        if(venteSerie.getVenteSerieData().get("Juillet") > 0)
            series.getData().add(new XYChart.Data<>("July", venteSerie.getVenteSerieData().get("Juillet")));
        if(venteSerie.getVenteSerieData().get("Aout") > 0)
            series.getData().add(new XYChart.Data<>("Aug", venteSerie.getVenteSerieData().get("Aout")));
        if(venteSerie.getVenteSerieData().get("Septembre") > 0)
            series.getData().add(new XYChart.Data<>("Sep", venteSerie.getVenteSerieData().get("Septembre")));
        if(venteSerie.getVenteSerieData().get("Octobre") > 0)
            series.getData().add(new XYChart.Data<>("Oct", venteSerie.getVenteSerieData().get("Octobre")));
        if(venteSerie.getVenteSerieData().get("Novembre") > 0)
            series.getData().add(new XYChart.Data<>("Nov", venteSerie.getVenteSerieData().get("Novembre")));
        if(venteSerie.getVenteSerieData().get("Decembre") > 0)
            series.getData().add(new XYChart.Data<>("Dec", venteSerie.getVenteSerieData().get("Decembre")));


        venteLineChart.getData().add(series);
    }


}
