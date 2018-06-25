import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe d'acces aux donnees contenues dans la table Acces
 * 
 * @author Lachelah/Tane/Abeke
 * @version 1.0
 * */
public class AccesDAO {
	
	final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final static String LOGIN = "BDD10";  
	final static String PASS = "BDD10";   

	

	/**
	 * Constructeur
	 */
	public AccesDAO() {

		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err
					.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}

	}

	/**
	 * Fonction ajouter acces
	 * @param acces
	 * @return retour
	 */
	public int ajouterAcces(Acces acces) {
		
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
			ps = con.prepareStatement("INSERT INTO ACCES (ID_ACCES, ID_LIEU, ID_TYPE_ACCES) VALUES (ACCES_SEQ.NEXTVAL, ?, ?)");
			ps.setInt(1, acces.getLieu());
			ps.setInt(2, acces.getType());

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
	 * Fonction rechercher acces
	 * @return retour
	 * @throws SQLException
	 */
	public List<Acces> rechercherAcces() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Acces> retour = new ArrayList<Acces> ();
		//connexion � la base de donn�es
		try {	
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			
			String instruction = "SELECT * FROM ACCES";
			
			//pr�paration de l'instruction SQL en fonction des valeurs con
			ps = con.prepareStatement(instruction);

			rs = ps.executeQuery();
			while(rs.next()) {
				retour.add(new Acces(rs.getInt("ID_TYPE_ACCES"), rs.getInt("ID_LIEU"), rs.getInt("ID_ACCES")));
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
	 * Fonction modifier acces
	 * @param a
	 */
	public void modifierAcces(Acces a) {
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
				ps = con.prepareStatement("UPDATE ACCES SET ID_TYPE_ACCES = ?, ID_LIEU  = ?  WHERE ID_ACCES = ?");
				ps.setInt(1, a.getType());
				ps.setInt(2, a.getLieu());
				ps.setInt(3, a.getId());
		
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
	
	/**
	 * fonction supprimer acces
	 * @param id
	 */
	public void supprimerAcces(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;

		// connexion � la base de donn�es
		try {
				// tentative de connexion
				con = DriverManager.getConnection(URL, LOGIN, PASS);
				// pr�paration de l'instruction SQL, chaque ? repr�sente une valeur
				ps = con.prepareStatement("DELETE FROM ACCES WHERE ID_ACCES = ?");
				ps.setInt(1, id);
		
				// Execution de la requ�te
				retour = ps.executeUpdate();
				
		} catch (Exception e) {
				e.printStackTrace();
				} finally {
				// fermeture du preparedStatement et de la connexion
					try {if (ps != null) ps.close();} catch (Exception ignore) {}
					try {if (con != null) con.close();} catch (Exception ignore) {}
				}
	}
	
	/**
	 * Fonction Lister types d'acces
	 * @return retour
	 * @throws SQLException
	 */
	public List<TypeAcces> listeAcces() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TypeAcces> retour = new ArrayList<TypeAcces>();
		
		//connexion a la base de donn�es
		try {	
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			
			
			//preparation de l'instruction SQL en fonction des valeurs con
			ps = con.prepareStatement("SELECT * FROM TYPE_ACCES");
			

			rs = ps.executeQuery();
			while(rs.next()) {
				retour.add(new TypeAcces(rs.getInt("ID_TYPE_ACCES"), rs.getString("NOM")));
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
	 * Fonction compter acces d'un lieu
	 * @param idLieu
	 * @return retour
	 * @throws SQLException
	 */
	public int CompterAcces(int idLieu) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int retour = 0;
		
		//connexion a la base de donn�es
		try {	
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			// preparation de  la requ�te en fonction des attributs non vides de la personne
			
			
			//preparation de l'instruction SQL en fonction des valeurs con
			ps = con.prepareStatement("SELECT COUNT(ID_LIEU) FROM ACCES WHERE ID_LIEU = ?");
			ps.setInt(1, idLieu);

			rs = ps.executeQuery();
			if(rs.next()) {
				retour = rs.getInt("COUNT(ID_LIEU)");
				System.out.println(rs.getInt("COUNT(ID_LIEU)"));
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
 * Fonction Creer un type d'acces
 * @param nom
 * @return retour
 */
public int ajouterTypeAcces(String nom) {
		
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
			ps = con.prepareStatement("INSERT INTO TYPE_ACCES (ID_TYPE_ACCES, NOM) VALUES (TYPE_ACCES_SEQ.NEXTVAL, ?)");
			ps.setString(1, nom);

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
