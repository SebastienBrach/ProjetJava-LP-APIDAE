package DAL;

public abstract class CatalogueDAOFactory {
	
    public static CatalogueDAOFactory getFactory(String choix) {
        if (choix.equals("Relationnel")) {
        	return new CatalogueDAORelationnelTrueFactory();
        }
        if (choix.equals("XML")) {
        	return new CatalogueDAOXMLTrueFactory();
        }
        return null;
    }
    public abstract I_CatalogueDAO getInstance();
}
