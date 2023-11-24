package fr.btn.sdbm.dao;

import fr.btn.sdbm.metier.Article;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ArticleDAO extends DAO<Article> {

    @Override
    public ArrayList<Article> getAll() {
        ArrayList<Article> articles = new ArrayList<>();
        String ps = "{call ps_allArticles}";
        try {
            CallableStatement cStmt = this.connection.prepareCall(ps);
            ResultSet rs = cStmt.executeQuery();
            while(rs.next()) {
                articles.add(new Article(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getFloat(5), rs.getFloat(4), rs.getInt(6), rs.getString(7), rs.getString(14), rs.getString(13), rs.getString(16), rs.getString(12), rs.getString(18)));
            }
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return articles;
    }
}
