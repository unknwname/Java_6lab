package view;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;

import controller.CustomTableModel;

import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame{
    JPanel panel_table = new JPanel();
    JPanel panel_buttons = new JPanel();

    JButton button_add = new JButton("Нанять");
    JButton button_remove = new JButton("Уволить");
    JButton button_info = new JButton("О сотруднике");

    JTable table = new JTable();
    JScrollPane scroll;

    public MainWindow(String title){
        super(title);

        panel_table.setLayout(new GridLayout());
        panel_buttons.setLayout(new FlowLayout());

        add(panel_table, "Center");
        add(panel_buttons, "South");

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scroll = new JScrollPane(table);
        panel_table.add(scroll);

        button_remove.setEnabled(false);
        button_info.setEnabled(false);
        panel_buttons.add(button_add);
        panel_buttons.add(button_remove);
        panel_buttons.add(button_info);
    }

    public void addListenerAddButton(ActionListener l){
        button_add.addActionListener(l);
    }

    public void addListenerRemoveButton(ActionListener l){
        button_remove.addActionListener(l);
    }

    public void addListenerInfoButton(ActionListener l){
        button_info.addActionListener(l);
    }

    public void setTableModel(CustomTableModel tm){
        table.setModel(tm);
    }

    public int getSelectedIndex(){
        return table.getSelectedRow();
    }

    public void start(){
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void addListenerTable(ListSelectionListener l){
        table.getSelectionModel().addListSelectionListener(l);
    }

    public void makeInfoEnabled(boolean b){
        button_info.setEnabled(b);
    }

    public void makeRemoveEnabled(boolean b){
        button_remove.setEnabled(b);
    }
}
