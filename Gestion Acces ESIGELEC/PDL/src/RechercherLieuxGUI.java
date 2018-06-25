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
 * Fenetre de recherche de lieux
 * 
 * @author Lachelah/Tane/Abeke
 * @version 1.0
 * */
public class RechercherLieuxGUI extends JFrame implements ActionListener{

	private JPanel container;
	private JButton boutonOui;
	private JButton boutonNon;
	private JLabel message;
	private JComboBox emplacements; 
	private LieuDAO ldao;
	List<Lieu> list = new ArrayList<Lieu>();
	
	/**
	 * Constructeur
	 * @throws SQLException
	 */
	public RechercherLieuxGUI() throws SQLException {
		
		this.ldao = new LieuDAO();
		this.setSize(400, 200);
		this.setTitle("Sélectionnez le lieu");
		this.setLocationRelativeTo(null);
		container = new JPanel();
		boutonOui = new JButton ("Modifier");
		boutonNon = new JButton ("Cancel");
		message = new JLabel("Lieux : ");
		emplacements = new JComboBox();
		
		
		list = ldao.rechercherLieux();
		for (int i = 0; i < list.size(); i++) {
			emplacements.addItem(list.get(i).getEmplacement());
		}
		
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
		gc.weighty = 2;
		
		/* pour dire qu'on ajoute un composant en position (i, j), on d�finit gridx=i et gridy=j */
		
		gc.gridwidth = 1;
		gc.gridx = 0;
		gc.gridy = 0;
		container.add(message, gc);
		gc.gridwidth = 2;
		gc.gridx = 1;
		gc.gridy = 0;
		container.add(emplacements, gc);
		gc.gridwidth = 1;
		gc.gridx = 1;
		gc.gridy = 1;
		container.add(boutonOui, gc);
		gc.gridx = 2;
		gc.gridy = 1;
		container.add(boutonNon, gc);
		
		
		
		boutonOui.addActionListener(this);
		boutonNon.addActionListener(this);
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
				if (ae.getSource() == boutonOui) {
					
					int j = (emplacements.getSelectedIndex());
					new ModifierLieuGUI(list.get(j));
					
					dispose();
				}
				if (ae.getSource() == boutonNon) {
					dispose();
				}
		
		}catch(Exception e) {
				JOptionPane.showMessageDialog(this, "Veuillez contrôler vos saisies", "Erreur", JOptionPane.ERROR_MESSAGE);
				System.err.println(e.getMessage());
			}
		
	}
	
	
}