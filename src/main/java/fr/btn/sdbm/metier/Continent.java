package fr.btn.sdbm.metier;

public class Continent {
    private int id;
    private String nomContinent;

    public Continent(int id, String nomContinent) {
        this.id = id;
        this.nomContinent = nomContinent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomContinent() {
        return nomContinent;
    }

    public void setNomContinent(String nomContinent) {
        this.nomContinent = nomContinent;
    }

    @Override
    public String toString() {
        return nomContinent;
    }
}
