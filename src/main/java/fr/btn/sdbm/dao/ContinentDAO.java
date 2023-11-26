package fr.btn.sdbm.dao;

import fr.btn.sdbm.metier.Continent;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ContinentDAO extends DAO<Continent, Continent>{
    @Override
    public ArrayList<Continent> getAll() {
        ArrayList<Continent> continents = new ArrayList<>();
        String request = "SELECT * FROM CONTINENT";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(request);
            while(rs.next())
                continents.add(new Continent(rs.getInt("ID_CONTINENT"), rs.getString("NOM_CONTINENT")));
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return continents;
    }

    @Override
    public ArrayList<Continent> getLike(Continent objet) {
        return null;
    }

    @Override
    public boolean update(Continent objet) {
        return false;
    }
}
