/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Andrei
 */
public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("listview.fxml"));
        Scene logIn = new Scene(loginRoot);
        stage.setScene(logIn);
        stage.show();
    }

    /**
     *  The main() method is ignored in correctly deployed JavaFX application.
     * 
     *  @param args the command line arguments
     **/
    public static void main(String[] args) {

        launch(args);
    }
}