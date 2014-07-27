package endgame;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import endgame.model.User;

public class ProfileController extends AnchorPane implements Initializable {

    @FXML
    private TextField user;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextArea address;
    @FXML
    private CheckBox subscribed;
    @FXML
    private Hyperlink logout;
    @FXML 
    private Button save;
    
    @FXML 
    private Label success;
    
    private Main application;
    
    public void setApp(Main application){
        this.application = application;
        User loggedUser = application.getLoggedUser();
        //subscribed.setSelected(loggedUser.isSubscribed());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void processSwitchClicked(ActionEvent event) {
        System.out.println("processSwitchClicked inside it");
    }

    public void processLogout(ActionEvent event) {
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            return;
        }

        application.userLogout();
    }
    
    public void saveProfile(ActionEvent event) {
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            animateMessage();
            return;
        }
        /*
        User loggedUser = application.getLoggedUser();
        loggedUser.setEmail(email.getText());
        loggedUser.setPhone(phone.getText());
        loggedUser.setSubscribed(subscribed.isSelected());
        loggedUser.setAddress(address.getText());
        animateMessage();*/

        application.stopLoop();
    }
    
    public void resetProfile(ActionEvent event){
        if (application == null){
            return;
        }
        /*email.setText("");
        phone.setText("");
        subscribed.setSelected(false);
        address.setText("");
        success.setOpacity(0.0);
        */
        application.runLoop();
    }

    private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), success);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }
}
