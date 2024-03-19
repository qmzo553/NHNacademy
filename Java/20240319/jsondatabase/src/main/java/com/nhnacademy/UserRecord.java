package com.nhnacademy;

public class UserRecord {
    
    int matches;
    int wins;

    public UserRecord(int matches, int wins) {
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
