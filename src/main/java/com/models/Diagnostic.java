package com.models;
import java.io.Serializable;
import java.util.HashSet;


public class Diagnostic implements Serializable {

    private static final long serialVersionUID = 1L;
    private HashSet<Trouble> troubles;

    public Diagnostic(HashSet<Trouble> troubles) {
        this.troubles = troubles;
    }
    public void setTroubles(HashSet<Trouble> troubles) {
        troubles = new HashSet<>();
    }
    public HashSet<Trouble> getTroubles() {
        return troubles;
    }
}