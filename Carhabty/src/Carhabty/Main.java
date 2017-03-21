/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Carhabty;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    Scene scene = new Scene(new Group());
    stage.setTitle("Toggle Button Sample");
    stage.setWidth(250);
    stage.setHeight(180);

    final ToggleGroup group = new ToggleGroup();

    group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
      public void changed(ObservableValue<? extends Toggle> ov, Toggle toggle,Toggle new_toggle) {
          System.out.println((Color) group.getSelectedToggle().getUserData());
      }
    });

    ToggleButton tb1 = new ToggleButton("A");
    tb1.setToggleGroup(group);
    tb1.setUserData(Color.LIGHTGREEN);
    tb1.setSelected(true);


    ToggleButton tb2 = new ToggleButton("B");
    tb2.setToggleGroup(group);
    tb2.setUserData(Color.LIGHTBLUE);
    ToggleButton tb3 = new ToggleButton("C");
    tb3.setToggleGroup(group);
    tb3.setUserData(Color.SALMON);
    

    HBox hbox = new HBox();

    hbox.getChildren().add(tb1);
    hbox.getChildren().add(tb2);
    hbox.getChildren().add(tb3);

    ((Group) scene.getRoot()).getChildren().add(hbox);
    stage.setScene(scene);
    stage.show();
  }
}