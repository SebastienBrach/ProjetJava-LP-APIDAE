package DAL;

public class CatalogueDAORelationnelTrueFactory extends CatalogueDAOFactory {
    @Override
    public I_CatalogueDAO getInstance() {
        return new CatalogueDAORelationnel();
    }
}
