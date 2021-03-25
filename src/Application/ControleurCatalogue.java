/**
 * 
 */
package Application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import DAL.I_CatalogueDAO;
import DAL.CatalogueDAOFactory;
import DAL.DAOException;
import Metier.Catalogue;
import Metier.I_Catalogue;
import Presentation.FenetreAccueil;


public class ControleurCatalogue {

	private static FenetreAccueil fenetreAccueil;
	private static List<I_Catalogue> lesCatalogues = new ArrayList<>();
	private static I_CatalogueDAO catalogueDAO = CatalogueDAOFactory.getFactory("Relationnel").getInstance();
	
	/**
	 * 
	 */
	public ControleurCatalogue() {
		try {
			lesCatalogues = catalogueDAO.read();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		ControleurCatalogue.fenetreAccueil=new FenetreAccueil();
		ControleurCatalogue.majAffichage();
	}
	
	private static void majAffichage()
	{
		String[] nomsCatalogues= new String[lesCatalogues.size()];
		String[] detailCatalogues= new String[lesCatalogues.size()];
		for (int i = 0; i < ControleurCatalogue.lesCatalogues.size(); i++) {
			I_Catalogue catalogue=ControleurCatalogue.lesCatalogues.get(i);
			String nomCatalogue=catalogue.getNom();
			Integer nbProduit=catalogue.getNomProduits().length;
			nomsCatalogues[i]=nomCatalogue;
			detailCatalogues[i]=nomCatalogue+" : "+nbProduit+" produits";
		}
		ControleurCatalogue.fenetreAccueil.majAffichage(nomsCatalogues,detailCatalogues);
	}
	
	public static void removeCatalogue(String nomCatalogue)
	{
		try {
			catalogueDAO.delete(nomCatalogue);
			lesCatalogues.removeIf(catalogue -> catalogue.getNom().equals(nomCatalogue));
		} catch (DAOException e) {
			e.printStackTrace();
		}
		ControleurCatalogue.majAffichage();
	}
	
	public static void addCatalogue(String nomCatalogue)
	{
		I_Catalogue newCatalogue = new Catalogue(nomCatalogue);
			try {
			catalogueDAO.create(newCatalogue);
			ControleurCatalogue.lesCatalogues.add(newCatalogue);
		} catch (DAOException e) {
			JOptionPane.showMessageDialog(fenetreAccueil,"Le catalogue existe dejà ");
			e.printStackTrace();
		}
		ControleurCatalogue.majAffichage();
	}
	
	public static void showMenuPrincipal(Integer index) throws SQLException
	{
		I_Catalogue unCatalogue=ControleurCatalogue.lesCatalogues.get(index);
		new ControleurFacade(unCatalogue);
	}

	public static List<I_Catalogue> getCatalogues()
	{
		try {
			return catalogueDAO.read();
		} catch (DAOException e) {
			e.printStackTrace();
		}

		return new ArrayList<I_Catalogue>();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ControleurCatalogue();
	}

}
