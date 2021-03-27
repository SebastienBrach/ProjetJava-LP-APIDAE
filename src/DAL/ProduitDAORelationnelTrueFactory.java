package DAL;

public class ProduitDAORelationnelTrueFactory extends ProduitDAOFactory {
    @Override
    public I_ProduitDAO getInstance() {
        return new ProduitDAORelationnel();
    }
}
