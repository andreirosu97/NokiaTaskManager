package logInGUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class TaskCell
{
    
    Task task;
    @FXML
    private AnchorPane hBox;
    @FXML
    private Label label1;
    @FXML
    private Button button;

    public TaskCell()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listCellItem.fxml"));
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

    public void setInfo(Task task)
    {
        this.task = task;
        label1.setText(task.getNume());
        button.setText("Done");
    }

    public AnchorPane getBox()
    {
        return hBox;
    }
    
    @FXML
     void initialize() {
         button.setOnAction((event) -> {
             System.out.println("Finished task : " + task.getNume());
             done();
});
     }   
    
    private void done() {
        task.done();
    }
}