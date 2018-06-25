import java.sql.Date;
import java.util.List;

/**
 * Classe Carte
 * 
 * @author Lachelah/Tane/Abeke
 * @version 1.0
 * */
public class Personne {

	private int id;
	private String nom;
	private String prenom;
	private Date dateNaissance = new Date(0);
	private int fonction;
	
	/**
	 * Constructeur
	 * @param nom
	 * @param prenom
	 * @param jour
	 * @param mois
	 * @param annee
	 * @param fonction
	 */
	public Personne(String nom, String prenom, int jour, int mois, int annee, int fonction) {
		
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance.setDate(jour);
		this.dateNaissance.setMonth(mois);
		this.dateNaissance.setYear(annee);
		this.fonction = fonction;
	}
	
	/**
	 * Constructeur
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param jour
	 * @param mois
	 * @param annee
	 * @param fonction
	 */
public Personne(int id, String nom, String prenom, int jour, int mois, int annee, int fonction) {
		
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance.setDate(jour);
		this.dateNaissance.setMonth(mois);
		this.dateNaissance.setYear(annee);
		this.fonction = fonction;
	}

/**
 * Getter d'id
 * @return id
 */
	public int getID() {
		return id;
	}
	
	/**
	 * Getter de nom
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Getter de prenom
	 * @return prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	
	/**
	 * Getter de fonction
	 * @return fonction
	 */
	public int getFonction() {
		return fonction;
	}
	
	/**
	 * Getter de date de naissance
	 * @return dateNaissance
	 */
	public Date getDateNaissance()
	{
		return dateNaissance;
	}
	
	/**
	 * Getter de chaine date de naissance
	 * @return dateNaissance.toString()
	 */
	public String getDateNaissanceString()
	{
		return dateNaissance.toString();
	}
	
	
	/**
	 * Getter de date de naissance
	 * @return java.sql.Date(dateNaissance.getTime())
	 */
	private static java.sql.Date getNaissance() {
	    java.util.Date dateNaissance = new java.util.Date();
	    return new java.sql.Date(dateNaissance.getTime());
	}

}
