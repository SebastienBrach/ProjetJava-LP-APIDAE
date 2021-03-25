
package DAL;

import java.sql.SQLException;
import java.util.List;
import Metier.I_Produit;

/**
 * AdaptateurDAOXML
 * Permet d'adapter le code de ProduitDAOXML avec le bon nom des méthodes
 */
public class AdaptateurDAOXML implements I_ProduitDAO {

	private ProduitDAOXML produitXML;
	
	public AdaptateurDAOXML() {
		this.produitXML = new ProduitDAOXML();
	}

	public boolean create(I_Produit produit, int catalogueId) throws DAOException {
		return this.produitXML.creer(produit);
	}
	
	@Override
	public int createAll(List<I_Produit> produits, int catalogueId) throws DAOException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public I_Produit readByName(String nomProduit) throws DAOException, SQLException {
		return this.produitXML.lire(nomProduit);
	}
	
	@Override
	public List<I_Produit> readByCatalogue(String nomCatalogue) throws DAOException {
		try {
			List<I_Produit> produits = this.read();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<I_Produit> read() throws DAOException, SQLException {
		return this.produitXML.lireTous();
	}

	@Override
	public boolean update(I_Produit produit) throws DAOException, SQLException {
		return this.produitXML.maj(produit);
	}

	@Override
	public boolean delete(I_Produit produit) throws DAOException, SQLException {
		return this.produitXML.supprimer(produit);
	}
	
}
