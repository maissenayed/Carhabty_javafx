package Views.controllers;

import com.jfoenix.controls.JFXButton;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;
import io.datafx.controller.FXMLController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ahlem
 */
@FXMLController(value = "/Views/fxml/AstuceDuJour.fxml", title = "")
public class AstuceDuJourController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private ImageView image;

    @FXML
    private TextArea description;

    @FXML
    private JFXButton retour;

    @FXML
    void retour(ActionEvent event) throws IOException {
        pane.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("Views/fxml/AfficherAstuce.fxml")));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Facebook facebook = new FacebookFactory().getInstance();
            // Use default values for oauth app id.

            facebook.setOAuthAppId("661006657416629", "489f88203b3cfe78c11a86c8e9b64f1a");
            //System.out.println("token:"+facebook.getOAuthAppAccessToken());
            // Get an access token from:
            // https://developers.facebook.com/tools/explorer
            // Copy and paste it below.
            String accessTokenString = facebook.getOAuthAppAccessToken().getToken();
            AccessToken at = new AccessToken("EAACEdEose0cBADH2bLYj3PyxinzqRFZAXEwDGOzwXlZAROWgVxlo2AH2ge1twv4ByOkui3z07QrKTxFjkJq9kkHcZAADV6qezPklAuQxfAUQVamcJySSyJtWZBvGmZBqF1GK521sWGGnXL2zFLozfwbRqtwKVq9jxkti8U0ZCXg8iAH245pkM2oTN0VZBhCBo0ZD");
            // Set access token.
            facebook.setOAuthAccessToken(at);

            // We're done.
            // Access group feeds.
            // You can get the group ID from:
            // https://developers.facebook.com/tools/explorer
            ResponseList<Post> feeds = facebook.getFeed("407549549613640",
                    new Reading().limit(1));
            // Get post.
            // Get more stuff...
            Post post = feeds.get(0);
            // Get (string) message.
            String message = post.getMessage();
            String date = post.getCreatedTime().toString();
            String id = post.getId();
            String picture = post.getPicture().toString();

            //System.out.println(picture);
            image.setImage(new Image(picture));

            //titre.setText(CurrentAstuce.Currenta.getTitre());
            //theme.setText(CurrentAstuce.Currenta.getTheme());
            description.setText(message);

        } catch (FacebookException ex) {
            Logger.getLogger(AstuceDuJourController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
