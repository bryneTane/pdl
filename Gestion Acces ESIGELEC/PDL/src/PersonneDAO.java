import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe d'acces aux donnees contenues dans la table Personne
 * 
 * @author Lachelah/Tane/Abeke
 * @version 1
 * */
public class PersonneDAO {
	
	final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final static String LOGIN = "BDD10";  //exemple BDD1
	final static String PASS = "BDD10";   //exemple BDD1

	

	/**
	 * Constructeur
	 */
	public PersonneDAO() {

		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err
					.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}

	}

	
	/**
	 * Fonction ajouter personne
	 * @param personne
	 * @return retour
	 */
	public int ajouter(Personne personne) {
		
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
			ps = con.prepareStatement("INSERT INTO personne (ID_PERSONNE, NOM, PRENOM, FONCTION, DATENAISSANCE) VALUES (PERS_SEQ.NEXTVAL, ?, ?, ?, ?)");
			ps.setString(1, personne.getNom());
			ps.setString(2, personne.getPrenom());
			ps.setInt(3, personne.getFonction());
			ps.setDate(4, personne.getDateNaissance());

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
	 * Fonction rechercher personne
	 * @param p
	 * @return retour
	 * @throws SQLException
	 */
	public List<Personne> rechercherPersonne(Personne p) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Personne> retour = new ArrayList<Personne> ();
		String n = " ";
		
		//connexion � la base de donn�es
		try {	
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			// pr�paration de  la requ�te en fonction des attributs non vides de la personne
			String instruction = "SELECT * FROM PERSONNE WHERE ";
			
			if(!p.getNom().isEmpty()) {
				instruction += "NOM = '"+ p.getNom()+"'";
				n = " AND ";
			}
			if(!p.getPrenom().isEmpty()) {
				instruction += n + "PRENOM = '"+ p.getPrenom()+"'";
				n = " AND ";
			}
			/*if(!p.getDateNaissanceString().isEmpty()) {
				instruction += n + "DATENAISSANCE = '"+ p.getDateNaissanceString()+"'";
				n = " AND ";
			}*/
			if(p.getFonction() == 0) {
				instruction += n + "FONCTION = '"+ p.getFonction()+"'";
			}	
			if(p.getFonction() == 0 && p.getPrenom().isEmpty() && p.getNom().isEmpty()) {
				instruction= "SELECT * FROM PERSONNE";
			}
				//System.out.println("instruction avant : " +instruction);
			//pr�paration de l'instruction SQL en fonction des valeurs con
			ps = con.prepareStatement(instruction);
				//System.out.println("instruction apr�s : " +instruction);

			rs = ps.executeQuery();
			while(rs.next()) {
				retour.add(new Personne(rs.getInt("ID_PERSONNE"), rs.getString("NOM"), rs.getString("PRENOM"), rs.getDate("DATENAISSANCE").getDate(), rs.getDate("DATENAISSANCE").getMonth(), rs.getDate("DATENAISSANCE").getYear(), rs.getInt("FONCTION")));
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
	 * Fonction modifier personne
	 * @param p
	 */
	public void modifierPersonne(Personne p) {
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
				ps = con.prepareStatement("UPDATE PERSONNE SET DATENAISSANCE = ?, PRENOM = ?, FONCTION = ?, NOM  = ?  WHERE ID_PERSONNE = ?");
				ps.setDate(1, p.getDateNaissance());
				ps.setString(2, p.getPrenom());
				ps.setInt(3, p.getFonction());
				ps.setString(4, p.getNom());
				ps.setInt(5, p.getID());
		
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
	 * Fonction Supprimer personne
	 * @param id
	 */
	public void supprimerPersonne(int id) {
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
				ps = con.prepareStatement("DELETE FROM PERSONNE WHERE ID_PERSONNE = ?");
				ps.setInt(1, id);
		
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
	 * Fonction lister Fonctions
	 * @return retour
	 * @throws SQLException
	 */
	public List<String> listeFonctions() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> retour = new ArrayList<String>();
		
		//connexion � la base de donn�es
		try {	
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			// pr�paration de  la requ�te en fonction des attributs non vides de la personne
			
			
			//pr�paration de l'instruction SQL en fonction des valeurs con
			ps = con.prepareStatement("SELECT * FROM FONCTION");
			

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
	 * Fonction ajouter fonction
	 * @param fonction
	 * @return retour
	 */
public int ajouterFonction(String fonction) {
		
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
			ps = con.prepareStatement("INSERT INTO fonction (ID_FONCTION, NOM) VALUES (FONCT_SEQ.NEXTVAL, ?)");
			ps.setString(1, fonction);

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
