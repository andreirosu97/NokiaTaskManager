/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logInGUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import dataManaging.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Andrei
 */
public class NormalUserController implements Initializable {

    User user_;
    
    @FXML
    private JFXButton logOutButton;
    @FXML
    private Label wellcomeText;
    @FXML
    private Label numberOfTasksActive;
    @FXML
    private JFXListView<Label> taskListView;

    
    public void populateScreen(User user) {
        user_ = user;
        wellcomeText.setText("Wellcome to your task manager " + user_.getFullName_());
        numberOfTasksActive.setText("Tasks active : " + user_.getNumberOfTasks());
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(int i = 0; i < 4; i++)
            try{
                Label lbl = new Label("Task " + i);
                taskListView.getItems().add(lbl);
            }catch(Exception e){
                System.out.println("Nu pot face lista !");
            }
    }    

    @FXML
    private void logOut(ActionEvent event) {
        NOKIA.closeStage();
        NOKIA.openLogInStage();
    }
    
}
