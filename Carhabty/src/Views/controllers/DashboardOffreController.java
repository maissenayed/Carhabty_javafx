/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import BackBone.ClientToServer.ImageSender;
import DataBase.Session;
import Entities.Offre;
import Entities.User;
import Services.OffreServices;
import Services.UserServices;

import com.jfoenix.controls.*;
import io.datafx.controller.FXMLController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.annotation.PostConstruct;
import tray.animations.AnimationType;
import tray.notification.NotificationType;

/**
 *
 * @author GARCII
 */
@FXMLController(value = "/Views/fxml/DashboardOffre.fxml", title = "")

public class DashboardOffreController implements Initializable {

    @FXML
    private TableView<Offre> OffreTable;

    @FXML
    private TableColumn idColumn;

    @FXML
    private TableColumn nomColumn;

    @FXML
    private TableColumn descriptionColumn;

    @FXML
    private TableColumn prixColumn;

    @FXML
    private TableColumn reductionColumn;

    @FXML
    private TableColumn dateExpColumn;

    @FXML
    private JFXTextField search;

    @FXML
    private TextArea description;

    @FXML
    private TextField nom;

    @FXML
    private TextField prix;

    @FXML
    private TextField reduction;

    @FXML
    private TextField date;

    @FXML
    private ImageView image;

    private int id_offre;

    private Offre o;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            FillTable();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        OffreServices OffreService = new OffreServices();
        search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                List<Offre> liste = null;
                try {

                    if (!search.getText().isEmpty()) {
                        liste = OffreService.SearchOffre(search.getText());
                        ObservableList<Offre> data = FXCollections.observableArrayList(liste);
                        OffreTable.setItems(data);
                    } else {

                        FillTable();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }

        });

            OffreTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            o = OffreTable.getSelectionModel().getSelectedItem();
            id_offre = o.getId();
            nom.setText(o.getNomOffre() + "");
            description.setText(o.getDescriptionOffre() + "");
            prix.setText(Float.toString(o.getPrix()));
            reduction.setText(Integer.toString(o.getReduction()));
            date.setText(o.getDateExp().toString());
            // System.out.println(id_offre);
            // OffreService.findById(id_offre).getImage();
            // System.out.println(OffreService.findById(id_offre).getImage());
            System.out.println("Image/"+OffreService.findById(id_offre).getImage());
           
            
               image.setImage(new Image("http://localhost/Carhabtyy/web/images/offres/" + OffreService.findById(id_offre).getImage()));
            //image.setImage(new Image("Image/"+OffreService.findById(id_offre).getImage()));

        });

    }

    private void FillTable() throws SQLException {

        OffreServices OffreService = new OffreServices();
        ObservableList<Offre> data = FXCollections.observableArrayList((Offre) null);
        ResultSet set;

        set = OffreService.List();

        while (set.next()) {

            Offre o = new Offre(set.getInt("id"), set.getString("nom_offre"), set.getString("description_offre"),
                    set.getFloat("prix"), set.getInt("taux_reduction"), set.getDate("date_expiration_offre"));
            data.add(o);

        }

        idColumn.setCellValueFactory(
                new PropertyValueFactory<Offre, Integer>("id")
        );

        nomColumn.setCellValueFactory(
                new PropertyValueFactory<Offre, String>("nomOffre")
        );

        descriptionColumn.setCellValueFactory(
                new PropertyValueFactory<Offre, String>("descriptionOffre")
        );

        prixColumn.setCellValueFactory(
                new PropertyValueFactory<Offre, Float>("prix")
        );

        reductionColumn.setCellValueFactory(
                new PropertyValueFactory<Offre, Integer>("reduction")
        );

        dateExpColumn.setCellValueFactory(
                new PropertyValueFactory<Offre, Date>("dateExp")
        );

        OffreTable.setItems(data);

    }

    @FXML
    void changePhoto(MouseEvent event) {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File image = chooser.showOpenDialog(new Stage());
    
        
         ImageSender is=new ImageSender();
         is.executeMultiPartRequest("http://localhost/Carhabtyy/web/app_dev.php/quiz/uploadImg", image, image.getName(),"offres");
         //Session.actualUser.setImage(image.getName());
         OffreServices offreService = new OffreServices();
         Offre o = new Offre();
         o.setImage(image.getName());
         o.setId(id_offre);
         offreService.updatePhoto(o);
         this.image.setImage(new Image("http://localhost/Carhabtyy/web/images/offres/" + o.getImage()));
        
        
        
        /*
        String filePath = image.getPath();
        String fileName = image.getName();

        try {

            InputStream inStream = null;
            OutputStream outStream = null;

            File ImageUplaoded = new File("src/Image/" + fileName);

            inStream = new FileInputStream(filePath);
            outStream = new FileOutputStream(ImageUplaoded);
            byte[] buffer = new byte[1024];
            int length;

            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
            inStream.close();
            outStream.close();
            System.out.println("File is copied successful!");

            OffreServices offreService = new OffreServices();
            offreService.findById(id_offre);
            Offre oi = new Offre();
            oi.setId(id_offre);
            oi.setImage(fileName);
            offreService.updatePhoto(oi);
            this.image.setImage(new Image("Image/" + offreService.findById(id_offre).getImage()));

        } catch (IOException e) {
            e.printStackTrace();
        }
*/
    }

    @FXML
    void DeleteOffre(ActionEvent event) throws SQLException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Fenetre de confirmation");
        alert.setHeaderText("Suppression d'une offre");
        alert.setContentText("Etes vous sures de vouloir supprimer cette offre?");
        ButtonType buttonTypeOne = new ButtonType("Oui");
        ButtonType buttonTypeTwo = new ButtonType("Non");
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            OffreServices offreService = new OffreServices();

            offreService.remove(o);
            Alert alertInfo = new Alert(Alert.AlertType.CONFIRMATION);
            alertInfo.setTitle("Information");
            alertInfo.setHeaderText("L'offre a été supprimé avec succès.");
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

    @FXML
    void updateOffre(ActionEvent event) throws SQLException {

        Offre o = new Offre();

              if(reduction.getText().matches("[0-9]+") &&
                !nom.getText().isEmpty() && 
                !prix.getText().isEmpty() && 
                 prix.getText().matches("[0-9]+") && 
                !description.getText().isEmpty()&&
                !prix.getText().isEmpty() &&
                !reduction.getText().isEmpty()){
            
            
        o.setNomOffre(nom.getText());
        o.setDescriptionOffre(description.getText());
        o.setPrix(Float.parseFloat(prix.getText()));
        o.setReduction(Integer.parseInt(reduction.getText()));
        o.setDateExp(Date.valueOf(date.getText()));
        o.setId(id_offre);
        OffreServices offreService = new OffreServices();
        offreService.update(o);
        FillTable();
    }else{

            tray.notification.TrayNotification tr = new tray.notification.TrayNotification();
            tr.setTitle("Carhabty");
            tr.setMessage("Erreur de lors du Modification");
            tr.setNotificationType(NotificationType.ERROR);
            tr.setAnimationType(AnimationType.SLIDE);
            tr.showAndDismiss(javafx.util.Duration.seconds(5));
  
}

}
}