package db;

import model.Administration;
import model.Engineer;
import model.Staff;
import model.Worker;

import java.sql.*;
import java.util.ArrayList;

public class SQLiteConnection {
    private static Connection connection = null; // Переменная для подключения

    // Стандартное подключение к бд SQLite
    public static void connectToDB() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        // Если подключения нет, то создаем
        if (connection == null) {
            //("имя движка") вызывает динамическую загрузку класса
            Class.forName("org.sqlite.JDBC");

            // Подключаемся к БД company.db (если ее нет, то будет создана)
            connection = DriverManager.getConnection("jdbc:sqlite:company.db");
        }
    }

    // Создание таблицы staff
    public static void createStaffTable() throws ClassNotFoundException, SQLException {
        Statement statement = connection.createStatement();

        // Выполняем запрос к бд
        statement.execute("CREATE TABLE if not exists 'staff' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'post' text, 'salary' INTEGER, 'details' text);");

        System.out.println("Таблица \"Персонал\" существует");
    }

    // Вставляем одну запись о человеке
    public static void insertStaff(String name, String post, int salary, String details) throws SQLException{
        // Создаем запрос
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO 'staff' ('name', 'post', 'salary', 'details') VALUES (?, ?, ?, ?); "
        );

        // Замещаем все ? в запросе
        // Цифра - номер вопроса
        statement.setString(1, name);
        statement.setString(2, post);
        statement.setInt(3, salary);
        statement.setString(4, details);

        // Выполняем запрос
        statement.execute();

        System.out.println("Запись вставлена");
    }

    // Удаляем запись о человеке по имени
    public static void deleteStaff(String name) throws SQLException{
        // Создаем запрос
        PreparedStatement statement = connection.prepareStatement("DELETE FROM staff WHERE name = ?");

        // Замещаем ?
        statement.setString(1, name);

        // Выполняем запрос
        statement.execute();

        System.out.println("Запись удалена");
    }

    // Получаем список персонала по таблице из БД
    public static ArrayList<Staff> getStaffList() throws ClassNotFoundException, SQLException
    {
        // Запрос
        Statement statement = connection.createStatement();
        // Сохраняем результат выполнения в ResultSet
        ResultSet result = statement.executeQuery("SELECT * FROM staff"); // Выборка данных с помощью команды SELECT

        // Создаем список
        ArrayList<Staff> staffList = new ArrayList<>();

        // Проходимся по всем полученным из БД записям и заполняем список
        while(result.next()) {
            // Данные, совпадающие у всех видов персонала
            String staffName = result.getString("name");
            int staffSalary = result.getInt("salary");
            String staffPost = result.getString("post");

            // Создаем кого-то из персонала
            Staff staff;

            // У инженеров и рабочих есть особые поля
            if (staffPost.equals("Инженер")) {
                // Получаем опыт из поля "детали"
                int experience = Integer.parseInt(result.getString("details"));
                // Создаем инженера
                staff = new Engineer(staffName, staffSalary, experience);
            }
            else if (staffPost.equals("Рабочий")) {
                // Получаем обязанность из поля "детали"
                String duty = result.getString("details");
                // Создаем рабочего
                staff = new Worker(staffName, staffSalary, duty);
            }
            else {
                // Если не инженер и не рабочий, то создаем админа
                staff = new Administration(staffName, staffSalary);
            }

            // Добавляем человека в список
            staffList.add(staff);
        }

        System.out.println("Таблица выгружена");

        // Возвращаем полученный список персонала
        return staffList;
    }
}
