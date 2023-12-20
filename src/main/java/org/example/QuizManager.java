package org.example;
import java.util.HashMap;
import java.util.Scanner;


public class QuizManager {
    public static HashMap<Integer, Participants> participants = new HashMap<>();// map of all participants
    public static  String instructorCSVPath = "Instructors.csv";
    public static  String participantCSVPath = "Participants.csv";
    public static HashMap<Integer, Quiz> mapOfQuiz = new HashMap<>();

    public static HashMap<Integer, Instructor> instructors = new HashMap<>();// map of all instructors

    public static void quizManager(Scanner scanner) {
        CSV.createFile(instructorCSVPath);
        CSV.createFile(participantCSVPath);

        while (true) {
            printRoleSelection();

            int roleChoice = scanner.nextInt();
            scanner.nextLine();

            switch (roleChoice) {
                case 1:
                    instructorMenu(scanner);
                    break;
                case 2:
                    participantMenu(scanner);
                    break;
                case 0:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 0.");
            }
        }
    }

    private static void printRoleSelection() {
        System.out.println("Choose your role:");
        System.out.println("[1] Instructor");
        System.out.println("[2] Participant");
        System.out.println("[0] Exit");
        System.out.print("Enter your choice: ");
    }

    private static void instructorMenu(Scanner scanner) {
        System.out.println("Welcome to the Instructor Menu......!");

        while (true) {
            printInstructorMenuOptions();

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                if (choice == 0) {
                    break;
                } else {
                    handleInstructorChoice(scanner, choice);
                }
            } catch (Exception exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        }
    }

    private static void printInstructorMenuOptions() {
        System.out.println("[1] Add new quiz.");
        System.out.println("[2] Manage quiz.");
        System.out.println("[3] Are you a new Instructor .");
        System.out.println("[0] Back to main menu.");
        System.out.print("Enter your choice: ");
    }

    private static void handleInstructorChoice(Scanner scanner, int choice) throws INF, QNF {
        switch (choice) {
            case 1:
                addQuiz(scanner);
                break;
            case 2:
                manageQuiz(scanner);
                break;
            case 3:
                createInstructor(scanner);
                break;
            default:
                System.out.println("Invalid choice. Please enter 1, 2, 3 or 0.");
        }
    }

    private static void participantMenu(Scanner scanner) {
        System.out.println("Welcome to the Participant Menu!");

        while (true) {
            printParticipantMenuOptions();

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                if (choice == 0) {
                    break;
                } else {
                    handleParticipantChoice(scanner, choice);
                }
            } catch (Exception exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        }
    }

    private static void printParticipantMenuOptions() {

        System.out.println("[1] Play Quiz.");
        System.out.println("[2] View your score.");
        System.out.println("[3] Are you a new Participant .");
        System.out.println("[0] Back to main menu.");
        System.out.print("Enter your choice: ");
    }

    private static void handleParticipantChoice(Scanner scanner, int choice) throws QNF, PNF {
        switch (choice) {
            case 1:
                playQuiz(scanner);
                break;
            case 2:
                scoreOfParticipant(scanner);
                break;

            case 3:
                createParticipant(scanner);
                break;

            default:
                System.out.println("Invalid choice. Please enter 1, 2, 3 or 0.");
        }
    }

    public static void addQuiz(Scanner scanner) throws INF {
        System.out.print("Enter your instructor UserID: ");
        int instructorUserId = scanner.nextInt();
        scanner.nextLine();

        validateInstructor(instructorUserId);

        System.out.print("Enter quiz ID: ");
        int newQuizId = scanner.nextInt();
        scanner.nextLine();

        validateQuizNotExists(newQuizId);

        Quiz newQuiz = new Quiz(newQuizId, scanner);
        mapOfQuiz.put(newQuizId, newQuiz);
        instructors.get(instructorUserId).addQuiz(newQuizId, newQuiz);
    }

    static void playQuiz(Scanner scanner) throws QNF, PNF {
        System.out.print("Enter participant's UserID: ");
        int particpantUserId = scanner.nextInt();
        scanner.nextLine();

        validatePlayer(particpantUserId);

        System.out.print("Enter quiz ID: ");
        int play_quiz_id = scanner.nextInt();
        scanner.nextLine();

        validateQuiz(play_quiz_id);

        Quiz player_quiz = mapOfQuiz.get(play_quiz_id);
        double score = player_quiz.playQuiz(scanner);
        participants.get(particpantUserId).updateScore(play_quiz_id, score);
        System.out.println("\nYou Completed Quiz. Your score is " + score + ".....!"+"\n");
    }

     static void createParticipant(Scanner scanner) {
        System.out.print("Enter participant UserID: ");
        int newParticipantUserId = scanner.nextInt();
        scanner.nextLine();

        validatePlayerNotExists(newParticipantUserId);

        System.out.print("Enter participant Email: ");
        String newParticipantUserName = scanner.nextLine();
        Participants newParticipant = new Participants(newParticipantUserId, newParticipantUserName);
        participants.put(newParticipantUserId, newParticipant);
        CSV.writeParticipant(newParticipant, participantCSVPath);
        System.out.println("Particpant added......!\n");
    }
static void createParticipantInCSV(Scanner scanner){
    System.out.print("Enter participant UserID: ");
    int newParticipantUserId = scanner.nextInt();
    scanner.nextLine();

//    validatePlayerNotExists(newParticipantUserId);

    System.out.print("Enter participant Email: ");
    String newParticipantUserName = scanner.nextLine();
    Participants newParticipant = new Participants(newParticipantUserId, newParticipantUserName);
    participants.put(newParticipantUserId, newParticipant);
    CSV.writeParticipant(newParticipant, participantCSVPath);
    System.out.println("Particpant added......!\n");

}

    static void createInstructorsInCSV(Scanner scanner){
        System.out.print("Enter Instructor UserID: ");
        int newInstructorUserId = scanner.nextInt();
        scanner.nextLine();

//    validatePlayerNotExists(newParticipantUserId);

        System.out.print("Enter Instructor Email: ");
        String newInstructorUserName = scanner.nextLine();
        Instructor instructor = new Instructor(newInstructorUserId, newInstructorUserName);
//        instructors.put(newInstructorUserId, instructor);
        CSV.writeInstructor(instructor, instructorCSVPath);
        System.out.println("Particpant added......!\n");

    }
    static void createInstructor(Scanner scanner) {
        System.out.print("Enter instructor UserID: ");
        int newInstructorUserId = scanner.nextInt();
        scanner.nextLine();

        validateInstructorNotExists(newInstructorUserId);

        System.out.print("Enter instructor Email: ");
        String newInstructorUserName = scanner.nextLine();
        Instructor newInstructor = new Instructor(newInstructorUserId, newInstructorUserName);
        instructors.put(newInstructorUserId, newInstructor);
        CSV.writeInstructor(newInstructor, instructorCSVPath);
        System.out.println("Instructor Addded....!\n");
    }

    private static void scoreOfParticipant(Scanner scanner) throws QNF, PNF {
        System.out.print("Enter participant's ID: ");
        int searchParticipantInMap = scanner.nextInt();
        scanner.nextLine();

        validatePlayer(searchParticipantInMap);

        System.out.print("Enter quiz ID: ");
        int searchQuizInMap = scanner.nextInt();
        scanner.nextLine();

        validateQuiz(searchQuizInMap);

        double participantScore = participants.get(searchParticipantInMap).getScore(searchQuizInMap);
        if (participantScore != Double.MIN_VALUE) {
            System.out.println("Participant " + searchParticipantInMap + " scored " + participantScore
                    + " in quiz " + searchQuizInMap + ".\n");
        }
    }

    private static void manageQuiz(Scanner scanner) throws QNF, INF {
        System.out.print("Enter instructor ID: ");
        int instructorUserId = scanner.nextInt();
        scanner.nextLine();

        validateInstructor(instructorUserId);

        System.out.print("Enter quiz ID: ");
        int quizId = scanner.nextInt();
        scanner.nextLine();

        validateQuiz(quizId);

        instructors.get(instructorUserId).manageQuiz(quizId, scanner);
    }

    private static void validatePlayer(int participantUserId) throws PNF {
        if (!participants.containsKey(participantUserId)) {
            throw new PNF("Participant not found...........!\n");
        }
    }

    private static void validatePlayerNotExists(int participantUserId) {
        if (participants.containsKey(participantUserId)) {
            System.out.println("You cannot create an already existing participant.......!\n");
            throw new IllegalArgumentException();
        }
    }

    private static void validateQuiz(int quizId) throws QNF {
        if (!mapOfQuiz.containsKey(quizId)) {
            throw new QNF("Quiz not found!\n");
        }
    }

    private static void validateQuizNotExists(int quizId) {
        if (mapOfQuiz.containsKey(quizId)) {
            System.out.println("Quiz already exists!\n");
            throw new IllegalArgumentException();
        }
    }

    private static void validateInstructor(int instructorId) throws INF {
        if (!instructors.containsKey(instructorId)) {
            throw new INF("Instructor not found!\n");
        }
    }

    private static void validateInstructorNotExists(int instructorId) {
        if (instructors.containsKey(instructorId)) {
            System.out.println("Instructor already exists!\n");
            throw new IllegalArgumentException();
        }
    }
}
