package fr.btn.sdbm.dao;

import java.sql.Connection;
import java.util.ArrayList;


public abstract class DAO<T, TSearch> {
    protected Connection connection;

    public DAO() {
        connection = SBDMConnect.getInstance();
    }
    public abstract ArrayList<T> getAll();
    public abstract ArrayList <T> getLike(TSearch object);
    public abstract boolean update(T object);

    public abstract boolean post(T object);
}
