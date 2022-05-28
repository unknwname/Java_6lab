package controller;

import javax.swing.JOptionPane;

import db.SQLiteConnection;
import model.*;
import view.*;

import java.sql.SQLException;


public class Controller {
    private Company company;
    private MainWindow mainWindow;
    private AddEmployeeWindow addEmployeeWindow;
    private CustomTableModel tableModel;

    public Controller (MainWindow mainWindow, AddEmployeeWindow addEmployeeWindow){
        this.addEmployeeWindow = addEmployeeWindow;
        this.mainWindow = mainWindow;

        // Try catch т.к. работа с подключением и это обязательно
        try {
            // Подключаемся к бд
            SQLiteConnection.connectToDB();
            // Создаем таблицу
            SQLiteConnection.createStaffTable();

            // Берем список персонала для компании из бд
            company = new Company(SQLiteConnection.getStaffList());
        }
        catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            // Сообщение об ошибке
            JOptionPane.showMessageDialog(this.mainWindow, "Ошибка при обращении к БД", "Ошибка", 2);
            e.printStackTrace();
        }

        tableModel = new CustomTableModel(company);

        this.mainWindow.setTableModel(tableModel);
        this.mainWindow.addListenerAddButton(e -> {
            this.addEmployeeWindow.start();
        });

        this.addEmployeeWindow.chooseComboBox(e -> {
            int index = this.addEmployeeWindow.getSelectedIndex();
            switch(index){
                case 1:
                    this.addEmployeeWindow.setVariableElement("Опыт работы (годы)", 1);
                    break;
                case 2:
                    this.addEmployeeWindow.setVariableElement("Выполняемая работа", 2);
                    break;
                case 0:
                    this.addEmployeeWindow.setVariableElement("", 0);
                    break;
            }
        });

        this.addEmployeeWindow.addListenerOKButton(e -> {
            String name = this.addEmployeeWindow.getName();
            String duty = this.addEmployeeWindow.getDuty();
            int salary = this.addEmployeeWindow.getSalary();
            int experience = this.addEmployeeWindow.getExperience();
            int index = this.addEmployeeWindow.getSelectedIndex();

            if(name.isEmpty()){
                JOptionPane.showMessageDialog(this.addEmployeeWindow, "Вы не ввели имя", "Ошибка", 2);
            }
            else{
                try {
                    switch (index) {
                        case 0:
                            // Добавляем администратора
                            SQLiteConnection.insertStaff(name, "Администратор", salary, "");

                            company.Hire(new Administration(name, salary));
                            break;
                        case 1:
                            // Добавляем инженера
                            SQLiteConnection.insertStaff(name, "Инженер", salary, Integer.toString(experience));

                            company.Hire(new Engineer(name, salary, experience));
                            break;
                        case 2:
                            if (!duty.isEmpty()) {
                                // Добавляем рабочего
                                SQLiteConnection.insertStaff(name, "Рабочий", salary, duty);

                                company.Hire(new Worker(name, salary, duty));
                            } else {
                                JOptionPane.showMessageDialog(this.addEmployeeWindow, "Вы не ввели работу", "Ошибка", 2);
                            }
                            break;
                        default:
                            break;
                    }
                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this.addEmployeeWindow, "Ошибка при добавлении в БД", "Ошибка", 2);
                    ex.printStackTrace();
                }

                tableModel.fireTableDataChanged();
            }
            this.addEmployeeWindow.close();
        });

        this.mainWindow.addListenerRemoveButton(e -> {
            int index = this.mainWindow.getSelectedIndex();

            // Удаляем из БД
            try {
                // Находим имя по индексу
                String name = company.get(index).getName();

                // Удаляем
                SQLiteConnection.deleteStaff(name);
            } catch (SQLException ex) {
                // Сообщение об ошибке
                JOptionPane.showMessageDialog(this.mainWindow, "Ошибка при удалении из БД", "Ошибка", 2);
                ex.printStackTrace();
            }

            company.Dismiss(index);
            tableModel.fireTableDataChanged();
        });

        this.mainWindow.addListenerInfoButton(e -> {
            int index = this.mainWindow.getSelectedIndex();
            String work = company.get(index).DoWork();
            JOptionPane.showMessageDialog(this.mainWindow, work, "О сотруднике", 1);
        });

        this.mainWindow.addListenerTable(e -> {
            if(this.mainWindow.getSelectedIndex() > -1){
                this.mainWindow.makeInfoEnabled(true);
                this.mainWindow.makeRemoveEnabled(true);
            }
            else{
                this.mainWindow.makeInfoEnabled(false);
                this.mainWindow.makeRemoveEnabled(false);
            }
        });
    }

}
