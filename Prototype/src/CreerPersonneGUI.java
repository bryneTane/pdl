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
import java.sql.Date;
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

import java.util.List;
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

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.BoxLayout;
import javax.swing.Box;

import java.util.List;

public class CreerPersonneGUI extends JFrame implements ActionListener{

	private JLabel nom;
	private JLabel prenom;
	private JLabel dateNaissance;
	private JLabel fonction;
	
	private JTextField nomField;
	private JTextField prenomField;
	private JComboBox jourField;
	private JComboBox moisField;
	private JComboBox anneeField;
	private JTextField fonctionField;
	
	private JButton valider;
	
	private JPanel container;
	
	private PersonneDAO persdao;
	
	public CreerPersonneGUI() {
		
		this.persdao = new PersonneDAO();
		
		this.setSize(450, 450);
		this.setTitle("Créer une Personne");
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
		jourField = new JComboBox<Integer>();
		moisField = new JComboBox<Integer>();
		anneeField = new JComboBox<Integer>();
		fonctionField = new JTextField();
		valider = new JButton("Enregistrer");
		
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
		container.add(valider);
		
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
		valider.setBounds(120, 300, 200, 50);
		
		valider.addActionListener(this);
		
		this.setContentPane(container);
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		int retour;
		
		
		try {
			if (ae.getSource() == valider) {
				//new AjouterCarte ();
				
				
				Personne p = new Personne(
						this.nomField.getText(),
						this.prenomField.getText(),
						Integer.parseInt(this.jourField.getSelectedItem().toString()),
						Integer.parseInt(this.moisField.getSelectedItem().toString())-1,
						Integer.parseInt(this.anneeField.getSelectedItem().toString()),
						this.fonctionField.getText()
						);
				retour = persdao.ajouter(p);
				JOptionPane.showMessageDialog(this, "Personne ajoutée avec succes", "INFO", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Veuillez contrôler vos saisies", "Erreur", JOptionPane.ERROR_MESSAGE);
			System.err.println("Veuillez contrôler vos saisies");
		}
		
	}


}
