//package Application;

//
//import Metier.Catalogue;
//import Metier.I_Catalogue;
//import Presentation.FenetreAchat;
//import Presentation.FenetreVente;
//
//public class ControleurAchatVente {
//	
//	private I_Catalogue catalogue;
//	private String[] nomProduit;
//
//	public ControleurAchatVente() {
//		this.catalogue = new Catalogue();
//		this.nomProduit = this.catalogue.getNomProduits();
//	}
//	
//	public void demanderAchat() {
//		new FenetreAchat(nomProduit);		
//	}
//	
//	public void demanderVente() {
//		new FenetreVente(nomProduit);
//	}
//	
//	public String acheter(String nom, int qte) {
//		catalogue.acheterStock(nom, qte);
//		return "Vous avez acheter "+qte+" "+nom;
//	}
//	
//	public String vendre(String nom, int qte) {
//		catalogue.vendreStock(nom, qte);
//		return "Vous avez vendu "+qte+" "+nom;
//	}
//}
//



package Application;

import java.sql.SQLException;

import DAL.DAOException;
import Metier.I_Produit;
import Metier.Produit;
import Presentation.FenetreAchat;
import Presentation.FenetreNouveauProduit;
import Presentation.FenetrePrincipale;
import Presentation.FenetreSuppressionProduit;
import Presentation.FenetreVente;

/**
 * 
 * @author Sébastien
 *
 */
public class ControleurAchatVente {
	
	private static FenetreAchat fenetreAchat;
	private static FenetreVente fenetreVente;
	
	public ControleurAchatVente() {
	}
	
	/**
	 * 
	 */
	public static void demanderAchat() {
		ControleurAchatVente.fenetreAchat = new FenetreAchat(ControleurFacade.getCatalogue().getNomProduits());		
	}
	
	/**
	 * 
	 */
	public static void demanderVente() {
		ControleurAchatVente.fenetreVente = new FenetreVente(ControleurFacade.getCatalogue().getNomProduits());
	}
	
	/**
	 * 
	 * @param nom
	 * @param qte
	 * @return
	 * @throws SQLException
	 */
	public static boolean acheter(String nom, int qte) throws SQLException {
		I_Produit produit = new Produit(nom , 0 , qte);
		try {
			ControleurFacade.getProduitDao().update(produit);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return ControleurFacade.getCatalogue().acheterStock(nom, qte);
	}
	
	/**
	 * 
	 * @param nom
	 * @param qte
	 * @return
	 * @throws SQLException
	 */
	public static boolean vendre(String nom, int qte) throws SQLException {
		I_Produit produit = new Produit(nom, 0, qte);
		try {
			ControleurFacade.getProduitDao().update(produit);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return ControleurFacade.getCatalogue().vendreStock(nom, qte);
	}
}

