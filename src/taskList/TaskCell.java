package taskList;

import dataManaging.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.joda.time.Days;
import org.joda.time.LocalDate;

public class TaskCell
{
    
    Task task;
    @FXML
    private AnchorPane hBox;
    
    @FXML
    private Label taskName;

    @FXML
    private Button doneButton;

    @FXML
    private Label difficulty;

    @FXML
    private Button abandonButton;
    
    @FXML
    private Label daysLeft;


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
        Days d = Days.daysBetween(new LocalDate(new java.util.Date()),new LocalDate(task.getDead_line()));
        this.task = task;
        taskName.setText(task.getNume());
        doneButton.setText("Done");
        abandonButton.setText("Abandon");
        difficulty.setText(String.valueOf(task.getDifficulty()));
        daysLeft.setText(String.valueOf(d.getDays()));
        setWarningColor(d.getDays());
    }

    public AnchorPane getBox()
    {
        return hBox;
    }
    
    @FXML
     void initialize() {
        doneButton.setOnAction((event) -> {
            System.out.println("Finished task : " + task.getNume());
            done();
        });
        
        abandonButton.setOnAction((event) -> {
            System.out.println("Abandoned task : " + task.getNume());
            abandon();
        });
     }   
    
    private void done() {
        task.done();
    }
    
    private void abandon() {
        task.abandon();
    }
    
    private void setWarningColor(int days) {
        if(days > 28)
            hBox.setStyle("-fx-background-color: #50a33e");
        if(days <= 28)
            hBox.setStyle("-fx-background-color: #fad049"); 
        if(days <= 14)
            hBox.setStyle("-fx-background-color: #f0521d");
        if(days <= 7)
            hBox.setStyle("-fx-background-color: #d02c2d");
    }
}