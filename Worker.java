package model;

public class Worker extends Staff {
    private String duty;

    public Worker(String name, int salary, String duty){
        super(name, salary);
        this.duty = duty;
    }

    @Override
    public String GetJob() {
        return "Рабочий ";
    }

    @Override
    public String GetDetails() {
        return "Обязанность: " + duty;
    }

    @Override
    public String DoWork(){
        return String.format("Рабочий %s выполняет действие: %s", getName(), duty);
    }
}