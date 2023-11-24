package fr.btn.sdbm.dao;

import fr.btn.sdbm.metier.Type;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TypeDAO extends DAO<Type>{
    @Override
    public ArrayList<Type> getAll() {
        ArrayList<Type> types = new ArrayList<>();
        String sqlRequest = "SELECT * FROM TYPEBIERE";
        try {
            Statement stm = this.connection.createStatement();
            ResultSet rs = stm.executeQuery(sqlRequest);
            while(rs.next())
                types.add(new Type(rs.getInt(1), rs.getString(2)));
        } catch(Exception e) {
            e.printStackTrace();
        }

        return types;
    }
}
