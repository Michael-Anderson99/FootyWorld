package com.example.footyworld.Squad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Team implements Serializable {

    String gk;
    String[] defenders;
    List<String> chemDefenders;
    String[] midfielders;
    List<String> chemMidfielders;
    String[] attackers;
    List<String> chemAttackers;
    int chem = 0;


    public Team(){};

    public Team(String gk, String[] defenders, String[] midfielders, String[] attackers) {
        this.gk = gk;
        this.defenders = defenders;
        this.midfielders = midfielders;
        this.attackers = attackers;
    }

    public Team(Team team) {
    }

    /*
        public String[] getGk() {
            return gk;
        }

        public void setGk(String[] gk) {
            this.gk = gk;
        }
    */

    //GOALKEEPERS
    public String getGk() {
        return gk;
    }

    public void setGk(String gk) {
        this.gk = gk;
    }


    //DEFENDERS
    public String[] getDefenders() {
        return defenders;
    }

    public void setDefenders(String[] defenders) {

        this.defenders = defenders;

    }

    public List<String> getChemDefenders() {
        return chemDefenders;
    }

    public void setChemDefenders(List<String> chemDefenders) {
        this.chemDefenders = chemDefenders;
    }


    //MIDFIELDERS
    public String[] getMidfielders() {
        return midfielders;
    }

    public void setMidfielders(String[] midfielders) {
      this.midfielders = midfielders;
    }

    public List<String> getChemMidfielders() {
        return chemMidfielders;
    }

    public void setChemMidfielders(List<String> chemMidfielders) {
        this.chemMidfielders = chemMidfielders;
    }

    //ATTACKERS
    public String[] getAttackers() {
        return attackers;
    }

    public void setAttackers(String[] attackers) {
       this.attackers = attackers;
    }

    public List<String> getChemAttackers() {
        return chemAttackers;
    }

    public void setChemAttackers(List<String> chemAttackers) {
        this.chemAttackers = chemAttackers;
    }




    //CHEMISTRY
    public int getChem() {
        return chem;
    }

    public void setChem(int chem) {
        this.chem = chem;
    }
}
