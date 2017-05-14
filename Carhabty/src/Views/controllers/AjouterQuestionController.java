/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import BackBone.ClientToServer.ImageSender;
import Entities.Media;
import Entities.Question;
import Entities.Reponse;
import Services.MediaService;
import Services.QuestionService;
import Services.ReponseService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import flowData.QuestionReponseValidation;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.annotation.PostConstruct;
import tray.animations.AnimationType;
import tray.notification.NotificationType;

/**
 *
 * @author Azgard
 */
@FXMLController(value = "/Views/fxml/ModifierQuestion.fxml", title = "AjouterQuestion")
public class AjouterQuestionController {
    
            
        
    private final String questionImgProp="http://localhost/Carhabty_web/Carhabty/web/images/question/";
    private final String uploadServiceUrl="http://localhost/Carhabty_web/Carhabty/web/app_dev.php/quiz/uploadImg";
    private final String dest="question";

    @FXMLViewFlowContext
    private ViewFlowContext context;

    private FlowHandler contentFlowHandler;

    @FXML
    private StackPane root;

    @FXML
    private JFXButton back;

    @FXML
    private JFXButton upload;

    @FXML
    private ImageView imageView;

    @FXML
    private Label name;

    @FXML
    private VBox repVbox;

    @FXML
    private JFXButton addRep;

    @FXML
    private JFXTextField questcon;

    @FXML
    private JFXComboBox<String> questcat;

    @FXML
    private JFXTextArea questexp;

    ObservableList<Reponse> reponseList;

    @FXML
    private HBox controlh;

    private ToggleGroup group = new ToggleGroup();

    private File imageFile;

    private Question requested;

    @PostConstruct
    public void init() throws FlowException, VetoException {

        requested = new Question();
        JFXButton modifbtn = new JFXButton("Ajouter");
        modifbtn.setPrefHeight(25);
        modifbtn.getStyleClass().add("updateBtn");
        reponseList = FXCollections.observableArrayList();
        try {
            writeQuestion(requested);
        } catch (IOException ex) {
            Logger.getLogger(AjouterQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        modifbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Object[] abc=reponseList.toArray();
                requested.setQuestionContent(questcon.getText());
                requested.setExplanation(questexp.getText());
                requested.setQuestionCategory(questcat.getSelectionModel().getSelectedItem().toString());
                 QuestionReponseValidation validator=new QuestionReponseValidation(requested,abc,imageFile);
                if(validator.isValid())
                { 
                QuestionService questionService = new QuestionService();

                System.out.println("yooooooo");

                if (imageFile != null) {
                    BackBone.ClientToServer.ImageSender sender = new ImageSender();
                    ImageSender s = new ImageSender();
                    String url = "";
                    String fileDest = "";
                    url = uploadServiceUrl;
                    fileDest = dest;

                    String response = s.executeMultiPartRequest(url, imageFile, imageFile.getName(), fileDest);
                    requested.setMedia(new Media());
                    requested.getMedia().setTypeMedia("img");
                    requested.getMedia().setDescription(imageFile.getName());
                    requested.getMedia().setImageName(imageFile.getName());

                    MediaService mediaService = new MediaService();
                    int medId = mediaService.add(requested.getMedia());
                    requested.getMedia().setId(medId);

                }

                int idq = questionService.add(requested);
                requested.setId(idq);
                ReponseService repService = new ReponseService();
                reponseList.forEach(e -> {
                    e.setQuestion(requested);
                    e.setTypeReponse(true);
                    repService.add(e);
                    System.out.println("add: " + e);

                });
                try {
                    writeQuestion(requested);
                } catch (IOException ex) {
                    Logger.getLogger(AjouterQuestionController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                                    tray.notification.TrayNotification tr = new tray.notification.TrayNotification();
                    tr.setTitle("Carhabty");
                    tr.setMessage("ajouterm avec succés");
                    tr.setNotificationType(NotificationType.SUCCESS);
                    tr.setAnimationType(AnimationType.SLIDE);
                    tr.showAndDismiss(javafx.util.Duration.seconds(3));

            }
            
                else {System.out.println("nooooo111");
                                    System.out.println("noooo111");
                    tray.notification.TrayNotification tr = new tray.notification.TrayNotification();
                    tr.setTitle("Carhabty");
                    tr.setNotificationType(NotificationType.ERROR);
                    tr.setAnimationType(AnimationType.SLIDE);
                    tr.showAndDismiss(javafx.util.Duration.seconds(3));
                }
            }

        });

        controlh.getChildren().add(modifbtn);

        try {
            writeQuestion(requested);
        } catch (IOException ex) {
            Logger.getLogger(ModifierQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        questcat.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                requested.setQuestionCategory(newValue);

            }
        });



    


        group.selectedToggleProperty().addListener(
                (ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) -> {
                    if (group.getSelectedToggle() != null) {
                        reponseList.forEach(e -> {
                            e.setOk(false);
                        });
                        reponseList.get(getIndex(group.getSelectedToggle().getUserData().toString())).setOk(true);
                        System.out.println(reponseList.get(getIndex(group.getSelectedToggle().getUserData().toString())).isOk());
                    }
                });

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                contentFlowHandler = (FlowHandler) context.getRegisteredObject("ContentFlowHandler");
                try {
                    contentFlowHandler.navigateTo(CodeController.class);
                    // bindNodeToController(treeTableViewRemove, ModifierQuestionController.class, contentFlow, contentFlowHandler);
                } catch (VetoException ex) {
                    Logger.getLogger(ModifierQuestionController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FlowException ex) {
                    Logger.getLogger(ModifierQuestionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        addRep.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Reponse r = new Reponse();
                reponseList.add(r);
                if (reponseList.size() == 4) {
                    addRep.disableProperty().setValue(Boolean.TRUE);
                }

                repVbox.getChildren().clear();
                setReponses(reponseList);
                System.out.println("- wasAdded");
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        upload.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String fileName;
                FileChooser chooser = new FileChooser();
                chooser.setTitle("Open File");
                imageFile = chooser.showOpenDialog(new Stage());
                String filePath = null;

                filePath = imageFile.toURI().toString();
                fileName = imageFile.getName();
                if (filePath != null) {
                    imageView.setImage(new Image(filePath));
                    name.setText(fileName);

                }

            }
        });

    }

    private void createReponseComp(Reponse item, int index, int size) {
        HBox hElem = new HBox();
        hElem.setId("h" + index);
        JFXTextField repContent = new JFXTextField();
        repContent.setText(item.getReponseContent());

        JFXRadioButton rdbtn = new JFXRadioButton();

        rdbtn.setUserData("r" + index);
        repContent.setId("f" + index);
        rdbtn.setToggleGroup(group);
        if (item.isOk()) {
            rdbtn.setSelected(true);
        }

        repContent.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                StringProperty textProperty = (StringProperty) observable;
                JFXTextField textField = (JFXTextField) textProperty.getBean();
                int elem = getIndex(textField.getId());
                reponseList.get(elem).setReponseContent(newValue);
                System.out.println(newValue);

            } catch (Exception e) {
                //e.printStackTrace();
            }
        });

        Image imageOk = new Image("/Image/delete.png");
        ImageView iv = new ImageView(imageOk);
        iv.setFitHeight(25);
        iv.setFitWidth(25);
        JFXButton removeBtn = new JFXButton("", iv);
        removeBtn.setTranslateY(7);
        removeBtn.setOnAction(myHandler);
        rdbtn.setTranslateY(15);
        removeBtn.setId(Integer.toString(index));
        if (size == 2) {
            removeBtn.disableProperty().setValue(Boolean.TRUE);
        }
        hElem.getChildren().add(repContent);
        hElem.getChildren().add(rdbtn);
        hElem.getChildren().add(removeBtn);
        repVbox.getChildren().add(hElem);
        if (size < 4) {
            addRep.disableProperty().setValue(Boolean.FALSE);
        }
    }

    public void setReponses(ObservableList<Reponse> reponseList) {
        repVbox.getChildren().clear();
        int index = 0;
        for (Reponse item : reponseList) {
            createReponseComp(item, index, reponseList.size());
            index++;
        }
    }

    final EventHandler<ActionEvent> myHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(final ActionEvent event) {
            JFXButton temp = (JFXButton) (event.getSource());
            System.out.println(temp.getId());
            reponseList.remove(Integer.parseInt(temp.getId()));
            repVbox.getChildren().clear();
            setReponses(reponseList);

        }
    };

    private int getIndex(String str) {
        char temp = str.charAt(1);
        return Character.getNumericValue(temp);
    }

    public void writeQuestion(Question q) throws IOException {
        imageView.setImage(new Image("/Image/no-image.jpg"));
        questcat.getSelectionModel().select("Priorié");
        questcon.setText("");
        questexp.setText("");
        Reponse r1 = new Reponse();
        Reponse r2 = new Reponse();
        reponseList= FXCollections.observableArrayList();
        reponseList.add(r1);
        reponseList.add(r2);
        setReponses(reponseList);

    }

}
