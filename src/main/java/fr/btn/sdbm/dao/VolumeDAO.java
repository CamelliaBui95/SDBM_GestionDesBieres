package fr.btn.sdbm.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class VolumeDAO extends DAO<Integer, Integer>{
    @Override
    public ArrayList<Integer> getAll() {
        ArrayList<Integer> volumes = new ArrayList<>();
        String request = "SELECT ROW_NUMBER() OVER ( ORDER BY VOLUME) AS ID_VOLUME, VOLUME FROM ARTICLE GROUP BY VOLUME;";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(request);
            while(rs.next())
                volumes.add(rs.getInt(2));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return volumes;
    }

    @Override
    public ArrayList<Integer> getLike(Integer volume) {
        return null;
    }

    @Override
    public boolean update(Integer objet) {
        return false;
    }

    @Override
    public boolean post(Integer object) {
        return false;
    }

    @Override
    public boolean delete(Integer object) {
        return false;
    }
}
