package com.example.footyworld.Squad;

import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Serializable {
    //String[] gk;
    String[] defenders;
    String[] midfielders;
    String[] attackers;

    public Team(){};

    public Team(String[] defenders, String[] midfielders, String[] attackers) {
        //this.gk = gk;
        this.defenders = defenders;
        this.midfielders = midfielders;
        this.attackers = attackers;
    }
/*
    public String[] getGk() {
        return gk;
    }

    public void setGk(String[] gk) {
        this.gk = gk;
    }
*/
    public String[] getDefenders() {
        return defenders;
    }

    public void setDefenders(String[] defenders) {

        this.defenders = defenders;

    }

    public String[] getMidfielders() {
        return midfielders;
    }

    public void setMidfielders(String[] midfielders) {
      this.midfielders = midfielders;
    }

    public String[] getAttackers() {
        return attackers;
    }

    public void setAttackers(String[] attackers) {
       this.attackers = attackers;
    }


}
