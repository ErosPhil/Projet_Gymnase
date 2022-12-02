package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modele.Association;
import modele.GestionAssociation;

public class AfficherAssociationController implements Initializable {
    ObservableList<Association> lesAssociations;
    @FXML
    private TableView<Association> tvAssociations;
    @FXML
    private TableColumn<Association, String> colonneRef;
    @FXML
    private TableColumn<Association, String> colonneVille;
    @FXML
    private TableColumn<Association, String> colonneAdresse;
    @FXML
    private TableColumn<Association, String> colonneNomResponsable;
    
    Stage oStage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lesAssociations = GestionAssociation.listeAssociations();
        tvAssociations.setItems(lesAssociations);
        colonneRef.setCellValueFactory(new PropertyValueFactory<Association, String>("refAsso"));
        colonneVille.setCellValueFactory(new PropertyValueFactory<Association, String>("adresse"));
        colonneAdresse.setCellValueFactory(new PropertyValueFactory<Association, String>("ville"));
        colonneNomResponsable.setCellValueFactory(new PropertyValueFactory<Association, String>("nomResponsable"));
        
        tvAssociations.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }    
    
    @FXML
    public void handleChangeItemTvAssociations()
    {
        Association monAssociation = tvAssociations.getSelectionModel().getSelectedItem(); //Item sélectionné
        MainApp.setMonAssociationSelectionnee(monAssociation); //"stockage" dans MainApp
        MainApp.setEtat("Modif");
        
        //Création de la fenêtre de confirmation
        try
        {
            oStage = new Stage();
            oStage.setTitle("Modification association");
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/vue/FenFXML_modificationAssociation.fxml"));
            
            AnchorPane rootLayout = (AnchorPane)loader.load();
            Scene scene = new Scene(rootLayout);
            oStage.setScene(scene);
            oStage.show();
        }
        catch(IOException e)
        {
            System.out.println("Erreur chargement seconde fenetre: modif : " + e.getMessage());
        }
    }
    
    @FXML
    public void handleInserer()
    {
        MainApp.setEtat("Insert");
        // Création de la fenêtre de confirmation
        try
        {
        oStage = new Stage();
        oStage.setTitle("Insertion nouvelle association");
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/vue/FenFXML_modificationAssociation.fxml"));
        AnchorPane rootLayout = (AnchorPane) loader.load();
        Scene scene = new Scene(rootLayout);
        oStage.setScene(scene);
        oStage.show();
        }
        catch (IOException e)
        {
        System.out.println("Erreur chargement fenetre fenetre: insert : " + e.getMessage());
        }
    }
    
    @FXML
    private void handleSupprimer()
    {
        Association monAssociation = tvAssociations.getSelectionModel().getSelectedItem();
        GestionAssociation.SupprimerAssociation(monAssociation.getRefAsso());
        
        int indexSelectionne = tvAssociations.getSelectionModel().getSelectedIndex();
        tvAssociations.getItems().remove(indexSelectionne);
        ObservableList<Association> lesAssociations = tvAssociations.getItems();
        MainApp.setMesAssociations(lesAssociations);
    }
}
