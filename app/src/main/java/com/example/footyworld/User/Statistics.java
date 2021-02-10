package com.example.footyworld.User;

import java.util.Date;

public class Statistics {

    String UserID;
    Date date;

    int goals;
    int assists;
    int saves;
    String gooodPlayMoments;

    public Statistics(){};

    public Statistics(String userID, Date date, int goals, int assists, int saves, String gooodPlayMoments) {

        this.UserID = userID;
        this.date = date;
        this.goals = goals;
        this.assists = assists;
        this.saves = saves;
        this.gooodPlayMoments = gooodPlayMoments;

    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getSaves() {
        return saves;
    }

    public void setSaves(int saves) {
        this.saves = saves;
    }

    public String getGooodPlayMoments() {
        return gooodPlayMoments;
    }

    public void setGooodPlayMoments(String gooodPlayMoments) {
        this.gooodPlayMoments = gooodPlayMoments;
    }

}
