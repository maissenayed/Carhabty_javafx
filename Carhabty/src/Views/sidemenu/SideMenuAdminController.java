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

@FXMLController(value = "/Views/fxml/SideMenuAdmin.fxml", title = "")
public class SideMenuAdminController {

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    @ActionTrigger("profile")
    private Label profile;

    @FXML
    @ActionTrigger("count")
    private Label count;

    @FXML
    @ActionTrigger("ListUser")
    private Label ListUser;

    @FXML
    @ActionTrigger("ListOffre")
    private Label ListOffre;

    @FXML
    @ActionTrigger("ListEvent")
    private Label ListEvent;

    @FXML
    @ActionTrigger("ListCoupon")
    private Label ListCoupon;

    @FXML
    private JFXListView<Label> sideList;

    @FXML
    private JFXListView<Label> subsideList;

    @FXML
    @ActionTrigger("codeAction")
    private Label code;

    @FXML
    @ActionTrigger("deconnexion")
    private Label deconnexion;

    @PostConstruct
    public void init() throws FlowException, VetoException {
        FlowHandler contentFlowHandler = (FlowHandler) context.getRegisteredObject("ContentFlowHandler");
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

        Flow contentFlow = (Flow) context.getRegisteredObject("ContentFlow");

        bindNodeToController(profile, ProfileController.class, contentFlow, contentFlowHandler);
        bindNodeToController(count, DashboardAdminController.class, contentFlow, contentFlowHandler);
        bindNodeToController(deconnexion, DeconnexionController.class, contentFlow, contentFlowHandler);
        bindNodeToController(ListUser, DeconnexionController.class, contentFlow, contentFlowHandler);
        bindNodeToController(ListEvent, DeconnexionController.class, contentFlow, contentFlowHandler);
        bindNodeToController(ListOffre, DeconnexionController.class, contentFlow, contentFlowHandler);
        bindNodeToController(code, CodeController.class, contentFlow, contentFlowHandler);
        bindNodeToController(ListUser, DeconnexionController.class, contentFlow, contentFlowHandler);

    }

    private void bindNodeToController(Node node, Class<?> controllerClass, Flow flow, FlowHandler flowHandler) {
        flow.withGlobalLink(node.getId(), controllerClass);
    }

}
