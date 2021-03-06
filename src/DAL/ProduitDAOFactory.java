package DAL;

public class ProduitDAOFactory {
	/**
	 * 
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
