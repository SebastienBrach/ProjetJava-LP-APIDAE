package DAL;

public class ProduitDAORelationnelConcreteFactory extends ProduitDAOFactory {
    @Override
    public I_ProduitDAO getInstance() {
        return new ProduitDAORelationnel();
    }
}
