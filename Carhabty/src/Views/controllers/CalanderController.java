
package Views.controllers;
import Services.CalendarEventService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Entities.CalendarEvent;
import Entities.Voiture;
import Functions.CurrentVoiture;
import com.jfoenix.validation.RequiredFieldValidator;
import io.datafx.controller.FXMLController;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;



/**
 * FXML Controller class
 *
 * @author Maissen
 */
@FXMLController(value = "/Views/fxml/Calander.fxml", title = "Afficher offre - Carhabty")
public class CalanderController implements Initializable {

    @FXML
    private JFXButton insertEvent;
    @FXML
    private JFXDatePicker StartDate;
    @FXML
    private JFXDatePicker EndDate;
    @FXML
    private JFXTextField title;
    @FXML
    private JFXCheckBox allDay;
    
    public JFXTextField voitureid ;
    
    public Voiture val ;
      
  
     
    
    
    ObservableList<CalendarEvent> List= FXCollections.observableArrayList();
    @FXML
    private TableView<CalendarEvent> calview;
    @FXML
    private TableColumn titlecal;
    @FXML
    private TableColumn startcal;
    @FXML
    private TableColumn  Endcal;
    @FXML
    private TableColumn allday;
    @FXML
    private JFXButton updateEvent;
    @FXML
    private JFXButton deleteEvent;
    @FXML
    private JFXTextField id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        voitureid.setText(CurrentVoiture.Currento.getMarque());
        System.out.println("yo"+CurrentVoiture.Currento.getMarque());
        
        LoadDataFromDATAbase();
        //form validateur
        RequiredFieldValidator valid =new RequiredFieldValidator();
        title.getValidators().add(valid);
        valid.setMessage("can't be empty");
        title.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    title.validate();
                    System.out.println("dude");            
                }
            }
        });
        
        calview.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                 DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
                 Date startDate = null;
                 Date endDate = null;
   
               CalendarEvent cal = calview.getItems().get(calview.getSelectionModel().getSelectedIndex());
                title.setText(cal.getTitle());
                 try {
                     
                     startDate = df.parse(cal.getStartDate());
                     endDate = df.parse(cal.getEndDate());
                     String newDateString = df.format(startDate);
                     System.out.println(newDateString);
                     } catch (ParseException e) {
                      e.printStackTrace();
                           }
                StartDate.setValue(fromDate(startDate));
                EndDate.setValue(fromDate(endDate));
                id.setText(String.valueOf(cal.getId()));
                           
            }
        });
     
    }    

    @FXML
    private void onInsert(ActionEvent event)throws SQLException, IOException  {
        if (title.getText().isEmpty() || allDay.getText().isEmpty() ||StartDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).isEmpty()
                || EndDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Une erreur s'est produite lors de l'ajout.");
            alert.setContentText("Vous devez remplir tous les champs!");
            alert.showAndWait();        
            return;
        }
        
        
        
        
        
       
      String Title = title.getText();
      boolean AllDay= allDay.isSelected();
      String dateS = StartDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      String dateN = EndDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      CalendarEvent cal=new CalendarEvent(); 
      cal.setTitle(Title);
        if (AllDay) {
             cal.setAllDay("true");
        }else cal.setAllDay("false");
        cal.setStartDate(dateS);
        cal.setEndDate(dateN);
        cal.setVoiture(CurrentVoiture.Currento);
        List.add(cal);
        CalendarEventService crud =new CalendarEventService();
        System.out.println(cal);  
        crud.addCalander(cal);
        TrayNotification tray = new TrayNotification("Félicitation", "Votre suivie a été insere avec succées",NotificationType.SUCCESS);
        tray.showAndWait();
      LoadDataFromDATAbase();   
    }
    public static LocalDate fromDate(Date date) {
    Instant instant = Instant.ofEpochMilli(date.getTime());
    return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        .toLocalDate();
  }

    @FXML
    private void onupdate(ActionEvent event) {
           if (title.getText().isEmpty() || allDay.getText().isEmpty() ||StartDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).isEmpty()
                || EndDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Une erreur s'est produite lors de l'modification.");
            alert.setContentText("Vous devez revoir tous les champs!");
            alert.showAndWait();        
            return;
        }
        
      String Title = title.getText();
      boolean AllDay= allDay.isSelected();
      String dateS = StartDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      String dateN = EndDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      int Id =Integer.valueOf(id.getText());
        CalendarEvent cal=new CalendarEvent();
        cal.setTitle(Title);
        if (AllDay) {
           cal.setAllDay("true");
        }else cal.setAllDay("false");  
        cal.setStartDate(dateS);
        cal.setEndDate(dateN);
        cal.setId(Id);
        cal.setVoiture(CurrentVoiture.Currento);
        CalendarEventService crud =new CalendarEventService();
        System.out.println(cal);
        crud.UpdateTitle(cal);
        TrayNotification tray = new TrayNotification("Félicitation", "Votre information a été modifier avec succées",NotificationType.SUCCESS);
        tray.showAndWait(); 
        LoadDataFromDATAbase();
       title.clear();
       
        
        
    }

    @FXML
    private void ondelete(ActionEvent event) {
          CalendarEventService crud =new CalendarEventService();
           if (title.getText().isEmpty() || allDay.getText().isEmpty() ||StartDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).isEmpty()
                || EndDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Une erreur s'est produite lors de l'modification.");
            alert.setContentText("Vous devez revoir tous les champs!");
            alert.showAndWait();        
            return;
        }
        
 
      int Id =Integer.valueOf(id.getText());
        CalendarEvent cal=new CalendarEvent();
       
        cal.setId(Id);
        crud.DeleteCalander(cal.getId());
        LoadDataFromDATAbase();
        TrayNotification tray = new TrayNotification("Félicitation", "Votre information a été supprimer avec succées",NotificationType.SUCCESS);
        tray.showAndWait(); 
    }
    
    
    public void LoadDataFromDATAbase(){
    
        
    
    
        CalendarEventService crud =new CalendarEventService();
        //get to do list 
      
        List=crud.ShowAllCalanders(CurrentVoiture.Currento.getId()); 
        titlecal.setCellValueFactory(new PropertyValueFactory<CalendarEvent,String>("title"));
        startcal.setCellValueFactory(new PropertyValueFactory<CalendarEvent,String>("startDate"));
        Endcal.setCellValueFactory(new PropertyValueFactory<CalendarEvent,String>("endDate"));
        allday.setCellValueFactory(new PropertyValueFactory<CalendarEvent,String>("allDay"));
        calview.setItems(List);
    
    
    
    
    
    
    }

    
         

   
}
