package dataManaging;

import exceptions.EmployeesNotFound;
import exceptions.NewOwnerOfTaskNotSet;
import exceptions.NewTaskNotFound;
import exceptions.NoTasksAvailable;
import exceptions.NoTasksFoundForUser;
import exceptions.NotStartProgram;
import exceptions.TaskCouldNotBeAbandoned;
import exceptions.TaskWasNotSetAsDone;
import exceptions.UserNotFound;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataBase{
	protected static Connection con;



	public DataBase() throws NotStartProgram{
		try	{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/timea_db", "root", "");
		}catch(ClassNotFoundException | SQLException ex){
			throw new NotStartProgram();
		}
	}

	public void clearDataTable(String dataTable) {
		try {
			String query = "DELETE FROM " + dataTable;
			con.createStatement().execute(query);
		}catch (SQLException e) {
			System.err.println("Error at deleting dataTable!");
		}
	}
        
        public ResultSet getResultSetWithQuery(String query) {
		try {
			ResultSet rs = con.createStatement().executeQuery(query);
			return rs;
		}catch (SQLException e) {
			System.err.println("Error at getResultSetWithQuery()");
		}
		return null;
	}
	
	public void getAllTheData(String dataTable) {
		try {
			//TODO get all the data
                        throw new SQLException(); // remove after implementation
		} catch (SQLException e) {
			System.err.println("Error at getAllTheData()");
		}
	}
        
        public static User getUserData(String uuid) throws UserNotFound{
            String query = "SELECT * FROM employees WHERE uuid = ? ";
            ResultSet rs;
            try{
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, uuid);
                rs = pst.executeQuery();
                if(rs.next())
                    return new User(
                            rs.getString("uuid"),
                            rs.getString("password"),
                            rs.getString("full_name"),
                            rs.getString("team"),
                            rs.getString("uuid_mgr"),
                            rs.getString("job_title"),
                            rs.getString("prg_lg"),
                            new ArrayList<>(Arrays.asList(
                                    rs.getInt("skill1"),
                                    rs.getInt("skill2"),
                                    rs.getInt("skill3"),
                                    rs.getInt("skill4")
                                    ))
                    );
            }catch (SQLException e) {
                System.err.println("Error at getUserData()");
            }
            throw new UserNotFound();
        }
        
        public static ObservableList getTasks(User user) throws NoTasksAvailable{
            String query = "SELECT * FROM tasks WHERE done = 0 AND prg_uuid IS NULL ORDER BY prg_lg";
            ArrayList<Task> tasks = new ArrayList<>();
            ResultSet rs;
            try{
                PreparedStatement pst = con.prepareStatement(query);
                rs = pst.executeQuery();
                while(rs.next()) {
                    tasks.add(new Task(user,
                        rs.getString("uuid"),
                        rs.getString("name"),
                        rs.getString("prg_lg"),
                        rs.getInt("difficulty"),
                        rs.getDate("dead_line"),
                        new ArrayList<>(Arrays.asList(
                                        rs.getInt("skill1"),
                                        rs.getInt("skill2"),
                                        rs.getInt("skill3"),
                                        rs.getInt("skill4")
                                        )),
                        rs.getString("prg_uuid"),
                        rs.getBoolean("done")));
                }
                if(tasks.size() > 0){
                    ObservableList o = FXCollections.observableArrayList();
                    o.setAll(tasks);
                    return o;
                }
            }catch (SQLException e) {
                System.err.println("Error at getNewTaskForUser()");
            }
            throw new NoTasksAvailable();
        }
        
        public static Task getNewTaskForUser(User user) throws NewTaskNotFound{
            String query = "SELECT * FROM tasks WHERE prg_lg = ? AND done = 0 AND prg_uuid IS NULL AND difficulty < ?  "
                    + "AND ( last_prg_uuid != ? OR last_prg_uuid IS NULL)";
            
            ArrayList<Task> tasks = new ArrayList<>();
            ResultSet rs;
            try{
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, user.getProgrammingLanguage_());
                if(user.hasDifficultTask())
                    pst.setInt(2, 6);
                else
                    pst.setInt(2,11);
                pst.setString(3, user.getUuid_());
                
                rs = pst.executeQuery();
                while(rs.next()) {
                    tasks.add(new Task(user,
                        rs.getString("uuid"),
                        rs.getString("name"),
                        rs.getString("prg_lg"),
                        rs.getInt("difficulty"),
                        rs.getDate("dead_line"),
                        new ArrayList<>(Arrays.asList(
                                        rs.getInt("skill1"),
                                        rs.getInt("skill2"),
                                        rs.getInt("skill3"),
                                        rs.getInt("skill4")
                                        )),
                        rs.getString("prg_uuid"),
                        rs.getBoolean("done")));
                }
                if(tasks.size() > 0){
                    user.selectBestTask(tasks);
                    setOwnerOfTask(user,tasks.get(0));
                    return tasks.get(0);
                }
                    
            }catch (SQLException e) {
                System.err.println("Error at getNewTaskForUser()");
            }catch (NewOwnerOfTaskNotSet e) {
                System.err.println("Error at setting the owner of the new task !");
            }
            throw new NewTaskNotFound();
        }
 
        public static ObservableList getEmployeesForUser(User user) throws EmployeesNotFound{
            String query = "SELECT * FROM employees WHERE uuid_mgr = ?";
            ArrayList<User> users = new ArrayList<>();
            ResultSet rs;
            try{
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, user.getUuid_());
                rs = pst.executeQuery();
                while(rs.next())
                    users.add(new User(
                            rs.getString("uuid"),
                            rs.getString("password"),
                            rs.getString("full_name"),
                            rs.getString("team"),
                            rs.getString("uuid_mgr"),
                            rs.getString("job_title"),
                            rs.getString("prg_lg"),
                            new ArrayList<>(Arrays.asList(
                                    rs.getInt("skill1"),
                                    rs.getInt("skill2"),
                                    rs.getInt("skill3"),
                                    rs.getInt("skill4")
                                    )))
                    );
                if(users.size() > 0){
                    ObservableList o = FXCollections.observableArrayList();
                    o.setAll(users);
                    return o;
                }
            }catch (SQLException e) {
                System.err.println("Error at getEmployeesForUser()");
            }
            throw new EmployeesNotFound(user.getFullName_());
        }
        
        public static ObservableList getTasksForUser(User user) throws NoTasksFoundForUser {
            String query = "SELECT * FROM tasks WHERE done = 0 AND prg_uuid = ?";
            ArrayList<Task> tasks = new ArrayList<>();
            ResultSet rs;
            try{
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, user.getUuid_());
                rs = pst.executeQuery();
                
                while(rs.next()) {
                    tasks.add(new Task(user,
                        rs.getString("uuid"),
                        rs.getString("name"),
                        rs.getString("prg_lg"),
                        rs.getInt("difficulty"),
                        rs.getDate("dead_line"),
                        new ArrayList<>(Arrays.asList(
                                        rs.getInt("skill1"),
                                        rs.getInt("skill2"),
                                        rs.getInt("skill3"),
                                        rs.getInt("skill4")
                                        )),
                        rs.getString("prg_uuid"),
                        rs.getBoolean("done")));
                }
                if(tasks.size() > 0){
                    ObservableList o = FXCollections.observableArrayList();
                    o.setAll(tasks);
                    return o;
                }
                    
            }catch (SQLException e) {
                System.err.println("Error at getNewTaskForUser()");
            }
            
            throw new NoTasksFoundForUser(user.getFullName_());
        }

                
        public static void setOwnerOfTask(User user, Task task) throws NewOwnerOfTaskNotSet {
            String query = "UPDATE tasks SET prg_uuid = ? WHERE uuid = ?";
            try{
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, user.getUuid_());
                pst.setString(2, task.getTaskUuid());
                pst.executeUpdate();
            }catch (SQLException e) {
                throw new NewOwnerOfTaskNotSet(task.getNume());
            }
        }
        
        public static void setTaskAsDone(Task task) throws TaskWasNotSetAsDone{
            String query = "UPDATE tasks SET done = '1' WHERE uuid = ?";
            try{
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, task.getTaskUuid());
                pst.executeUpdate();
            }catch (SQLException e) {
                throw new TaskWasNotSetAsDone(task.getNume());
            }
        }
        
        public static void abandonTask(Task task) throws TaskCouldNotBeAbandoned {
            String query = "UPDATE tasks SET last_prg_uuid = ?, prg_uuid = NULL WHERE uuid = ?";
            try{
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, task.getUser_().getUuid_());
                pst.setString(2, task.getTaskUuid());
                pst.executeUpdate();
            }catch (SQLException e) {
                throw new TaskCouldNotBeAbandoned(task.getNume());
            }
        }
}
