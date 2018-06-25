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

/**
 * Fenetre de modification de lieu
 * 
 * @author Lachelah/Tane/Abeke
 * @version 1.0
 * */
public class ModifierLieuGUI extends JFrame implements ActionListener{

	private JLabel emplacement;
	
	private JLabel fonction;
	
	private JTextField emplacementField;
	
	
	private JButton valider;
	private JButton modifierHoraire;
	private JButton ajouterAcces;
	private JButton supprimerAcces;
	
	private JPanel container;
	
	private ArrayList<Horaire> horaires;
	private Lieu l;
	private LieuDAO ldao;
	
	/**
	 * Constructeur
	 * @param l
	 * @throws SQLException
	 */
	public ModifierLieuGUI(Lieu l) throws SQLException {
		
		this.l = l;
		ldao = new LieuDAO();
		horaires = new ArrayList<Horaire>();
		this.setSize(450, 450);
		this.setTitle("Modifier un Lieu");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		container = new JPanel();
		
		container.setLayout(null);
		
		emplacement = new JLabel("Emplacement : ");
		
		
		emplacementField = new JTextField(l.getEmplacement());
		
		
		valider = new JButton("Enregistrer");
		modifierHoraire = new JButton("Horaires");
		ajouterAcces = new JButton("Ajouter Accès");
		supprimerAcces = new JButton("Gérer Accès");
		
		
		container.add(emplacement);
		container.add(emplacementField);
		
		
		container.add(valider);
		container.add(modifierHoraire);
		container.add(ajouterAcces);
		container.add(supprimerAcces);
		
		emplacement.setBounds(30, 0, 200, 100);
		emplacementField.setBounds(130, 35, 270, 30);
		valider.setBounds(120, 280, 200, 30);
		modifierHoraire.setBounds(10, 150, 120, 30);
		ajouterAcces.setBounds(160, 150, 120, 30);
		supprimerAcces.setBounds(310, 150, 120, 30);
		
		valider.addActionListener(this);
		modifierHoraire.addActionListener(this);
		ajouterAcces.addActionListener(this);
		supprimerAcces.addActionListener(this);
		
		this.setContentPane(container);
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	/**
	 * fonction actionPerformed
	 * @param ae
	 * @Override
	 */
	public void actionPerformed(ActionEvent ae) {
		int retour;
		//Lieu l = new Lieu(Integer.parseInt(nbAccesField.getSelectedItem().toString()), emplacementField.getText());
		
		try {
			
			if(ae.getSource() == ajouterAcces) {
				
				new AjouterAcces(l);
			}
			
			if(ae.getSource() == supprimerAcces) {
				
				new ListerAccesGUI(0, l);
			}
			
			
			if (ae.getSource() == modifierHoraire) {
				
				new AjouterHoraires(l);
			}
			if (ae.getSource() == valider) {
				ldao.setEmplacement(emplacementField.getText(), l.getId());
				JOptionPane.showMessageDialog(this, "Lieu modifié avec succes", "INFO", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Veuillez contôler vos saisies", "Erreur", JOptionPane.ERROR_MESSAGE);
			System.err.println(e.getMessage());
		}
		
		
	}

}
