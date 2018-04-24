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
import javax.swing.JComboBox;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.BoxLayout;
import javax.swing.Box;

import java.util.ArrayList;
import java.util.List;

public class RechercherPersonneGUI extends JFrame implements ActionListener{

	private int rs;
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
	private JButton rechercher;
	private JComboBox resultats;
	
	private JPanel container;
	
	private PersonneDAO persdao;
	private List<Personne> list = new ArrayList<Personne>();
	
	public RechercherPersonneGUI(int rs) {
		
		persdao = new PersonneDAO();
		
		this.rs = rs;
		this.setSize(450, 450);
		this.setTitle("Rechercher une Personne");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
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
		fonctionField = new JTextField();
		valider = new JButton("Valider");
		rechercher = new JButton("Rechercher");
		resultats = new JComboBox();
		
		
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
		container.add(rechercher);
		container.add(resultats);
		
		nom.setBounds(30, 0, 200, 100);
		nomField.setBounds(100, 35, 300, 30);
		prenom.setBounds(30, 60, 200, 100);
		prenomField.setBounds(100, 95, 300, 30);
		dateNaissance.setBounds(30, 125, 200, 100);
		jourField.setBounds(170, 160, 50, 30);
		moisField.setBounds(245, 160, 50, 30);
		anneeField.setBounds(315, 160, 80, 30);
		fonction.setBounds(30, 195, 200, 100);
		fonctionField.setBounds(100, 230, 300, 30);
		rechercher.setBounds(295, 290, 110, 30);
		resultats.setBounds(30, 290, 250, 30);
		valider.setBounds(110, 350, 200, 50);
		
		valider.addActionListener(this);
		rechercher.addActionListener(this);
		
		this.setContentPane(container);
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		int retour;
		try {
			if(ae.getSource() == rechercher) {
				resultats.removeAllItems();
				list.removeAll(list);
				Personne p = new Personne(
						this.nomField.getText(),
						this.prenomField.getText(),
						Integer.parseInt(this.jourField.getSelectedItem().toString()),
						Integer.parseInt(this.moisField.getSelectedItem().toString()),
						Integer.parseInt(this.anneeField.getSelectedItem().toString()),
						this.fonctionField.getText()
						);
				list = persdao.rechercherPersonne(p);
				for (int i = 0; i < list.size(); i++) {
					resultats.addItem((i+1) + " - " + list.get(i).getNom() + " " + list.get(i).getPrenom());
				}
			}
			if (ae.getSource() == valider) {
				int j = (resultats.getSelectedIndex());
				if (rs == 1) {
					new ModifierPersonneGUI(list.get(j));
				}
				else {
					new SupprimerPersonneGUI(list.get(j).getID());
				}
				
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			System.err.println("Veuillez contrôler vos saisies");
		}
		
	}


}
