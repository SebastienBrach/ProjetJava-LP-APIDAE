package DAL;

import Metier.Catalogue;
import Metier.I_Catalogue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogueDAORelationnel implements I_CatalogueDAO {
	private PreparedStatement pst;
	private ResultSet rs;
	private Connection cn;
	private String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String login = "brachs";
	private String mdp = "21092020";
	
	/**
	 * CatalogueDAORelationnel() 
	 * Permet de se connecter à la BD
	 */
    public void CatalogueDAORelationnel() {
    	try {
			Class.forName(driver);
			this.cn = DriverManager.getConnection(this.url, this.login, this.mdp);
		}
		catch(SQLException | ClassNotFoundException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
    }

    /**
	 * create(I_Catalogue p)
	 * Ajoute un catalogue à la table Catalogue
	 * Appel de la procédure nouveauProduit au lieu de faire un INSERT INTO classique (car on ne peut pas ajouter l'id en dur)
	 */
    @Override
    public boolean create(I_Catalogue c) throws DAOException {
    	CatalogueDAORelationnel();
		try {
			String query = "CALL nouveauCatalogue(?)";
			this.pst = this.cn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.pst.setString(3, c.getNom());
			this.rs = pst.executeQuery();
			return true;
		} catch(SQLException e) {
			System.out.println("erreur create catalogue");
			System.out.println(e.getMessage());
			return false;
		}
    }

	@Override
    public List<I_Catalogue> read() throws DAOException {
    	CatalogueDAORelationnel();
		try {
			String query = "SELECT idCatalogue, nomCatalogue FROM catalogue";
			this.pst = this.cn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.rs = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            List<I_Catalogue> catalogues = new ArrayList<>();
            while (this.rs.next()) {
                String nomCatalogue;
                nomCatalogue = this.rs.getString("nomCatalogue");
                int idCatalogue;
                idCatalogue = this.rs.getInt("idCatalogue");
                catalogues.add(new Catalogue(nomCatalogue, idCatalogue));
            }
            return catalogues;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }

    @Override
    public boolean delete(I_Catalogue catalogue) throws DAOException {
        return delete(catalogue.getNom());
    }

    @Override
    public boolean delete(String nomCatalogue) throws DAOException {
        PreparedStatement pst = null;

        try {
            pst = cn.prepareStatement("DELETE FROM catalogue WHERE nomCatalogue = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            pst.setString(1, nomCatalogue);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet rows = null;

        try {
            rows = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            e.printStackTrace();
        }

        try {
            boolean isRowExist = rows.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}
