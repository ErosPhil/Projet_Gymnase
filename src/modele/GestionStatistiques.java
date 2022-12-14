package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class GestionStatistiques {
    //Renvoie une liste de refAsso correspondant aux associations ayant utilisé la salle passée en paramètre
    public static ObservableList<String> listeRefAssoUtilisantUneSalle(String prefSalle){ 
        ObservableList<String> lesRefAsso = FXCollections.observableArrayList();
        String uneRefAsso;
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
            req = "SELECT DISTINCT refAsso FROM reservation WHERE refSalle = '"+ prefSalle +"'";
            rs = stmt.executeQuery(req);
            while(rs.next())
            {
                uneRefAsso = rs.getString("refAsso");
                lesRefAsso.add(uneRefAsso);
            }
            stmt.close();
            conn.close();
            return lesRefAsso;
        }
        catch(Exception e)
        {
            System.out.println("Erreur Requete SQL listeRefAssoUtilisantUneSalle - " + e.getMessage());
            return null;
        }
    }
    
    //Renvoie le nombre total de réservations qui ont été passées pour la salle passée en paramètre
    public static int nombreTotalReservationsPourUneSalle(String prefSalle){
        Connection conn;
        Statement stmt;
        ResultSet rs;
            String pilote = "org.gjt.mm.mysql.Driver";
            String url = new String("jdbc:mysql://localhost/gymnase");
        String req;
        int nbRes = 0;
        try
        {
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();
            req = "SELECT * FROM reservation where refSalle = '"+ prefSalle +"'";
            rs = stmt.executeQuery(req);
            while(rs.next())
            {
                nbRes++;
                //System.out.println("+1 T");
            }
            stmt.close();
            conn.close();
            return nbRes;
        }
        catch(Exception e)
        {
            System.out.println("Erreur Requete SQL nombreTotalReservationsPourUneSalle - " + e.getMessage());
            return -100;
        }
    }
    
    //Renvoie le nombre total de réservations qui ont été passées par une association spécifique pour la salle, toutes deux passées en paramètres
    public static int nombreTotalReservationsDUneAssoPourUneSalle(String prefAsso, String prefSalle){
        Connection conn;
        Statement stmt;
        ResultSet rs;
            String pilote = "org.gjt.mm.mysql.Driver";
            String url = new String("jdbc:mysql://localhost/gymnase");
        String req;
        int nbRes = 0;
        try
        {
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();
            req = "SELECT * FROM reservation where refAsso = '"+ prefAsso +"' AND refSalle = '"+ prefSalle +"'";
            rs = stmt.executeQuery(req);
            while(rs.next())
            {
                nbRes++;
                //System.out.println("+1 A");
            }
            stmt.close();
            conn.close();
            return nbRes;
        }
        catch(Exception e)
        {
            System.out.println("Erreur Requete SQL nombreTotalReservationsDUneAssoPourUneSalle - " + e.getMessage());
            return -100;
        }
    }
    
    public static ObservableList<ArrayList> iterationsHoraires(String prefSalle){
        ObservableList<ArrayList> lesIterationsHoraires = FXCollections.observableArrayList();
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
            req = "SELECT heure, count(*)'nombreIterations' FROM reservation where refSalle = '"+ prefSalle +"' GROUP BY heure ORDER BY heure ASC";
            rs = stmt.executeQuery(req);
            while(rs.next())
            {
                ArrayList<Object> list = new ArrayList<>();
                list.add(rs.getString("heure"));
                list.add(rs.getString("nombreIterations"));
                lesIterationsHoraires.add(list);
            }
            stmt.close();
            conn.close();
            return lesIterationsHoraires;
        }
        catch(Exception e)
        {
            System.out.println("Erreur Requete SQL nombreTotalReservationsDUneAssoPourUneSalle - " + e.getMessage());
            return null;
        }
    }
}