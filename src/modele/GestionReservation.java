package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GestionReservation {
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
