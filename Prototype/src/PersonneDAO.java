import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe d'accès aux données contenues dans la table Personne
 * 
 * @author Lachelah/Tane/Abeke
 * @version 1
 * */
public class PersonneDAO {
	
	final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final static String LOGIN = "BINTOU";  //exemple BDD1
	final static String PASS = "Bintou";   //exemple BDD1

	

	
	public PersonneDAO() {

		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err
					.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}

	}

	
	public int ajouter(Personne personne) {
		
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;

		// connexion à la base de données
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// préparation de l'instruction SQL, chaque ? représente une valeur
			// à communiquer dans l'insertion
			// les getters permettent de récupérer les valeurs des attributs
			// souhaités
			ps = con.prepareStatement("INSERT INTO personne (ID, NOM, PRENOM, FONCTION, DATENAISSANCE) VALUES (PERS_SEQ1.NEXTVAL, ?, ?, ?, ?)");
			ps.setString(1, personne.getNom());
			ps.setString(2, personne.getPrenom());
			ps.setString(3, personne.getFonction());
			ps.setDate(4, personne.getDateNaissance());

			// Exécution de la requête
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
	
	
	public List<Personne> rechercherPersonne(Personne p) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Personne> retour = new ArrayList<Personne> ();
		String n = " ";
		
		//connexion à la base de données
		try {	
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			// préparation de  la requête en fonction des attributs non vides de la personne
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
			if(!p.getFonction().isEmpty()) {
				instruction += n + "FONCTION = '"+ p.getFonction()+"'";
			}	
			if(p.getFonction().isEmpty() && p.getPrenom().isEmpty() && p.getNom().isEmpty()) {
				instruction= "SELECT * FROM PERSONNE";
			}
				//System.out.println("instruction avant : " +instruction);
			//préparation de l'instruction SQL en fonction des valeurs con
			ps = con.prepareStatement(instruction);
				//System.out.println("instruction après : " +instruction);

			rs = ps.executeQuery();
			while(rs.next()) {
				retour.add(new Personne(rs.getInt("ID"), rs.getString("NOM"), rs.getString("PRENOM"), rs.getDate("DATENAISSANCE").getDate(), rs.getDate("DATENAISSANCE").getMonth(), rs.getDate("DATENAISSANCE").getYear(), rs.getString("FONCTION")));
			}	
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally{ //de toute façon
			//fermeture du rs, preparedStatement et de la connexion
			try {if (ps != null) rs.close();} catch (Exception ignore) {}
			try {if (ps != null) ps.close();} catch (Exception ignore) {}
			try {if (ps != null) con.close();} catch (Exception ignore) {}
		}
		return retour;
		
	}
	
	
	public void modifierPersonne(Personne p) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;

		// connexion à la base de données
		try {
				// tentative de connexion
				con = DriverManager.getConnection(URL, LOGIN, PASS);
				// préparation de l'instruction SQL, chaque ? représente une valeur
				// à communiquer dans l'insertion
				// les getters permettent de récupérer les valeurs des attributs
				// souhaités
				ps = con.prepareStatement("UPDATE PERSONNE SET DATENAISSANCE = ?, PRENOM = ?, FONCTION = ?, NOM  = ?  WHERE ID = ?");
				ps.setDate(1, p.getDateNaissance());
				ps.setString(2, p.getPrenom());
				ps.setString(3, p.getFonction());
				ps.setString(4, p.getNom());
				ps.setInt(5, p.getID());
		
				// Exécution de la requête
				retour = ps.executeUpdate();
		} catch (Exception e) {
				e.printStackTrace();
				} finally {
				// fermeture du preparedStatement et de la connexion
					try {if (ps != null) ps.close();} catch (Exception ignore) {}
					try {if (con != null) con.close();} catch (Exception ignore) {}
				}

		}
	
	public void supprimerPersonne(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;

		// connexion à la base de données
		try {
				// tentative de connexion
				con = DriverManager.getConnection(URL, LOGIN, PASS);
				// préparation de l'instruction SQL, chaque ? représente une valeur
				// à communiquer dans l'insertion
				// les getters permettent de récupérer les valeurs des attributs
				// souhaités
				ps = con.prepareStatement("DELETE FROM PERSONNE WHERE ID = ?");
				ps.setInt(1, id);
		
				// Exécution de la requête
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
