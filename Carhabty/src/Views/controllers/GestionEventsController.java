/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import Entities.Event;
import Entities.Offre;
import Functions.CurrentOffre;
import java.awt.event.KeyEvent;
import Functions.SendMail;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.mail.MessagingException;
import Services.EventServices;
import io.datafx.controller.FXMLController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javax.annotation.PostConstruct;
import tray.animations.AnimationType;
import tray.notification.NotificationType;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
@FXMLController(value = "/Views/fxml/GestionEvents.fxml", title = "Material Design Example")
public class GestionEventsController {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXListView<Event> ListEvent;

  

    @FXML
    private JFXButton BtnSupp;

    @FXML
    private JFXButton BtnInv;

    @FXML
    private JFXTextField TxtRech;

    @FXML
    private JFXTextField TxtTitre;

    @FXML
    private JFXTextField TxtAdrs;

    @FXML
    private DatePicker TxtDate;

    @FXML
    private JFXTextArea TxtDescription;

    @FXML
    private JFXButton BtnAjouter;

    @FXML
    private JFXButton BtnModifier;

    private int id;
    
    
    @FXML
    public void OnFiltrer() {
        ObservableList<Event> entries = FXCollections.observableArrayList();
        TxtRech.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldVal,
                    Object newVal) {
                search((String) oldVal, (String) newVal);
            }

            public void search(String oldVal, String newVal) {
                if (oldVal != null && (newVal.length() < oldVal.length())) {
                    ListEvent.setItems(entries);
                }
                String value = newVal;
                ObservableList<Event> subentries = FXCollections.observableArrayList();
                for (Event entry : ListEvent.getItems()) {
                    boolean match = true;
                    String filterInputText = TxtRech.getText();
                    if (!filterInputText.toUpperCase().contains(value)) {
                        match = false;
                        break;
                    }
                    if (match) {
                        subentries.add(entry);
                    }
                }
                System.out.println(subentries.toString());
                ListEvent.setItems(subentries);
            }
        });

    }

    @PostConstruct
    public void init() {

        BtnAjouter.setOnAction(c -> {

            EventServices ES = new EventServices();
            
            
            if (TxtDate.getValue().isAfter(LocalDate.now())) {
                
                
           Event e = new Event(TxtDescription.getText(), TxtTitre.getText(),  TxtAdrs.getText(), TxtDate.getValue().toString());
               
                
                ES.AjouterEvent(e);

                TxtTitre.setText(null);
                TxtDescription.setText(null);
                TxtAdrs.setText(null);
                TxtDate.setValue(LocalDate.now());

                ObservableList<Event> data = FXCollections.observableArrayList();
                data = ES.RecupererEvent();
                ListEvent.setItems(data);

                tray.notification.TrayNotification tr = new tray.notification.TrayNotification();
                tr.setTitle("Carhabty");
                tr.setMessage("Evenement Ajouté avec Succés ");
                tr.setNotificationType(NotificationType.SUCCESS);
                tr.setAnimationType(AnimationType.POPUP);
                tr.showAndDismiss(javafx.util.Duration.seconds(5));
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Attention ! ");
                alert.setHeaderText("Vérifier la date de votre evenements !! ");
                alert.showAndWait();

            }

        });

        BtnModifier.setOnAction(e -> {

         
            
            
            
            EventServices ES = new EventServices();

                Event ev = new Event();
                ev.setId(id);
                System.out.println(id);
                ev.setDescription(TxtDescription.getText());
                ev.setAdresse(TxtAdrs.getText());
                ev.setTitle(TxtTitre.getText());
                ev.setEventDate(TxtDate.getValue().toString());
           
              

            ES.Modifier(ev);
            tray.notification.TrayNotification tr = new tray.notification.TrayNotification();
            tr.setTitle("Carhabty");
            tr.setMessage("Evenement Modifié avec Succés ");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.setAnimationType(AnimationType.SLIDE);
            tr.showAndDismiss(javafx.util.Duration.seconds(5));
            
             EventServices Es = new EventServices();
        
        ListEvent.getItems().setAll(Es.RecupererEvent());
        
         ListEvent.setCellFactory(new Callback<ListView<Event>, ListCell<Event>>() {
            @Override
            public ListCell<Event> call(ListView<Event> param) {
               return new EventFactory(); }

            
        });
            
            
                   
        });

        BtnSupp.setOnAction(e -> {

            EventServices ES = new EventServices();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Cet Evenement sera supprimé ! ");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                ES.Supprimer(ListEvent.getSelectionModel().getSelectedItem().getId());
                ObservableList<Event> data = FXCollections.observableArrayList();
                data = ES.RecupererEvent();
                ListEvent.setItems(data);
                tray.notification.TrayNotification tr = new tray.notification.TrayNotification();
                tr.setTitle("Carhabty");
                tr.setMessage("Evenement Supprimé avec Succés ");
                tr.setNotificationType(NotificationType.SUCCESS);
                tr.setAnimationType(AnimationType.SLIDE);
                tr.showAndDismiss(javafx.util.Duration.seconds(5));

            }

        });

        BtnInv.setOnAction(e -> {

            try {
                new SendMail("mehdi.abidi@esprit.tn", "Bonjour vous etes invités à l'évenement "
                        + ListEvent.getSelectionModel().getSelectedItem().getTitle() + " Le "
                        + ListEvent.getSelectionModel().getSelectedItem().getEventDate() + " à "
                        + ListEvent.getSelectionModel().getSelectedItem().getAdresse());
            } catch (MessagingException ex) {
                Logger.getLogger(GestionEventsController.class.getName()).log(Level.SEVERE, null, ex);
            }

            tray.notification.TrayNotification tr = new tray.notification.TrayNotification();
            tr.setTitle("Carhabty");
            tr.setMessage("Invitation envoyée ");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.setAnimationType(AnimationType.SLIDE);
            tr.showAndDismiss(javafx.util.Duration.seconds(5));

        });

        
     
        
        
        EventServices Es = new EventServices();
        
        ListEvent.getItems().setAll(Es.RecupererEvent());
        
         ListEvent.setCellFactory(new Callback<ListView<Event>, ListCell<Event>>() {
            @Override
            public ListCell<Event> call(ListView<Event> param) {
               return new EventFactory(); }

            
        });
        
         
         
         
         
          ListEvent.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Event>() {
            @Override
            public void changed(ObservableValue<? extends Event> observable, Event oldValue, Event newValue) {
                
              Event event =   ListEvent.getSelectionModel().selectedItemProperty().get();
              
              
              id = event.getId();
              TxtAdrs.setText(event.getAdresse());
              TxtTitre.setText(event.getTitle());
          //    TxtDate.setValue(event.getEventDate());
              TxtDescription.setText(event.getDescription());         
                
            }

        });

    }

}
