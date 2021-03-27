package DAL;

public class ProduitDAOXMLTrueFactory extends ProduitDAOFactory {
    @Override
    public I_ProduitDAO getInstance() {
        return new AdaptateurDAOXML();
    }
}
