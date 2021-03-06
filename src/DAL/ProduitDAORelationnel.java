package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Metier.I_Produit;
import Metier.Produit;

public class ProduitDAORelationnel implements I_ProduitDAO {
	
	/**
	 * Initialisation de variables
	 */
	private PreparedStatement pst;
	private ResultSet rs;
	private Connection cn;
	private String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String login = "brachs";
	private String mdp = "21092020";
	
	/**
	 * ProduitDAORelationnel() =>
	 */
	public void ProduitDAORelationnel() {
		try {
			Class.forName(driver);
			this.cn = DriverManager.getConnection(url, login, mdp);
		}
		catch(SQLException | ClassNotFoundException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * create(I_Produit p) =>
	 */
	public boolean create(I_Produit p) {
		ProduitDAORelationnel();
		try {
			String query = "CALL nouveauProduit (?, ?, ?)";
			this.pst = this.cn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.pst.setString(1, p.getNom());
			this.pst.setDouble(2, p.getPrixUnitaireHT());
			this.pst.setInt(3, p.getQuantite());
			this.rs = pst.executeQuery();
			return true;
		} catch(SQLException e) {
			System.out.println("erreur create produit");
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	/**
	 * createAll(List<I_Produit> produits) =>
	 */
	@Override
	public int createAll(List<I_Produit> produits) throws DAOException, SQLException {
		int compteurProduit = 0;
        for (I_Produit newProduit : produits) {
            if (this.create(newProduit)) {
            	compteurProduit++;
			}
        }
        return compteurProduit;  	
	}
	
	/**
	 * 
	 */
	public I_Produit readByName(String nomProduit) throws DAOException, SQLException {
		ProduitDAORelationnel();
		String query = "SELECT * FROM PRODUIT WHERE nomProduit = ?";
		this.pst = this.cn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		this.pst.setString(1, nomProduit);
		try {
			this.rs = pst.executeQuery();
			this.rs.next();
			float prixUnitaireHT = this.rs.getFloat("prixUnitaireHT");
			int qteStock = this.rs.getInt("qteStock");
			return new Produit(nomProduit, prixUnitaireHT, qteStock);
		} catch(SQLException e) {
			System.out.println("erreur readByName produit");
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 
	 */
	public List<I_Produit> read() throws DAOException, SQLException {
		ProduitDAORelationnel();
		String query = "SELECT * FROM PRODUIT";
		this.pst = this.cn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		try {
			this.rs = this.pst.executeQuery();
			List<I_Produit> produits = new ArrayList<>();
			while(this.rs.next()) {
                String nomProduit = this.rs.getString("nomProduit");
                float prixUnitaireHT = this.rs.getFloat("prixUnitaireHT");
                int qteStock = this.rs.getInt("qteStock");
                produits.add(new Produit(nomProduit, prixUnitaireHT, qteStock));
            }
			return produits;
		} catch(SQLException e) {
			System.out.println("erreur read produit");
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 
	 */
	public boolean update(I_Produit p) throws DAOException, SQLException {
		ProduitDAORelationnel();
		String query = "UPDATE PRODUIT SET nomProduit=?, prixUnitaireHT=?, qteStock=? WHERE nomProduit = ?";
		this.pst = this.cn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		this.pst.setString(1, p.getNom());
		this.pst.setDouble(2, p.getPrixUnitaireHT());
		this.pst.setInt(3, p.getQuantite());
        this.pst.setString(4, p.getNom());
		try {
			this.rs = pst.executeQuery();
			return true;
		} catch(SQLException e) {
			System.out.println("erreur update produit");
			System.out.println(e.getMessage());
			return false;
		}	
	}
	
	/**
	 * 
	 */
	public boolean delete(I_Produit p) throws DAOException, SQLException {
		ProduitDAORelationnel();
		String query = "DELETE FROM PRODUIT WHERE nomProduit=?";
		this.pst = this.cn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		this.pst.setString(1, p.getNom());
		try {
			this.rs = pst.executeQuery();
			return true;
		} catch(SQLException e) {
			System.out.println("erreur delete produit");
			System.out.println(e.getMessage());
			return false;
		}	
	}

		
//	public void deconnexion() throws DAOException, SQLException {
//		this.rs.close();
//		this.pst.close();
//		this.cn.close();
//	}	
	
//	public static void main(String[] args) throws DAOException, SQLException {
//		ProduitDAORelationnel p = new ProduitDAORelationnel();
//		I_Produit produit = new Produit("Test2", 3, 4);
//		p.update(produit);
//	}
}
