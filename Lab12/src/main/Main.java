package main;

import javax.swing.*;

import java.awt.Container;
import java.awt.ComponentOrientation;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;

public class Main {
    static JFrame frame;

    public static void createPanelUI(Container container)
    {
        RequestDbController requestDbController = new RequestDbController();

        container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridwidth = 2;
        JList list = new JList(requestDbController.getWorkerList().toArray());
        JScrollPane scrollableList = new JScrollPane(list);
        container.add(scrollableList, constraints);

        constraints.gridwidth = 1;
        constraints.gridy = 1;
        JButton buttonInfo = new JButton("Info");
        ActionListener actionListener = e -> {
            Worker worker = (Worker)list.getSelectedValue();
            Report report = requestDbController.getReport(worker.getId());
            JOptionPane.showMessageDialog(null, worker.toString() + "\n" + report.toString());
            System.out.println("textField.getText()");
        };
        buttonInfo.addActionListener(actionListener);
        container.add(buttonInfo, constraints);

        constraints.gridx = 1;
        JButton buttonSalary = new JButton("Salary");
        actionListener = e -> {
            Worker worker = (Worker)list.getSelectedValue();
            Report report = requestDbController.getReport(worker.getId());
            JOptionPane.showMessageDialog(null, "Salary - " + (report.getRate() * report.getTime()) + "$");
        };
        buttonSalary.addActionListener(actionListener);
        container.add(buttonSalary, constraints);
    }

    public static void main(String[] args) {
        DatabaseService databaseService = DatabaseService.getDatabaseService();
        databaseService.generateStructure();

        frame = new JFrame("GridBagLayoutTest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createPanelUI(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }
}
