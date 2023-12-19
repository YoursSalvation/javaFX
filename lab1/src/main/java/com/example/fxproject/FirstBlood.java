package com.example.fxproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstBlood extends Application {

    public void start(Stage primaryStage) {
//        Button btn = new Button(); //создаем кнопку
//        btn.setText("Say 'Hello World'"); //задаем ей текст
//        btn.setOnAction(new EventHandler<ActionEvent>() { //обработчик нажатия
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });

        Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("FirstBlood.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        Scene scene = new Scene(root, 300, 250); //создаем сцену
        primaryStage.setTitle("Hello World"); //создаем окно с заголовком
        primaryStage.setScene(scene); //вставляем в окно сцену
        primaryStage.show(); //показываем окно
    }

    public static void main(String[] args) {
        launch();
    }
}