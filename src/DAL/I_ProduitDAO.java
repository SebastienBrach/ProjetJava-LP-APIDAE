package DAL;

import java.sql.SQLException;
import java.util.List;
import Metier.I_Produit;

public interface I_ProduitDAO {
	boolean create(I_Produit produit, int catalogueId) throws DAOException, SQLException;
    int createAll(List<I_Produit> produits, int catalogueId) throws DAOException, SQLException;
    I_Produit readByName(String nomProduit) throws DAOException, SQLException;
    List<I_Produit> readByCatalogue(String nomCatalogue) throws DAOException;
    List<I_Produit> read() throws DAOException, SQLException;
    boolean update(I_Produit produit) throws DAOException, SQLException;
    boolean delete(I_Produit produit) throws DAOException, SQLException;	
}
