package fr.btn.sdbm.metier;

public class Type {
    private int ID;
    private String nomType;

    public Type(int ID, String nomType) {
        this.ID = ID;
        this.nomType = nomType;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    @Override
    public String toString() {
        return nomType;
    }
}
