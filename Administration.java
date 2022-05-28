package model;

import java.util.Random;

public class Administration extends Staff {

    public Administration(String name, int salary) {
        super(name, salary);
    }

    @Override
    public String DoWork(){
        Random rnd = new Random();
        int action = rnd.nextInt(3);
        String answer;

        switch (action) {
            case 0:
                answer = "Администратор следит за персоналом.";
                break;
            case 1:
                answer = "Администратор проводит встречу.";
                break;
            case 2:
                answer ="Администратор организовывает мероприятие.";
                break;
            default: answer = "";
        }
        return answer;
    }

    @Override
    public String GetJob() {
        return "Администратор ";
    }

    @Override
    public String GetDetails() {
        return "";
    }
}
