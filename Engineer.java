package model;

public class Engineer extends Staff {
    private int experience;

    public Engineer(String name, int salary, int experience) {
        super(name, salary);
        this.experience = experience;
    }

    @Override
    public String DoWork(){
        return String.format("Инженер %s использует свой опыт (%s) на практике.", getName(), experience);
    }

    @Override
    public String GetJob() {
        return "Инженер ";
    }

    @Override
    public String GetDetails() {
        return "Стаж: " + Integer.toString(experience);
    }
}
