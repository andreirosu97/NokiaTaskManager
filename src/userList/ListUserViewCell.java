package userList;

import com.jfoenix.controls.JFXListCell;
import dataManaging.User;

public class ListUserViewCell extends JFXListCell<User>
{
    @Override
    public void updateItem(User obj, boolean empty)
    {
        super.updateItem(obj,empty);
        if(obj != null)
        {
            UserCell data = new UserCell();
            data.setInfo(obj);
            setGraphic(data.getBox());
        }
    }
}