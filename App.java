import view.*;
import controller.*;

public class App {
    public static void main(String[] args) throws Exception {
        MainWindow mainWindow = new MainWindow("Работники компании");
        AddEmployeeWindow addEmployeeWindow = new AddEmployeeWindow("Добавление сотрудника");
        new Controller(mainWindow, addEmployeeWindow);
        mainWindow.start();
    }
}
