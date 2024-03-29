package com.nhnacademy;

import java.util.HashSet;

public abstract class Node {
    static HashSet<String> nodeId = new HashSet<>();
    String identifier;
    String name;

    protected Node(String name, String identifier) {
        if(nodeId.contains(identifier)) {
            throw new IllegalArgumentException();
        }

        this.name = name;
        this.identifier = identifier;
        nodeId.add(identifier);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public String getIdentifier() { 
        return identifier;
    }
}
