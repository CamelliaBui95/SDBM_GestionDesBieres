package fr.btn.sdbm.service;

import fr.btn.sdbm.dao.VendreDAO;
import fr.btn.sdbm.metier.Vendre;
import fr.btn.sdbm.metier.VenteSerie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class VendreBean {
    private VendreDAO vendreDAO;
    private ObservableList<VenteSerie> venteSeries;
    private ObservableList<Vendre> ventesDesArticles;
    private FilteredList<Vendre> filteredVentesDesArticles;
    private SortedList<Vendre> sortedVentesDesArticles;

    public VendreBean() {
        this.vendreDAO = new VendreDAO();
        venteSeries = FXCollections.observableArrayList();
        ventesDesArticles = FXCollections.observableArrayList();

        venteSeries.addAll(vendreDAO.getVentesSeriesPerMonthAndYear());
        filteredVentesDesArticles = new FilteredList<>(ventesDesArticles, null);
        sortedVentesDesArticles = new SortedList<>(filteredVentesDesArticles);
    }

    public void getVentesByYearForArticles(int annee) {
        ventesDesArticles.setAll(vendreDAO.getVentesByYearForArticles(annee));
    }

    public ObservableList<VenteSerie> getVenteSeries() {
        return venteSeries;
    }

    public ObservableList<Vendre> getVentesDesArticles() {
        return ventesDesArticles;
    }

    public SortedList<Vendre> getSortedVentesDesArticles() {
        return sortedVentesDesArticles;
    }
}
