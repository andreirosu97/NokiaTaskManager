package demo;

import logInGUI.ListViewCell;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.util.Callback;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import logInGUI.ListViewCell;
import logInGUI.Task;

 public class ListViewController 
 {

     @FXML private   ResourceBundle      resources;

     @FXML private   URL                 location;

     @FXML private   JFXListView            listView;

     private         List<Task>        stringList     = new ArrayList<>(5);
     private         ObservableList      observableList = FXCollections.observableArrayList();

     public void setListView(){

         System.out.println("Apelat setListView()");
//         stringList.add(new Task("Pula4"));
//         stringList.add(new Task("Pula3"));
//         stringList.add(new Task("Pula2"));
//         stringList.add(new Task("Pula1"));

         observableList.setAll(stringList);

         listView.setItems(observableList);

         listView.setCellFactory(new Callback<JFXListView<Task>, com.jfoenix.controls.JFXListCell<Task>>() {
                 @Override
                 public JFXListCell<Task> call(JFXListView<Task> listView) {
                     return new ListViewCell();
                 }
             });
     }

     @FXML
     void initialize() {
         assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'CustomList.fxml'.";

         setListView();
     }

 }//ListViewController