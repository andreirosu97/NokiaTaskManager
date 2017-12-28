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
public class NewOwnerOfTaskNotSet extends Exception {
    public NewOwnerOfTaskNotSet(String taskName) {
        super("New owner of taks: " + taskName + " could not be set !");
    }
}
