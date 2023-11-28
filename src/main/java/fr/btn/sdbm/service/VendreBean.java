package fr.btn.sdbm.service;

import fr.btn.sdbm.dao.VendreDAO;
import fr.btn.sdbm.metier.VenteSerie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VendreBean {
    private VendreDAO vendreDAO;
    private ObservableList<VenteSerie> venteSeries;

    public VendreBean() {
        this.vendreDAO = new VendreDAO();
        venteSeries = FXCollections.observableArrayList();

        venteSeries.addAll(vendreDAO.getVentesSeriesPerMonthAndYear());
    }

    public ObservableList<VenteSerie> getVenteSeries() {
        return venteSeries;
    }

    public void setVenteSeries(ObservableList<VenteSerie> venteSeries) {
        this.venteSeries = venteSeries;
    }
}
