/**
 * Classe Horaire
 * 
 * @author Lachelah/Tane/Abeke
 * @version 1.0
 * */
public class Horaire {

	int idLieu;
	private String heureOuverture;
	private String heureFermeture;
	
	/**
	 * Constructeur
	 * @param heureOuverture
	 * @param heureFermeture
	 */
	public Horaire(String heureOuverture, String heureFermeture) {
		
		this.heureOuverture = heureOuverture;
		this.heureFermeture = heureFermeture;
	}
	
	/**
	 * Constructeur
	 * @param idLieu
	 * @param heureOuverture
	 * @param heureFermeture
	 */
public Horaire(int idLieu, String heureOuverture, String heureFermeture) {
		
		this.idLieu = idLieu;
		this.heureOuverture = heureOuverture;
		this.heureFermeture = heureFermeture;
	}
	
/**
 * Fonction convertissant l'heure en chaine de caractères
 * @return (heureOuverture + " - " + heureFermeture)
 */
	public String stringify() {
		return (heureOuverture + " - " + heureFermeture);
	}

	/**
	 * Getter de l'heure d'ouverture
	 * @return heureOuverture
	 */
	public String gethouv() {
		return heureOuverture;
	}
	
	/**
	 * Getter de l'heure de fermetrure
	 * @return heureFermeture
	 */
	public String gethferm() {
		return heureFermeture;
	}
}
