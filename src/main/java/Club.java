import annotations.Ignore;

public class Club {
    private String name;

    @Ignore
    private double budget;

    public String getName() {
        return name;
    }

    public double getBudget() {
        return budget;
    }

    public Club(String name, double budget) {
        this.name = name;
        this.budget = budget;
    }
}
