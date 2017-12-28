/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logInGUI;

import dataManaging.User;
import exceptions.UserNotSetYet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Andrei
 */
public class InterfaceOpener {
    private User user_ = null;
    private final Stage stage_;
    
    public InterfaceOpener(User user, Stage stage) throws UserNotSetYet{
        user_ = user;
        stage_ = stage;
        try{
            String mgr = user_.getUuidManager_();
            user_ = user;
            OpenInterface(mgr);
        }catch(NullPointerException e){
            throw new UserNotSetYet();
        }
    }
    
    private void OpenInterface(String userManager) {
        try{
            if(userManager == null) {
                OpenManagerInterface();
            }else{
                OpenNormalInterface();
            }
        }catch (Exception ex) {
            System.err.println("Exception caught in OpenInterface in InterfaceOpener.java");
        }
    }
    
    private void OpenManagerInterface() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR !");
        alert.setHeaderText(null);
        alert.setContentText("UI not yet implemented ! ");
        alert.showAndWait();
    }
    
    private void OpenNormalInterface() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Pane userRoot = loader.load(getClass().getResource("normalUser.fxml").openStream());
        NormalUserController controller = (NormalUserController)loader.getController();
        controller.populateScreen(user_);
        Scene userScene = new Scene(userRoot);
        stage_.setScene(userScene);
        stage_.show();
    }
    
}
