package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;

public class GestionOccupation {
    
    public static ObservableList<Reservation> listeReservationsPourUneSalleEntre2Dates(String prefSalle, LocalDate pDate1, LocalDate pDate2){ 
        ObservableList<Reservation> lesReservationsDelaSalleEnDate = FXCollections.observableArrayList();
        Reservation uneReservation;
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
            req = "SELECT * FROM reservation WHERE reservation.refSalle = '"+ prefSalle +"'"
                    + " AND reservation.date BETWEEN '"+ pDate1 +"' AND '"+ pDate2 +"'";
            rs = stmt.executeQuery(req);
            while(rs.next())
            {
                uneReservation = new Reservation(rs.getString("refSalle"),LocalDate.parse(rs.getString("date")),LocalTime.parse(rs.getString("heure")),rs.getString("refAsso"));
                lesReservationsDelaSalleEnDate.add(uneReservation);
            }
            stmt.close();
            conn.close();
            return lesReservationsDelaSalleEnDate;
        }
        catch(Exception e)
        {
            System.out.println("Erreur Requete SQL listeReservationsPourUneSalleEntre2Dates - " + e.getMessage());
            return null;
        }
    }
}
