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
public class TaskCouldNotBeAbandoned extends Exception {
    public TaskCouldNotBeAbandoned(String msg) {
        super("Task " + msg + " could not be abandoned !");
    }
}
