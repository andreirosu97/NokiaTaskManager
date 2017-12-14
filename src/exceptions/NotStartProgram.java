/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import javafx.scene.control.Alert;

/**
 *
 * @author Andrei
 */
public class NotStartProgram extends Exception {
    public NotStartProgram() {
        super("Did not connect !");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("CONNECTION ERROR !");
        alert.setHeaderText(null);
        alert.setContentText("Could not connect to the database ! "
                + "Check internet connection!");
        alert.showAndWait();
    }
}
