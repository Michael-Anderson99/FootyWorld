package com.example.footyworld.Squad;

import java.util.ArrayList;

public class Squad {

    //String userId;
    String squadName;
    String playerListId;

    public Squad() {
    }

    public Squad(String squadName, String playerListId) {
        //this.userId = userId;
        this.squadName = squadName;
        this.playerListId = playerListId;
    }

    /*
    public String getUserId() {
        return userId;
    }
    */

    public String getSquadName() {
        return squadName;
    }

    public String getPlayerListId() {
        return playerListId;
    }
}
