import java.util.ArrayList;

/**
 * Classe Lieu
 * 
 * @author Lachelah/Tane/Abeke
 * @version 1.0
 * */
public class Lieu {

	//attributs
	private int idLieu;
	private int nbAcces;
	private String emplacement;
	/**
	 * Constructeur
	 * @param idLieu
	 * @param nbAcces
	 * @param emplacement
	 */
	public Lieu(int idLieu, int nbAcces, String emplacement) {
		this.idLieu = idLieu;
		this.nbAcces = nbAcces;
		this.emplacement = emplacement;
	}
	
	/**
	 * Constructeur
	 * @param emplacement
	 */
	public Lieu(String emplacement) {
		this.nbAcces = 0;
		this.emplacement = emplacement;
	}
	
	/**
	 * Setter de nombre d'acces
	 * @param n
	 */
	public void setNbAcces(int n) {
		this.nbAcces = n;
	}
	
	/**
	 * Fonction incrementant le nombre d'acces
	 */
	public void addAcces() {
		this.nbAcces++;
	}

	/**
	 * Getter de nombre d'acces
	 * @return nbAcces
	 */
	public int getNbAcces() {
		return nbAcces;
	}
	
	/**
	 * Getter d'emplacement
	 * @return emplacement
	 */
	public String getEmplacement() {
		return emplacement;
	}
	
	/**
	 * Getter d'id
	 * @return idLieu
	 */
	public int getId() {
		return idLieu;
	}
}
