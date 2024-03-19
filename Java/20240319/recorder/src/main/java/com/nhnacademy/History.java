package com.nhnacademy;

public class History {
    
    int matches = 0;
    int wins;

    public History(int matches, int wins) {
        this.matches = matches;
        this.wins = wins;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getMatches() {
        return matches;
    }

    public int getWins() {
        return wins;
    }
}
