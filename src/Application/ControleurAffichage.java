package Application;

import Presentation.FenetreAffichage;

public class ControleurAffichage {
	
	private static FenetreAffichage fenetreAffichage;

	public ControleurAffichage() {}
	
	public static void demanderAffichage() {
		ControleurAffichage.fenetreAffichage = new FenetreAffichage(ControleurFacade.getCatalogue().toString());
	}
}
