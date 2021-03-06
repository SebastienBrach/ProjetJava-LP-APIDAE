package Metier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Catalogue implements I_Catalogue {
	
	private ArrayList<I_Produit> lesProduits;
	
	public Catalogue() {
		this.lesProduits = new ArrayList<>();
	}

	@Override
	public boolean addProduit(I_Produit produit) {
		if(produit == null || produit.getNom() == null || produit.getPrixUnitaireHT() <= 0 || produit.getQuantite() < 0) {
			return false;
		} 
		for(I_Produit p : lesProduits) {
			if(p.getNom().equals(produit.getNom())) {
				return false;
			}
		}
		return lesProduits.add(produit);
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		I_Produit p = new Produit(nom, prix, qte);
		return this.addProduit(p);
	}
	

	@Override
	public int addProduits(List<I_Produit> l) {
		if(l == null) {
			return 0;
		}
		int n = 0;
		for(I_Produit p : l) {
			if(this.addProduit(p)) {
				n++;
			}
		}
		return n;
	}
	

	@Override
	public boolean removeProduit(String nom) {
		for(I_Produit p : lesProduits) {
			if(p.getNom().equals(nom)) {
				this.lesProduits.remove(p);
				return true;
			}
		}
		return false;
	}
	

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		if(qteAchetee <= 0) {
			return false;
		}
		for(I_Produit p : lesProduits) {
			if(p.getNom().equals(nomProduit)) {
				p.ajouter(qteAchetee);
				return true;
			}
		}
		return false;
	}
	

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		for(I_Produit p : lesProduits) {
			if(p.getNom().equals(nomProduit)) {
				if(p.getQuantite() != 0) {
					return p.enlever(qteVendue);
				}
			}
		}
		return false;
	}
	

	@Override
	public String[] getNomProduits() {
		String[] nomsProduits = new String[lesProduits.size()];
		for(int i=0; i<lesProduits.size(); i++) {
			nomsProduits[i] = lesProduits.get(i).getNom();
		}
		Arrays.sort(nomsProduits);
		return nomsProduits;
	}

	
	@Override
	public double getMontantTotalTTC() {
		double total = 0;
		for(I_Produit p : lesProduits) {
			total += p.getPrixStockTTC();
		}
		return (double) Math.round(total * 100) / 100;
	}
	

	@Override
	public void clear() {
		while(!lesProduits.isEmpty()) {
			lesProduits.remove(0);
		}
	}
	

	@Override
	public String toString() {
        String message = "";
        for (I_Produit produit : lesProduits) {
            message += produit.toString();
            message += "\n";
        }
        message += "\nMontant total TTC du stock : " + String.format("%.2f", getMontantTotalTTC()) + " ?";
        return message;
    }
	
	
}
