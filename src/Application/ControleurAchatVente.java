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

public class ControleurAchatVente {
	
	private static FenetreAchat fenetreAchat;
	private static FenetreVente fenetreVente;
	
	public ControleurAchatVente() {
	}
	
	public static void demanderAchat() {
		ControleurAchatVente.fenetreAchat = new FenetreAchat(ControleurFacade.getCatalogue().getNomProduits());		
	}
	
	public static void demanderVente() {
		ControleurAchatVente.fenetreVente = new FenetreVente(ControleurFacade.getCatalogue().getNomProduits());
	}
	
	public static boolean acheter(String nom, int qte) throws SQLException {
		I_Produit produit = new Produit(nom , 0 , qte);
		try {
			ControleurFacade.getProduitDao().update(produit);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return ControleurFacade.getCatalogue().acheterStock(nom, qte);
	}

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

