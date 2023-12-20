package org.example;


import java.util.HashMap;

public class Participants {
    private static final double DEFAULT_SCORE = Double.MIN_VALUE;

    int participantUserId;
    private String participantEmail;
    private HashMap<Integer, Double> onePlayerManyQuizzes;

    public Participants(int participantUserId, String participantEmail) {
        this.participantUserId = participantUserId;
        this.participantEmail = participantEmail;
        this.onePlayerManyQuizzes = new HashMap<>();
    }

    public void updateScore(int quizID, double score) {
        onePlayerManyQuizzes.put(quizID, score);
    }

    public double getScore(int quizID) {
        double score = onePlayerManyQuizzes.getOrDefault(quizID, DEFAULT_SCORE);
        if (score == DEFAULT_SCORE) {
            System.out.println("You didnt participate in this.\n");
        }
        return score;
    }

    @Override
    public String toString() {
        return String.format("%d,%s", participantUserId, participantEmail);
    }
}

