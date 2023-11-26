package fr.btn.sdbm.dao;

import fr.btn.sdbm.metier.Article;
import fr.btn.sdbm.service.ArticleSearch;

import java.sql.*;
import java.util.ArrayList;

public class ArticleDAO extends DAO<Article, ArticleSearch> {

    @Override
    public ArrayList<Article> getAll() {
        ArrayList<Article> articles = new ArrayList<>();
        String ps = "{call ps_allArticles}";
        try {
            CallableStatement cStmt = this.connection.prepareCall(ps);
            ResultSet rs = cStmt.executeQuery();
            while(rs.next()) {
                Article article = new Article(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getFloat(5), rs.getFloat(4), rs.getInt(6),
                        rs.getString(7), rs.getString(14), rs.getString(13),
                        rs.getString(16), rs.getString(12), rs.getString(18));
                article.setIdCouleur(rs.getInt("ID_COULEUR"));
                article.setIdType(rs.getInt("ID_TYPE"));
                article.setIdMarque(rs.getInt("ID_MARQUE"));
                article.setIdFabricant(rs.getInt("ID_FABRICANT"));
                article.setIdPays(rs.getInt("ID_PAYS"));
                article.setIdContinent(rs.getInt("ID_CONTINENT"));

                articles.add(article);
            }
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return articles;
    }

    @Override
    public ArrayList<Article> getLike(ArticleSearch articleSearch) {
        ArrayList<Article> articles = new ArrayList<>();
        String spReq = "{call ps_searchArticles(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        String nomArticle = articleSearch.getNomArticle();
        int volume = articleSearch.getVolume() == null || articleSearch.getVolume().getId() == 0 ? 0 : Integer.parseInt(articleSearch.getVolume().getVolume());
        float titrageMin = articleSearch.getTitrage().getMin();
        float titrageMax = articleSearch.getTitrage().getMax();
        int idCouleur = articleSearch.getCouleur() == null ? 0 : articleSearch.getCouleur().getId();
        int idMarque = articleSearch.getMarque() == null ? 0 : articleSearch.getMarque().getId();
        int idType = articleSearch.getType() == null ? 0 : articleSearch.getType().getID();
        int idPays = articleSearch.getPays() == null ? 0 : articleSearch.getPays().getId();
        int idFabricant = articleSearch.getFabricant() == null ? 0 : articleSearch.getFabricant().getId();
        int idContinent = articleSearch.getContinent() == null ? 0 : articleSearch.getContinent().getId();
        try {
           CallableStatement stmt = connection.prepareCall(spReq);
           stmt.setString(1, nomArticle);
           stmt.setInt(2, volume);
           stmt.setFloat(3, titrageMin);
           stmt.setFloat(4, titrageMax);
           stmt.setInt(5, idCouleur);
           stmt.setInt(6, idMarque);
           stmt.setInt(7, idType);
           stmt.setInt(8, idPays);
           stmt.setInt(9, idFabricant);
           stmt.setInt(10, idContinent);
           stmt.setInt(11, 0);
           stmt.setInt(12, 0);
           stmt.registerOutParameter(13, Types.INTEGER);
           stmt.executeQuery();
           stmt.getMoreResults();
           ResultSet rs = stmt.getResultSet();
           while(rs.next()) {
               Article article = new Article(rs.getInt(1), rs.getString(2), rs.getInt("Volume"),
                       rs.getFloat("Titrage"), rs.getFloat("Prix_Achat"), rs.getInt("Stock"), rs.getString("Couleur"),
                       rs.getString("Type"), rs.getString("Marque"), rs.getString("Fabricant"), rs.getString("Pays"),
                       rs.getString("Continent"));
               article.setIdCouleur(rs.getInt("ID_COULEUR"));
               article.setIdType(rs.getInt("ID_TYPE"));
               article.setIdMarque(rs.getInt("ID_MARQUE"));
               article.setIdFabricant(rs.getInt("ID_FABRICANT"));
               article.setIdPays(rs.getInt("ID_PAYS"));
               article.setIdContinent(rs.getInt("ID_CONTINENT"));
               articles.add(article);
           }

           rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return articles;
    }

    @Override
    public boolean update(Article article) {
        String rq = "{call ps_modifierArticle(?,?,?,?,?,?,?,?,?)}";
        try {
            PreparedStatement stmt = connection.prepareStatement(rq, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, article.getId());
            stmt.setString(2, article.getNomArticle());
            stmt.setFloat(3, article.getPrixAchat());
            stmt.setInt(4, article.getVolume());
            stmt.setFloat(5, article.getTitrage());
            stmt.setInt(6, article.getIdMarque());
            stmt.setInt(7, article.getIdCouleur());
            stmt.setInt(8, article.getIdType());
            stmt.setInt(9, article.getStock());
            stmt.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean post(Article article) {
        String ps = "{call ps_insertArticle(?,?,?,?,?,?,?,?)}";
        try {
            PreparedStatement stmt = connection.prepareStatement(ps);
            stmt.setString(1, article.getNomArticle());
            stmt.setFloat(2, article.getPrixAchat());
            stmt.setInt(3, article.getVolume());
            stmt.setFloat(4, article.getTitrage());
            stmt.setInt(5, article.getIdMarque() + 1);
            stmt.setInt(6, article.getIdCouleur());
            stmt.setInt(7, article.getIdType());
            stmt.setInt(8, article.getStock());
            stmt.execute();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
