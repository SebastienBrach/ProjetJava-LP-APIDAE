package DAL;

public abstract class ProduitDAOFactory {
	/**
	 * getProduitDAO(String choix)
	 * Permet l'ajout d'un nouveau type de stockage des données
	 * @param choix
	 * @return
	 */
    public static ProduitDAOFactory getFactory(String choix) {
    	
    	I_ProduitDAO produitDAO = null;
    	if(choix.equals("Relationnel")) {
            return new ProduitDAORelationnelTrueFactory();
    	}
    	if(choix.equals("XML")) {
            return new ProduitDAOXMLTrueFactory();
    	}
        return null;
    }
    
    public abstract I_ProduitDAO getInstance();
}
