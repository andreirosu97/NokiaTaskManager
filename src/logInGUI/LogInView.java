/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logInGUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class LogInView implements Initializable {
    
    private final LogInModel model_;

    public LogInView() {
        model_ = new LogInModel(this);
    }
    
    @FXML
    private JFXTextField userName;

    @FXML
    private JFXPasswordField passWord;
    
    @FXML
    private JFXButton button;

    private void loginMessage(String title, String info){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(info);
        alert.showAndWait();
    }
    @FXML
    void moveForward(ActionEvent event) {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_TAB);  
        } catch (AWTException e) {
        }
    }
    
    @FXML
    void logInButton(ActionEvent event) {
        String user = userName.getText();
        String pass = passWord.getText();
        if(model_.checkValidityOfUser(user,pass)){
            loginMessage("Login Message", "Successful login !");
            NOKIA.closeStage();
            NOKIA.openUserStage(model_);
        }
        else
            loginMessage("Login Message","User of Password incorrect !! TRY AGAIN !");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
