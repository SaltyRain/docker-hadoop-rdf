package org.rdf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

// Create a CSV file with 10000 records of patient data
public class PatientDataGenerator {

    public static void main(String[] args) {
        List<String> firstNames = Arrays.asList("Robert", "Alice", "John", "Maria", "Steve", "Rachel", "Michael", "Laura", "Nick", "Sophia");
        List<String> lastNames = Arrays.asList("Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Garcia", "Rodriguez", "Wilson");
        List<String> diagnoses = Arrays.asList("Flu", "Cold", "Allergy", "Asthma", "Diabetes", "Hypertension", "Bronchitis", "Arthritis", "COVID-19", "Migraine", "Anxiety", "Depression", "Insomnia", "Obesity", "Heart Disease");

        List<String[]> data = generateData(firstNames, lastNames, diagnoses, 10000); // Generate 10000 records
        String filePath = "patient_data.csv";

        try {
            saveToCSV(data, filePath);
            System.out.println("Data saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private static List<String[]> generateData(List<String> firstNames, List<String> lastNames, List<String> diagnoses, int recordCount) {
        List<String[]> data = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < recordCount; i++) {
            String firstName = firstNames.get(random.nextInt(firstNames.size()));
            String lastName = lastNames.get(random.nextInt(lastNames.size()));
            String uniqueIdentifier = String.format("%04d", i); // Generate a unique identifier
            String fullName = firstName + " " + lastName + "_" + uniqueIdentifier; // Append the unique identifier to the name
            String diagnosis = diagnoses.get(random.nextInt(diagnoses.size()));
            data.add(new String[]{fullName, diagnosis});
        }

        return data;
    }

    private static void saveToCSV(List<String[]> data, String filePath) throws IOException {
        FileWriter csvWriter = new FileWriter(filePath);

        for (String[] record : data) {
            csvWriter.append(String.join(",", record));
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }
}
