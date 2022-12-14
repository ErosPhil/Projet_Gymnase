package controleur;
    //Plan occupation : calendrier horaire pour les jour où il y a au moins une réservation
import java.io.IOException;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modele.*;

public class MainApp extends Application 
{
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    //private static ObservableList<Association>mesAssociations;
    private static Association monAssociationSelectionnee;

    private static String etat;
    
    @Override
    public void start(Stage primaryStage) 
    {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Gestion Gymnase");
        
        try
        {
        // Chargement du layout racine à partir du fichier fxml
        FXMLLoader loader = new
        FXMLLoader(MainApp.class.getResource("/vue/FenFXML_NoeudRacine.fxml"));
        //borderpane contenant la menubar et le anchorpane
        rootLayout = (BorderPane) loader.load();
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
        }
        catch (IOException e)
        {
        // Exception qui intervient si le fichier fxml file n'a pas pu être chargé
        e.printStackTrace();
        }
        accueil();
    }
    
    public Stage getPrimaryStage()
    {
        return primaryStage;
    }
    
    // Ouvre fenêtre Accueil
    public void accueil()
    {
        try
        {
            //Charge le fichier fxml et le place au centre du layout principal
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/vue/FenAccueil.fxml"));
            AnchorPane overviewPage = (AnchorPane) loader.load();
            rootLayout.setCenter(overviewPage);
        }
        catch(IOException e)
        {
            //Exception qui intervient si le fichier fxml file n'a pas pu être chargé
            e.printStackTrace();
        }
    }
    
    // Etat
    public static void setEtat(String petat)
    {
        etat = petat;
    }
    public static String getEtat()
    {
        return etat;
    }
    
    // Association
    /*public static void setMesAssociations(ObservableList<Association> pmesAssociations)
    {
        mesAssociations = pmesAssociations;
    }
    public static ObservableList<Association> getMesAssociations()
    {
        return mesAssociations;
    }*/
    
    public static void setMonAssociationSelectionnee(Association pmonAssociationSelectionnee){
        monAssociationSelectionnee = pmonAssociationSelectionnee;
    }
    public static Association getMonAssociationSelectionnee()
    {
        return monAssociationSelectionnee;
    }

    // Main
    public static void main(String[] args) {
        launch(args);
    }
    
}