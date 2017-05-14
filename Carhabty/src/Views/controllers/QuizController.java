/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import DataBase.Session;
import Entities.Question;
import Entities.Reponse;
import Services.QuestionService;
import Services.ReponseService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import com.jfoenix.controls.JFXDialogLayout;
import flowData.QuizAdapter;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javax.annotation.PostConstruct;


/**
 *
 * @author Azgard
 */
@FXMLController(value = "/Views/fxml/Quiz.fxml", title = "Quiz")
public class QuizController {
    
    
    private final String questionImgProp="http://localhost/Carhabty_web/Carhabty/web/images/question/";
    

    @FXMLViewFlowContext
    private ViewFlowContext context;

    private final int nbQuestion;

    @FXML
    private Label quizNumber;

    @FXML
    private ImageView imageView;

    @FXML
    private Label explication;

    @FXML
    private JFXButton previous;

    @FXML
    private JFXButton next;

    @FXML
    private VBox repContainer;

    @FXML
    private Label laQuestion;

    @FXML
    private AnchorPane rootS;

    @FXML
    private HBox submitContainer;

    @FXML
    private StackPane root;

    @FXML
    private JFXDialog dialog;

    @FXML
    private JFXDialogLayout diaLyout;

    @FXML
    private JFXButton acceptButton;

    @FXML
    private JFXButton canceltButton;

    @FXML
    private AnchorPane ecran;

    int posInList;

    private boolean finish;

    List<QuizAdapter> bindList;

    public QuizController() throws IOException {
        this.nbQuestion = 10;
    }

    public int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    public List<Question> randomQuestion() {
        QuestionService qs = new QuestionService();
        List<Question> allQuestion = qs.findALL();
        List<Question> randomList = new ArrayList();
        int x;
        for (int i = 0; i <= nbQuestion; i++) {
            x = randomWithRange(0, allQuestion.size() - 1);
            randomList.add(allQuestion.get(x));
            allQuestion.remove(x);
        }

        return randomList;
    }

    @PostConstruct
    public void init() throws FlowException, VetoException, IOException, Exception {

        finish = false;
        posInList = 1;
        next.setDisable(false);
        previous.setDisable(true);

        List<Question> randomList = randomQuestion();

        bindList = new ArrayList<QuizAdapter>();
        ecran.getChildren().removeIf(x -> x.getId().equals("chart"));
        randomList.forEach(e -> {
            bindList.add(new QuizAdapter(e));
        });
        ReponseService rs = new ReponseService();
        int quizNum = rs.findLastQuizNumber(Session.actualUser);

        next.setOnAction(e -> {
            posInList++;
            if (posInList <= nbQuestion) {
                QuizAdapter request = bindList.get(posInList);
                try {
                    WriteNodesQuestion(request);
                } catch (IOException ex) {
                    Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (posInList == nbQuestion && !finish) {
                next.setDisable(true);
                setBtnValidation();
            }

            if (posInList == nbQuestion + 1 && finish) {
                setPie();
                next.setDisable(true);
            }

            previous.setDisable(false);

        });

        previous.setOnAction(e -> {

            posInList--;
            next.setDisable(false);
            QuizAdapter request = bindList.get(posInList);
            ecran.getChildren().removeIf(x -> x.getId().equals("chart"));

            try {
                WriteNodesQuestion(request);
            } catch (IOException ex) {
                Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (posInList <= 1) {
                previous.setDisable(true);
            }

        });

        WriteNodesQuestion(bindList.get(posInList));

    }

    public void WriteNodesQuestion(QuizAdapter quest) throws IOException, Exception {
        imageView.setImage(new Image(questionImgProp + quest.getQ().getMedia().getImageName()));
        imageView.setX(80);
        imageView.setY(40);
        imageView.setFitHeight(240);
        explication.setText(quest.getQ().getExplanation());
        laQuestion.setText(quest.getQ().getQuestionContent());
        explication.setVisible(finish);
        writeNodeReponses(quest);
        System.out.println(posInList + "---" + quest.getQ().getId());
        setBadge(posInList);

    }

    public void writeNodeReponses(QuizAdapter quest) {
        repContainer.getChildren().clear();
        repContainer.setSpacing(5);
        double prwidth = repContainer.getPrefWidth();

        Set<Map.Entry<Reponse, Boolean>> entrySet = quest.getRep().entrySet();
        Map.Entry<Reponse, Boolean> tmp;

        Iterator it = entrySet.iterator();
        int idbtn = 0;
        while (it.hasNext()) {
            idbtn++;
            tmp = (Map.Entry<Reponse, Boolean>) it.next();
            JFXButton btn = new JFXButton(tmp.getKey().getReponseContent());
            btn.setId("b" + idbtn);
            btn.getStyleClass().add("btn-rep");
            btn.setPrefSize(prwidth, 60.0);
            if (tmp.getValue()) {
                btn.getStyleClass().add("pressed");
                btn.setDisable(true);
            }

            btn.setDisable(finish);

            if (finish && (tmp.getKey().isOk())) {
                btn.getStyleClass().add("success");
            } else if (finish && !tmp.getKey().isOk() && tmp.getValue()) {
                btn.getStyleClass().add("faile");
            }
            btn.setOnAction(choose);
            repContainer.getChildren().add(btn);

        }
    }

    final EventHandler<ActionEvent> choose = e -> {
        JFXButton choix = (JFXButton) (e.getSource());
        int x = getIndex(choix.getId());
        bindList.get(posInList).setChoice(x);
        writeNodeReponses(bindList.get(posInList));

    };

    private int getIndex(String str) {
        char temp = str.charAt(1);
        return Character.getNumericValue(temp);
    }

    private void setBadge(int i) {

        Image badge = new Image("Image/badge.png");
        String str = "Q" + i;
        if (i > nbQuestion) {
            str = "S";
        }
        ImageView bdgview = new ImageView(badge);
        bdgview.maxWidth(5);
        bdgview.maxHeight(5);
        quizNumber.maxWidth(100);
        quizNumber.maxHeight(100);
        quizNumber.setGraphic(bdgview);
        quizNumber.setFont(Font.font("Cambria", 20));
        quizNumber.setTextFill(Paint.valueOf("white"));
        quizNumber.setText(str);
        quizNumber.setTranslateX(10);
        quizNumber.setTranslateY(4);

        int dec;
        if(str.length()==3)
        {dec=-36;}
        else
            if(str.length()==2)
            {dec=-33;}
            else
                dec=-27;
            quizNumber.setGraphicTextGap(dec);
  

    }

    private void setBtnValidation() {

        if (!finish && submitContainer.getChildren().isEmpty()) {
            JFXButton btnSubmit = new JFXButton("Vérifier");
            btnSubmit.getStyleClass().add("success");
            btnSubmit.setButtonType(JFXButton.ButtonType.RAISED);
            btnSubmit.setOnAction(a -> {
                confirmSubmit();
            });
            submitContainer.getChildren().addAll(btnSubmit);
        }

    }

    private void setBtnRejouer() {
        JFXButton rejouer = new JFXButton("rejouer");
        rejouer.getStyleClass().add("success");
        rejouer.setButtonType(JFXButton.ButtonType.RAISED);
        submitContainer.setSpacing(5);
        rejouer.setOnAction(k -> {
            submitContainer.getChildren().clear();
            try {
                init();
            } catch (FlowException ex) {
                Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (VetoException ex) {
                Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        submitContainer.getChildren().clear();
        submitContainer.getChildren().add(rejouer);
    }

    private void confirmSubmit() {

        dialog.setTransitionType(DialogTransition.CENTER);
        int nbrChoices = getNumberChoices();
        if (nbrChoices == 0) {
            diaLyout.setBody(new Label("Vous n'avez pas répondu à aucune question ! \nEtes vous sur de valider ?"));
        } else if (nbrChoices < nbQuestion / 2) {
            diaLyout.setBody(new Label("Vous avez répondu moin de 50% des questions ! \nEtes vous sur de valider ?"));
        } else {
            diaLyout.setBody(new Label("Etes vous sur de valider ?"));
        }

        acceptButton.setOnAction(x -> {
            finish = true;
            writeNodeReponses(bindList.get(posInList));
            setBtnRejouer();
            posInList++;
            setPie();

            dialog.close();
          int qnmbr=new ReponseService().findLastQuizNumber(Session.actualUser);
            qnmbr++;
            for(QuizAdapter e:bindList)
            {
                e.save(qnmbr);
            }

        });
        canceltButton.setOnAction(x -> {
            dialog.close();
        });
        dialog.show((StackPane) context.getRegisteredObject("ContentPane"));

    }

    private int getNumberChoices() {
        int x = 0;
        for (QuizAdapter e : bindList) {
            if (e.choiceIsSet()) {
                x++;
            }
        }
        return x;
    }

    private int getNumberCorrectChoices() {
        int x = 0;
        for (QuizAdapter e : bindList) {
            if (e.isCorrect()) {
                x++;
            }
        }
        return x;
    }

    private void setPie() {
        setBadge(posInList);
        int nbrGood = getNumberCorrectChoices();
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Bonne réponse", nbrGood),
                        new PieChart.Data("Mauvaise réponse", nbQuestion - nbrGood)
                );
        final PieChart chart = new PieChart(pieChartData);
        chart.setLayoutY(-50);
        chart.setId("chart");

        imageView.setImage(null);
        ecran.getChildren().add(chart);
        laQuestion.setText("");
        explication.setText("");
        repContainer.getChildren().clear();
    }

}
