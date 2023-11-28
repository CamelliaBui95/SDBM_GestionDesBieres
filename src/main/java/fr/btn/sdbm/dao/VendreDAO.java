package fr.btn.sdbm.dao;

import fr.btn.sdbm.metier.Vendre;
import fr.btn.sdbm.metier.VendreSearch;
import fr.btn.sdbm.metier.VenteSerie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class VendreDAO extends DAO<Vendre, VendreSearch>{
    @Override
    public ArrayList<Vendre> getAll() {
        return null;
    }

    @Override
    public ArrayList<Vendre> getLike(VendreSearch object) {
        return null;
    }

    @Override
    public boolean update(Vendre object) {
        return false;
    }

    @Override
    public boolean post(Vendre object) {
        return false;
    }

    @Override
    public boolean delete(Vendre object) {
        return false;
    }

    public ArrayList<VenteSerie> getVentesSeriesPerMonthAndYear() {
        ArrayList<VenteSerie> venteSeries = new ArrayList<>();
        String ps = "{call ps_showVentesFrom2014To2022_2}";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(ps);
            ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next()) {
                VenteSerie serie = new VenteSerie(Integer.toString(rs.getInt(1)));
                for(int i = 2; i < 14; i++) {
                    String colName = rsmd.getColumnName(i);
                    long data = (long) rs.getInt(i);
                    serie.addData(colName, data);
                }
                serie.setCA(rs.getString(14));
                venteSeries.add(serie);
            }
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return venteSeries;
    }

    public ArrayList<Vendre> getVentesByYearForArticles(int annee) {
        ArrayList<Vendre> ventes = new ArrayList<>();
        String ps = "{call ps_showVenteDesArticlesParAnnee(?)}";
        try {
            PreparedStatement stmt = connection.prepareStatement(ps);
            stmt.setInt(1, annee);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
                ventes.add(new Vendre(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return ventes;
    }
}
