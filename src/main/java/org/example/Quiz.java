package org.example;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Quiz {
    int quizID;
    private ArrayList<Question> questions;
    int[] timers;
    LeaderBoard leaderBoard;
    QuizTag difficulty;

    public Quiz(int quizID, QuizTag difficulty, Scanner scanner) {
        this.quizID = quizID;
        this.difficulty = difficulty;

        this.leaderBoard = new LeaderBoard();
        // create the quiz
        this.questions = new ArrayList<>();

        this.timers = new int[this.questions.size()];


        initializeTimers();
        createQuiz(scanner);
    }

    private void createQuiz(Scanner sc) {
        System.out.print("Enter number of questions for quiz: ");
        int num_of_questions = sc.nextInt();
        sc.nextLine();

        for (int ques_num = 1; ques_num <= num_of_questions; ques_num++) {
            System.out.print("\nEnter question " + ques_num + ": ");
            String question = sc.nextLine();

            System.out.print("Question Type? MCQ (1) OPEN_ENDED (2) ");
            int type = sc.nextInt();
            sc.nextLine();

            QuestionLevel questionLevel = (type == 1) ? QuestionLevel.MCQ : QuestionLevel.OPEN_ENDED;

            String option1 = "";
            String option2 = "";
            int correct_option = 0;
            String correct_ans = "";

            if (questionLevel == QuestionLevel.MCQ) {
                System.out.print("\tEnter option..... 1 ");
                option1 = sc.nextLine();

                System.out.print("\tEnter option .....2 ");
                option2 = sc.nextLine();

                System.out.print("Enter Correct option.Either 1 or 2 ");
                correct_option = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.print("Enter correct answer: ");
                correct_ans = sc.nextLine();
            }

            System.out.print("Enter timer Duration : ");
            int timerDuration = sc.nextInt();
            sc.nextLine();

            Question q = (questionLevel == QuestionLevel.MCQ) ?
                    new Question(question, option1, option2, correct_option, timerDuration) :
                    new Question(question, correct_ans, timerDuration);

            this.questions.add(q);
            System.out.println("Question added successfully.......!\n");
        }
        System.out.println("Quiz created successfully........!\n");
    }


    public double playQuiz(Scanner sc, int playerID) {
        Collections.shuffle(questions);
        ArrayList<Integer> choices = new ArrayList<>();
        ArrayList<String> answers = new ArrayList<>();
        for (int i = 0; i < this.questions.size(); i++) {
            System.out.println(questions.get(i));
            if (questions.get(i).questionLevel.equals(QuestionLevel.MCQ)) {
                System.out.print("Enter Answer. Either 1 or 2 ");
                int choice = sc.nextInt();
                sc.nextLine();
                choices.add(choice);
            } else {
                System.out.print("Enter your answer (enter text): ");
                String answer = sc.nextLine();
                answers.add(answer);
            }
            System.out.println();
        }
        return scoreQuiz(choices, answers, playerID);
    }


    public double scoreQuiz(ArrayList<Integer> choices, ArrayList<String> answers, int playerID) {
        System.out.println("\nQuiz Feedback......! ");
        double correct = 0;
        int mcqidx = 0;
        int openidx = 0;

        for (int i = 0; i < this.questions.size(); i++) {
            Question currentQuestion = this.questions.get(i);
            System.out.print("Question " + (i + 1) + ") ");

            if (currentQuestion.questionLevel.equals(QuestionLevel.MCQ)) {
                int selectedChoice = choices.get(mcqidx);

                if (selectedChoice != 0 && currentQuestion.checkAnswer(selectedChoice)) {
                    correct += 10.0;
                    System.out.print("Your answer is correct.......!\n");
                } else {
                    System.out.print("Your answer is incorrect. Expected answer: " + currentQuestion.getAnswerMCQ() + "\n");
                }
                mcqidx++;
            } else {
                String userAnswer = answers.get(openidx);

                if (!userAnswer.isEmpty() && currentQuestion.checkAnswer(userAnswer)) {
                    correct += 10.0;
                    System.out.print("Your answer is correct!\n");
                } else {
                    System.out.print("Incorrect answers. Expected answer: " + currentQuestion.getAnswerOpen() + "\n");
                }
                openidx++;
            }
        }

        double score = (correct );
        this.leaderBoard.updateLeaderboard(playerID, score);
        return score;
    }

    public void addNewQuestion(Scanner scanner) {
        String op_1 = "";
        String op_2 = "";
        int correct_option = 0;
        String correct_ans = "";
        QuestionLevel questionLevel;
        System.out.print("\n\tEnter question........! ");
        String question = scanner.nextLine();

        System.out.print("\tEnter Question Type MCQ-1 OPEN-ENDED-2.....!  ");
        int type = scanner.nextInt();
        scanner.nextLine();

        if (type == 1) {
            questionLevel = QuestionLevel.MCQ;

            System.out.print("\t\tEnter option 1.......!  ");
            op_1 = scanner.nextLine();

            System.out.print("\t\tEnter option 2.........! ");
            op_2 = scanner.nextLine();


            System.out.print("\tCorrect Answer.Either 1 or 2......!  ");
            correct_option = scanner.nextInt();
            scanner.nextLine();
        } else {
            questionLevel = QuestionLevel.OPEN_ENDED;

            System.out.print("\tEnter correct answer: ");
            correct_ans = scanner.nextLine();
        }

        System.out.print("\tEnter timer duration for this question (in seconds): ");
        int timerDuration = scanner.nextInt();
        scanner.nextLine();

        Question q;
        if (questionLevel.equals(QuestionLevel.MCQ)) {
            q = new Question(question, op_1, op_2, correct_option, timerDuration);
        } else {
            q = new Question(question, correct_ans, timerDuration);
        }
        this.questions.add(q);
        System.out.println("\tQuestion added successfully!\n");
    }

    private void initializeTimers(){
        for(int i=0;i<this.timers.length;i++){
            this.timers[i]=this.questions.get(i).timerDuration;
        }
    }

    public void editQuestion(int questionIndex, Scanner scanner) {
        deleteQuestion(questionIndex);
        addNewQuestionAtIndex(questionIndex-1,scanner);
    }

    public void addNewQuestionAtIndex(int index, Scanner sc) {
        String op_1 = "";
        String op_2 = "";
        int correct_option = 0;
        String correct_ans = "";
        QuestionLevel questionLevel;
        System.out.print("\n\tEnter question: ");
        String question = sc.nextLine();

        System.out.print("\tEnter type of the question (1-mcq, 2-open ended): ");
        int type = sc.nextInt();
        sc.nextLine();

        if (type == 1) {
            questionLevel = QuestionLevel.MCQ;

            System.out.print("\t\tEnter option 1: ");
            op_1 = sc.nextLine();

            System.out.print("\t\tEnter option 2: ");
            op_2 = sc.nextLine();

            System.out.print("\tEnter correct answer option (enter 1/2/3): ");
            correct_option = sc.nextInt();
            sc.nextLine();
        } else {
            questionLevel = QuestionLevel.OPEN_ENDED;

            System.out.print("\t\tEnter correct answer: ");
            correct_ans = sc.nextLine();
        }

        System.out.print("\tEnter timer duration for this question (in seconds): ");
        int timerDuration = sc.nextInt();
        sc.nextLine();

        Question q;
        if (questionLevel.equals(QuestionLevel.MCQ)) {
            q = new Question(question, op_1, op_2, correct_option, timerDuration);
        } else {
            q = new Question(question, correct_ans, timerDuration);
        }
        this.questions.add(index, q);
        System.out.println("\tQuestion added successfully!\n");
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
        String quiz = "";
        int ques_num = 1;
        for (Question question : this.questions) {
            quiz += "\n\n" + ques_num + ")\n" + question.toString();
            ques_num++;
        }
        return quiz;
    }
}

