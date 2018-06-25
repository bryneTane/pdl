import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe d'acc�s aux donn�es contenues dans la table Carte
 * 
 * @author Lachelah/Tane/Abeke
 * @version 1
 * */
public class CarteDAO {
	
	final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final static String LOGIN = "BDD10";  
	final static String PASS = "BDD10";   

	

	/**
	 * Constructeur
	 */
	public CarteDAO() {

		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err
					.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}

	}
	/**
	 * Fonction Ajouter carte à une personne
	 * @param idPers
	 * @return
	 */
public int ajouter(int idPers) {
		
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;

		// connexion � la base de donn�es
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// pr�paration de l'instruction SQL, chaque ? repr�sente une valeur
			// � communiquer dans l'insertion
			ps = con.prepareStatement("INSERT INTO CARTE (ID_CARTE, NUMERO, PERSONNE) VALUES (CARTE_SEQ.NEXTVAL, CARTE_SEQ.NEXTVAL, ?)");
			ps.setInt(1, idPers);	

			// Ex�cution de la requ�te
			retour = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// fermeture du preparedStatement et de la connexion
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return retour;

	}
	
}