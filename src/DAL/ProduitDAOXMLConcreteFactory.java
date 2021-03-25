package DAL;

public class ProduitDAOXMLConcreteFactory extends ProduitDAOFactory {
    @Override
    public I_ProduitDAO getInstance() {
        return new AdaptateurDAOXML();
    }
}
