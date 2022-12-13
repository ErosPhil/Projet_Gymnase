package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GestionAccueillir {
    
    public static void AjouterAccueil(String prefSalle, String pnomSport)
    {
        Connection conn;
        Statement stmt;
        ResultSet rs;
        String pilote = "org.gjt.mm.mysql.Driver";
        String url = new String("jdbc:mysql://localhost/gymnase");
        String req;
        try
        {
            Class.forName((pilote));
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();
                req = "Insert into accueillir values('"+prefSalle+"','"+pnomSport+"')";
            int nbLigne = stmt.executeUpdate(req);
            stmt.close();
            conn.close();
        }
        catch(Exception e)
        {
            System.out.println("Erreur Requete SQL ajout Acceuil - " + e.getMessage());
        }
    }
    
    public static void SupprimerAccueil(String prefSalle, String pnomSport)
    {
        Connection conn;
        Statement stmt;
        ResultSet rs;
        String pilote = "org.gjt.mm.mysql.Driver";
        String url = new String("jdbc:mysql://localhost/gymnase");
        String req;
        try
        {
            Class.forName((pilote));
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();
                req = "DELETE FROM accueillir WHERE refSalle = '"+prefSalle+"' AND nomSportAutorise = '"+ pnomSport +"'";
            int nbLigne = stmt.executeUpdate(req);
            stmt.close();
            conn.close();
        }
        catch(Exception e)
        {
            System.out.println("Erreur Requete SQL suppression Accueil - " + e.getMessage());
        }
    }
    
    public static ObservableList<Salle> lesSallesDansAccueillir()
    {
        ObservableList<Salle> lesSalles = FXCollections.observableArrayList();
        Connection conn;
        Statement stmt;
        ResultSet rs;
            String pilote = "org.gjt.mm.mysql.Driver";
            String url = new String("jdbc:mysql://localhost/gymnase");
        String req;
        try
        {
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();
            req = "SELECT DISTINCT a.refSalle,surface,typeRevetement FROM accueillir a, salle s Where s.refSalle = a.refSalle";
            rs = stmt.executeQuery(req);
            while(rs.next())
            {
                Salle uneSalle = new Salle(rs.getString("refSalle"),Float.parseFloat(rs.getString("surface")),rs.getString("typeRevetement"));
                lesSalles.add(uneSalle);
            }
            stmt.close();
            conn.close();
            return lesSalles;
        }
        catch(Exception e)
        {
            System.out.println("Erreur Requete SQL listeSallesDansAccueillir - " + e.getMessage());
            return null;
        }
    }
    
    public static ObservableList<Sport> listeAccueilsPourUneSalle(String prefSalle){
        ObservableList<Sport> lesSports = FXCollections.observableArrayList();
        Sport unSport;
        Connection conn;
        Statement stmt;
        ResultSet rs;
            String pilote = "org.gjt.mm.mysql.Driver";
            String url = new String("jdbc:mysql://localhost/gymnase");
        String req;
        try
        {
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();
            req = "SELECT nomSportAutorise FROM accueillir where refSalle = '"+prefSalle+"'";
            rs = stmt.executeQuery(req);
            while(rs.next())
            {
                unSport = new Sport(rs.getString("nomSportAutorise"));
                lesSports.add(unSport);
            }
            stmt.close();
            conn.close();
            return lesSports;
        }
        catch(Exception e)
        {
            System.out.println("Erreur Requete SQL listeAcceuils - " + e.getMessage());
            return null;
        }
    }
    
    public static ObservableList<Sport> listeSportsPasAccueillisParUneSalle(String prefSalle){
        ObservableList<Sport> lesSports = FXCollections.observableArrayList();
        Sport unSport;
        Connection conn;
        Statement stmt;
        ResultSet rs;
            String pilote = "org.gjt.mm.mysql.Driver";
            String url = new String("jdbc:mysql://localhost/gymnase");
        String req;
        try
        {
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();
            req = "SELECT sport.nomSport from sport WHERE nomSport NOT IN (\n" +
"    SELECT nomSportAutorise FROM accueillir WHERE accueillir.refSalle = '"+prefSalle+"'\n" +
"    )";
            rs = stmt.executeQuery(req);
            while(rs.next())
            {
                unSport = new Sport(rs.getString("nomSport"));
                lesSports.add(unSport);
            }
            stmt.close();
            conn.close();
            return lesSports;
        }
        catch(Exception e)
        {
            System.out.println("Erreur Requete SQL listePratiques - " + e.getMessage());
            return null;
        }
    }
}
