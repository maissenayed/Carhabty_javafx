package Views.sidemenu;

import com.jfoenix.controls.JFXListView;
import Views.controllers.*;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

import javax.annotation.PostConstruct;

@FXMLController(value = "/Views/fxml/SideMenu.fxml", title = "")
public class SideMenuController {

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    @ActionTrigger("profile")
    private Label profile;
@FXML
    @ActionTrigger("astuce")
    private Label astuce;
    @FXML
    @ActionTrigger("offres")
    private Label offres;

    @FXML
    @ActionTrigger("oldoffres")
    private Label oldoffres;

    @FXML
    @ActionTrigger("event")
    private Label event;

    @FXML
    @ActionTrigger("localisation")
    private Label localisation;

    @FXML
    @ActionTrigger("contact")
    private Label contact;

    @FXML
    @ActionTrigger("deconnexion")
    private Label deconnexion;

    @FXML
    @ActionTrigger("mescoupon")
    private Label mescoupon;

    @FXML
    private JFXListView<Label> subsideList;

    @FXML
    private JFXListView<Label> sideList;

    @PostConstruct
    public void init() throws FlowException, VetoException {
        FlowHandler contentFlowHandler = (FlowHandler) context.getRegisteredObject("ContentFlowHandler");

        subsideList.propagateMouseEventsToParent();
        subsideList.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
            if (newVal != null) {
                try {
                    contentFlowHandler.handle(newVal.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        sideList.propagateMouseEventsToParent();
        sideList.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
            if (newVal != null) {
                try {
                    contentFlowHandler.handle(newVal.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Flow contentFlow = (Flow) context.getRegisteredObject("ContentFlow");

        bindNodeToController(profile, ProfileController.class, contentFlow, contentFlowHandler);
        bindNodeToController(offres, AfficherOffreController.class, contentFlow, contentFlowHandler);
        bindNodeToController(astuce, AjouterAstuceController.class, contentFlow, contentFlowHandler);
        bindNodeToController(localisation, MapController.class, contentFlow, contentFlowHandler);
        bindNodeToController(deconnexion, DeconnexionController.class, contentFlow, contentFlowHandler);
        bindNodeToController(event, GestionEventsController.class, contentFlow, contentFlowHandler);
        bindNodeToController(oldoffres, AfficherOffreExpireeController.class, contentFlow, contentFlowHandler);
        bindNodeToController(contact, ContactController.class, contentFlow, contentFlowHandler);
        bindNodeToController(mescoupon, MyCouponListController.class, contentFlow, contentFlowHandler);

    }

    private void bindNodeToController(Node node, Class<?> controllerClass, Flow flow, FlowHandler flowHandler) {
        flow.withGlobalLink(node.getId(), controllerClass);
    }

}
