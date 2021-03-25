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
            return new ProduitDAORelationnelConcreteFactory();
    	}
    	if(choix.equals("XML")) {
            return new ProduitDAOXMLConcreteFactory();
    	}
        return null;
    }
    
    public abstract I_ProduitDAO getInstance();
}
