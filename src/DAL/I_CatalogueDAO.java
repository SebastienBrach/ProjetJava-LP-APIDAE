package DAL;

import Metier.I_Catalogue;

import java.util.List;

public interface I_CatalogueDAO {
    boolean create(I_Catalogue catalogue) throws DAOException;
    List<I_Catalogue> read() throws DAOException;
    boolean delete(I_Catalogue catalogue) throws DAOException;
    boolean delete(String nomCatalogue) throws DAOException;
}
