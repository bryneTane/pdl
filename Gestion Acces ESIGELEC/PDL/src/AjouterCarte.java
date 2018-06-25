import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
/**
 * Fenetre d'ajout de carte
 * 
 * @author Lachelah/Tane/Abeke
 * @version 1.0
 * */
public class AjouterCarte extends JFrame implements ActionListener{

	private JPanel container;
	private JButton boutonOui;
	private JButton boutonNon;
	private JLabel message;
	private CarteDAO cardao;
	private PersonneDAO persdao;
	private Personne p;
	
	/**
	 * Constructeur
	 * @param p
	 */
	public AjouterCarte(Personne p) {
		
		this.p = p;
		cardao = new CarteDAO();
		persdao = new PersonneDAO();
		this.setSize(300, 200);
		this.setTitle("Créer un Badge");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		container = new JPanel();
		boutonOui = new JButton ("Oui");
		boutonNon = new JButton ("Non");
		message = new JLabel("Voulez vous ajouter une carte ?");
		
		container.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;
		
		/* insets d�finir la marge entre les composant new Insets(margeSup�rieure, margeGauche, margeInf�rieur, margeDroite) */
		gc.insets = new Insets(25, 15, 25, 15);
		
		/* ipady permet de savoir o� on place le composant s'il n'occupe pas la totalit� de l'espace disponnible */
		gc.ipady = gc.anchor = GridBagConstraints.CENTER;

		/* weightx d�finit le nombre de cases en abscisse */
		gc.weightx = 2;
		
		/* weightx d�finit le nombre de cases en ordonn�e */
		gc.weighty = 2;
		
		/* pour dire qu'on ajoute un composant en position (i, j), on d�finit gridx=i et gridy=j */
		
		gc.gridwidth = 2;
		gc.gridx = 0;
		gc.gridy = 0;
		
		container.add(message, gc);
		gc.gridwidth = 1;
		gc.gridx = 0;
		gc.gridy = 1;
		container.add(boutonOui, gc);
		gc.gridx = 1;
		gc.gridy = 1;
		container.add(boutonNon, gc);
		
		
		
		boutonOui.addActionListener(this);
		boutonNon.addActionListener(this);
		this.setContentPane(container);
		this.setVisible(true);
	}

	/**
	 * fonction actionPerformed
	 * @param ae
	 * @Override
	 */
	public void actionPerformed(ActionEvent ae) {
		
		try {
				if (ae.getSource() == boutonOui) {
					
					List<Personne> list = new ArrayList<Personne>();
					list = persdao.rechercherPersonne(p);
					cardao.ajouter(list.get(list.size()-1).getID());
					JOptionPane.showMessageDialog(this, "Personne ajoutée avec succes", "INFO", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
				if (ae.getSource() == boutonNon) {
					JOptionPane.showMessageDialog(this, "Personne ajoutée avec succes", "INFO", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
		
		}catch(Exception e) {
				JOptionPane.showMessageDialog(this, "Veuillez contrôler vos saisies", "Erreur", JOptionPane.ERROR_MESSAGE);
				System.err.println("Veuillez contrôler vos saisies");
			}
		
	}

	
}
