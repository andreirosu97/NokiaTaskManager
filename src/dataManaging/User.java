/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataManaging;

import exceptions.EmployeesNotFound;
import exceptions.NewOwnerOfTaskNotSet;
import exceptions.NewTaskNotFound;
import exceptions.NoTasksAvailable;
import exceptions.NoTasksFoundForUser;
import exceptions.TaskCapacityReached;
import exceptions.UserAlreadyHasDifficultTask;
import exceptions.UsersProgrammingLanguageDoesNotMatchTask;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User {
    private String uuid_;
    private String pass_;
    private String fullName_;
    private String team_;
    private String uuidManager_;
    private String jobTitle_;
    private String programmingLanguage_;
    private ArrayList<Integer> skills_;
    private ObservableList tasks_ = FXCollections.observableArrayList();
    private ObservableList employees_ = FXCollections.observableArrayList();

    public Boolean checkPasswordValidity(String pass){
        return pass_.equals(pass);
    }
    
    public User() {
    }

    public User(String uuid_, String pass_, String fullName_, String team_, 
            String uuidManager_, String jobTitle_, String programmingLanguage_, 
            ArrayList<Integer> skills_) {
        this.uuid_ = uuid_;
        this.pass_ = pass_;
        this.fullName_ = fullName_;
        this.team_ = team_;
        this.uuidManager_ = uuidManager_;
        this.jobTitle_ = jobTitle_;
        this.programmingLanguage_ = programmingLanguage_;
        this.skills_ = skills_;
        if(uuidManager_ != null)
            getTasksFromDB();
        else{
            getEmployeesFromDB(); 
            try{
                tasks_ = DataBase.getTasks(this);
            }catch(NoTasksAvailable e){
                System.out.println("No tasks were found !");
            }
        }
    }
    
     private void getEmployeesFromDB() {
        try{
            employees_ = DataBase.getEmployeesForUser(this);
            System.out.println("Employees for " + this.fullName_ +" added !");
        }catch(EmployeesNotFound e) {
            System.out.println("No employees were found for " + this.getFullName_());
        }
    }

    public void getTaskFromDB() {
        if(tasks_.size() < 3) {
            try{
                tasks_.add(DataBase.getNewTaskForUser(this));
                System.out.println("New Task was added for " + fullName_ + "!");
            }catch(NewTaskNotFound e) {
                System.out.println("No tasks were found for " + this.getFullName_());
            }
        }
    }
    
    public void removeTask(Task task) {
        tasks_.remove(task);
    }
    
    
    private void getTasksFromDB() {
        try {
            tasks_ = DataBase.getTasksForUser(this);
        } catch (NoTasksFoundForUser ex) {
            getTaskFromDB();
            Logger.getLogger(User.class.getName()).log(Level.FINE, "No tasks yet assigned!");
        }
    }

    
    public String getNumberOfTasks() {
        return String.valueOf(tasks_.size());
    }
    
    public IntegerBinding getTasksSizeBinding() {
        return Bindings.size(tasks_);
    }
    
    public Boolean hasDifficultTask() {
        for(int i=0; i < tasks_.size(); i++)
            if(((Task)tasks_.get(i)).isDifficult())
                return true;
        return false;
    }

    public void selectBestTask(ArrayList<Task> tasks) {
        Collections.sort(tasks, new Comparator<Task>() {
        @Override
        public int compare(Task task1, Task task2)
        {
            int a=0,b=0;
            int distance1 = 0;
            int distance2 = 0;
            for(int i:skills_)
                distance1 += Math.abs(i - task1.getSkills_().get(a++));
            for(int j:skills_)
                distance2 += Math.abs(j - task2.getSkills_().get(b++));

            if(distance1 > distance2)
                return 1;
            else
                if(distance1 == distance2)
                    return 0;
            return -1;
        }
    });
    }
    
    public void addTask(Task task) throws UserAlreadyHasDifficultTask,UsersProgrammingLanguageDoesNotMatchTask,TaskCapacityReached{
        if(tasks_.size() == 3)
            throw new TaskCapacityReached();
        if(!task.getProgrammingLanguage().equals(this.programmingLanguage_)) 
            throw new UsersProgrammingLanguageDoesNotMatchTask();
        if((task.isDifficult() && this.hasDifficultTask()))
            throw new UserAlreadyHasDifficultTask();
        try {
            task.changeUser(this);
            tasks_.add(task);
            DataBase.setOwnerOfTask(this, task);
        } catch (NewOwnerOfTaskNotSet ex) {
            System.out.println("New owner of task could not be set !");
        }
    }          
    
    /*Getters and setters*/
    public ObservableList getTasks() {
        return tasks_;
    }
    
    public String getUuid_() {
        return uuid_;
    }

    public void setUuid_(String uuid_) {
        this.uuid_ = uuid_;
    }

    public String getPass_() {
        return pass_;
    }

    public void setPass_(String pass_) {
        this.pass_ = pass_;
    }

    public String getFullName_() {
        return fullName_;
    }

    public void setFullName_(String fullName_) {
        this.fullName_ = fullName_;
    }

    public String getTeam_() {
        return team_;
    }

    public void setTeam_(String team_) {
        this.team_ = team_;
    }

    public String getUuidManager_() {
        return uuidManager_;
    }

    public void setUuidManager_(String uuidManager_) {
        this.uuidManager_ = uuidManager_;
    }

    public String getJobTitle_() {
        return jobTitle_;
    }

    public void setJobTitle_(String jobTitle_) {
        this.jobTitle_ = jobTitle_;
    }

    public String getProgrammingLanguage_() {
        return programmingLanguage_;
    }

    public void setProgrammingLanguage_(String programmingLanguage_) {
        this.programmingLanguage_ = programmingLanguage_;
    }

    public ArrayList<Integer> getSkills_() {
        return skills_;
    }

    public void setSkills_(ArrayList<Integer> skills_) {
        this.skills_ = skills_;
    }

    public ObservableList getEmployees_() throws EmployeesNotFound{
        if(employees_.isEmpty())
            throw new EmployeesNotFound(this.fullName_);
        return employees_;
    }

    @Override
    public String toString() {
        return null;
    }
}
