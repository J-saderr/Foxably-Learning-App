package com.example.foxably.Todo;

import com.example.foxably.Controller.MainController;
import com.example.foxably.Login.getData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;

public class TodoController extends MainController {

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

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public void initialize() {
        taskList.setItems(ListData());
        doneList.setItems(loadDoneTasksFromDatabase());

        taskList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && newVal.isDone()) {
                newVal.setDone(false);
                doneTasks.remove(newVal);
                taskList.getItems().add(newVal);
            }
        });

        doneList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && !newVal.isDone()) {
                newVal.setDone(true);
                taskList.getItems().remove(newVal);
                doneTasks.add(newVal);
            }
        });
    }

    @FXML
    void addTask() {

        String sql = "INSERT INTO Task (user_id,title, date, on_going) "
                + "VALUES(?,?,?,?)";

        connect = connectDb();

        try{
            Alert alert;

            if(titleField.getText().isEmpty()
                    || dateField.getValue() == null
            ){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                String checkData = "SELECT * FROM Task WHERE id = '"
                        +titleField.getText()+"'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if(result.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Task ID: " + titleField.getText() + " was already exist!");
                    alert.showAndWait();
                }else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, getData.userid);
                    prepare.setString(2, titleField.getText());
                    prepare.setString(3, String.valueOf(dateField.getValue()));
                    prepare.setString(4, "pending");

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                    titleField.clear();
                    dateField.getEditor().clear();
                    ListData();
                }
            }
        }catch(Exception e){e.printStackTrace();}

    }
    public ObservableList<Task> ListData(){
        ObservableList<Task> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Task WHERE on_going = 'pending'";

        connect = connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Task taskD;

            while(result.next()){
                taskD = new Task(result.getInt("id"), result.getString("title"), result.getDate("date"));

                listData.add(taskD);
            }
        }catch(Exception e){e.printStackTrace();}
        return listData;
    }

    @FXML
    void doneTask() {
        int selectedIndex = taskList.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Task selectedTask = taskList.getItems().get(selectedIndex);
            taskList.getItems().remove(selectedTask);
            selectedTask.setDone(true);
            Update(selectedTask.getId(), "done");
            taskList.getItems().remove(selectedTask);
            doneList.getItems().add(selectedTask);
        }
    }

    public void Update(int taskId, String status) {
        String sql = "UPDATE Task SET on_going = ? WHERE id = ? ";

        connect = connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, "done");
            prepare.setInt(2, taskId);

            int rowsAffected = prepare.executeUpdate();

            if (rowsAffected > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Task status updated successfully!");
                alert.showAndWait();

                ListData();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("No task found with the given ID or the task is not pending.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private ObservableList<Task> loadDoneTasksFromDatabase() {
        ObservableList<Task> doneTasksList = FXCollections.observableArrayList(); // Tạo một danh sách mới

        try {
            String sql = "SELECT * FROM Task WHERE on_going = 'done'";
            Connection connection = connectDb();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Task task = new Task(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getDate("date"));
                task.setDone(true);
                doneTasksList.add(task);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doneTasksList;
    }
    @FXML
    void deleteTask() {
        int selectedTaskIndex = taskList.getSelectionModel().getSelectedIndex();
        int selectedDoneTaskIndex = doneList.getSelectionModel().getSelectedIndex();

        if (selectedTaskIndex != -1) {
            Task selectedTask = taskList.getItems().get(selectedTaskIndex);
            taskList.getItems().remove(selectedTaskIndex);
            deleteTaskFromDatabase(selectedTask.getId());
        }

        if (selectedDoneTaskIndex != -1) {
            Task selectedDoneTask = doneList.getItems().get(selectedDoneTaskIndex);
            doneList.getItems().remove(selectedDoneTaskIndex);
            deleteTaskFromDatabase(selectedDoneTask.getId());
        }
    }

    public void deleteTaskFromDatabase(int taskId) {
        String sql = "DELETE FROM Task WHERE id = ?";

        try {
            Connection connection = connectDb();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, taskId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Task deleted from database.");
            } else {
                System.out.println("No task deleted from database.");
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
