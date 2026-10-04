package StudentRecordsManager;

import java.awt.*;
import java.awt.event.*;

public class StudentRecordsGUI extends Frame implements ActionListener {

    private TextField nameField;
    private TextField ageField;
    private TextField matricField;
    private TextField programmeField;

    private Choice studentTypeChoice;

    private TextArea recordsArea;

    private Label statusLabel;

    private Button addButton;
    private Button clearButton;
    private Button displayButton;
    private Button saveButton;
    private Button loadButton;

    private StudentManager manager;

    public StudentRecordsGUI() {

        manager = new StudentManager();

        setTitle("Student Records Manager");
        setSize(850, 700);
        setLayout(new BorderLayout(10, 10));

        // =====================================================
        // HEADER
        // =====================================================

        Panel headerPanel = new Panel(new GridLayout(2, 1));

        Label titleLabel =
            new Label("STUDENT RECORDS MANAGER", Label.CENTER);

        titleLabel.setFont(
            new Font("Arial", Font.BOLD, 22)
        );

        Label subtitleLabel =
            new Label(
                "Student Information Management System",
                Label.CENTER
            );

        subtitleLabel.setFont(
            new Font("Arial", Font.PLAIN, 14)
        );

        headerPanel.add(titleLabel);
        headerPanel.add(subtitleLabel);

        // =====================================================
        // FORM
        // =====================================================

        Panel formPanel =
            new Panel(new GridBagLayout());

        GridBagConstraints gbc =
            new GridBagConstraints();

        gbc.insets =
            new Insets(5, 10, 5, 10);

        gbc.fill =
            GridBagConstraints.HORIZONTAL;

        Label formTitle =
            new Label("STUDENT INFORMATION");

        formTitle.setFont(
            new Font("Arial", Font.BOLD, 15)
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        formPanel.add(formTitle, gbc);

        gbc.gridwidth = 1;

        // Name
        gbc.gridx = 0;
        gbc.gridy = 1;

        formPanel.add(
            new Label("Name:"),
            gbc
        );

        nameField = new TextField(30);

        gbc.gridx = 1;

        formPanel.add(
            nameField,
            gbc
        );

        // Age
        gbc.gridx = 0;
        gbc.gridy = 2;

        formPanel.add(
            new Label("Age:"),
            gbc
        );

        ageField = new TextField(10);

        gbc.gridx = 1;

        formPanel.add(
            ageField,
            gbc
        );

        // Matric Number
        gbc.gridx = 0;
        gbc.gridy = 3;

        formPanel.add(
            new Label("Matric No:"),
            gbc
        );

        matricField = new TextField(30);

        gbc.gridx = 1;

        formPanel.add(
            matricField,
            gbc
        );

        // Programme
        gbc.gridx = 0;
        gbc.gridy = 4;

        formPanel.add(
            new Label("Programme:"),
            gbc
        );

        programmeField = new TextField(30);

        gbc.gridx = 1;

        formPanel.add(
            programmeField,
            gbc
        );

        // Student Type
        gbc.gridx = 0;
        gbc.gridy = 5;

        formPanel.add(
            new Label("Student Type:"),
            gbc
        );

        studentTypeChoice = new Choice();

        studentTypeChoice.add("Regular Student");
        studentTypeChoice.add("Part-Time Student");

        gbc.gridx = 1;

        formPanel.add(
            studentTypeChoice,
            gbc
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        Panel buttonPanel =
            new Panel(
                new FlowLayout(
                    FlowLayout.CENTER,
                    10,
                    8
                )
            );

        addButton =
            new Button("Add Student");

        clearButton =
            new Button("Clear");

        displayButton =
            new Button("Display Records");

        saveButton =
            new Button("Save Records");

        loadButton =
            new Button("Load Records");

        addButton.addActionListener(this);
        clearButton.addActionListener(this);
        displayButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);

        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        // =====================================================
        // TOP SECTION
        // =====================================================

        Panel topSection =
            new Panel(new BorderLayout());

        topSection.add(
            headerPanel,
            BorderLayout.NORTH
        );

        topSection.add(
            formPanel,
            BorderLayout.CENTER
        );

        topSection.add(
            buttonPanel,
            BorderLayout.SOUTH
        );

        add(
            topSection,
            BorderLayout.NORTH
        );

        // =====================================================
        // RECORDS SECTION
        // =====================================================

        Panel recordsPanel =
            new Panel(new BorderLayout(5, 5));

        Label recordsTitle =
            new Label("STUDENT RECORDS");

        recordsTitle.setFont(
            new Font("Arial", Font.BOLD, 15)
        );

        recordsPanel.add(
            recordsTitle,
            BorderLayout.NORTH
        );

        recordsArea =
            new TextArea(
                "No student records available.",
                15,
                80,
                TextArea.SCROLLBARS_VERTICAL_ONLY
            );

        recordsArea.setEditable(false);

        recordsArea.setFont(
            new Font("Monospaced", Font.PLAIN, 13)
        );

        recordsPanel.add(
            recordsArea,
            BorderLayout.CENTER
        );

        add(
            recordsPanel,
            BorderLayout.CENTER
        );

        // =====================================================
        // STATUS BAR
        // =====================================================

        Panel statusPanel =
            new Panel(new BorderLayout());

        statusLabel =
            new Label("Status: Ready.");

        statusLabel.setFont(
            new Font("Arial", Font.BOLD, 12)
        );

        statusLabel.setPreferredSize(
            new Dimension(800, 30)
        );

        statusPanel.add(
            statusLabel,
            BorderLayout.CENTER
        );

        add(
            statusPanel,
            BorderLayout.SOUTH
        );

        // =====================================================
        // WINDOW CLOSING
        // =====================================================

        addWindowListener(
            new WindowAdapter() {

                public void windowClosing(
                    WindowEvent e
                ) {
                    System.exit(0);
                }
            }
        );

        setVisible(true);
    }

    // =========================================================
    // BUTTON ACTIONS
    // =========================================================

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addButton) {

            addStudent();

        } else if (e.getSource() == clearButton) {

            clearFields();

            statusLabel.setText(
                "Status: Input fields cleared."
            );

        } else if (e.getSource() == displayButton) {

            recordsArea.setText(
                manager.getRecordsText()
            );

            statusLabel.setText(
                "Status: Student records displayed."
            );

        } else if (e.getSource() == saveButton) {

            manager.saveToFile("students.dat");

            statusLabel.setText(
                "Status: Records saved to students.dat."
            );

        } else if (e.getSource() == loadButton) {

            manager.loadFromFile("students.dat");

            recordsArea.setText(
                manager.getRecordsText()
            );

            statusLabel.setText(
                "Status: Records loaded successfully."
            );
        }
    }

    // =========================================================
    // ADD STUDENT
    // =========================================================

    private void addStudent() {

        try {

            String name =
                nameField.getText().trim();

            String ageText =
                ageField.getText().trim();

            String matricNumber =
                matricField.getText().trim();

            String programme =
                programmeField.getText().trim();

            if (name.isEmpty()
                || ageText.isEmpty()
                || programme.isEmpty()) {

                statusLabel.setText(
                    "Status: Please fill in all required fields."
                );

                return;
            }

            int age =
                Integer.parseInt(ageText);

            String studentType =
                studentTypeChoice.getSelectedItem();

            if (studentType.equals(
                "Regular Student")) {

                if (matricNumber.isEmpty()) {

                    statusLabel.setText(
                        "Status: Matric number is required."
                    );

                    return;
                }

                Student student =
                    new Student(
                        name,
                        age,
                        matricNumber,
                        programme
                    );

                manager.addPerson(student);

            } else {

                PartTimeStudent student =
                    new PartTimeStudent(
                        name,
                        age,
                        programme
                    );

                manager.addPerson(student);
            }

            recordsArea.setText(
                manager.getRecordsText()
            );

            statusLabel.setText(
                "Status: Student added successfully."
            );

            clearFields();

        } catch (NumberFormatException ex) {

            statusLabel.setText(
                "Status: Age must be a valid number."
            );

        } catch (InvalidStudentException ex) {

            statusLabel.setText(
                "Status: " + ex.getMessage()
            );
        }
    }

    // =========================================================
    // CLEAR FIELDS
    // =========================================================

    private void clearFields() {

        nameField.setText("");
        ageField.setText("");
        matricField.setText("");
        programmeField.setText("");

        studentTypeChoice.select(
            "Regular Student"
        );
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(String[] args) {

        new StudentRecordsGUI();
    }
}