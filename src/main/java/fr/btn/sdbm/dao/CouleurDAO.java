package fr.btn.sdbm.dao;

import fr.btn.sdbm.metier.Couleur;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CouleurDAO extends DAO<Couleur>{
    @Override
    public ArrayList<Couleur> getAll() {
        ArrayList<Couleur> couleurs = new ArrayList<>();
        String request = "SELECT * FROM COULEUR";
        try {
            Statement stm = this.connection.createStatement();
            ResultSet rs = stm.executeQuery(request);
            while(rs.next())
                couleurs.add(new Couleur(rs.getInt(1), rs.getString(2)));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return couleurs;
    }
}
