package userList;

import dataManaging.Task;
import dataManaging.User;
import exceptions.TaskCapacityReached;
import exceptions.UserAlreadyHasDifficultTask;
import exceptions.UsersProgrammingLanguageDoesNotMatchTask;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.input.DataFormat;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;

public class UserCell
{
    
    User user;
    @FXML
    private AnchorPane hBox;
    @FXML
    private Label name;

    @FXML
    private Label nrTasks;

    @FXML
    private Label language;

    public UserCell()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listUserCellItem.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void setInfo(User user)
    {
        this.user = user;
        name.setText(user.getFullName_());
        nrTasks.textProperty().bind(user.getTasksSizeBinding().asString());
        language.setText(user.getProgrammingLanguage_());
    }

    public AnchorPane getBox()
    {
        return hBox;
    }
    
    @FXML
    void initialize() {
        hBox.setOnDragOver((event) -> {
            if(event.getDragboard().hasContent(DataFormat.RTF)){
                event.acceptTransferModes(TransferMode.ANY);
                System.out.println("Over " + user.getFullName_());
            }
        }
        );
        
        hBox.setOnDragDropped((event) -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error at adding task to employee!");
            alert.setHeaderText(null);
            try {
                user.addTask((Task)event.getDragboard().getContent(DataFormat.RTF));
            } catch (TaskCapacityReached ex) {
                alert.setContentText("The employee already has 3 tasks !\nGive him a break would'ga ?!");
                alert.showAndWait();
            } catch (UsersProgrammingLanguageDoesNotMatchTask ex) {
                alert.setContentText("The programming language that the tasks requires does not match "
                        + "the one the employee is specialised in !\nTry adding a task in " + user.getProgrammingLanguage_() + "!");
                alert.showAndWait();
            } catch (UserAlreadyHasDifficultTask ex) {
                alert.setContentText("The user already has a difficult task !\n\nTry adding a easier task!");
                alert.showAndWait();
            }
        });
    }
}