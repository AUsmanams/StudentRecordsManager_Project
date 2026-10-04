package StudentRecordsManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class StudentManager {

    private Person[] people;
    private int count;

    public StudentManager() {
        people = new Person[10];
        count = 0;
    }

    public void addPerson(Person person) throws InvalidStudentException {

        if (person.getAge() < 16) {
            throw new InvalidStudentException(
                "Student age must be 16 or above."
            );
        }

        if (count < people.length) {
            people[count] = person;
            count++;
        } else {
            throw new InvalidStudentException(
                "Student record storage is full."
            );
        }
    }

    public void displayAll() {

        for (int i = 0; i < count; i++) {
            people[i].displayInfo();
        }
    }

    // Returns all records as text for the GUI
    public String getRecordsText() {

        if (count == 0) {
            return "No student records available.";
        }

        StringBuilder records = new StringBuilder();

        records.append(
            "STUDENT RECORDS\n"
        );

        records.append(
            "==============================================================\n"
        );

        for (int i = 0; i < count; i++) {

            Person person = people[i];

            records.append("Record ").append(i + 1).append("\n");
            records.append("Name: ").append(person.getName()).append("\n");
            records.append("Age: ").append(person.getAge()).append("\n");

            if (person instanceof Student) {

                Student student = (Student) person;

                records.append("Type: Regular Student\n");
                records.append("Matric No: ")
                       .append(student.getMatricNumber())
                       .append("\n");
                records.append("Programme: ")
                       .append(student.getProgramme())
                       .append("\n");

            } else if (person instanceof PartTimeStudent) {

                PartTimeStudent student =
                    (PartTimeStudent) person;

                records.append("Type: Part-Time Student\n");
                records.append("Programme: ")
                       .append(student.getProgramme())
                       .append("\n");
            }

            records.append(
                "--------------------------------------------------------------\n"
            );
        }

        return records.toString();
    }

    // Serialization
    public void saveToFile(String fileName) {

        try {
            FileOutputStream fileOutput =
                new FileOutputStream(fileName);

            ObjectOutputStream objectOutput =
                new ObjectOutputStream(fileOutput);

            objectOutput.writeObject(people);

            objectOutput.close();
            fileOutput.close();

            System.out.println("Students saved successfully.");

        } catch (IOException e) {

            System.out.println(
                "Error saving students: " + e.getMessage()
            );
        }
    }

    // Deserialization
    public void loadFromFile(String fileName) {

        try {
            FileInputStream fileInput =
                new FileInputStream(fileName);

            ObjectInputStream objectInput =
                new ObjectInputStream(fileInput);

            people = (Person[]) objectInput.readObject();

            count = 0;

            for (int i = 0; i < people.length; i++) {
                if (people[i] != null) {
                    count++;
                }
            }

            objectInput.close();
            fileInput.close();

            System.out.println("Students loaded successfully.");

        } catch (IOException | ClassNotFoundException e) {

            System.out.println(
                "Error loading students: " + e.getMessage()
            );
        }
    }
}