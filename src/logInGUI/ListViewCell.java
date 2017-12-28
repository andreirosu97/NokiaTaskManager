package logInGUI;

import com.jfoenix.controls.JFXListCell;

public class ListViewCell extends JFXListCell<Task>
{
    @Override
    public void updateItem(Task obj, boolean empty)
    {
        super.updateItem(obj,empty);
        if(obj != null)
        {
            System.out.println("Apelat updateItem");
            TaskCell data = new TaskCell();
            data.setInfo(obj);
            setGraphic(data.getBox());
        }
    }
}