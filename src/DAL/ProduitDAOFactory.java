package DAL;

public class ProduitDAOFactory {
	/**
	 * getProduitDAO(String choix)
	 * Permet l'ajout d'un nouveau type de stockage des données
	 * @param choix
	 * @return
	 */
    public static I_ProduitDAO getProduitDAO(String choix) {
    	I_ProduitDAO produitDAO = null;
    	if(choix.equals("Relationnel")) {
    		produitDAO = new ProduitDAORelationnel();
    	}
    	else if(choix.equals("XML")) {
    		produitDAO = new AdaptateurDAOXML();
    	}
        return produitDAO;
    }
}
