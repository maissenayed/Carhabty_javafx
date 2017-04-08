/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import io.datafx.controller.FXMLController;
import java.util.Date;
import java.util.GregorianCalendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javax.annotation.PostConstruct;

/**
 *
 * @author GARCII
 */
@FXMLController(value = "/Views/fxml/StatistiqueOffre.fxml", title = "Statistique des offres - Carhabty")
public class StatistiqueController {

    @FXML
    private LineChart LineChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @PostConstruct
    public void init() {

        XYChart.Series serie = new XYChart.Series();

        java.util.Date t = new java.util.Date();
        java.util.Date now = new java.sql.Date(t.getTime());

        serie.getData().add(new XYChart.Data("gg", now));
        LineChart.getData().addAll(serie);

        
        
     
    }

}
