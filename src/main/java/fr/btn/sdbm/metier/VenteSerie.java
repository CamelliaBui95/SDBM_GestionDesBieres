package fr.btn.sdbm.metier;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.HashMap;

public class VenteSerie {
    private String name;
    private String CA;
    private HashMap<String, Long> venteSerieData;
    // mois + quantite

    public VenteSerie(String name) {
        this.name = name;
        this.venteSerieData = new HashMap<>();
    }

    public void addData(String title, long data) {
        venteSerieData.putIfAbsent(title, data);
    }

    public String getCA() {
        return CA;
    }

    public void setCA(String CA) {
        this.CA = CA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Long> getVenteSerieData() {
        return venteSerieData;
    }

    @Override
    public String toString() {
        return name + " = " + venteSerieData.toString();
    }
}
