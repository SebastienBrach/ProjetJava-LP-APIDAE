package DAL;

public class CatalogueDAOXMLTrueFactory extends CatalogueDAOFactory {
    @Override
    public I_CatalogueDAO getInstance() {
        return new CatalogueDAOXML();
    }
}
