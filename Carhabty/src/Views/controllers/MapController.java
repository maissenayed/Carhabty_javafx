/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import Services.UserServices;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javax.annotation.PostConstruct;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author GARCII
 */
@FXMLController(value = "/Views/fxml/Map.fxml")
public class MapController implements MapComponentInitializedListener, DirectionsServiceCallback {

    @FXML
    private GoogleMapView mapView;

    protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;

    private GoogleMap map;

    private GeocodingService geocodingService;

    protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();

    @FXML
    protected TextField fromTextField;

    @FXML
    protected TextField toTextField;

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

    private Marker marker;

    @Override
    public void mapInitialized() {

        geocodingService = new GeocodingService();
        MapOptions mapOptions = new MapOptions();

        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();
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
                new LatLong(36.899489, 10.189467))
                .visible(Boolean.TRUE)
                .title("My Marker");

        Marker marker = new Marker(markerOptions);

        map.addMarker(marker);

        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>Vous êtes ici</h2>"
        );

        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        fredWilkeInfoWindow.open(map, marker);

    }

    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {

    }

    @PostConstruct
    public void init() {

        toTextField.setVisible(false);
        fromTextField.setVisible(false);
        // UserServices userService = new UserServices();

        lavage.selectedProperty().addListener((obs, oldVal, newVal) -> {

            if (lavage.isSelected()) {

                geocodingService.geocode("cité avicenne", (GeocodingResult[] results, GeocoderStatus status) -> {

                    LatLong latLong = null;

                    if (status == GeocoderStatus.ZERO_RESULTS) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                        alert.show();
                        return;
                    } else if (results.length > 1) {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                        alert.show();
                        latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                    } else {
                        latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                    }

                    //marker
                    MarkerOptions markerOptions = new MarkerOptions();

                    markerOptions.position(
                            latLong)
                            .visible(Boolean.TRUE)
                            .title("My Marker");

                    marker = new Marker(markerOptions);
                    List ma = new ArrayList();
                    ma.add(marker);
                    map.addMarkers(ma);

                    InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                    infoWindowOptions.content("<h2>Yours car</h2>"
                    );

                    InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
                    fredWilkeInfoWindow.open(map, marker);

                    //  map.setCenter(latLong);
                });

            } else {
                map.removeMarker(marker);
            }

        });

        autoecole.selectedProperty().addListener((obs, oldVal, newVal) -> {

            if (autoecole.isSelected()) {

                geocodingService.geocode("tunis", (GeocodingResult[] results, GeocoderStatus status) -> {

                    LatLong latLong = null;

                    if (status == GeocoderStatus.ZERO_RESULTS) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                        alert.show();
                        return;
                    } else if (results.length > 1) {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                        alert.show();
                        latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                    } else {
                        latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                    }

                    //marker
                    MarkerOptions markerOptions = new MarkerOptions();

                    markerOptions.position(
                            latLong)
                            .visible(Boolean.TRUE)
                            .title("My Marker");

                    marker = new Marker(markerOptions);
                    List ma = new ArrayList();
                    ma.add(marker);
                    map.addMarkers(ma);

                    InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                    infoWindowOptions.content("<h2>Lavage Totale</h2>"
                    );

                    InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
                    fredWilkeInfoWindow.open(map, marker);

                    //  map.setCenter(latLong);
                });

            } else {
                map.removeMarker(marker);
            }

        });

        mapView.addMapInializedListener(this);
        toTextField.setText("soussa");
        fromTextField.setText("tunis");

        to.bindBidirectional(toTextField.textProperty());
        from.bindBidirectional(fromTextField.textProperty());

        valider.setOnAction(e -> {

            DirectionsRequest request = new DirectionsRequest(from.get(), to.get(), TravelModes.DRIVING);
            directionsService.getRoute(request, this, new DirectionsRenderer(true, mapView.getMap(), directionsPane));

        });

    }

}
