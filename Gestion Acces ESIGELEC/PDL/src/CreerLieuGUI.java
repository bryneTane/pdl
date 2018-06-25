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
 * Fenetre de creation de Lieu
 * 
 * @author Lachelah/Tane/Abeke
 * @version 1.0
 * */
public class CreerLieuGUI extends JFrame implements ActionListener{

	//Attributs
	private JLabel emplacement;
	private JLabel nbAcces;
	private JLabel houv;
	private JLabel hferm;
	private JLabel fonction;
	
	private JTextField emplacementField;
	private JComboBox nbAccesField;
	private JComboBox houvField;
	private JComboBox hfermField;
	
	private JButton valider;
	private JButton ajoutHoraire;
	
	private JPanel container;
	
	private ArrayList<Horaire> horaires;
	private LieuDAO ldao;
	private AccesDAO adao;
	
	private String hours = "";
	
	/**
	 * Constructeur
	 * @throws SQLException
	 */
	public CreerLieuGUI() throws SQLException {
		
		adao = new AccesDAO();
		ldao = new LieuDAO();
		horaires = new ArrayList<Horaire>();
		this.setSize(450, 450);
		this.setTitle("Créer un Lieu");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		container = new JPanel();
		
		container.setLayout(null);
		
		emplacement = new JLabel("Emplacement : ");
		nbAcces = new JLabel("Nombre d'accès : ");
		houv = new JLabel("	Ouverture : ");
		hferm = new JLabel("Fermeture : ");
		
		emplacementField = new JTextField();
		nbAccesField = new JComboBox();
		houvField = new JComboBox();
		hfermField = new JComboBox();
		
		valider = new JButton("Enregistrer");
		ajoutHoraire = new JButton("Ajouter Horaire");
		
		for(int i = 0; i < 6; i++) {
			nbAccesField.addItem(i);
		}
		for (int i=7; i < 19; i++) {
			if (i <= 9) {
				houvField.addItem("0" + i + "h00");
				houvField.addItem("0" + i + "h15");
				houvField.addItem("0" + i + "h30");
				houvField.addItem("0" + i + "h45");
			}
			else {
				houvField.addItem(i + "h00");
				houvField.addItem(i + "h15");
				houvField.addItem(i + "h30");
				houvField.addItem(i + "h45");
			}
		}
		
		remplissage(houvField, hfermField);
		
		
		container.add(emplacement);
		container.add(emplacementField);
		
		container.add(nbAcces);
		container.add(nbAccesField);
		
		container.add(houv);
		container.add(hferm);
		container.add(houvField);
		container.add(hfermField);
		
		container.add(valider);
		container.add(ajoutHoraire);
		
		emplacement.setBounds(30, 0, 200, 100);
		emplacementField.setBounds(140, 35, 270, 30);
		nbAcces.setBounds(30, 60, 200, 100);
		nbAccesField.setBounds(160, 95, 250, 30);
		houv.setBounds(30, 125, 200, 100);
		houvField.setBounds(140, 160, 90, 30);
		hferm.setBounds(240, 125, 200, 100);
		hfermField.setBounds(340, 160, 90, 30);
		valider.setBounds(120, 300, 200, 50);
		ajoutHoraire.setBounds(140, 220, 160, 30);
		
		valider.addActionListener(this);
		ajoutHoraire.addActionListener(this);
		houvField.addActionListener(this);
		
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
		Lieu l = new Lieu(emplacementField.getText());
		try {
			
			if (ae.getSource() == houvField) {
				
				hfermField.removeAllItems();
				remplissage(houvField, hfermField);
			}
			if (ae.getSource() == ajoutHoraire) {
				horaires.add(new Horaire(houvField.getSelectedItem().toString(), hfermField.getSelectedItem().toString()));
				hours = hours + horaires.get(horaires.size() - 1).stringify() + "\n";
				JOptionPane.showMessageDialog(this, hours);
				
			}
			if (ae.getSource() == valider) {
				int n = 0;
				ldao.ajouter(l);
				l = ldao.rechercherLieuMax();
				for (int i = 0; i < horaires.size(); i++) {
					ldao.ajouterHoraire(horaires.get(i), l.getId());
				}
				while(n < Integer.parseInt(nbAccesField.getSelectedItem().toString())) {
					n++;
					new AjouterAcces(l);
					dispose();
				}
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Veuillez contôler vos saisies", "Erreur", JOptionPane.ERROR_MESSAGE);
			System.err.println(e.getLocalizedMessage());
		}
		
	}
	
	/**
	 * Fonction remplissant les listes d'heures
	 * @param box1
	 * @param box2
	 */
	public void remplissage (JComboBox box1, JComboBox box2) {
		int h = Integer.parseInt(box1.getSelectedItem().toString().substring(0, 2));
		int m = Integer.parseInt(box1.getSelectedItem().toString().substring(3, 5));
		while (m != 45) {
			m += 15;
			if (h < 10) {
				box2.addItem("0" + h + "h" + m);
			}
			else {
				box2.addItem(h + "h" + m);
			}
		}
		for (int i = Integer.parseInt(box1.getSelectedItem().toString().substring(0, 2)) + 1; i < 20; i++) {
			if (i < 10) {
				box2.addItem("0" + i + "h00");
				box2.addItem("0" + i + "h15");
				box2.addItem("0" + i + "h30");
				box2.addItem("0" + i + "h45");
			}
			else {
				box2.addItem(i + "h00");
				box2.addItem(i + "h15");
				box2.addItem(i + "h30");
				box2.addItem(i + "h45");
			}
		}
	}

}
