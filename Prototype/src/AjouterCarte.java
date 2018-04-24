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

import java.util.List;

public class AjouterCarte extends JFrame implements ActionListener{

	private JPanel container;
	private JButton boutonOui;
	private JButton boutonNon;
	private JLabel message;
	public AjouterCarte() {
		
		this.setSize(300, 200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		container = new JPanel();
		boutonOui = new JButton ("Oui");
		boutonNon = new JButton ("Non");
		message = new JLabel("Voulez vous ajouter une carte ?");
		
		container.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;
		
		/* insets définir la marge entre les composant new Insets(margeSupérieure, margeGauche, margeInférieur, margeDroite) */
		gc.insets = new Insets(25, 15, 25, 15);
		
		/* ipady permet de savoir où on place le composant s'il n'occupe pas la totalité de l'espace disponnible */
		gc.ipady = gc.anchor = GridBagConstraints.CENTER;

		/* weightx définit le nombre de cases en abscisse */
		gc.weightx = 2;
		
		/* weightx définit le nombre de cases en ordonnée */
		gc.weighty = 2;
		
		/* pour dire qu'on ajoute un composant en position (i, j), on définit gridx=i et gridy=j */
		
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
		
		
		
		
		this.setContentPane(container);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}

}
