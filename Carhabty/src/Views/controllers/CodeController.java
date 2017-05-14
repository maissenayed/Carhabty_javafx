/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import Entities.Question;
import Services.QuestionService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import flowData.QuestionFlowData;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javax.annotation.PostConstruct;

/**
 *
 * @author Azgard
 */
@FXMLController(value = "/Views/fxml/ListingQuestion.fxml", title = "Listing des question")
public class CodeController {

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    private JFXTreeTableView<QuestionAdapter> treeTableView;

    @FXML
    private JFXTreeTableColumn<QuestionAdapter, String> questContentCol;

    @FXML
    private JFXTreeTableColumn<QuestionAdapter, String> categorieCol;

    @FXML
    private JFXTreeTableColumn<QuestionAdapter, Integer> idQuestCol;

    @FXML
    private Label treeTableViewCount;

    @FXML
    private JFXButton treeTableViewAdd;

    @FXML
    private JFXButton treeTableViewRemove;

    @FXML
    private JFXTextField searchField;

    @PostConstruct
    public void init() throws FlowException, VetoException {

        ObservableList<QuestionAdapter> questions = FXCollections.observableArrayList();

        idQuestCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<QuestionAdapter, Integer> param) -> {
            if (idQuestCol.validateValue(param)) {
                return param.getValue().getValue().idQuest.asObject();
            } else {
                return idQuestCol.getComputedValue(param);
            }
        });

        categorieCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<QuestionAdapter, String> param) -> {
            if (categorieCol.validateValue(param)) {
                return param.getValue().getValue().categorie;
            } else {
                return categorieCol.getComputedValue(param);
            }
        });

        questContentCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<QuestionAdapter, String> param) -> {
            if (questContentCol.validateValue(param)) {
                return param.getValue().getValue().questContent;
            } else {
                return questContentCol.getComputedValue(param);
            }
        });
        //-----------------------------------------------------------------------------

        List<Question> data = (new QuestionService()).findALL();

        data.forEach(e -> {

            QuestionAdapter qa = new QuestionAdapter(e.getId(), e.getQuestionCategory(), e.getQuestionContent());
            questions.add(qa);

        });

        //-------------------------------------
        treeTableView.setRoot(new RecursiveTreeItem<QuestionAdapter>(questions, RecursiveTreeObject::getChildren));
        treeTableView.setShowRoot(false);

        treeTableViewCount.textProperty().bind(Bindings.createStringBinding(() -> "( " + treeTableView.getCurrentItemsCount() + " )", treeTableView.currentItemsCountProperty()));
       // treeTableViewAdd.disableProperty().bind(Bindings.notEqual(-1, treeTableView.getSelectionModel().selectedIndexProperty()));
        treeTableViewRemove.disableProperty().bind(Bindings.equal(-1, treeTableView.getSelectionModel().selectedIndexProperty()));

        //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
        FlowHandler contentFlowHandler = (FlowHandler) context.getRegisteredObject("ContentFlowHandler");

        treeTableViewAdd.setOnMouseClicked((MouseEvent e) -> {

            try {
                contentFlowHandler.navigateTo(AjouterQuestionController.class);
            } catch (VetoException ex) {
                Logger.getLogger(CodeController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FlowException ex) {
                Logger.getLogger(CodeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        treeTableViewRemove.setOnMouseClicked((e) -> {
            QuestionAdapter value = treeTableView.getSelectionModel().selectedItemProperty().get().getValue();
            QuestionFlowData idq = new QuestionFlowData(value.idQuest.intValue());
            context.register("idquest", idq);
      
            System.out.println(context.getRegisteredObject("idquest"));

            try {

                contentFlowHandler.navigateTo(ModifierQuestionController.class);

            } catch (VetoException ex) {
                Logger.getLogger(CodeController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FlowException ex) {
                Logger.getLogger(CodeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        searchField.textProperty().addListener((o, oldVal, newVal) -> {
            System.out.println(newVal);
            treeTableView.setPredicate(t -> (t.getValue().idQuest.get() + " ").contains(newVal) || (t.getValue().categorie.get() + " ").toUpperCase().contains(newVal.toUpperCase()) || (t.getValue().questContent.get() + " ").toUpperCase().contains(newVal.toUpperCase()));
        });

    }
}

class QuestionAdapter extends RecursiveTreeObject<QuestionAdapter> {

    public IntegerProperty idQuest;
    public StringProperty categorie;
    public StringProperty questContent;

    public QuestionAdapter(int id, String cat, String content) {
        this.idQuest = new SimpleIntegerProperty(id);
        this.categorie = new SimpleStringProperty(cat);
        this.questContent = new SimpleStringProperty(content);
    }

}
