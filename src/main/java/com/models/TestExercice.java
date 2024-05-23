package com.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestExercice extends Test implements Serializable {
    private String name;
    private String description;
    private List<Exercice> exercices;

    public TestExercice(String name, String description) {
        super(name,description);
        this.exercices = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Exercice> getExercices() {
        return exercices;
    }

    public void addExercice(Exercice exercice) {
        this.exercices.add(exercice);
    }
}

