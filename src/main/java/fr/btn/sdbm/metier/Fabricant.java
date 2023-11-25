package fr.btn.sdbm.metier;

public class Fabricant {
    private int id;
    private String nomFabricant;

    public Fabricant(int id, String nomFabricant) {
        this.id = id;
        this.nomFabricant = nomFabricant;
    }

    public Fabricant() {
        this.id = 0;
        this.nomFabricant = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomFabricant() {
        return nomFabricant;
    }

    public void setNomFabricant(String nomFabricant) {
        this.nomFabricant = nomFabricant;
    }

    @Override
    public String toString() {
        return nomFabricant;
    }
}
