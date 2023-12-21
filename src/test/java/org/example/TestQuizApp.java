package org.example;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestQuizApp {
    @BeforeAll
    public static void beforeall() {
        System.out.println("Started Testing");
    }

    @AfterAll
    public static void AfterAll() {
        System.out.println("Ended Testing");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Testcase start");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("Testcase end");
    }


    @Test
    public void addInstructor() {

        int instructorsBeforeAdd = QuizManager.instructors.size();
        String input = "123\nuser@example.com\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        QuizManager.createInstructor(scanner);
        int instructorsAfterAdd = QuizManager.instructors.size();
        assertEquals(instructorsBeforeAdd + 1, instructorsAfterAdd);
    }

    @Test
    public void addParticipant() {

        int participantsBeforeAdd = QuizManager.participants.size();
        String input = "123\nuser@example.com\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        QuizManager.createParticipant(scanner);
        int participantsAfterAdd = QuizManager.participants.size();
        assertEquals(participantsBeforeAdd + 1, participantsAfterAdd);
    }

    @Test
    public void addParticipantInCSV() throws IOException {

        int countBeforeAdd= CSV.countEntriesInCSV("Participants.csv");
        String input = "2234\nuser@example.com\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        QuizManager.createParticipantInCSV(scanner);
        int countAfterAdd= CSV.countEntriesInCSV("Participants.csv");

        assertEquals(countBeforeAdd + 1, countAfterAdd);
    }


    @Test
    public void addInstructorInCSV() throws IOException {

        int countBeforeAdd= CSV.countEntriesInCSV("Instructors.csv");
        String input = "229\nuser@example.com\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        QuizManager.createInstructorsInCSV(scanner);
        int countAfterAdd= CSV.countEntriesInCSV("Instructors.csv");

        assertEquals(countBeforeAdd + 1, countAfterAdd);
    }



    @Test
    public void addQuiz() throws INF {


        int countBeforeAdd=QuizManager.mapOfQuiz.size();

        String input = "123\nuser@example.com\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner createInstructor = new Scanner(System.in);
        QuizManager.createInstructor(createInstructor);
        String input2= "123\n1\n1\n1\nQ1\n2\nans1\n5\n";
        System.setIn((new ByteArrayInputStream(input2.getBytes())));
        Scanner createQuiz= new Scanner(System.in);
        QuizManager.addQuiz(createQuiz);
        int countAfterAdd=QuizManager.mapOfQuiz.size();
        assertEquals(countBeforeAdd+1,countAfterAdd);

    }
    @Test
public void addMultipleQuestions() throws INF {


        String input = "123\nuser@example.com\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner createInstructor = new Scanner(System.in);
        QuizManager.createInstructor(createInstructor);
        String input2= "123\n1\n1\n2\nQ1\n2\nans1\n5\nQ2\n2\nans2\n5\n";
        System.setIn((new ByteArrayInputStream(input2.getBytes())));
        Scanner createQuiz= new Scanner(System.in);
        QuizManager.addQuiz(createQuiz);

}

@Test
    public void addMultipleQuiz() throws INF {
    int countBeforeAdd=QuizManager.mapOfQuiz.size();
    String input = "123\nuser@example.com\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Scanner createInstructor = new Scanner(System.in);
    QuizManager.createInstructor(createInstructor);
    String input2= "123\n1\n1\n1\nQ1\n2\nans1\n5\n";

    System.setIn((new ByteArrayInputStream(input2.getBytes())));
    Scanner createQuiz= new Scanner(System.in);
    QuizManager.addQuiz(createQuiz);

    String input3="123\n2\n1\n1\nQ1\n2\nans1\n5\n";
    System.setIn((new ByteArrayInputStream(input3.getBytes())));
    Scanner createQuiz2= new Scanner(System.in);
    QuizManager.addQuiz(createQuiz2);
    int countAfterAdd=QuizManager.mapOfQuiz.size();

}

@Test
     public void playQuizWithoutAParticipant(){

        String input="989\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner= new Scanner(System.in);
        try{
            QuizManager.playQuiz(scanner);
            fail("Here exception should come");
        }
        catch(Exception exception){
            assertTrue(true);
        }

    }

    @Test
    public void createQuizWithoutAnInstructor() {

        String input="989\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner= new Scanner(System.in);
        try{
            QuizManager.addQuiz(scanner);
            fail("Here exception should come");
        }
        catch(Exception exception){
            assertTrue(true);
        }

    }




}