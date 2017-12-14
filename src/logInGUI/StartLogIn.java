/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logInGUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Andrei
 */
public class StartLogIn {

    public StartLogIn(Stage stage) throws Exception{
        Parent loginRoot = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene logIn = new Scene(loginRoot);
        stage.setScene(logIn);
        stage.show();
    }
    
}
