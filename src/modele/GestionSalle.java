package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GestionSalle {
    public static ObservableList<Salle> listeSalles()
    {
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
            req = "Select * from salle";
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
            System.out.println("Erreur Requete SQL listeSalles - " + e.getMessage());
            return null;
        }
    }
}
