package fr.btn.sdbm.dao;

import fr.btn.sdbm.metier.Continent;
import fr.btn.sdbm.metier.Pays;

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
    public ArrayList<Pays> getLike(Pays objet) {
        return null;
    }

    @Override
    public boolean update(Pays objet) {
        return false;
    }

    @Override
    public boolean post(Pays object) {
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
