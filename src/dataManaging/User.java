/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataManaging;

import java.util.ArrayList;

public class User {
    private String uuid_;
    private String pass_;
    private String fullName_;
    private String team_;
    private String uuidManager_;
    private String jobTitle_;
    private String programmingLanguage_;
    private ArrayList<Integer> skills_;

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

    public String getNumberOfTasks() {
        return "PULA";
    }
   
}