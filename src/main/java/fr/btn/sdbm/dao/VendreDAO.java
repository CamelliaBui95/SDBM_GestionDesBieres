package fr.btn.sdbm.dao;

import fr.btn.sdbm.metier.Vendre;
import fr.btn.sdbm.metier.VendreSearch;
import fr.btn.sdbm.metier.VenteSerie;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

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
                VenteSerie serie = new VenteSerie("Vente-" + rs.getInt(1));
                for(int i = 2; i < 14; i++) {
                    String colName = rsmd.getColumnName(i);
                    long data = (long) rs.getInt(i);
                    serie.addData(colName, data);
                }
                //System.out.println(serie);
                venteSeries.add(serie);
            }
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return venteSeries;
    }
}
