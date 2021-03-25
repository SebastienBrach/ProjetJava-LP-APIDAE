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

public class ControleurProduit {
	

	private static FenetreNouveauProduit fenetreNouveauProduit;
	private static FenetreSuppressionProduit fenetreSuppressionProduit;

	public ControleurProduit() {
	}
	
	public static void afficherFenetreNouveauProduit()
	{
	 	ControleurProduit.fenetreNouveauProduit=new FenetreNouveauProduit();
	}
	
	public static void afficherFenetreSuppressionProduit()
	{
		ControleurProduit.fenetreSuppressionProduit=new FenetreSuppressionProduit(ControleurFacade.getCatalogue().getNomProduits());
	}
	
	public static boolean  addProduit(String nom, double prix, int qte) throws SQLException
	{
		I_Produit unProduit=new Produit(nom, prix, qte);
		try {
			ControleurFacade.getProduitDao().create(unProduit, ControleurFacade.getCatalogue().getId());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ControleurFacade.getCatalogue().addProduit(unProduit);
	}
	
	public static boolean removeProduit(String nom) throws SQLException
	{
		I_Produit unProduit=new Produit(nom, 0, 0);
		try {
			ControleurFacade.getProduitDao().delete(unProduit);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ControleurFacade.getCatalogue().removeProduit(nom);
	}
}
