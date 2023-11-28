package fr.btn.sdbm.dao;

import fr.btn.sdbm.metier.Fabricant;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class FabricantDAO extends DAO<Fabricant, Fabricant>{

    @Override
    public ArrayList<Fabricant> getAll() {
        ArrayList<Fabricant> fabricants = new ArrayList<>();
        String request = "SELECT * FROM FABRICANT";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(request);
            while(rs.next())
                fabricants.add(new Fabricant(rs.getInt("ID_FABRICANT"), rs.getString("NOM_FABRICANT")));
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return fabricants;
    }

    @Override
    public ArrayList<Fabricant> getLike(Fabricant objet) {
        return null;
    }

    @Override
    public boolean update(Fabricant objet) {
        return false;
    }

    @Override
    public boolean post(Fabricant object) {
        return false;
    }

    @Override
    public boolean delete(Fabricant object) {
        return false;
    }
}
