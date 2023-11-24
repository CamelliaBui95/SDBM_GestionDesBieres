package fr.btn.sdbm.dao;

import java.sql.Connection;
import java.util.ArrayList;


public abstract class DAO<T> {
    protected Connection connection;

    public DAO() {
        connection = SBDMConnect.getInstance();
    }
    public abstract ArrayList<T> getAll();
}
