/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import DataBase.Session;
import Services.ReponseService;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.util.VetoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javax.annotation.PostConstruct;
import tray.animations.AnimationType;
import tray.notification.NotificationType;

/**
 *
 * @author Azgard
 */
@FXMLController(value = "/Views/fxml/StatQuiz.fxml", title = "Quiz")
public class StatQuizController {

    @FXML
    private AnchorPane root;

    @PostConstruct
    public void init() throws FlowException, VetoException, SQLException {
        ReponseService rs=new ReponseService();
        int tp=rs.findLastQuizNumber(Session.actualUser);
        
        if(tp>0)
        {
        
        
        
        Map<Integer, Integer> statis = new ReponseService().getStatistique(Session.actualUser);

        ListIterator<Entry<Integer, Integer>> iter = new ArrayList<>(statis.entrySet()).listIterator(statis.size());
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        // int bound=statis.entrySet().
        xAxis.setLabel("N°Quiz");
        yAxis.setLabel("Bonne Réponse");

        final LineChart<Number, Number> lineChart
                = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle("Progréssion");
        XYChart.Series series = new XYChart.Series();
        series.setName("Mes résultat");
        Map<Integer, Integer> rstatis = new LinkedHashMap<Integer, Integer>();
        while (iter.hasPrevious()) {
            Entry<Integer, Integer> itr = iter.previous();
            series.getData().add(new XYChart.Data(itr.getKey(), itr.getValue()));
            rstatis.put(itr.getKey(), itr.getValue());
        }
        
        int bound = rstatis.entrySet().iterator().next().getKey() - 1;
        xAxis.setAutoRanging(false);
        xAxis.setUpperBound(bound + 10);
        yAxis.setUpperBound(bound + 10);

        xAxis.setLowerBound(bound);
        yAxis.setLowerBound(bound);
        xAxis.setTickUnit(1);
        yAxis.setTickUnit(1);
        yAxis.setAutoRanging(false);
        xAxis.minWidth(Double.MAX_VALUE);

        lineChart.getData().add(series);
        lineChart.setPrefWidth(900);
        lineChart.setPrefHeight(250);
        Pane pnLine = new Pane();
        pnLine.getChildren().add(lineChart);
        pnLine.getStyleClass().add("space1");

       

        //***********************************
        final CategoryAxis xbAxis = new CategoryAxis();
        final NumberAxis ybAxis = new NumberAxis();
        final BarChart<String, Number> bc
                = new BarChart<>(xbAxis, ybAxis);
        bc.setTitle("Résultat par type de question");
        xAxis.setLabel("Quiz");
        yAxis.setLabel("nombre de réponse");

        Map<String, ArrayList<Integer>> mp = new ReponseService().getAllInfoReponse(Session.actualUser);
        XYChart.Series allRep = new XYChart.Series();
        XYChart.Series correSeries = new XYChart.Series();
        XYChart.Series wrongSeries = new XYChart.Series();
        allRep.setName("Toutes les réponses");
        correSeries.setName("Bonne Réponse");
        wrongSeries.setName("Mauvaise Réponse");

        for (Map.Entry<String, ArrayList<Integer>> entry : mp.entrySet()) {
            allRep.getData().add(new XYChart.Data(entry.getKey(), entry.getValue().get(0)));
            correSeries.getData().add(new XYChart.Data(entry.getKey(), entry.getValue().get(1)));
            wrongSeries.getData().add(new XYChart.Data(entry.getKey(), entry.getValue().get(0) - entry.getValue().get(1)));
        }

        bc.getData().addAll(correSeries, allRep, wrongSeries);

        bc.setPrefWidth(900);
        bc.setPrefHeight(250);

        Pane pnBar = new Pane();
        pnBar.getChildren().add(bc);
        pnBar.getStyleClass().add("space1");

        VBox chartPage = new VBox();
        chartPage.setSpacing(5);
        chartPage.getChildren().add(pnLine);
        chartPage.getChildren().add(pnBar);
        chartPage.setLayoutX(200);
        chartPage.setLayoutY(20);
        
        
        
         root.getChildren().add(chartPage);
        }
        
        
        else
        {	tray.notification.TrayNotification tr = new tray.notification.TrayNotification();
            tr.setTitle("Carhabty");
            tr.setMessage("Vous devez jouer");
            tr.setNotificationType(NotificationType.INFORMATION);
            tr.setAnimationType(AnimationType.SLIDE);
            tr.showAndDismiss(javafx.util.Duration.seconds(3));}


    }
}
