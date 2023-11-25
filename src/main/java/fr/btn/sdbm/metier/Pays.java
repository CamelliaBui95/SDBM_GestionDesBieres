package fr.btn.sdbm.metier;

public class Pays {
    private int id;
    private String nomPays;
    private Continent continent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomPays() {
        return nomPays;
    }

    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public Pays(int id, String nomPays, Continent continent) {
        this.id = id;
        this.nomPays = nomPays;
        this.continent = continent;
        //System.out.println(nomPays + " - " + continent);
    }

    public Pays() {
        this.id = 0;
        this.nomPays = "";
    }

    @Override
    public String toString() {
        return nomPays;
    }
}
