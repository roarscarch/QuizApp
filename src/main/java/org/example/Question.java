package org.example;


public class Question {
    private String question;
    private String[] options;
    private int correctOption;
    private String correct_ans;
    QuestionLevel questionLevel;

    int timerDuration;

    public Question(String question, String option1, String option2 ,int correctOption,int timerDuration) {
        this.question = question;
        this.options = new String[]{option1, option2};
        this.correctOption = correctOption;
        this.timerDuration=timerDuration;
        this.questionLevel = QuestionLevel.MCQ;
    }

   
    public Question(String question, String correct_ans, int timerDuration) {
        this.question = question;
        this.correct_ans = correct_ans;
        this.timerDuration = timerDuration;
        this.questionLevel = QuestionLevel.OPEN_ENDED;
    }

    public boolean checkAnswer(int choice) {
        return choice == this.correctOption;
    }
    public boolean checkAnswer(String answer) {
        return answer.equalsIgnoreCase(this.correct_ans);
    }


    public int getAnswerMCQ() {
        return this.correctOption;
    }
    public String getAnswerOpen() {
        return this.correct_ans;
    }
    public QuestionLevel getQuestionType() {
        return this.questionLevel;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(this.question);

        if (this.questionLevel.equals(QuestionLevel.MCQ)) {
            for (int i = 0; i < 2; i++) {
                result.append("\n\t").append(i + 1).append(") ").append(this.options[i]);
            }
        }

        return result.toString();
    }


}

