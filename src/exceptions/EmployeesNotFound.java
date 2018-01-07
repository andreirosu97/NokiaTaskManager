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
public class EmployeesNotFound extends Exception {

    public EmployeesNotFound(String msg) {
        super("Employees were not found for " + msg);
    }
}
