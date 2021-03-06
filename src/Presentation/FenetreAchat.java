package Presentation;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import Application.ControleurAchatVente;

public class FenetreAchat extends JFrame implements ActionListener {

	private JButton btAchat;
	private JTextField txtQuantite;
	private JComboBox<String> combo;

	public FenetreAchat(String[] lesProduits) {

		setTitle("Achat");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btAchat = new JButton("Achat");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantité achetée"));
		contentPane.add(txtQuantite);
		contentPane.add(btAchat);

		btAchat.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btAchat) {
			String nomProd=(String)combo.getSelectedItem();
			int quantite=Integer.parseInt(txtQuantite.getText());
			try {
				ControleurAchatVente.acheter(nomProd, quantite);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		this.dispose();
	}

}
