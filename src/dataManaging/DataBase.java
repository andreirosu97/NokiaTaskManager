package dataManaging;

import exceptions.NotStartProgram;
import exceptions.UserNotFound;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

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
}
