package com.isys221group9.tic_tac_toe.models;

// player class
public class Player {
    // players name
    private String name;
    // players marker
    private char marker;

    // Player constructor
    public Player(String name, char marker) {
        this.name = name;
    }

    // Get Player Name
    public String getName() {
        return name;
    }

    // Get Player Marker
    public char getMarker() {
        return marker;
    }

    // Set Player Name
    public void setName(String name) {
        this.name = name;
    }

    // Set Player Marker
    public void setMarker(char marker) {
        this.marker = marker;
    }
}
