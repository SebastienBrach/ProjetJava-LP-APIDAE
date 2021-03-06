//package Application;
//
//import Metier.Catalogue;
//import Metier.Produit;
//
//public class ControleurAffichage {
//
//	public ControleurAffichage() {
//		
//	}
//	
//	public void demanderAffichage() {
//		
//	}
//}



package Application;

import Presentation.FenetreAffichage;

public class ControleurAffichage {
	
	private static FenetreAffichage fenetreAffichage;

	public ControleurAffichage() {}
	
	/**
	 * 
	 */
	public static void demanderAffichage() {
		ControleurAffichage.fenetreAffichage = new FenetreAffichage(ControleurFacade.getCatalogue().toString());
	}
}
