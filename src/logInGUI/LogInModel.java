/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logInGUI;

import dataManaging.DataBase;
import dataManaging.User;
import exceptions.UserNotFound;

/**
 *
 * @author Andrei
 */
public class LogInModel{
    
    private final LogInView view_;
    private User user_ = null;
    
    public LogInModel(LogInView view) {
        view_ = view;
    }
    
    public Boolean checkValidityOfUser(String user, String pass) {
        try{
            user_ = DataBase.getUserData(user);
            if(user_.checkPasswordValidity(pass))
                return true;
        }catch(UserNotFound e)
        {
            System.err.println("User was not found in table !");
        }
        return false; 
    }
    
    public User getUser(){
        return user_;
    }
}