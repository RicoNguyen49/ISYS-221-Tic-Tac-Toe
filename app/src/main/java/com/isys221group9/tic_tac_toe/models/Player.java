package com.isys221group9.tic_tac_toe.models;

// player class
public class Player {
    // players name
    private final String name;
    // players marker
    private final char marker;

    // Player constructor
    public Player(String name, char marker) {
        this.name = name;
        this.marker = marker;
    }

    // Get Player Name
    public String getName() {
        return name;
    }

    // Get Player Marker
    public char getMarker() {
        return marker;
    }

}
