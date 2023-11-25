package fr.btn.sdbm.metier;

public class Volume {
    private int id;
    private String volume;

    public Volume(int id, String volume) {
        this.id = id;
        this.volume = volume;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return volume;
    }
}
