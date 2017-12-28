/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logInGUI;

import dataManaging.DataBase;
import dataManaging.User;
import exceptions.TaskWasNotSetAsDone;
import java.util.ArrayList;

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
    private ArrayList<Integer> skills_;
    private String programmerUuid;
    private Boolean done;

    public Task(User user_, String taskUuid, String nume, String programmingLanguage, int difficulty, ArrayList<Integer> skills_, String programmerUuid, Boolean done) {
        this.user_ = user_;
        this.taskUuid = taskUuid;
        this.nume = nume;
        this.programmingLanguage = programmingLanguage;
        this.difficulty = difficulty;
        this.skills_ = skills_;
        this.programmerUuid = programmerUuid;
        this.done = done;
    }

    public String getNume() {
        return nume;
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

    public String getTaskUuid() {
        return taskUuid;
    }

    public boolean isDifficult() {
        return difficulty>6;
    }
}
