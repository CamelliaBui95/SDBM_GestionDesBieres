package fr.btn.sdbm.dao;

import fr.btn.sdbm.metier.Continent;
import fr.btn.sdbm.metier.Fabricant;
import fr.btn.sdbm.metier.Marque;
import fr.btn.sdbm.metier.Pays;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MarqueDAO extends DAO<Marque, Marque>{
    private ArrayList<Pays> pays;
    private ArrayList<Fabricant> fabricants;
    @Override
    public ArrayList<Marque> getAll() {
        ArrayList<Marque> marques = new ArrayList<>();
        String request = "SELECT * FROM MARQUE";
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery(request);
            while(rs.next()) {
                int idMarque = rs.getInt(1);
                String nomMarque = rs.getString(2);
                Pays pays = getPaysForMarque(rs.getInt(3));
                Fabricant fabricant = getFabricantForMarque(rs.getInt(4));
                marques.add(new Marque(idMarque, nomMarque, pays, fabricant));
            }
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return marques;
    }

    @Override
    public ArrayList<Marque> getLike(Marque marque) {
        ArrayList<Marque> marqueList = new ArrayList<>();
        String request = "{call ps_searchMarques(?)}";
        try {
            PreparedStatement stmt = connection.prepareCall(request);
            stmt.setInt(1, marque.getFabricant().getId());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                int idMarque = rs.getInt(1);
                String nomMarque = rs.getString(2);
                int idPays = rs.getInt(3);
                int idFabricant = rs.getInt(4);
                Fabricant fabricant = getFabricantForMarque(idFabricant);
                Pays pays = getPaysForMarque(idPays);
                marqueList.add(new Marque(idMarque, nomMarque, pays, fabricant));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return marqueList;
    }

    @Override
    public boolean update(Marque objet) {
        return false;
    }

    @Override
    public boolean post(Marque object) {
        return false;
    }

    @Override
    public boolean delete(Marque object) {
        return false;
    }

    private Pays getPaysForMarque(int idPays) {
        if(pays == null || idPays == 0)
            return null;
        return pays.get(idPays - 1);
    }

    private Fabricant getFabricantForMarque(int idFabricant) {
        if(fabricants == null || idFabricant == 0)
            return null;
        return fabricants.get(idFabricant - 1);
    }

    public void setPays(ArrayList<Pays> pays) {
        this.pays = pays;
    }

    public void setFabricants(ArrayList<Fabricant> fabricants) {
        this.fabricants = fabricants;
    }
}
