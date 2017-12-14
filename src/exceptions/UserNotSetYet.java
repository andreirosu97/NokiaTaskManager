/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Andrei
 */
public class UserNotSetYet extends Exception {

    public UserNotSetYet() {
        super("User wasn't set at this point !");
    }
}
