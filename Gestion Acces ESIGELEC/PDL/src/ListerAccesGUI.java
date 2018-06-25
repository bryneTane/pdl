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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
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
 * Fenetre menu de gestion de Acces
 * 
 * @author Lachelah/Tane/Abeke
 * @version 1.0
 * */
public class ListerAccesGUI extends JFrame implements ActionListener{

	private JPanel container;
	
	private JComboBox typesAccesField;
	private JComboBox emplacementsField;
	
	private JLabel emplacement;
	private JLabel typeAcces;
	
	private JList affichAcces;
	private DefaultListModel dlm;
	
	private JButton modifierAcces;
	private JButton supprimerAcces;
	private JButton cancel;
	private JButton refresh;
	
	private AccesDAO adao;
	private LieuDAO ldao;
	
	private List<Acces> listAcces;
	private List<Acces> listAccesFiltree;
	private List<TypeAcces> listTypes;
	private List<Lieu> listLieux;
	private Lieu l;
	
	private int n;
	
	/**
	 * Constructeur
	 * @param n
	 * @param l
	 * @throws SQLException
	 */
	public ListerAccesGUI(int n, Lieu l) throws SQLException {
		
		this.l = l;
		this.n = n;
		this.adao = new AccesDAO();
		this.ldao = new LieuDAO();
		
		this.setTitle("Liste des Accès");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		container = new JPanel();
		container.setLayout(null);
		if (n == 1) {
			emplacement = new JLabel("Emplacement : ");
		}
		typeAcces = new JLabel("Types d'accès : ");
		
		listTypes = new ArrayList<TypeAcces>();
		listAcces = new ArrayList<Acces>();
		listAccesFiltree = new ArrayList<Acces>();
		listLieux = new ArrayList<Lieu>();
		
		listTypes = adao.listeAcces();
		listLieux = ldao.rechercherLieux();
		listAcces = adao.rechercherAcces();
		
		typesAccesField = new JComboBox();
		for(int i = 0; i < listTypes.size(); i++) {
			typesAccesField.addItem(listTypes.get(i).getNom());
		}
		if (n == 1) {
			emplacementsField = new JComboBox();
			for(int i = 0; i < listLieux.size(); i++) {
				emplacementsField.addItem(listLieux.get(i).getEmplacement());
			}
		}
		
		int idl = 0;
		int idt = 0;
		
		dlm = new DefaultListModel();
		affichAcces = new JList(dlm);
		if (n == 1) {
			for(int i = 0; i < listAcces.size(); i++) {
				idl = listAcces.get(i).getLieu();
				idt = listAcces.get(i).getType();
				for (int j = 0; j < listLieux.size(); j++) {
					if(idl == listLieux.get(j).getId()) {
						for(int k = 0; k < listTypes.size(); k++) {
							if (idt == listTypes.get(k).getId()) {
								dlm.addElement("Type : " + listTypes.get(k).getNom() + "; Emplacement : " + listLieux.get(j).getEmplacement());
								listAccesFiltree.add(listAcces.get(i));
							}
						}
					}
				}
			}
		}else {
			for(int i = 0; i < listAcces.size(); i++) {
				idl = listAcces.get(i).getLieu();
				idt = listAcces.get(i).getType();
				for(int k = 0; k < listTypes.size(); k++) {
					if ((idt == listTypes.get(k).getId()) && (idl == l.getId())) {
						dlm.addElement("Type : " + listTypes.get(k).getNom() + "; Emplacement : " + l.getEmplacement());
						listAccesFiltree.add(listAcces.get(i));
					}
				}
			}
		}
		
		
		modifierAcces = new JButton("Modifier");
		supprimerAcces = new JButton("Supprimer");
		cancel = new JButton("Cancel");
		refresh = new JButton("Refresh");
		
		if(n == 1) {
			emplacement.setBounds(30, 30, 120, 20);
			container.add(emplacement);
			emplacementsField.setBounds(140, 30, 100, 20);
			container.add(emplacementsField);
		}
		typeAcces.setBounds(250, 30, 120, 20);
		container.add(typeAcces);
		typesAccesField.setBounds(370, 30, 100, 20);
		container.add(typesAccesField);
		refresh.setBounds(200, 65, 100, 20);
		container.add(refresh);
		affichAcces.setBounds(30, 130, 330, 200);
		container.add(affichAcces);
		modifierAcces.setBounds(30, 360, 100, 20);
		container.add(modifierAcces);
		supprimerAcces.setBounds(160, 360, 130, 20);
		container.add(supprimerAcces);
		cancel.setBounds(320, 360, 100, 20);
		container.add(cancel);
		
		
		refresh.addActionListener(this);
		modifierAcces.addActionListener(this);
		supprimerAcces.addActionListener(this);
		cancel.addActionListener(this);
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
			if (ae.getSource() == refresh) {
				affich();
			}
			if(ae.getSource() == modifierAcces) {
				if (affichAcces.getSelectedIndices().length == 1) {
					new ModifierAccesGUI(listAccesFiltree.get(affichAcces.getSelectedIndex()));
				}else {
					JOptionPane.showMessageDialog(this, "Sélectionnez un et un seul accès à modifier", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
			if(ae.getSource() == supprimerAcces) {
				int[] tab = affichAcces.getSelectedIndices();
				int j = 0;
				if (tab.length >= 1) {
					while(j < tab.length) {
						adao.supprimerAcces(listAccesFiltree.get(tab[j]).getId());
						ldao.setNbAcces(listAccesFiltree.get(tab[j]).getLieu(), adao.CompterAcces(listAccesFiltree.get(tab[j]).getLieu()));
						j++;
					}
					JOptionPane.showMessageDialog(this, "Accès supprimé avec succès", "INFO", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}else {
					JOptionPane.showMessageDialog(this, "Sélectionnez au moins un accès à supprimer", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			if(ae.getSource() == cancel) {
				JOptionPane.showMessageDialog(this, "Modification d'accès terminée", "INFO", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Veuillez contôler vos saisies", "Erreur", JOptionPane.ERROR_MESSAGE);
			System.err.println(e.getMessage());
		}
		
	}
	
	/**
	 * Fonction Remplissant la liste d'acces filtrée en fonction des criteres specifiés
	 * @throws SQLException
	 */
	public void affich() throws SQLException {
		
		int idl = 0, idt = 0;
		if (n == 1) {
			listAcces = adao.rechercherAcces();
			listAccesFiltree.removeAll(listAccesFiltree);
			idl = listLieux.get(emplacementsField.getSelectedIndex()).getId();
			idt = listTypes.get(typesAccesField.getSelectedIndex()).getId();
			dlm.removeAllElements();
			for(int i = 0; i < listAcces.size(); i++) {
				if ((idt == listAcces.get(i).getType()) && (idl == listAcces.get(i).getLieu())) {
					dlm.addElement("Type : " + listTypes.get(typesAccesField.getSelectedIndex()).getNom() + "; Emplacement : " + listLieux.get(emplacementsField.getSelectedIndex()).getEmplacement());
					listAccesFiltree.add(listAcces.get(i));
				}
			}
		}else {
			listAcces = adao.rechercherAcces();
			listAccesFiltree.removeAll(listAccesFiltree);
			idt = listTypes.get(typesAccesField.getSelectedIndex()).getId();
			dlm.removeAllElements();
			for(int i = 0; i < listAcces.size(); i++) {
				idl = listAcces.get(i).getLieu();
				if ((idt == listAcces.get(i).getType()) && (idl == l.getId())) {
					dlm.addElement("Type : " + listTypes.get(typesAccesField.getSelectedIndex()).getNom() + "; Emplacement : " + l.getEmplacement());
					listAccesFiltree.add(listAcces.get(i));
				}
			}
		}
		
	}

}
