package scences;

import client.ChatClient;
import client.ClientConsole;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class LogInController {

    @FXML
    private Button LogInBTN;

    @FXML
    private Button LogInGuestBTN;

    @FXML
    private PasswordField PasswordPF;

    @FXML
    private Text UserNameTF1;

    @FXML
    private Text UserNameT;

    @FXML
    private AnchorPane LogInAP;

    @FXML
    private TextField UserNameTF;

    @FXML
    private Label LoginStatusL;

    public void Login(ActionEvent event) throws IOException {
        String userName = "!" + UserNameTF.getText();
        String password = "$" + PasswordPF.getText();
        boolean SignInFlag = false;
        SignInFlag = ConnectionController.client.handleMessageFromClientUI(userName);
        SignInFlag = ConnectionController.client.handleMessageFromClientUI(password);
        if (SignInFlag) {
            LoginStatusL.setText("Login Success");
            LoginStatusL.setTextFill(Color.BLUE);
            String CustomerHomeScene = "/scences/CustomerHome.fxml"; // main screen
            ClientConsole.changeScene(CustomerHomeScene);
        }
        else {
            LoginStatusL.setText("Login Failed");
            LoginStatusL.setTextFill(Color.RED);
        }
    }

    public void Guest(ActionEvent event) throws IOException {
        String CustomerHomeScene = "/scences/CustomerHome.fxml"; // main screen
        ClientConsole.changeScene(CustomerHomeScene);


    }
    public void SignUp(ActionEvent event) throws IOException {
        String RegisterScene = "/scences/RegisterScene.fxml";
        ClientConsole.changeScene(RegisterScene);
    }
}
