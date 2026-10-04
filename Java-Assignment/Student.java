package StudentRecordsManager;

public class Student extends Person {

    private String matricNumber;
    private String programme;

    public Student(String name, int age, String matricNumber, String programme) {
        super(name, age);
        this.matricNumber = matricNumber;
        this.programme = programme;
    }

    public String getMatricNumber() {
        return matricNumber;
    }

    public String getProgramme() {
        return programme;
    }

    @Override
    public void displayInfo() {
        System.out.println(
            "Regular Student: " + getName()
            + ", Age: " + getAge()
            + ", Matric No: " + matricNumber
            + ", Programme: " + programme
        );
    }
}
