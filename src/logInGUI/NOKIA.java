/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logInGUI;

import dataManaging.DataBase;
import dataManaging.User;
import exceptions.NotStartProgram;
import exceptions.UserNotSetYet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Andrei
 */
public class NOKIA extends Application {
    private DataBase b;
    private static StartLogIn logIn_;
    private static Stage stage_;
    
    @Override
    public void start(Stage stage){
        try{
            b = new DataBase();
            openLogInStage();
        }catch(NotStartProgram e){} 
    }
    
    
    public static void openLogInStage() {
        try{
            stage_ = new Stage();
            logIn_ = new StartLogIn(stage_);
        }catch (Exception ex) {
            Logger.getLogger(NOKIA.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public static void openUserStage(LogInModel model) {
        try{
            stage_ = new Stage();
            InterfaceOpener interface_ = new InterfaceOpener(model.getUser(), stage_);
        }catch(UserNotSetYet e){
            System.err.println(e);
        }
    }
    
    public static void closeStage() {
        try{
            stage_.close();
        }catch(NullPointerException e){
            System.err.println("There is no stage active !");
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
