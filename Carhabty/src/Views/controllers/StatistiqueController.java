/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import Services.CouponServices;
import io.datafx.controller.FXMLController;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map.Entry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javax.annotation.PostConstruct;

/**
 *
 * @author GARCII
 */
@FXMLController(value = "/Views/fxml/StatistiqueOffre.fxml", title = "Statistique des offres - Carhabty")
public class StatistiqueController {

    @FXML
    private AnchorPane anchorPane;

    @PostConstruct
    public void init() throws SQLException {

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

        xAxis.setLabel("Mois");
        xAxis.setUpperBound(12);
        xAxis.setLowerBound(1);
        xAxis.setTickUnit(1);

        yAxis.setLabel("Nombre de Coupons");
        yAxis.setTickUnit(1);
        yAxis.setMinorTickLength(1.0);
        yAxis.setMinorTickVisible(false);
        //       yAxis.setUpperBound(30);
        xAxis.setAutoRanging(false);
        yAxis.setAutoRanging(false);

        lineChart.setTitle("Statistiques des Ventes");

        XYChart.Series series = new XYChart.Series();

        CouponServices cs = new CouponServices();
        //System.out.println(cs.StatistiqueVente());

        for (Entry<Integer, Integer> entry : cs.StatistiqueVente().entrySet()) {
            System.out.println("nbcoupon " + entry.getValue());
            System.out.println("mois " + entry.getKey());

            series.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));

        }
        lineChart.getData().addAll(series);
        ((List) series.getData()).stream().forEach(e -> System.out.println(((XYChart.Data< Integer, Integer>) e).getYValue()));
      //  double i = ((XYChart.Data< Integer, Integer>) ((List) series.getData()).stream().max((e, f) -> ((XYChart.Data< Integer, Integer>) e).getYValue() - ((XYChart.Data< Integer, Integer>) f).getYValue()).get()).getYValue();
      //  System.out.println(i);
        yAxis.setUpperBound(5 + 1);
        Pane pnLine = new Pane();
        lineChart.setPrefWidth(1000);
        lineChart.setPrefHeight(500);
        pnLine.getChildren().add(lineChart);
        pnLine.getStyleClass().add("space1");
        pnLine.setLayoutX(180);
        pnLine.setLayoutY(70);

        anchorPane.getChildren().add(pnLine);

    }

}
