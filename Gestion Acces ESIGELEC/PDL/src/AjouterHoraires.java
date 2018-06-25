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
 * Fenetre d'ajout de fonction
 * 
 * @author Lachelah/Tane/Abeke
 * @version 1.0
 * */
public class AjouterHoraires extends JFrame implements ActionListener{

	private JPanel container;
	private JButton boutonOui;
	private JButton boutonNon;
	private JButton boutonValider;
	private JLabel message;
	private JComboBox houvField;
	private JComboBox hfermField;
	private Lieu l;
	
	private HoraireDAO hdao;
	private List<Horaire> horaires;
	private String hours = "";
 	
	/**
	 * Constructeur
	 * @param l
	 * @throws SQLException
	 */
	public AjouterHoraires(Lieu l) throws SQLException {
		
		horaires = new ArrayList<Horaire>();
		hdao = new HoraireDAO();
		this.l = l;
		this.setSize(400, 300);
		this.setTitle("Ajouter des Créneaux horaires");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		container = new JPanel();
		boutonOui = new JButton ("Ajouter");
		boutonNon = new JButton ("Cancel");
		boutonValider = new JButton ("Valider");
		message = new JLabel("Sélectionnez l'heure de début et l'heure de fin : ");
		
		houvField = new JComboBox();
		hfermField = new JComboBox();
		
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
		
		container.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;
		
		/* insets d�finir la marge entre les composant new Insets(margeSup�rieure, margeGauche, margeInf�rieur, margeDroite) */
		gc.insets = new Insets(25, 15, 25, 15);
		
		/* ipady permet de savoir o� on place le composant s'il n'occupe pas la totalit� de l'espace disponnible */
		gc.ipady = gc.anchor = GridBagConstraints.CENTER;

		/* weightx d�finit le nombre de cases en abscisse */
		gc.weightx = 3;
		
		/* weightx d�finit le nombre de cases en ordonn�e */
		gc.weighty = 3;
		
		/* pour dire qu'on ajoute un composant en position (i, j), on d�finit gridx=i et gridy=j */
		
		gc.gridwidth = 3;
		gc.gridx = 0;
		gc.gridy = 0;
		container.add(message, gc);
		gc.gridwidth = 1;
		gc.gridx = 1;
		gc.gridy = 1;
		container.add(houvField, gc);
		gc.gridwidth = 1;
		gc.gridx = 2;
		gc.gridy = 1;
		container.add(hfermField, gc);
		gc.gridwidth = 1;
		gc.gridx = 0;
		gc.gridy = 2;
		container.add(boutonOui, gc);
		gc.gridx = 2;
		gc.gridy = 2;
		container.add(boutonNon, gc);
		gc.gridx = 1;
		gc.gridy = 2;
		container.add(boutonValider, gc);
		
		
		
		boutonOui.addActionListener(this);
		boutonNon.addActionListener(this);
		boutonValider.addActionListener(this);
		houvField.addActionListener(this);
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
				
				if(ae.getSource() == houvField) {
					hfermField.removeAllItems();
					remplissage(houvField, hfermField);
				}
				if (ae.getSource() == boutonOui) {
					
					horaires.add(new Horaire(houvField.getSelectedItem().toString(), hfermField.getSelectedItem().toString()));
					hours = hours + horaires.get(horaires.size() - 1).stringify() + "\n";
					JOptionPane.showMessageDialog(this, hours);
				}
				if (ae.getSource() == boutonValider) {
					hdao.supprimerHoraires(l.getId());
					for (int i = 0; i < horaires.size(); i++) {
						hdao.ajouterHoraire(horaires.get(i), l.getId());
					}
					JOptionPane.showMessageDialog(this, "Horaires ajoutés avec succes", "INFO", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
				if (ae.getSource() == boutonNon) {
					JOptionPane.showMessageDialog(this, "Horaires non ajoutés", "INFO", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
		
		}catch(Exception e) {
				JOptionPane.showMessageDialog(this, "Veuillez contrôler vos saisies", "Erreur", JOptionPane.ERROR_MESSAGE);
				System.err.println(e.getMessage());
			}
		
	}
	/**
	 * Fonction remplissant les listes déroulantes d'horaires
	 * @param box1
	 * @param box2
	 */
	public void remplissage (JComboBox box1, JComboBox box2) {
		//System.out.println(box1);
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
