package org.example;



import java.util.ArrayList;
import java.util.Scanner;

public class Quiz {
    private int quizID;
    private ArrayList<Question> questions;

    public Quiz(int quizID, Scanner scanner) {
        this.quizID = quizID;
        this.questions = new ArrayList<>();
        createQuiz(scanner);
    }

    private void createQuiz(Scanner scanner) {
        System.out.print("Enter number of questions for quiz: ");
        int num_of_questions = scanner.nextInt();
        scanner.nextLine();

        for (int ques_num = 1; ques_num <= num_of_questions; ques_num++) {
            addNewQuestion(scanner);
        }
        System.out.println("Quiz created successfully.........!\n");
    }

    public double playQuiz(Scanner sc) {
        int[] choices = new int[this.questions.size()];
        for (int i = 0; i < this.questions.size(); i++) {
            System.out.println(questions.get(i));
            System.out.print("Enter your answer from 2 options: ");
            choices[i] = sc.nextInt();
            sc.nextLine();
            System.out.println();
        }
        return scoreQuiz(choices);
    }

    public double scoreQuiz(int[] choices) {
        double correct = 0;
        for (int i = 0; i < choices.length; i++) {
            if (choices[i] != 0 && this.questions.get(i).checkAnswer(choices[i])) {
                correct += 10.0;
            }
        }
        return (correct);
    }

    public void addNewQuestion(Scanner scanner ) {
        System.out.print("\nEnter question........! ");
        String question = scanner.nextLine();

        System.out.print("\tEnter option 1........! ");
        String option1 = scanner.nextLine();

        System.out.print("\tEnter option 2.........!");
        String option2 = scanner.nextLine();



        System.out.print("\tEnter correct answer option.....! ");
        int correctAnswer = scanner.nextInt();
        scanner.nextLine();

        Question q = new Question(question, option1, option2, correctAnswer);
        this.questions.add(q);
        System.out.println("Question Added......!\n");
    }

    public void editQuestion(int questionIndex, Scanner scanner) {
        deleteQuestion(questionIndex);
        addNewQuestion(scanner);
    }

    public void deleteQuestion(int questionIndex) {
        if (questionIndex > 0 && questionIndex <= this.questions.size()) {
            this.questions.remove(questionIndex - 1);
            System.out.println("Question Deleted....!\n");
        } else {
            System.out.println("Write Proper Question to Delete.\n");
        }
    }

    @Override
    public String toString() {
        if (this.questions.isEmpty()) {
            return "Quiz is empty.";
        }
        StringBuilder quiz = new StringBuilder();
        for (int questionNumber = 1; questionNumber <= this.questions.size(); questionNumber++) {
            quiz.append(String.format("\n%d)\n%s", questionNumber, this.questions.get(questionNumber - 1)));
        }
        return quiz.toString();
    }
}

