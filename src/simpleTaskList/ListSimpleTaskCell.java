package simpleTaskList;

import com.jfoenix.controls.JFXListCell;
import dataManaging.Task;

public class ListSimpleTaskCell extends JFXListCell<Task>
{
    @Override
    public void updateItem(Task obj, boolean empty)
    {
        super.updateItem(obj,empty);
        if(obj != null)
        {
            SimpleTaskCell data = new SimpleTaskCell();
            data.setInfo(obj);
            setGraphic(data.getBox());
        }
    }
}