/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import Entities.Astuce;
import Functions.CurrentAstuce;
import Services.AstuceServices;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import io.datafx.controller.FXMLController;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
@FXMLController(value = "/Views/fxml/AfficherAstuce.fxml", title = "")
public class AfficherAstuceController implements Initializable {

    
    
    @FXML
    private ImageView image;
    
    
    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<Astuce> TabAstuce;

    @FXML
    private TableColumn id;

    @FXML
    private TableColumn<Astuce, String> titre;

    @FXML
    private TableColumn<Astuce, String> theme;

    @FXML
    private TableColumn<Astuce, String> description;

    @FXML
    private TextField rechercher;

    @FXML
    private Button Consulter;
    
    @FXML
    private JFXButton astuceFb;

    @FXML
    private Button Supprimer;

    @FXML
    private Button modifier;

    @FXML
    private Button retour;

        @FXML
    private JFXButton pdf;
    
    
    @FXML
    private JFXTextField desc;
    
    
    
    @FXML
    private JFXTextField title;
    
    
    
    
    @FXML
    private JFXTextField themes;
    
    
    @FXML
    void buildData2(KeyEvent event) {

    }

    @FXML
    void consulter(ActionEvent event) throws IOException {
              pane.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("Views/fxml/DetailsAstuce.fxml")));

    }
    
      @FXML
    void export(ActionEvent event) {
         
        Document document = new Document();
        
        try{
            Astuce a = CurrentAstuce.getCurrentAstuce();
            PdfWriter.getInstance(document, new FileOutputStream(a.getTitre()+".pdf"));
            document.open();
            com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance("src//Image//"+a.getImage_name());
            //img.setAbsolutePosition(450f, 10f);
            img.scaleToFit(100f, 100f);
            document.add(img);
            Paragraph p1 = new Paragraph(a.getTheme());
            Paragraph p2 = new Paragraph(a.getTitre());
            Paragraph p3 = new Paragraph(a.getDescription());
            document.add(p1);
            document.add(p2);
            document.add(p3);
        }
        catch(Exception e){
            System.out.println(e);
        }
        document.close();
                 

    }

    @FXML
    void modifier(ActionEvent event) throws SQLException {

        Astuce a = new Astuce();

        a.setTitre(title.getText());
        a.setTheme(themes.getText());
        a.setDescription(desc.getText());
        a.setId(id_astuce);
        AstuceServices astuceService = new AstuceServices();
        astuceService.update(a);
        FillTable();

    }

    @FXML
    void retour(ActionEvent event) {

    }
    
    @FXML
    void importAstuce(ActionEvent event) throws IOException {
        pane.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("Views/fxml/AstuceDuJour.fxml")));

    }

    @FXML
    void supprimer(ActionEvent event) throws SQLException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Fenetre de confirmation");
        alert.setHeaderText("Suppression d'une astuce");
        alert.setContentText("Etes vous sures de vouloir supprimer cette astuce?");
        ButtonType buttonTypeOne = new ButtonType("Oui");
        ButtonType buttonTypeTwo = new ButtonType("Non");
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            AstuceServices astuceService = new AstuceServices();

            astuceService.remove(a);
            Alert alertInfo = new Alert(Alert.AlertType.CONFIRMATION);
            alertInfo.setTitle("Information");
            alertInfo.setHeaderText("L'astuce a été supprimé avec succès.");
            alertInfo.showAndWait();
            FillTable();
        } else {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Erreur");
            alertError.setHeaderText("Une erreur s'est produite lors de la suppresion.");
            alertError.setContentText("Veuillez vérifier cette opération.");
            alertError.showAndWait();

        }

    }

    private Astuce a;
    private int id_astuce;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FillTable();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        AstuceServices AstuceService = new AstuceServices();
        

         
        

        
            TabAstuce.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            a = TabAstuce.getSelectionModel().getSelectedItem();
            id_astuce = a.getId();
            AstuceServices aq = new AstuceServices();
            Astuce ac = new Astuce();
             ac= aq.findById(id_astuce);
             
                CurrentAstuce.setCurrentAstuce(ac);
         //
         
                themes.setText(ac.getTheme());
                title.setText(ac.getTitre());
                desc.setText(ac.getDescription());
         
         System.out.println("Image/" + ac.getImage_name());
            image.setImage(new Image("Image/" + ac.getImage_name()));
            
            
            
            
            
        });
    }

    private void FillTable() throws SQLException {

        AstuceServices AstuceService = new AstuceServices();
        ObservableList<Astuce> data = FXCollections.observableArrayList();
        ResultSet set;

        set = AstuceService.List();

        while (set.next()) {

            Astuce a = new Astuce(set.getInt("id"), set.getString("titre"), set.getString("theme"), set.getString("description"));

            data.add(a);

        }

        id.setCellValueFactory(
                new PropertyValueFactory<Astuce, Integer>("id")
        );

        titre.setCellValueFactory(
                new PropertyValueFactory<Astuce, String>("titre")
        );

        theme.setCellValueFactory(
                new PropertyValueFactory<Astuce, String>("theme")
        );

        description.setCellValueFactory(
                new PropertyValueFactory<Astuce, String>("description")
        );

        FilteredList<Astuce> filteredData = new FilteredList<>(data, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(astuce -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

//                if (astuce.getTitre().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches first name.
//                } else if (astuce.getTheme().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches last name.
//                }
                return false; // Does not match.
            });
        });
        TabAstuce.setItems(filteredData);

    }

}
