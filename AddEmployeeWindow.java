package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddEmployeeWindow extends JFrame{
    private JLabel label_name = new JLabel("Имя сотрудника");
    private JLabel label_salary = new JLabel("Зарплата сотрудника");
    private JLabel label_job = new JLabel("Должность сотрудника");
    private JLabel label_variable = new JLabel("");

    private JTextField name = new JTextField();
    private JSpinner salary = new JSpinner(new SpinnerNumberModel(20000, 20000, 200000, 1000));
    private JComboBox<String> comboBoxJob;
    JSpinner experience = new JSpinner(new SpinnerNumberModel(1, 1, 60, 1));
    private JTextField duty = new JTextField();

    private JPanel panelElements = new JPanel();
    private JButton buttonOK = new JButton("OK");

    private Font font = new Font("Roboto", Font.PLAIN, 16);

    public AddEmployeeWindow(String title){
        super(title);

        panelElements.setLayout(new GridLayout(4, 2));

        String[] jobs = {
            "Администратор",
            "Инженер",
            "Рабочий"
        };
        comboBoxJob = new JComboBox<>(jobs);

        setFancyFont(label_name);
        setFancyFont(label_job);
        setFancyFont(label_salary);
        setFancyFont(label_variable);
        setFancyFont(name);
        setFancyFont(duty);

        panelElements.add(label_name);
        panelElements.add(name);
        panelElements.add(label_salary);
        panelElements.add(salary);
        panelElements.add(label_job);
        panelElements.add(comboBoxJob);
        panelElements.add(label_variable);

        add(panelElements, "Center");
        add(buttonOK, "South");
    }

    public void start(){
        setSize(500, 500);
        setVisible(true);
    }

    private void setFancyFont(JLabel label){
        label.setFont(font);
        label.setHorizontalAlignment(0);
    }

    private void setFancyFont(JTextField field){
        field.setFont(font);
        field.setHorizontalAlignment(0);
    }

    public void setVariableElement(String text, int mode){
        label_variable.setText(text);
        switch(mode){
            case 1:
                panelElements.remove(duty);
                panelElements.add(experience);
                break;
            case 2:
                panelElements.remove(experience);
                panelElements.add(duty);
                break;
            case 0:
                panelElements.remove(experience);
                panelElements.remove(duty);
                break;
        }
    }

    public void addListenerOKButton(ActionListener l){
        buttonOK.addActionListener(l);
    }

    public int getSelectedIndex(){
        return comboBoxJob.getSelectedIndex();
    }

    public void chooseComboBox(ActionListener l){
        comboBoxJob.addActionListener(l);
    }

    public String getName() {
        return name.getText();
    }

    public int getSalary() {
        return (Integer) salary.getValue();
    }

    public int getExperience() {
        return (Integer) experience.getValue();
    }

    public String getDuty() {
        return duty.getText();
    }

    public void close(){
        setVisible(false);
        name.setText("");
        duty.setText("");
        salary.setValue(20000);
        experience.setValue(1);
    }
}
