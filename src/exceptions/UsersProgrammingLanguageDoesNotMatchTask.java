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
public class UsersProgrammingLanguageDoesNotMatchTask extends Exception {
    public UsersProgrammingLanguageDoesNotMatchTask() {
        super("Users programming language is not the same as the tasks!");
    }
}
