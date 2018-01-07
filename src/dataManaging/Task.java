/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataManaging;

import exceptions.TaskCouldNotBeAbandoned;
import exceptions.TaskWasNotSetAsDone;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Andrei
 */
public class Task {
    private User user_;
    private String taskUuid;
    private String nume;
    private String programmingLanguage;
    private int difficulty; 
    private Date dead_line;
    private ArrayList<Integer> skills_;
    private String programmerUuid;
    private Boolean done;
    private String lastProgrammerUuid;

    public Task(User user_, String taskUuid, String nume, String programmingLanguage, int difficulty, Date dead_line, ArrayList<Integer> skills_, String programmerUuid, Boolean done) {
        this.user_ = user_;
        this.taskUuid = taskUuid;
        this.nume = nume;
        this.programmingLanguage = programmingLanguage;
        this.difficulty = difficulty;
        this.dead_line = dead_line;
        this.skills_ = skills_;
        this.programmerUuid = programmerUuid;
        this.done = done;
    }

    public ArrayList<Integer> getSkills_() {
        return skills_;
    }

    
    public String getNume() {
        return nume;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    } 
    
    
    @Override
    public String toString() {
        return null;
    }
    
    public void done() {
        try{
            user_.removeTask(this);
            DataBase.setTaskAsDone(this);
        }catch(TaskWasNotSetAsDone e) {
            System.err.println(e);
        }
    }
    
    public void abandon() {
        try{
            user_.removeTask(this);
            DataBase.abandonTask(this);
        }catch(TaskCouldNotBeAbandoned e) {
            System.err.println(e);
        }
    }

    public String getTaskUuid() {
        return taskUuid;
    }

    public void changeUser(User user) {
        user_.removeTask(this);
        user_ = user;
    }

    public boolean isDifficult() {
        return difficulty>=6;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public User getUser_() {
        return user_;
    }

    public Date getDead_line() {
        return dead_line;
    }

}
