package com.models;

import java.io.Serializable;
import java.util.HashSet;

public class TestQuestionnaire extends Test implements Serializable {
    private HashSet<Question> questions;
    private static final long serialVersionUID = 1L;

    public TestQuestionnaire(String nom, String description, HashSet<Question> questions) {
        super(nom, description);
        this.questions = questions;
    }

    public HashSet<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(HashSet<Question> questions) {
        this.questions = questions;
    }

    public double calculateAverageScore() {
        if (questions == null || questions.isEmpty()) {
            return 0;
        }
        int totalScore = 0;
        for (Question question : questions) {
            totalScore += question.getScore();
        }
        return (double) totalScore / questions.size();
    }
}
