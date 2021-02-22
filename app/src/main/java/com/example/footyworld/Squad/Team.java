package com.example.footyworld.Squad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Team implements Serializable {
    //String[] gk;
    String[] defenders;
    String chemDefenders1, defenders2;
    String[] midfielders;
    String chemMidfielders;
    String[] attackers;
    String chemAttackers;



    int chem = 0;


    public Team(){};

    public Team(String[] defenders, String[] midfielders, String[] attackers) {
        //this.gk = gk;
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

    //DEFENDERS
    public String[] getDefenders() {
        return defenders;
    }

    public String getChemDefenders1() {
        return chemDefenders1;
    }

    public void setChemDefenders1(String chemDefenders1) {
        this.chemDefenders1 = chemDefenders1;
    }

    public void setDefenders(String[] defenders) {

        this.defenders = defenders;

    }


    //MIDFIELDERS
    public String[] getMidfielders() {
        return midfielders;
    }

    public void setMidfielders(String[] midfielders) {
      this.midfielders = midfielders;
    }

    public String getChemMidfielders() {
        return chemMidfielders;
    }

    public void setChemMidfielders(String chemMidfielders) {
        this.chemMidfielders = chemMidfielders;
    }


    //ATTACKERS
    public String[] getAttackers() {
        return attackers;
    }

    public void setAttackers(String[] attackers) {
       this.attackers = attackers;
    }

    public String getChemAttackers() {
        return chemAttackers;
    }

    public void setChemAttackers(String chemAttackers) {
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
