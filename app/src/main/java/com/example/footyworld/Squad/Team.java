package com.example.footyworld.Squad;

import java.io.Serializable;
import java.util.List;


public class Team implements Serializable {

    String gk;
    String[] defenders;
    List<String> listDefenders;
    String[] midfielders;
    List<String> listMidfielders;
    String[] attackers;
    List<String> listAttackers;
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

    public List<String> getListDefenders() {
        return listDefenders;
    }

    public void setListDefenders(List<String> listDefenders) {
        this.listDefenders = listDefenders;
    }


    //MIDFIELDERS
    public String[] getMidfielders() {
        return midfielders;
    }

    public void setMidfielders(String[] midfielders) {
      this.midfielders = midfielders;
    }

    public List<String> getListMidfielders() {
        return listMidfielders;
    }

    public void setListMidfielders(List<String> listMidfielders) {
        this.listMidfielders = listMidfielders;
    }

    //ATTACKERS
    public String[] getAttackers() {
        return attackers;
    }

    public void setAttackers(String[] attackers) {
       this.attackers = attackers;
    }

    public List<String> getChemAttackers() {
        return listAttackers;
    }

    public void setListAttackers(List<String> listAttackers) {
        this.listAttackers = listAttackers;
    }




    //CHEMISTRY
    public int getChem() {
        return chem;
    }

    public void setChem(int chem) {
        this.chem = chem;
    }
}
