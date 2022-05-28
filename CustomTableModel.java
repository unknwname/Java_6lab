package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import db.SQLiteConnection;
import model.Company;
import model.Staff;

public class CustomTableModel extends AbstractTableModel{
    private ArrayList<Staff> employees;
    private String[] heads = {"Имя", "Зарплата", "Должность"};

    public CustomTableModel(Company company) {
        this.employees = company.getEmployees();
    }

    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        return heads.length;
    }

    @Override
    public String getColumnName(int column) {
        return heads[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return employees.get(rowIndex).getName();
            case 1: return employees.get(rowIndex).getSalary();
            case 2: return employees.get(rowIndex).GetJob();
            default: return "";
        }
    }

}
