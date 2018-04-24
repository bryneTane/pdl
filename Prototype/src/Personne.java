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

import java.sql.Date;
import java.util.List;

public class Personne {

	private int id;
	private String nom;
	private String prenom;
	private Date dateNaissance = new Date(0);
	private String fonction;
	
	public Personne(String nom, String prenom, int jour, int mois, int annee, String fonction) {
		
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance.setDate(jour);
		this.dateNaissance.setMonth(mois);
		this.dateNaissance.setYear(annee);
		this.fonction = fonction;
	}
	
public Personne(int id, String nom, String prenom, int jour, int mois, int annee, String fonction) {
		
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance.setDate(jour);
		this.dateNaissance.setMonth(mois);
		this.dateNaissance.setYear(annee);
		this.fonction = fonction;
	}

	public int getID() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public String getFonction() {
		return fonction;
	}
	
	public Date getDateNaissance()
	{
		return dateNaissance;
	}
	
	public String getDateNaissanceString()
	{
		return dateNaissance.toString();
	}
	
	
	/** https://www.mkyong.com/jdbc/how-to-insert-date-value-in-preparedstatement/ **/
	
	private static java.sql.Date getNaissance() {
	    java.util.Date dateNaissance = new java.util.Date();
	    return new java.sql.Date(dateNaissance.getTime());
	}

}
