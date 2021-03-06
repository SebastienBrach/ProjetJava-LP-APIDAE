//package Application;
//
//import Metier.Catalogue;
//import Metier.I_Catalogue;
//import Presentation.FenetreNouveauProduit;
//import Presentation.FenetreSuppressionProduit;
//
//public class ControleurCreationSuppression {
//	
//	private I_Catalogue catalogue;
//
//	public ControleurCreationSuppression() {
//		this.catalogue = new Catalogue();
//	}
//	
//	public void demanderSuppression() {
//		String[] nomProduit = catalogue.getNomProduits();		
//		new FenetreSuppressionProduit(nomProduit);
//	}
//	
//	public String supprimerProduit(String nomProduit) {
//		if(catalogue.removeProduit(nomProduit)) {
//			System.out.println("Suppression de "+nomProduit);
//			return "Suppression de "+nomProduit;
//		} else {
//			System.out.println("Erreur de la suppression");
//			return "Erreur Suppression";
//		}
////		new FenetreMessage("Suppression");
//	}
//	
//	public void demanderAjout() {
//		new FenetreNouveauProduit();
//	}
//	
//	public String ajouterProduit(String nomProduit, double prix, int qte) {
//		if(catalogue.addProduit(nomProduit, prix, qte)) {
//			System.out.println("Ajout de "+nomProduit+" a "+prix+"€"+" avec une quantité de "+qte);
//			return "Ajout de "+nomProduit+" a "+prix+"€"+" avec une quantité de "+qte;
//		} else {
//			System.out.println("Erreur d'ajout");
//			return "Erreur Ajout";
//		}
////		new FenetreMessage("Ajout");
//	}
//	
// 
//}



package Application;

import java.sql.SQLException;

import DAL.DAOException;
import Metier.I_Catalogue;
import Metier.I_Produit;
import Metier.Produit;
import Presentation.FenetreAchat;
import Presentation.FenetreAffichage;
import Presentation.FenetreNouveauProduit;
import Presentation.FenetrePrincipale;
import Presentation.FenetreSuppressionProduit;
import Presentation.FenetreVente;

public class ControleurCreationSuppression {
	
	private static FenetreNouveauProduit fenetreNouveauProduit;
	private static FenetreSuppressionProduit fenetreSuppressionProduit;
	
	public ControleurCreationSuppression() {
	}
	
	/**
	 * 
	 */
	public static void demanderAjout() {
		ControleurCreationSuppression.fenetreNouveauProduit = new FenetreNouveauProduit();
	}
	
	/**
	 * 
	 */
	public static void demanderSuppression() {
		ControleurCreationSuppression.fenetreSuppressionProduit = new FenetreSuppressionProduit(ControleurFacade.getCatalogue().getNomProduits());
	}
	
	/**
	 * 
	 * @param nomProduit
	 * @return
	 * @throws SQLException
	 */
	public static boolean supprimerProduit(String nomProduit) throws SQLException {
		I_Produit produit = new Produit(nomProduit,0,0);
		try {
			ControleurFacade.getProduitDao().create(produit);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return ControleurFacade.getCatalogue().addProduit(produit);
	}

	/**
	 * 
	 * @param nomProduit
	 * @param prix
	 * @param qte
	 * @return
	 * @throws SQLException
	 */
	public static boolean ajouterProduit(String nomProduit, double prix, int qte) throws SQLException {
		I_Produit unProduit=new Produit(nomProduit, prix, qte);
		try {
			ControleurFacade.getProduitDao().create(unProduit);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return ControleurFacade.getCatalogue().addProduit(unProduit);
	}
	
 
}
