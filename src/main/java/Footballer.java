import annotations.Property;

public class Footballer {

    @Property("first_name")
    private String name;

    private String surname;

    private double value;

    @Property("player_age")
    private int age;

    @Property("all_skills")
    private String[] skills;

    public Footballer(String name, String surname, double value, int age, String[] skills) {
        this.name = name;
        this.surname = surname;
        this.value = value;
        this.age = age;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public double getValue() {
        return value;
    }

    public int getAge() {
        return age;
    }

    public String[] getSkills() {
        return skills;
    }
}
