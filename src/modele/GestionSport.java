package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GestionSport {
    public static ObservableList<Sport> listeSports()
    {
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
            req = "Select * from sport";
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
            System.out.println("Erreur Requete SQL listeSports - " + e.getMessage());
            return null;
        }
    }
        
    public static void AjouterSport(String pnomSport)
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
                req = "Insert into sport values('"+pnomSport+"')";
            int nbLigne = stmt.executeUpdate(req);
            stmt.close();
            conn.close();
        }
        catch(Exception e)
        {
            System.out.println("Erreur Requete SQL ajout Sport - " + e.getMessage());
        }
    }
    
    public static void SupprimerSport(String pnomSport)
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

                req = "DELETE FROM sport WHERE nomSport = '"+ pnomSport +"'";

            int nbLigne = stmt.executeUpdate(req);
            stmt.close();
            conn.close();
        }
        catch(Exception e)
        {
            System.out.println("Erreur Requete SQL suppression Sport - " + e.getMessage());
        }
    }
}
