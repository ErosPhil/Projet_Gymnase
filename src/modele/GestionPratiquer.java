package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GestionPratiquer {
    public static ObservableList<Pratiquer> listePratiques()
    {
        ObservableList<Pratiquer> lesPratiques = FXCollections.observableArrayList();
        Pratiquer unePratique;
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
            req = "Select * from pratiquer";
            rs = stmt.executeQuery(req);
            while(rs.next())
            {
                unePratique = new Pratiquer(rs.getString("refAsso"),rs.getString("nomSport"));
                lesPratiques.add(unePratique);
            }
            stmt.close();
            conn.close();
            return lesPratiques;
        }
        catch(Exception e)
        {
            System.out.println("Erreur Requete SQL listePratiques - " + e.getMessage());
            return null;
        }
    }
    
    public static void AjouterPratique(String prefAsso, String pnomSport)
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
                req = "Insert into pratiquer values('"+prefAsso+"','"+pnomSport+"')";
            int nbLigne = stmt.executeUpdate(req);
            stmt.close();
            conn.close();
        }
        catch(Exception e)
        {
            System.out.println("Erreur Requete SQL ajout Pratique - " + e.getMessage());
        }
    }
    
    public static void SupprimerPratique(String prefAsso, String pnomSport)
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

                req = "DELETE FROM pratiquer WHERE refAsso = '"+prefAsso+"' AND nomSport = '"+ pnomSport +"'";

            int nbLigne = stmt.executeUpdate(req);
            stmt.close();
            conn.close();
        }
        catch(Exception e)
        {
            System.out.println("Erreur Requete SQL suppression Pratique - " + e.getMessage());
        }
    }
    
    public static ObservableList<Association> lesAssociationsDansPratiquer()
    {
        ObservableList<Association> lesAsso = FXCollections.observableArrayList();
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
            req = "SELECT DISTINCT p.refAsso,ville,adresse,nomResponsable FROM pratiquer p, association a Where p.refAsso = a.refAsso";
            rs = stmt.executeQuery(req);
            while(rs.next())
            {
                Association uneAssociation = new Association(rs.getString("refAsso"),rs.getString("adresse"),rs.getString("ville"),rs.getString("nomResponsable"));
                lesAsso.add(uneAssociation);
            }
            stmt.close();
            conn.close();
            return lesAsso;
        }
        catch(Exception e)
        {
            System.out.println("Erreur Requete SQL listeAssociationsDansPratiquer - " + e.getMessage());
            return null;
        }
    }
}
