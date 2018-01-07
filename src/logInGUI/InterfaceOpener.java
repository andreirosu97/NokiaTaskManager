/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logInGUI;

import dataManaging.User;
import exceptions.UserNotSetYet;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
    
    private void OpenManagerInterface() throws Exception{
        System.out.println("Manager " + user_.getFullName_() +" logged in !");
        FXMLLoader loader = new FXMLLoader();
        Pane userRoot = loader.load(getClass().getResource("manager.fxml").openStream());
        ManagerController controller = (ManagerController)loader.getController();
        controller.populateScreen(user_);
        Scene userScene = new Scene(userRoot);
        stage_.setScene(userScene);
        stage_.show();
    }
    
    private void OpenNormalInterface() throws Exception{
        System.out.println("User " + user_.getFullName_() +" logged in !");
        FXMLLoader loader = new FXMLLoader();
        Pane userRoot = loader.load(getClass().getResource("normalUser.fxml").openStream());
        NormalUserController controller = (NormalUserController)loader.getController();
        controller.populateScreen(user_);
        Scene userScene = new Scene(userRoot);
        stage_.setScene(userScene);
        stage_.show();
    }
    
}
