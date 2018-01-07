package simpleTaskList;

import dataManaging.Task;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;


public class SimpleTaskCell
{
    
    Task task;
    @FXML
    private AnchorPane hBox;
    
    @FXML
    private Label taskName;
    
    @FXML
    private Label language;

    @FXML
    private Label difficulty;

    public SimpleTaskCell()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listSimpleTaskCellItem.fxml"));
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
        taskName.setText(task.getNume());
        language.setText(task.getProgrammingLanguage());
        difficulty.setText(String.valueOf(task.getDifficulty()));
    }

    public AnchorPane getBox()
    {
        return hBox;
    }
    
    @FXML
    void initialize() {
        hBox.setOnDragDetected((event) -> {
            Dragboard db = hBox.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.put(DataFormat.RTF, task);
            db.setContent(content);
            event.consume();
            System.out.println("Dragged " + task.getNume());
        }
        );
    }
}