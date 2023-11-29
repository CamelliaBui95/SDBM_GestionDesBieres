package fr.btn.sdbm.dao;

import fr.btn.sdbm.metier.Continent;
import fr.btn.sdbm.metier.Pays;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PaysDAO extends DAO<Pays, Pays>{
    private ArrayList<Continent> continents;
    @Override
    public ArrayList<Pays> getAll() {
        ArrayList<Pays> pays = new ArrayList<>();
        String request = "SELECT * FROM PAYS";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(request);
            while(rs.next()) {
                int idPays = rs.getInt(1);
                String nomPays = rs.getString(2);
                int idContinent = rs.getInt(3);
                Continent continent = getContinentForPays(idContinent);
                pays.add(new Pays(idPays, nomPays, continent));
            }
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return pays;
    }

    @Override
    public ArrayList<Pays> getLike(Pays pays) {
        ArrayList<Pays> paysList = new ArrayList<>();
        String request = "{call ps_searchPays(?)}";
        try {
            PreparedStatement stmt = connection.prepareCall(request);
            stmt.setInt(1, pays.getContinent().getId());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                int idPays = rs.getInt(1);
                String nomPays = rs.getString(2);
                int idContinent = rs.getInt(3);
                Continent continent = getContinentForPays(idContinent);
                paysList.add(new Pays(idPays, nomPays, continent));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return paysList;
    }

    @Override
    public boolean update(Pays objet) {
        return false;
    }

    @Override
    public boolean post(Pays object) {
        return false;
    }

    @Override
    public boolean delete(Pays object) {
        return false;
    }

    private Continent getContinentForPays(int idContinent) {
        if(continents == null)
            return null;

        return continents.get(idContinent - 1);
    }

    public void setContinents(ArrayList<Continent> continents) {
        this.continents = continents;
    }
}
