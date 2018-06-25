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
 * Fenetre menu de gestion de Lieux
 * 
 * @author Lachelah/Tane/Abeke
 * @version 1.0
 * */
public class GererLieuGUI extends JFrame implements ActionListener {

	private JPanel container;
	private JButton creerLieu;
	private JButton modifierLieu;
	private JButton ajouterTypeAcces;
	/**
	 * Constructeur
	 */
	public GererLieuGUI() {
		
		this.setSize(350, 350);
		this.setTitle("Gérer les Lieux");
		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		creerLieu = new JButton("     Créer un lieu       ");
		creerLieu.addActionListener(this);
		modifierLieu = new JButton("      Modifier lieu       ");
		modifierLieu.addActionListener(this);
		ajouterTypeAcces = new JButton("      Gérer Accès       ");
		ajouterTypeAcces.addActionListener(this);

		container.add(Box.createRigidArea(new Dimension(100, 50)));
		container.add(creerLieu);
		container.add(Box.createRigidArea(new Dimension(100, 50)));
		container.add(modifierLieu);
		container.add(Box.createRigidArea(new Dimension(100, 50)));
		container.add(ajouterTypeAcces);
		container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(container);
		this.setVisible(true);
	}
	
	@Override
	/**
	 * fonction actionPerformed
	 * @param ae
	 * @Override
	 */
	public void actionPerformed(ActionEvent ae) {
		try {
			if (ae.getSource() == creerLieu) {
				new CreerLieuGUI();
			}
			if (ae.getSource() == modifierLieu) {
				new RechercherLieuxGUI();
			}
			if (ae.getSource() == ajouterTypeAcces) {
				new GererAccesGUI();
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(this, "Veuillez contrôler vos saisies", "Erreur", JOptionPane.ERROR_MESSAGE);
			System.err.println("Veuillez contrôler vos saisies");
		}

	}
}
