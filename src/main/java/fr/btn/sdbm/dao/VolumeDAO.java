package fr.btn.sdbm.dao;

import fr.btn.sdbm.metier.Volume;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class VolumeDAO extends DAO<Volume, Volume>{
    @Override
    public ArrayList<Volume> getAll() {
        ArrayList<Volume> volumes = new ArrayList<>();
        String request = "SELECT ROW_NUMBER() OVER ( ORDER BY VOLUME) AS ID_VOLUME, VOLUME FROM ARTICLE GROUP BY VOLUME;";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(request);
            while(rs.next())
                volumes.add(new Volume(rs.getInt(1), Integer.toString(rs.getInt(2))));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return volumes;
    }

    @Override
    public ArrayList<Volume> getLike(Volume objet) {
        return null;
    }
}
