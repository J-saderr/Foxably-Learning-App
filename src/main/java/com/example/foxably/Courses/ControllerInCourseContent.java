package com.example.foxably.Courses;

import com.example.foxably.Controller.MainController;
import com.example.foxably.Login.getData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerInCourseContent extends MainController implements Initializable {
    @FXML
    private Label nameOfCourse;
    private Scene scene;
    private Stage stage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayName();
    }
    public void displayName(){
        String name = getData.coursename;
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        nameOfCourse.setText(name);
    }
    @FXML
    void Chapter0(ActionEvent event) throws Exception{
        getData.chapter0 = "Chapter 0";
        getData.detailChapter1 = null;
        getData.detailChapter2 = null;
        getData.detailChapter3 = null;
        getData.detailChapter4 = null;
        getData.detailChapter5 = null;
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/foxably/chapter-view.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void Chapter1(ActionEvent event) throws Exception{
        getData.chapter1 = "Chapter 1";
        getData.detailChapter0 = null;
        getData.detailChapter2 = null;
        getData.detailChapter3 = null;
        getData.detailChapter4 = null;
        getData.detailChapter5 = null;
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/foxably/chapter-view.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void Chapter2(ActionEvent event) throws Exception{
        getData.chapter2 = "Chapter 2";
        getData.detailChapter1 = null;
        getData.detailChapter0 = null;
        getData.detailChapter3 = null;
        getData.detailChapter4 = null;
        getData.detailChapter5 = null;
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/foxably/chapter-view.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void Chapter3(ActionEvent event) throws Exception{
        getData.chapter3 = "Chapter 3";
        getData.detailChapter1 = null;
        getData.detailChapter2 = null;
        getData.detailChapter0 = null;
        getData.detailChapter4 = null;
        getData.detailChapter5 = null;
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/foxably/chapter-view.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void Chapter4(ActionEvent event) throws Exception{
        getData.chapter4 = "Chapter 4";
        getData.detailChapter1 = null;
        getData.detailChapter2 = null;
        getData.detailChapter3 = null;
        getData.detailChapter0 = null;
        getData.detailChapter5 = null;
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/foxably/chapter-view.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void Chapter5(ActionEvent event) throws Exception{
        getData.chapter5 = "Chapter 5";
        getData.detailChapter1 = null;
        getData.detailChapter2 = null;
        getData.detailChapter3 = null;
        getData.detailChapter4 = null;
        getData.detailChapter0 = null;
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/foxably/chapter-view.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
