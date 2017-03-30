/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsRenderer;
import com.lynden.gmapsfx.service.directions.DirectionsRequest;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.directions.TravelModes;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import io.datafx.controller.FXMLController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import tray.notification.NotificationType;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

/**
 *
 * @author GARCII
 */
@FXMLController(value = "/Views/fxml/map.fxml", title = "")
public class MapController implements Initializable, MapComponentInitializedListener,DirectionsServiceCallback {
    
    @FXML
    private GoogleMapView mapView;
    /*
    @FXML
    private TextField addressTextField;
    */
     protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;
    
    private GoogleMap map;
    
    private GeocodingService geocodingService;

    private StringProperty address = new SimpleStringProperty();
    
    private StringProperty from = new SimpleStringProperty();
   
    private StringProperty to = new SimpleStringProperty();
  
    
    @FXML
    private CheckBox lavage;

    @FXML
    private JFXCheckBox autoecole;

    @FXML
    private JFXCheckBox vendeur;

    @FXML
    private JFXCheckBox mecanicien;

      @FXML
    private JFXButton valider;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        
        
        valider.setOnAction(e -> handleOptions(lavage,autoecole,vendeur,mecanicien));
               
        
        
    
        
        
        /*
        to.bindBidirectional(toTextField.textProperty());
        from.bindBidirectional(fromTextField.textProperty());
        */
        
        mapView.addMapInializedListener(this);
       // address.bind(addressTextField.textProperty());
    }    
    
    
     @Override
    public void mapInitialized() {
        
        
        
        geocodingService = new GeocodingService();
        MapOptions mapOptions = new MapOptions();
        
        mapOptions.center(new LatLong(47.6097, -122.3331))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        map = mapView.createMap(mapOptions);
        
     MarkerOptions markerOptions = new MarkerOptions();

    markerOptions.position( 
                 new LatLong(47.6, -122.3) )
                .visible(Boolean.TRUE)
                .title("My Marker");

    Marker marker = new Marker( markerOptions );

    map.addMarker(marker);
    
               InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>Fred Wilkie</h2>"
                                + "Current Location: Safeway<br>"
                                + "ETA: 45 minutes" );

        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        fredWilkeInfoWindow.open(map, marker);
    
   
    
    
    
    
    }
    
    
    @FXML
    public void RechercheAdresse(ActionEvent event) {
        
        
        DirectionsRequest request = new DirectionsRequest(from.get(), to.get(), TravelModes.DRIVING);
        directionsService.getRoute(request, this, new DirectionsRenderer(true, mapView.getMap(), directionsPane));
        
       
        
     if (directionsService!= null){
          MapOptions options = new MapOptions();

        options.center(new LatLong(47.606189, -122.335842))
                .zoomControl(true)
                .zoom(12)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        
        GoogleMap map = mapView.createMap(options);
        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();
         DirectionsRequest request1 = new DirectionsRequest(from.get(), to.get(), TravelModes.DRIVING);
        directionsService.getRoute(request1, this, new DirectionsRenderer(true, mapView.getMap(), directionsPane));
        
     }
        
        
        geocodingService.geocode(address.get(), (GeocodingResult[] results, GeocoderStatus status) -> {
            
            LatLong latLong = null;
            
            if( status == GeocoderStatus.ZERO_RESULTS) {
                TrayNotification tray = new TrayNotification("Warning", "Résultat introuvable",NotificationType.ERROR);
                tray.showAndWait();
                return;
            } else if( results.length > 1 ) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            }
            
            map.setCenter(latLong);

        });
    }

    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
       
    }

    private void handleOptions(CheckBox lavage, JFXCheckBox autoecole1, JFXCheckBox vendeur1, JFXCheckBox mecanicien1) {
        
            
     
        
        if(lavage.isSelected()){
            
            
            
        }
        else if(autoecole.isSelected())
            System.out.println("autoecole");
        else if(vendeur.isSelected())
            System.out.println("vendeur");
        else if(mecanicien.isSelected())
            System.out.println("mecan");
        
        
    }
}