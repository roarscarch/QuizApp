package org.example;


public class Question {
    private String question;
    private String[] options;
    private int correctOption;

    public Question(String question, String option1, String option2 ,int correctOption) {
        this.question = question;
        this.options = new String[]{option1, option2};
        this.correctOption = correctOption;
    }

    public boolean checkAnswer(int choice) {
        return choice == this.correctOption;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.question);

        for (int i = 0; i < options.length; i++) {
            stringBuilder.append(String.format("\n\t%d) %s", i + 1, options[i]));
        }

        return stringBuilder.toString();
    }
}

