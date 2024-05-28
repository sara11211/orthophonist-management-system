package com.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestExercice extends Test implements Serializable {
    private List<Exercice> exercices;
    private static final long serialVersionUID = 1L;

    public TestExercice(String name, String description) {
        super(name,description);
        this.exercices = new ArrayList<>();
    }

    public List<Exercice> getExercices() {
        return exercices;
    }

    public void addExercice(Exercice exercice) {
        this.exercices.add(exercice);
    }

    public double calculateAverageScore() {
        if (exercices == null || exercices.isEmpty()) {
            return 0;
        }
        int totalScore = exercices.stream().mapToInt(Exercice::getScore).sum();
        return (double) totalScore / exercices.size();
    }
}

