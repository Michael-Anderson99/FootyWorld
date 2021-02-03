package com.example.footyworld.Squad;

public class Player
{
    String playerName;
    String position1;
    String position;
    String playerListID;

    public Player() {
    }

    public Player(String playerName, String position1, String position, String playerListID) {
        this.playerName = playerName;
        this.position1 = position1;
        this.position = position;
        this.playerListID = playerListID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPosition1() {
        return position1;
    }

    public String getPosition() {
        return position;
    }

    public String getPlayerListID() {
    return playerListID;
}
}
