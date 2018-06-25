import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Component;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.BoxLayout;
import javax.swing.Box;

import java.util.ArrayList;
import java.util.List;

/**
 * Fenetre de modification de personne
 * 
 * @author Lachelah/Tane/Abeke
 * @version 1.0
 * */
public class ModifierPersonneGUI extends JFrame implements ActionListener{

	private JLabel nom;
	private JLabel prenom;
	private JLabel dateNaissance;
	private JLabel fonction;
	
	private JTextField nomField;
	private JTextField prenomField;
	private JComboBox jourField;
	private JComboBox moisField;
	private JComboBox anneeField;
	private JComboBox fonctionField;
	
	private JButton valider;
	private JButton creerBadge;
	private JButton modifierBadge;
	private JButton delBadge;
	private JButton cancel;
	
	private JPanel container;
	
	private PersonneDAO persdao;
	
	private Personne p;
	
	private int id;
	
	/**
	 * Constructeur
	 * @param pers
	 * @throws SQLException
	 */
	public ModifierPersonneGUI(Personne pers) throws SQLException {
		
		p = pers;
		id = pers.getID();
		persdao = new PersonneDAO();
		List<String> listFonc = new ArrayList<String>();
		listFonc = persdao.listeFonctions();
		
		this.setSize(450, 480);
		this.setTitle("Modifier une Personne");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		
		container.setLayout(null);
		
		nom = new JLabel("Nom : ");
		prenom = new JLabel("Prenom : ");
		dateNaissance = new JLabel("Date de Naissance : ");
		fonction = new JLabel("Fonction : ");
		
		nomField = new JTextField();
		prenomField = new JTextField();
		jourField = new JComboBox();
		moisField = new JComboBox();
		anneeField = new JComboBox();
		fonctionField = new JComboBox();
		for (int i=0; i<listFonc.size(); i++) {
			fonctionField.addItem(listFonc.get(i));
		}
		valider = new JButton("Enregistrer");
		creerBadge = new JButton("Créer Badge");
		modifierBadge = new JButton("Modifier Badge");
		delBadge = new JButton("Supprimer Bagde");
		cancel = new JButton("Cancel");
		
		for (int i=1; i < 32; i++) {
			if (i < 13) {
				moisField.addItem(i);
			}
			jourField.addItem(i);
			anneeField.addItem(1989 + i);
		}
		
		container.add(nom);
		container.add(nomField);
		
		container.add(prenom);
		container.add(prenomField);
		
		container.add(dateNaissance);
		container.add(jourField);
		container.add(moisField);
		container.add(anneeField);
		
		container.add(fonction);
		container.add(fonctionField);
		container.add(creerBadge);
		container.add(modifierBadge);
		container.add(delBadge);
		container.add(valider);
		container.add(cancel);
		
		nom.setBounds(30, 0, 200, 100);
		nomField.setBounds(100, 35, 300, 30);
		prenom.setBounds(30, 60, 200, 100);
		prenomField.setBounds(100, 95, 300, 30);
		dateNaissance.setBounds(30, 125, 200, 100);
		jourField.setBounds(170, 160, 50, 30);
		moisField.setBounds(240, 160, 50, 30);
		anneeField.setBounds(310, 160, 80, 30);
		fonction.setBounds(30, 195, 200, 100);
		fonctionField.setBounds(100, 230, 300, 30);
		valider.setBounds(120, 380, 150, 50);
		creerBadge.setBounds(30, 300, 120, 50);
		modifierBadge.setBounds(160, 300, 120, 50);
		delBadge.setBounds(290, 300, 120, 50);
		cancel.setBounds(310, 380, 100, 50);
		
		valider.addActionListener(this);
		creerBadge.addActionListener(this);
		modifierBadge.addActionListener(this);
		delBadge.addActionListener(this);
		cancel.addActionListener(this);
		
		nomField.setText(pers.getNom());
		prenomField.setText(pers.getPrenom());
		fonctionField.setSelectedIndex(pers.getFonction() - 1);
		jourField.setSelectedItem(pers.getDateNaissance().getDate());
		moisField.setSelectedItem(pers.getDateNaissance().getMonth() + 1);
		anneeField.setSelectedItem(pers.getDateNaissance().getYear());
		
		
		this.setContentPane(container);
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void finalize() {
		System.out.println("Nettoyé");
	}

	@Override
	/**
	 * fonction actionPerformed
	 * @param ae
	 * @Override
	 */
	public void actionPerformed(ActionEvent ae) {
		try {
			if (ae.getSource() == valider) {
				Personne p = new Personne(
						this.id,
						this.nomField.getText(),
						this.prenomField.getText(),
						Integer.parseInt(this.jourField.getSelectedItem().toString()),
						Integer.parseInt(this.moisField.getSelectedItem().toString())-1,
						Integer.parseInt(this.anneeField.getSelectedItem().toString()),
						this.fonctionField.getSelectedIndex() + 1
						);
				persdao.modifierPersonne(p);
				JOptionPane.showMessageDialog(this, "Personne modifiée avec succes", "INFO", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
			if (ae.getSource() == creerBadge) {
				new AjouterCarte(p);
			}
			if (ae.getSource() == modifierBadge) {
				JOptionPane.showMessageDialog(this, "Pas encore fonctionnel", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			if (ae.getSource() == delBadge) {
				JOptionPane.showMessageDialog(this, "Pas encore fonctionnel", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			if (ae.getSource() == cancel) {
				this.dispose();
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Veuillez contrôler vos saisies", "Erreur", JOptionPane.ERROR_MESSAGE);
			System.err.println("Veuillez contrôler vos saisies");
		}
		
	}


}
