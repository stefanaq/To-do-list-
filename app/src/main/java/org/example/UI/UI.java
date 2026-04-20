package org.example.UI;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.example.model.Priority;
import org.example.model.Task;
import org.example.service.TaskService;

public class UI extends JFrame {
    private TaskService service;

    private JTextField descripTextField;
    private JComboBox priorityBox;

    private JTable taskTable;
    private DefaultTableModel tableModel;

    private JButton addTaskButton;
    private JButton markCompleteButton;

    public UI(TaskService serv){
        this.service = serv;

        setTitle("To Do");
        setSize(1000,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //input stuff
        JPanel inputPanel = new JPanel();

        descripTextField = new JTextField(30);
        priorityBox = new JComboBox<>(Priority.values());
        addTaskButton = new JButton("Add Task");

        inputPanel.add(new JLabel("Task: "));
        inputPanel.add(descripTextField);
        inputPanel.add(priorityBox);
        inputPanel.add(addTaskButton);

        add(inputPanel, BorderLayout.NORTH);

        //Center panel shit
        String[] columns = {"Description", "Priority", "Completed"};
        tableModel = new DefaultTableModel(columns,0);
        taskTable = new JTable(tableModel);

        add(new JScrollPane(taskTable), BorderLayout.CENTER);

        //bottom panel stuff
        JPanel bottomPanel = new JPanel();
        markCompleteButton = new JButton("Mark Complete");

        bottomPanel.add(markCompleteButton);
        add(bottomPanel,BorderLayout.SOUTH);

        //Initial Load
        refreshTable();

        addTaskButton.addActionListener(e -> handleAddTask());
        markCompleteButton.addActionListener(e -> handleMarkCompleted());
        }

        //Handler methods
    private void handleAddTask() {
        try {
            String description = descripTextField.getText();
            Priority priority = (Priority) priorityBox.getSelectedItem();

            Task newTask = new Task(description,priority);
            service.addTask(newTask);

            descripTextField.setText("");
            refreshTable();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void handleMarkCompleted() {
        int selectedRow = taskTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a task first");
            return;
        }

        try {
            service.markCompleted(selectedRow);
            refreshTable();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    // ===== REFRESH TABLE =====
    private void refreshTable() {
        tableModel.setRowCount(0); // clear table

        List<Task> tasks = service.getAllTasks();

        for (Task task : tasks) {
            Object[] row = {
                task.getDescription(),
                task.getPriority(),
                task.isCompleted() ? "Yes" : "No"
            };
            tableModel.addRow(row);
        }


}
}
