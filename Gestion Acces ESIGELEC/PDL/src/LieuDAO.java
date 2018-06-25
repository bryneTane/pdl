import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe d'acces aux donnees contenues dans la table Lieux
 * 
 * @author Lachelah/Tane/Abeke
 * @version 1
 * */
public class LieuDAO {
	
	final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final static String LOGIN = "BDD10";  //exemple BDD1
	final static String PASS = "BDD10";   //exemple BDD1

	

	/**
	 * Constructeur
	 */
	public LieuDAO() {

		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err
					.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}

	}

	/**
	 * Fonction ajouter Lieu
	 * @param lieu
	 * @return retour
	 */
	public int ajouter(Lieu lieu) {
		
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
			ps = con.prepareStatement("INSERT INTO LIEUX (ID_LIEU, NB_ACCES, EMPLACEMENT) VALUES (LIEU_SEQ.NEXTVAL, ?, ?)");
			ps.setInt(1, lieu.getNbAcces());
			ps.setString(2, lieu.getEmplacement());

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
	 * Fonction rechercher lieu
	 * @return retour
	 * @throws SQLException
	 */
	public List<Lieu> rechercherLieux() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Lieu> retour = new ArrayList<Lieu> ();
		//connexion � la base de donn�es
		try {	
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			
			String instruction = "SELECT * FROM LIEUX";
			
				
			//pr�paration de l'instruction SQL en fonction des valeurs con
			ps = con.prepareStatement(instruction);
				

			rs = ps.executeQuery();
			while(rs.next()) {
				retour.add(new Lieu(rs.getInt("ID_LIEU"), rs.getInt("NB_ACCES"), rs.getString("EMPLACEMENT")));
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
	 * Fonction Rechercher lieu avec l'id maximal
	 * @return retour
	 * @throws SQLException
	 */
	public Lieu rechercherLieuMax() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Lieu retour = null;
		//connexion � la base de donn�es
		try {	
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			
			String instruction = "SELECT * FROM LIEUX WHERE ID_LIEU = (SELECT MAX(ID_LIEU) FROM LIEUX)";
			
				
			//pr�paration de l'instruction SQL en fonction des valeurs con
			ps = con.prepareStatement(instruction);
				

			rs = ps.executeQuery();
			rs.next();
			retour = new Lieu(rs.getInt("ID_LIEU"), rs.getInt("NB_ACCES"), rs.getString("EMPLACEMENT"));
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
	 * Fonction modifier lieu
	 * @param l
	 */
	public void modifierLieu(Lieu l) {
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
				ps = con.prepareStatement("UPDATE LIEUX SET NB_ACCES = ?, EMPLACEMENT  = ?  WHERE ID_LIEU = ?");
				ps.setInt(1, l.getNbAcces());
				ps.setString(2, l.getEmplacement());
				ps.setInt(3, l.getId());
		
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
	 * Fonction Supprimer lieu
	 * @param id
	 */
	public void supprimerLieu(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;

		// connexion � la base de donn�es
		try {
				// tentative de connexion
				con = DriverManager.getConnection(URL, LOGIN, PASS);
				// pr�paration de l'instruction SQL, chaque ? repr�sente une valeur
				// � communiquer dans l'insertion
				ps = con.prepareStatement("DELETE FROM LIEUX WHERE ID_LIEU = ?");
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
	 * Fonction Supprimer Horaire d'un lieu
	 * @param id
	 */
	public void supprimerHoraire(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;

		// connexion � la base de donn�es
		try {
				// tentative de connexion
				con = DriverManager.getConnection(URL, LOGIN, PASS);
				// pr�paration de l'instruction SQL, chaque ? repr�sente une valeur
				// � communiquer dans l'insertion
				
				ps = con.prepareStatement("DELETE FROM HORAIRES WHERE ID_LIEU = ?");
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
	 * Fonction lister les d'acces
	 * @return retour
	 * @throws SQLException
	 */
	public List<String> listeAcces() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> retour = new ArrayList<String>();
		
		//connexion a la base de donn�es
		try {	
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			
			//preparation de l'instruction SQL en fonction des valeurs con
			ps = con.prepareStatement("SELECT * FROM TYPE_ACCES");
			

			rs = ps.executeQuery();
			while(rs.next()) {
				retour.add(rs.getString("NOM"));
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
	 * Fonction rechercher Horaires d'un lieu
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
	 * Fonction ajouter horaire à un lieu
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
 * Fonction actualiser le nombre d'acces
 * @param idLieu
 * @param nb
 */
public void setNbAcces(int idLieu, int nb) {
	Connection con = null;
	PreparedStatement ps = null;
	int retour = 0;

	// connexion � la base de donn�es
	try {
			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// pr�paration de l'instruction SQL, chaque ? repr�sente une valeur
			// � communiquer dans l'insertion
			
			ps = con.prepareStatement("UPDATE LIEUX SET NB_ACCES = ? WHERE ID_LIEU = ?");
			ps.setInt(1, nb);
			ps.setInt(2, idLieu);
	
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
 * Fonction actuaisant l'emplacement d'un lieu
 * @param emp
 * @param idLieu
 */
public void setEmplacement(String emp, int idLieu) {
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
			ps = con.prepareStatement("UPDATE LIEUX SET EMPLACEMENT = ? WHERE ID_LIEU = ?");
			ps.setString(1, emp);
			ps.setInt(2, idLieu);
	
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


