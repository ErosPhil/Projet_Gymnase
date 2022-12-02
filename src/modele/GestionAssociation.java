package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GestionAssociation {
    public static ObservableList<Association> listeAssociations()
    {
        ObservableList<Association> lesAssociations = FXCollections.observableArrayList();
        Association uneAssociation;
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
            req = "Select * from association";
            rs = stmt.executeQuery(req);
            while(rs.next())
            {
                uneAssociation = new Association(rs.getString("refAsso"),rs.getString("adresse"),rs.getString("ville"),rs.getString("nomResponsable"));
                lesAssociations.add(uneAssociation);
            }
            stmt.close();
            conn.close();
            return lesAssociations;
        }
        catch(Exception e)
        {
            System.out.println("Erreur Requete SQL listeAssociations - " + e.getMessage());
            return null;
        }
    }
        
    public static void EnregistrerAssociation(String etat, String pref, String padresse, String pville, String presponsable)
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
            if(etat.equals("Modif"))
            {
                req = "Update association set ville = '" + pville + "', adresse ='" + padresse + "', nomResponsable ='" + presponsable + "'";
                req += "Where refAsso = '" + pref + "'";
            }
            else
            {
                req = "Insert into association values('"+pref+"','"+padresse+"','"+pville+"','"+presponsable+"')";
            }
            int nbLigne = stmt.executeUpdate(req);
            stmt.close();
            conn.close();
        }
        catch(Exception e)
        {
            System.out.println("Erreur Requete SQL mise à jour Association - " + e.getMessage());
        }
    }
    
    public static void SupprimerAssociation(String pref)
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

                req = "DELETE FROM association WHERE refAsso = '"+ pref +"'";

            int nbLigne = stmt.executeUpdate(req);
            stmt.close();
            conn.close();
        }
        catch(Exception e)
        {
            System.out.println("Erreur Requete SQL suppression Association - " + e.getMessage());
        }
    }
}
