package Carhabty;

import Services.CouponServices;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
/*
 
public class LineChartSample extends Application {
 
    @Override public void start(Stage stage) {
        try{
          CouponServices coupon = new CouponServices();
          ResultSet resultat = coupon.StatistiqueVente();
         while (resultat.next()) {

              int nb = resultat.getInt(1);
              Date date =  resultat.getDate(2);
              System.out.println(date);
              
              
                 
        stage.setTitle("Line Chart Sample");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
       
        //creating the chart
        final LineChart lineChart =  new LineChart(xAxis,yAxis);
                
        lineChart.setTitle("Statistique des Ventes");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        //populating the series with data
       series.getData().add(new XYChart.Data(nb, date));
       series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));
              
              
              
              
                
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
        
        stage.setScene(scene);
        stage.show();
       
              
              
              
              

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
     
      
    }
 
    public static void main(String[] args) {
        launch(args);
    }

}*/