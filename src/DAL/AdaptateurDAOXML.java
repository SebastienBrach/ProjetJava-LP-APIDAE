
package DAL;

import java.sql.SQLException;
import java.util.List;
import Metier.I_Produit;

/**
 * 
 * @author Sébastien
 *
 */
public class AdaptateurDAOXML implements I_ProduitDAO {

	private ProduitDAOXML produitXML;
	
	public AdaptateurDAOXML() {
		this.produitXML = new ProduitDAOXML();
	}

	/**
	 * 
	 */
	@Override
	public boolean create(I_Produit produit) throws DAOException {
		return this.produitXML.creer(produit);
	}
	
	/**
	 * 
	 */
	@Override
	public int createAll(List<I_Produit> produits) throws DAOException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 
	 */
	@Override
	public I_Produit readByName(String nomProduit) throws DAOException, SQLException {
		return this.produitXML.lire(nomProduit);
	}
	
	/**
	 * 
	 */
	@Override
	public List<I_Produit> read() throws DAOException, SQLException {
		return this.produitXML.lireTous();
	}

	/**
	 * 
	 */
	@Override
	public boolean update(I_Produit produit) throws DAOException, SQLException {
		return this.produitXML.maj(produit);
	}

	/**
	 * 
	 */
	@Override
	public boolean delete(I_Produit produit) throws DAOException, SQLException {
		return this.produitXML.supprimer(produit);
	}

}
