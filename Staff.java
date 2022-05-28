package model;

public abstract class Staff {
    private String name;
    private int salary;

    public Staff(String name, int salary){
        this.name = name;
        this.salary = salary;
    }

    public abstract String DoWork();
    public abstract String GetJob();
    public abstract String GetDetails();

    public String getName(){
        return name;
    }

    public int getSalary(){
        return salary;
    }
}
