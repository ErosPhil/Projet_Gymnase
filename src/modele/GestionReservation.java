package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GestionReservation {
        public static ObservableList<Salle> listeSallesCompatiblesAvecUneAsso(String prefAsso){
        ObservableList<Salle> lesSalles = FXCollections.observableArrayList();
        Salle uneSalle;
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
            req = "SELECT DISTINCT salle.refSalle, salle.surface, salle.typeRevetement FROM salle, accueillir, pratiquer "
                    + "WHERE salle.refSalle = accueillir.refSalle AND accueillir.nomSportAutorise = pratiquer.nomSport "
                    + "AND pratiquer.refAsso = '"+ prefAsso +"'";
            rs = stmt.executeQuery(req);
            while(rs.next())
            {
                uneSalle = new Salle(rs.getString("refSalle"),Float.parseFloat(rs.getString("surface")),rs.getString("typeRevetement"));
                lesSalles.add(uneSalle);
            }
            stmt.close();
            conn.close();
            return lesSalles;
        }
        catch(Exception e)
        {
            System.out.println("Erreur Requete SQL listeSallesCompatiblesAvecUneAsso - " + e.getMessage());
            return null;
        }
    }
    
    public static ObservableList<Sport> listeSportsAccueillisParUneSalle(String prefSalle, String prefAsso){
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
            req = "SELECT sport.nomSport FROM sport, accueillir, pratiquer "
                    + "WHERE sport.nomSport = accueillir.nomSportAutorise AND pratiquer.nomSport = accueillir.nomSportAutorise "
                    + "AND accueillir.refSalle = '"+ prefSalle +"' AND pratiquer.refAsso = '"+ prefAsso +"'";
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
            System.out.println("Erreur Requete SQL listeSallesCompatiblesAvecUneAsso - " + e.getMessage());
            return null;
        }
    }
    
    public static boolean verifReservationPossible(String prefSalle, String pDate, String pHeure, String prefAsso){
        Connection conn;
        Statement stmt;
        ResultSet rs;
        boolean possible = true;
            String pilote = "org.gjt.mm.mysql.Driver";
            String url = new String("jdbc:mysql://localhost/gymnase");
        String req;
        try
        {
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();
            req = "SELECT * FROM reservation WHERE reservation.refSalle = '"+ prefSalle +
                    "' AND reservation.date = '"+ pDate +"' AND reservation.heure = '"+ pHeure +
                    "' AND reservation.refAsso = '"+ prefAsso +"'";
            rs = stmt.executeQuery(req);
            if(rs.next())
            {
                possible = false;
            }
            stmt.close();
            conn.close();
            return possible;
        }
        catch(Exception e)
        {
            System.out.println("Erreur Requete SQL listeSallesCompatiblesAvecUneAsso - " + e.getMessage());
            return false;
        }
    }
    
    public static void Reserver(String prefSalle, String pDate, String pHeure, String prefAsso)
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
                req = "Insert into reservation values('"+ prefSalle +"','"+ pDate +"','"+ pHeure +"','"+ prefAsso +"')";
            int nbLigne = stmt.executeUpdate(req);
            stmt.close();
            conn.close();
        }
        catch(Exception e)
        {
            System.out.println("Erreur Requete SQL ajout Reservation - " + e.getMessage());
        }
    }
}
