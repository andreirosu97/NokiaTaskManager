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
public class NoTasksAvailable extends Exception {
    public NoTasksAvailable() {
        super("No tasks to be found !");
    }
}
