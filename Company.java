package model;

import java.util.ArrayList;

public class Company {
    private ArrayList<Staff> employees;

    public Company(ArrayList<Staff> employees) {
        this.employees = employees;
    }

    public Staff get(int index){
        return employees.get(index);
    }

    public void Hire(Staff employee){
        employees.add(employee);
    }

    public void Dismiss(int index){
        employees.remove(index);
    }

    public String GetInfo(Staff employee){
        StringBuilder info = new StringBuilder();

        info.append(employee.GetJob());
        info.append(employee.getName() + "\n");
        info.append("Зарплата: " + employee.getSalary() + "\n");
        info.append(employee.GetDetails());

        return info.toString();
    }

    public ArrayList<Staff> getEmployees() {
        return employees;
    }
}
