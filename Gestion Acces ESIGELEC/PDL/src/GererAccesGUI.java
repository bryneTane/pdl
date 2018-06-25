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
 * Fenetre menu de gestion d'acces
 * 
 * @author Lachelah/Tane/Abeke
 * @version 1.0
 * */
public class GererAccesGUI extends JFrame implements ActionListener{

	private JPanel container;
	private JButton gererPersonnes;
	private JButton gererLieux;
	/**
	 * Constructeur
	 */
	public GererAccesGUI() {
		
		this.setSize(350, 300);
		this.setTitle("Gérer Accès");
		this.setLocationRelativeTo(null);
		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		gererPersonnes = new JButton("    Créer Type Accès    ");
		gererPersonnes.addActionListener(this);
		gererLieux = new JButton("      Gérer les Accès     ");
		gererLieux.addActionListener(this);
		gererPersonnes.setSize(500, 100);
		gererLieux.setSize(500, 100);
		container.add(Box.createRigidArea(new Dimension(100, 50)));
		container.add(gererPersonnes);
		container.add(Box.createRigidArea(new Dimension(100, 50)));
		container.add(gererLieux);
		container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
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
			if (ae.getSource() == gererPersonnes) {
				new AjouterTypeAcces();
			}
			if (ae.getSource() == gererLieux) {
				new ListerAccesGUI(1, null);
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(this, "Veuillez contrôler vos saisies", "Erreur", JOptionPane.ERROR_MESSAGE);
			System.err.println("Veuillez contrôler vos saisies");
		}
	}

}