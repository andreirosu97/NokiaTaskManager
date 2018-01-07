/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logInGUI;

import userList.ListUserViewCell;
import dataManaging.Task;
import simpleTaskList.ListSimpleTaskCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import dataManaging.User;
import exceptions.EmployeesNotFound;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Andrei
 */
public class ManagerController {

    User user_;
    
    @FXML
    private JFXButton logOutButton;
    @FXML
    private Label wellcomeText;
    @FXML
    private JFXListView exmployeeListView;
    @FXML
    private JFXListView tasksListView;
    
    private         ObservableList      observableList = FXCollections.observableArrayList();
    private         ObservableList      observableTasksList = FXCollections.observableArrayList();

    @FXML
    private AnchorPane mainPane;


    public void populateScreen(User user) {
        user_ = user;
        wellcomeText.setText("Wellcome to your employees manager  " + user_.getFullName_());
        setEmployeesListView();  
        setTasksListView();
    }
    
    public void setEmployeesListView(){
       try{
            observableList = user_.getEmployees_();
            exmployeeListView.setItems(observableList);
            exmployeeListView.setCellFactory(
                new Callback<JFXListView<User>, 
                        com.jfoenix.controls.JFXListCell<User>>() {
                    @Override
                    public JFXListCell<User> call(JFXListView<User> listView) {
                        return new ListUserViewCell();
                    }
                });
       }catch(EmployeesNotFound e) {
           System.out.println("No employees were found !");
       }
     }
    
    public void setTasksListView() {
        observableTasksList = user_.getTasks();
        tasksListView.setItems(observableTasksList);
        tasksListView.setCellFactory(
            new Callback<JFXListView<Task>, 
                    com.jfoenix.controls.JFXListCell<Task>>() {
                @Override
                public JFXListCell<Task> call(JFXListView<Task> listView) {
                    return new ListSimpleTaskCell();
                }
            });
    }
    
    @FXML
    private void logOut(ActionEvent event) {
        NOKIA.closeStage();
        NOKIA.openLogInStage();
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    void initialize() {
         assert exmployeeListView != null : "fx:id=\"listView\" was not injected: "
                 + "check your FXML file 'CustomList.fxml'.";
     }  
}
