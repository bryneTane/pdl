/**
 * Classe Type d'acces
 * 
 * @author Lachelah/Tane/Abeke
 * @version 1.0
 * */
public class TypeAcces {
	
	private String nom;
	private int id;
	
	/**
	 * Constructeur
	 * @param id
	 * @param nom
	 */
	public TypeAcces(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}
	
	/**
	 * Constructeur
	 * @param nom
	 */
	public TypeAcces(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Getter d'id de type acces
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Getter de nom de type d'acces
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}
}
