package Presentation;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import Application.ControleurCatalogue;
import Metier.I_Catalogue;

public class FenetreAccueil extends JFrame implements ActionListener {

	private JButton btAjouter, btSupprimer, btSelectionner;
	private JTextField txtAjouter;
	private JLabel lbNbCatalogues;
	private JComboBox cmbSupprimer, cmbSelectionner;
	private TextArea taDetailCatalogues;

	public FenetreAccueil() {
		setTitle("Catalogues");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		JPanel panInfosCatalogues = new JPanel();
		JPanel panNbCatalogues = new JPanel();
		JPanel panDetailCatalogues = new JPanel();
		JPanel panGestionCatalogues = new JPanel();
		JPanel panAjouter = new JPanel();
		JPanel panSupprimer = new JPanel();
		JPanel panSelectionner = new JPanel();
		panNbCatalogues.setBackground(Color.white);
		panDetailCatalogues.setBackground(Color.white);
		panAjouter.setBackground(Color.gray);
		panSupprimer.setBackground(Color.lightGray);
		panSelectionner.setBackground(Color.gray);
		
		panNbCatalogues.add(new JLabel("Nous avons actuellement : "));
		lbNbCatalogues = new JLabel();
		panNbCatalogues.add(lbNbCatalogues);
		
		taDetailCatalogues = new TextArea();
		taDetailCatalogues.setEditable(false);
		new JScrollPane(taDetailCatalogues);
		taDetailCatalogues.setPreferredSize(new Dimension(300, 100));
		panDetailCatalogues.add(taDetailCatalogues);

		panAjouter.add(new JLabel("Ajouter un catalogue : "));
		txtAjouter = new JTextField(10);
		panAjouter.add(txtAjouter);
		btAjouter = new JButton("Ajouter");
		panAjouter.add(btAjouter);

		panSupprimer.add(new JLabel("Supprimer un catalogue : "));
		cmbSupprimer = new JComboBox();
		cmbSupprimer.setPreferredSize(new Dimension(100, 20));
		panSupprimer.add(cmbSupprimer);
		btSupprimer = new JButton("Supprimer");
		panSupprimer.add(btSupprimer);

		panSelectionner.add(new JLabel("Selectionner un catalogue : "));
		cmbSelectionner = new JComboBox();
		cmbSelectionner.setPreferredSize(new Dimension(100, 20));
		panSelectionner.add(cmbSelectionner);
		btSelectionner = new JButton("Selectionner");
		panSelectionner.add(btSelectionner);
		
		panGestionCatalogues.setLayout (new BorderLayout());
		panGestionCatalogues.add(panAjouter, "North");
		panGestionCatalogues.add(panSupprimer);
		panGestionCatalogues.add(panSelectionner, "South");
		
		panInfosCatalogues.setLayout(new BorderLayout());
		panInfosCatalogues.add(panNbCatalogues, "North");
		panInfosCatalogues.add(panDetailCatalogues, "South");
				
		contentPane.add(panInfosCatalogues, "North");
		contentPane.add(panGestionCatalogues, "South");
		pack();

//		btAjouter.addActionListener(this);
//		btSupprimer.addActionListener(this);
//		btSelectionner.addActionListener(this);
//		
//		String[] tab  = {"Formacia" , "Le Redoutable", "Noitaicossa"}; 
//		modifierListesCatalogues(tab);
//		String[] tab2 = {"Formacia : 6 produits" , "Le Redoutable : 4 produits" , "Noitaicossa : 0 produits" };
//		modifierDetailCatalogues(tab2);
//		modifierNbCatalogues(3);
//		setVisible(true);
		

		List<I_Catalogue> catalogues = ControleurCatalogue.getCatalogues();
		List<String> catalogueNamesList = new ArrayList<>();
		List<String> catalogueDetailsList = new ArrayList<>();
		for (I_Catalogue catalogue : catalogues) {
			int nbProduits = catalogue.getProduits().size();
			catalogueNamesList.add(catalogue.getNom());
			catalogueDetailsList.add(catalogue.getNom() + " : " + nbProduits + " produits");
		}

		String[] catalogueNames = new String[catalogueNamesList.size()];
		catalogueNames = catalogueNamesList.toArray(catalogueNames);

		modifierListesCatalogues(catalogueNames);

		String[] catalogueDetails = new String[catalogueDetailsList.size()];
		catalogueDetails = catalogueDetailsList.toArray(catalogueDetails);

		modifierDetailCatalogues(catalogueDetails);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btAjouter) {
			String texteAjout = txtAjouter.getText();
			if (!texteAjout.equals("")) {
				System.out.println("ajouter le catalogue "+texteAjout);
				txtAjouter.setText(null);
			}
		}
		if (e.getSource() == btSupprimer) {
			String texteSupprime = (String)cmbSupprimer.getSelectedItem();
			if (texteSupprime != null) {
				System.out.println("supprime catalogue "+texteSupprime);
			}
		}
		if (e.getSource() == btSelectionner) {
			String texteSelection = (String)cmbSupprimer.getSelectedItem();
			if (texteSelection != null) {
				System.out.println("selectionne catalogue "+texteSelection);
				this.dispose();
			}
		}	
	}

	private void modifierListesCatalogues(String[] nomsCatalogues) {
		cmbSupprimer.removeAllItems();
		cmbSelectionner.removeAllItems();
		if (nomsCatalogues != null) {
			for (int i=0 ; i<nomsCatalogues.length; i++) {
				cmbSupprimer.addItem(nomsCatalogues[i]);
				cmbSelectionner.addItem(nomsCatalogues[i]);
			}
		}
	}
	
	private void modifierDetailCatalogues(String[] detailCatalogues) {
		taDetailCatalogues.setText("");
		if (detailCatalogues != null) {
			for (int i=0 ; i<detailCatalogues.length; i++) {
				taDetailCatalogues.append(detailCatalogues[i]+"\n");
			}
		}
	}
	
	public void majAffichage(String[] nomsCatalogues,String[] detailCatalogues) {
		this.modifierListesCatalogues(nomsCatalogues);
		this.modifierDetailCatalogues(detailCatalogues);
	}
	
	public static void main(String[] args) {
		new FenetreAccueil();
	}
}