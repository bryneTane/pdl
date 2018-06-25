import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe d'acces aux donnees contenues dans la table Horaires
 * 
 * @author Lachelah/Tane/Abeke
 * @version 1
 * */
public class HoraireDAO {
	
	final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final static String LOGIN = "BDD10";  
	final static String PASS = "BDD10";   
	

	/**
	 * Constructeur
	 */
	public HoraireDAO() {

		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err
					.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}

	}
	
	/**
	 * Fonction de recherche d'horaires
	 * @param idLieu
	 * @return retour
	 * @throws SQLException
	 */
	public List<Horaire> rechercherHoraires(int idLieu) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Horaire> retour = new ArrayList<Horaire> ();
		//connexion � la base de donn�es
		try {	
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			//pr�paration de l'instruction SQL en fonction des valeurs con
			ps = con.prepareStatement("SELECT * FROM HORAIRE WHERE ID_LIEU = ?");
			ps.setInt(1, idLieu);
				//System.out.println("instruction apr�s : " +instruction);

			rs = ps.executeQuery();
			while(rs.next()) {
				retour.add(new Horaire(rs.getString("HEURE_OUVERTURE"), rs.getString("HEURE_FERMETURE")));
			}	
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally{ //de toute fa�on
			//fermeture du rs, preparedStatement et de la connexion
			try {if (ps != null) rs.close();} catch (Exception ignore) {}
			try {if (ps != null) ps.close();} catch (Exception ignore) {}
			try {if (ps != null) con.close();} catch (Exception ignore) {}
		}
		return retour;
		
	}
	
	/**
	 * Fonction d'ajout d'horaire
	 * @param horaire
	 * @param idLieu
	 * @return retour
	 */
public int ajouterHoraire(Horaire horaire, int idLieu) {
		
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;

		// connexion � la base de donn�es
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// pr�paration de l'instruction SQL, chaque ? repr�sente une valeur
			// � communiquer dans l'insertion
			// les getters permettent de r�cup�rer les valeurs des attributs
			// souhait�s
			ps = con.prepareStatement("INSERT INTO HORAIRES (ID_HORAIRE, ID_LIEU, HEURE_OUVERTURE, HEURE_FERMETURE) VALUES (HORAIRE_SEQ.NEXTVAL, ?, ?, ?)");
			ps.setInt(1, idLieu);
			ps.setString(2, horaire.gethouv());
			ps.setString(3, horaire.gethferm());

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

/**
 * Fonction Supprimer horaire
 * @param idLieu
 */
	public void supprimerHoraires(int idLieu) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
	
		// connexion � la base de donn�es
		try {
				// tentative de connexion
				con = DriverManager.getConnection(URL, LOGIN, PASS);
				// pr�paration de l'instruction SQL, chaque ? repr�sente une valeur
				// � communiquer dans l'insertion
				// les getters permettent de r�cup�rer les valeurs des attributs
				// souhait�s
				ps = con.prepareStatement("DELETE FROM HORAIRES WHERE ID_LIEU = ?");
				ps.setInt(1, idLieu);
		
				// Ex�cution de la requ�te
				retour = ps.executeUpdate();
		} catch (Exception e) {
				e.printStackTrace();
				} finally {
				// fermeture du preparedStatement et de la connexion
					try {if (ps != null) ps.close();} catch (Exception ignore) {}
					try {if (con != null) con.close();} catch (Exception ignore) {}
				}
	}

	
}
