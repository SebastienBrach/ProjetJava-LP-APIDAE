package Application;

import java.sql.SQLException;

import DAL.DAOException;
import DAL.I_ProduitDAO;
import DAL.ProduitDAOFactory;
import Metier.Catalogue;
import Metier.I_Catalogue;
import Presentation.FenetrePrincipale;

/**
 * ControleurFacade
 * Controleur Central
 * Permet d'initialiser un seul catalogue
 * Permet de faire de l'aggressive loading
 * Permet de choisir facilement le type de stockage de données
 */
public class ControleurFacade {
	
	private ControleurCreationSuppression controleurCreationSuppression; 
	private ControleurAchatVente controleurAchatVente;
	private ControleurAffichage controleurAffichage; 
	private static FenetrePrincipale fenetrePrincipale;
	private static I_Catalogue catalogue;
	private static I_ProduitDAO produitDao;
	
	public ControleurFacade() throws SQLException{
		ControleurFacade.produitDao = ProduitDAOFactory.getProduitDAO("XML");
		ControleurFacade.catalogue = new Catalogue();
		try {
			ControleurFacade.catalogue.addProduits(produitDao.read());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		this.controleurCreationSuppression = new ControleurCreationSuppression();
		this.controleurAchatVente = new ControleurAchatVente();
		this.controleurAffichage = new ControleurAffichage();
		this.afficherFenetrePrincipale();
	}
	
	private void afficherFenetrePrincipale() { ControleurFacade.fenetrePrincipale = new FenetrePrincipale();}
	
	public static I_Catalogue getCatalogue() { return ControleurFacade.catalogue;}
	
	public ControleurCreationSuppression getControleurCreationSuppression() { return controleurCreationSuppression;}
	
	public ControleurAchatVente getControleurAchatVente() { return controleurAchatVente;}
	
	public ControleurAffichage getControleurAffichage() { return controleurAffichage;}
	
	public static I_ProduitDAO getProduitDao() { return produitDao;}
	
	public static void main(String[] args) throws SQLException {
		new ControleurFacade();
	}
}
