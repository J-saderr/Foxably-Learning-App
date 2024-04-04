package com.example.foxably.Todo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class TodoController {

    @FXML
    private ListView<Task> taskList;

    @FXML
    private ListView<Task> doneList;

    @FXML
    private TextField titleField;

    @FXML
    private DatePicker dateField;

    private ObservableList<Task> tasks;
    private ObservableList<Task> doneTasks;

    public void initialize() {
        tasks = FXCollections.observableArrayList();
        doneTasks = FXCollections.observableArrayList();
        taskList.setItems(tasks);
        doneList.setItems(doneTasks);
        taskList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && newVal.isDone()) {
                newVal.setDone(false);
                tasks.remove(newVal);
                doneTasks.add(newVal);
            }
        });
        doneList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && !newVal.isDone()) {
                newVal.setDone(true);
                doneTasks.remove(newVal);
                tasks.add(newVal);
            }
        });
    }

    @FXML
    void addTask() {
        String title = titleField.getText().trim();
        if (!title.isEmpty() && dateField.getValue() != null) {
            Task newTask = new Task(title, dateField.getValue());
            tasks.add(newTask);
            titleField.clear();
            dateField.getEditor().clear();
        }
    }

    @FXML
    void doneTask() {
        int selectedIndex = taskList.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Task selectedTask = taskList.getItems().get(selectedIndex);
            taskList.getItems().remove(selectedTask);
            selectedTask.setDone(true);
            doneList.getItems().add(selectedTask);
        }
    }

    @FXML
    void deleteTask() {
        int selectedIndex = taskList.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            tasks.remove(selectedIndex);
        }
    }
}
