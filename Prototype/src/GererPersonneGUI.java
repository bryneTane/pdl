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

public class GererPersonneGUI extends JFrame implements ActionListener {

	private JPanel container;
	private JButton creerPersonne;
	private JButton modifierPersonne;
	private JButton supprimerPersonne;
	
	public GererPersonneGUI() {
		
		this.setSize(350, 350);
		this.setTitle("Gérer les Personnes");
		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		creerPersonne = new JButton("Créer une personne");
		creerPersonne.addActionListener(this);
		modifierPersonne = new JButton("  Modifier personne  ");
		modifierPersonne.addActionListener(this);
		supprimerPersonne = new JButton("Supprimer personne");
		supprimerPersonne.addActionListener(this);

		container.add(Box.createRigidArea(new Dimension(100, 50)));
		container.add(creerPersonne);
		container.add(Box.createRigidArea(new Dimension(100, 50)));
		container.add(modifierPersonne);
		container.add(Box.createRigidArea(new Dimension(100, 50)));
		container.add(supprimerPersonne);
		container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(container);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		int retour;
		try {
			if (ae.getSource() == creerPersonne) {
				new CreerPersonneGUI();
			}
			if (ae.getSource() == modifierPersonne) {
				new RechercherPersonneGUI(1);
			}
			if (ae.getSource() == supprimerPersonne) {
				new RechercherPersonneGUI(0);
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(this, "Veuillez contrôler vos saisies", "Erreur", JOptionPane.ERROR_MESSAGE);
			System.err.println("Veuillez contrôler vos saisies");
		}

	}
}
