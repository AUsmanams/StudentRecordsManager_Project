package StudentRecordsManager;

public class PartTimeStudent extends Person {

    private String programme;

    public PartTimeStudent(String name, int age, String programme) {
        super(name, age);
        this.programme = programme;
    }

    public String getProgramme() {
        return programme;
    }

    @Override
    public void displayInfo() {
        System.out.println(
            "Part-Time Student: " + getName()
            + ", Age: " + getAge()
            + ", Programme: " + programme
        );
    }
}
