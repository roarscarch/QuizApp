package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.*;

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
        String input = "225\nuser@example.com\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        QuizManager.createParticipantInCSV(scanner);
        int countAfterAdd= CSV.countEntriesInCSV("Participants.csv");

        assertEquals(countBeforeAdd + 1, countAfterAdd);
    }


    @Test
    public void addInstructorInCSV() throws IOException {

        int countBeforeAdd= CSV.countEntriesInCSV("Instructors.csv");
        String input = "225\nuser@example.com\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        QuizManager.createInstructorsInCSV(scanner);
        int countAfterAdd= CSV.countEntriesInCSV("Instructors.csv");

        assertEquals(countBeforeAdd + 1, countAfterAdd);
    }




}