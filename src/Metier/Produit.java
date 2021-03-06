package Metier;

import Metier.I_Produit;
import Metier.Produit;

public class Produit implements I_Produit {
	
	private int quantiteStock;
	private String nom;
	private double prixUnitaireHT;
	private static double tauxTVA; 
	
	public Produit(String nom, double prixUnitaireHT, int qte) {
		this.nom = nom;
		this.prixUnitaireHT = prixUnitaireHT;
		this.quantiteStock = qte;
		Produit.tauxTVA = 0.2;
	}
	
	@Override
	public boolean ajouter(int qteAchetee) {
		if(qteAchetee > 0) {
			this.quantiteStock += qteAchetee;
			return true;
		}
		return false;
	}

	@Override
	public boolean enlever(int qteVendue) {
		if(qteVendue > 0 && qteVendue < getQuantite()) {
			this.quantiteStock -= qteVendue;
			return true;
		} 
		return false;
	}

	@Override
	public String getNom() {
		return this.nom.replace("\u0009", " ");
	}

	@Override
	public int getQuantite() {
		return this.quantiteStock;
	}

	@Override
	public double getPrixUnitaireHT() {
		return this.prixUnitaireHT;
	}

	@Override
	public double getPrixUnitaireTTC() {
		return this.prixUnitaireHT + (this.prixUnitaireHT * Produit.tauxTVA);
	}

	@Override
	public double getPrixStockTTC() {
		double total = 0;
		for(int i = 0; i < this.quantiteStock; i++) {
			total += this.getPrixUnitaireTTC();
		}
		return total;
	}

	@Override
	public String toString() {
		return getNom() + " - prix HT : " + String.format("%.2f", getPrixUnitaireHT()) + " € - prix TTC : " + String.format("%.2f", getPrixUnitaireTTC()) + " € - quantité en stock : " + getQuantite();
	}
}
