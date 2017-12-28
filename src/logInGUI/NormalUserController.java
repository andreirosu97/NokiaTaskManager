/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logInGUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import dataManaging.User;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Andrei
 */
public class NormalUserController {

    User user_;
    
    @FXML
    private JFXButton logOutButton;
    @FXML
    private Label wellcomeText;
    @FXML
    private Label numberOfTasksActive;
    @FXML
    private JFXListView listView;
    
    private final         List<Task>        stringList     = new ArrayList<>(5);
    private         ObservableList      observableList = FXCollections.observableArrayList();
    @FXML
    private AnchorPane mainPane;
    @FXML
    private JFXButton moreTasks;


    
    public void populateScreen(User user) {
        user_ = user;
        wellcomeText.setText("Wellcome to your task manager " + user_.getFullName_());
        numberOfTasksActive.textProperty().bind(user.getTasksSizeBinding().asString());
        setListView();      
    }
    
    public void setListView(){

         observableList = user_.getTasks();
         
         listView.setItems(observableList);
         listView.setCellFactory(
             new Callback<JFXListView<Task>, 
                     com.jfoenix.controls.JFXListCell<Task>>() {
                 @Override
                 public JFXListCell<Task> call(JFXListView<Task> listView) {
                     return new ListViewCell();
                 }
             });
     }
    
    @FXML
    private void logOut(ActionEvent event) {
        NOKIA.closeStage();
        NOKIA.openLogInStage();
    }

    @FXML
    private void getMoreTasks(ActionEvent event) {
        int numberOfTasks = Integer.parseInt(user_.getNumberOfTasks());
        System.out.println("Get more tasks!");
        user_.getTaskFromDB();
        if(numberOfTasks == Integer.parseInt(user_.getNumberOfTasks() ))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Getting tasks message");
            alert.setHeaderText(null);
            alert.setContentText("No tasks are available that suit your skills!\n Come back later !");
            alert.showAndWait();
        }
    }
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    void initialize() {
         assert listView != null : "fx:id=\"listView\" was not injected: "
                 + "check your FXML file 'CustomList.fxml'.";
     }  
}
