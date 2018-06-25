/**
 * @author ABEKE, LACHELAH, TANE
 * @version  1.0
 * 
 * Classe Acces
 **/
public class Acces {
	
	//attributs de l'acces 
	private int idType;
	private int idLieu;
	private int idAcces;
	
/**
 * Constructeur
 * @param idType, idLieu 
 * 
 **/
	public Acces(int idType, int idLieu) {
		this.idType = idType;
		this.idLieu = idLieu;
	}
/**
 * Constructeur
 * @param idType, idLieu, idAcces
 **/
	
	public Acces(int idType, int idLieu, int idAcces) {
		this.idType = idType;
		this.idLieu = idLieu;
		this.idAcces = idAcces;
	}
/**
 * Getter de type
 * @return idType
 **/
	public int getType() {
		return idType;
	}

/**
 * Getter d'identifiant du lieu
 * @return idLieu
 **/
	public int getLieu() {
		return idLieu;
	}

/**
 * Getter de l'identifiant
 * @return idAcces
 **/
	public int getId() {
		return idAcces;
	}

}
