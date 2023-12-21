package org.example;

import java.util.Arrays;

public class LeaderBoard {
    private static final int MAX_SIZE = 3;

    private int[] participantsUserIDs;
    private double[] scores;
    private int size;

    public LeaderBoard() {
        this.participantsUserIDs = new int[MAX_SIZE];
        this.scores = new double[MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; i++) {
            participantsUserIDs[i] = -1;
            scores[i] = -1.0;
        }
        this.size = 0;
    }

    public void updateLeaderboard(int playerID, double score) {
        if (size < MAX_SIZE || score > scores[0]) {
            int insertIndex = 0;
            while (insertIndex < size && score <= scores[insertIndex]) {
                insertIndex++;
            }

            // Merge the shiftElementsDown logic
            for (int i = size - 1; i > insertIndex; i--) {
                participantsUserIDs[i] = participantsUserIDs[i - 1];
                scores[i] = scores[i - 1];
            }

            // Update the leaderboard with new data
            participantsUserIDs[insertIndex] = playerID;
            scores[insertIndex] = score;

            // Adjust the size, ensuring it does not exceed the maximum size
            size = Math.min(size + 1, MAX_SIZE);
        }
    }

    // The shiftElementsDown method is no longer needed, as its logic is merged into updateLeaderboard

    public void displayLeaderboard() {
        System.out.println("Leaderboard:");

        for (int i = 0; i < size; i++) {
            System.out.println("Position " + (i + 1) + ") Participant " + participantsUserIDs[i] + " with score " + scores[i]);
        }

        System.out.println();
    }
}
