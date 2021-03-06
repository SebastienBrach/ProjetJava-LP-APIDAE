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

	public static void demanderAjout() {
		ControleurCreationSuppression.fenetreNouveauProduit = new FenetreNouveauProduit();
	}
	
	public static void demanderSuppression() {
		ControleurCreationSuppression.fenetreSuppressionProduit = new FenetreSuppressionProduit(ControleurFacade.getCatalogue().getNomProduits());
	}
	
	public static boolean supprimerProduit(String nomProduit) throws SQLException {
		I_Produit produit = new Produit(nomProduit,0,0);
		try {
			ControleurFacade.getProduitDao().create(produit);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return ControleurFacade.getCatalogue().addProduit(produit);
	}

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
