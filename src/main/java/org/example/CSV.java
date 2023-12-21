package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.nio.file.Path;
import java.util.HashSet;
import java.io.IOException;
import java.nio.file.Files;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class CSV {

    public static void createFile(String filepath) {
        if (!Files.exists(Path.of(filepath))) {
            try {
                Files.createFile(Path.of(filepath));
                System.out.println("Created file: " + filepath);
            } catch (IOException exception) {
                System.out.println("Error creating file: " + exception.getMessage());
            }
        } else {
           // System.out.println("File already exists!");
        }
    }


    private static void writeData(HashSet<Integer> setOfParticipantsOrInstructor, String filepath, String header, Object data) {
        if (Files.exists(Path.of(filepath))) {
            if (setOfParticipantsOrInstructor != null && !setOfParticipantsOrInstructor.contains(data instanceof Instructor ? ((Instructor) data).instructorUserID : ((Participants) data).participantUserId)) {
                try (PrintWriter writer = new PrintWriter(new FileWriter(filepath, true))) {
                    if (Files.size(Path.of(filepath)) == 0) {
                        writer.println(header);
                    }
                    writer.println(data);
                } catch (IOException exception) {
                    System.out.println("Error writing to file: " + exception.getMessage());
                }
            }
        }
    }


    public static void writeInstructor(Instructor instructor, String filepath) {
        HashSet<Integer> instructorMap = setOfParticipantsOrInstructor(filepath);
        writeData(instructorMap, filepath, "Instructor UserID,Instructor Email", instructor);
    }

    public static void writeParticipant(Participants participants, String filepath) {
        HashSet<Integer> participantSet = setOfParticipantsOrInstructor(filepath);
        writeData(participantSet, filepath, "Participant UserId,Participant Email", participants);
    }


    public static HashSet<Integer> setOfParticipantsOrInstructor(String filepath) {
        HashSet<Integer> set = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                int id = Integer.parseInt(line.split(",")[0].trim());
                set.add(id);
            }
        } catch (IOException | NumberFormatException exception) {
            System.out.println("Error reading file: " + exception.getMessage());
            return null;
        }

        return set;
    }

     static int countEntriesInCSV(String csvFilePath) throws IOException {
        int count = 0;

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            // Read the header (if present) to skip it
            String[] header = reader.readNext();

            // Count the entries
            while (reader.readNext() != null) {
                count++;
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return count;
    }


}
