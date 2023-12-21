package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Instructor {
    private static final int EXIT_CHOICE = 0;
    private static final int ADD_QUESTION_CHOICE = 1;
    private static final int EDIT_QUESTION_CHOICE = 2;
    private static final int DELETE_QUESTION_CHOICE = 3;

    private static final String MENU_MESSAGE = "\tQuiz Operations for an Instructor .";
    private static final String ADD_QUESTION_MESSAGE = " Press one to add a new question.";
    private static final String EDIT_QUESTION_MESSAGE = "Press two to edit a Question.";
    private static final String DELETE_QUESTION_MESSAGE = "Press three to Delete";
    private static final String ENTER_CHOICE_MESSAGE = "\tEnter your choice or press 0 to exit: ";

    int instructorUserID;
    String InstructorEmail;
    HashMap<Integer, Quiz> mapOfAllQuiz;// every quiz has their own id

    public Instructor(int instructorUserID, String InstructorEmail) {
        this.instructorUserID = instructorUserID;
        this.InstructorEmail = InstructorEmail;
        mapOfAllQuiz = new HashMap<>();// every instructor is assigned his own map of quizzes
    }

    public void addQuiz(int quizID, Quiz quiz) {
        this.mapOfAllQuiz.put(quizID, quiz);
    }

    public void manageQuiz(int quizID, Scanner scanner) {
        if (this.mapOfAllQuiz.containsKey(quizID)) {
            Quiz quiz = mapOfAllQuiz.get(quizID);
            System.out.println(MENU_MESSAGE);
            System.out.println(ADD_QUESTION_MESSAGE);
            System.out.println(EDIT_QUESTION_MESSAGE);
            System.out.println(DELETE_QUESTION_MESSAGE);
            System.out.print(ENTER_CHOICE_MESSAGE);

            try {
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case EXIT_CHOICE:
                        return;
                    case ADD_QUESTION_CHOICE:
                        quiz.addNewQuestion(scanner);
                        break;
                    case EDIT_QUESTION_CHOICE:
                        EditQuestion(quiz, scanner);
                        break;
                    case DELETE_QUESTION_CHOICE:
                        DeleteQuestion(quiz, scanner);
                        break;
                    default:
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            }
        } else {
            System.out.println("Instructor does not have this quiz.");
        }
    }

    private void EditQuestion(Quiz quiz, Scanner scanner) {
        System.out.println("This is the Quiz you wanna Edit");
        System.out.println(quiz);
        System.out.print("\nWhich Question number to Edit");
        int editQuestion = scanner.nextInt();
        scanner.nextLine();
        quiz.editQuestion(editQuestion, scanner);
    }

    private void DeleteQuestion(Quiz quiz, Scanner scanner) {
        System.out.println("\n This is the quiz you wanna Delete");
        System.out.println(quiz);
        System.out.print("\nWhich question do you want to delete ");
        int deleteQuestion = scanner.nextInt();
        scanner.nextLine();
        quiz.deleteQuestion(deleteQuestion);
        System.out.println("Question deleted successfully!\n");
    }

    public void getQuizzesByDifficulty(int difficulty) {
        if (difficulty != 1 && difficulty != 2 && difficulty != 3) {
            System.out.println("Please enter valid difficulty.\n");
        }
        QuizTag quizTag = difficulty == 1 ? QuizTag.EASY
                : difficulty == 2 ? QuizTag.MEDIUM : QuizTag.HARD;
        List<Quiz> filteredQuizzes = mapOfAllQuiz.values().stream()
                .filter(quiz -> quiz.difficulty == quizTag)
                .collect(Collectors.toCollection(ArrayList::new));
        if (filteredQuizzes.size() != 0) {
            System.out.println("Following are the quizzes of difficulty " + quizTag);
            for (Quiz quiz : filteredQuizzes) {
                System.out.println("-- Quiz ID: " + quiz.quizID);
            }
            System.out.println();
        } else {
            System.out.println("No quizzes of difficulty " + quizTag + " found!\n");
        }
    }

    @Override
    public String toString() {
        return String.format("%d,%s", this.instructorUserID, this.InstructorEmail);
    }
}
