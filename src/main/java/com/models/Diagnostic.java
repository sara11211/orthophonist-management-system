package com.models;
import java.util.HashSet;


public class Diagnostic {
    private HashSet<Trouble> troubles;

    public void setTroubles(HashSet<Trouble> troubles) {
        this.troubles = troubles;
    }
    public HashSet<Trouble> getTroubles() {
        return troubles;
    }
}
