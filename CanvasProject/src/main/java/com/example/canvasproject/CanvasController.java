package com.example.canvasproject;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class CanvasController extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root,200,200);
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(350);
            primaryStage.setMaxWidth(350);
            primaryStage.setMinHeight(600);
            primaryStage.show();
            root.setTop(getButton(root));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public HBox getButton(BorderPane root) {
        HBox planeBox = new HBox();
        Button ovalButton = new Button("Овал");
        ovalButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                getOvalMenu(root);
            }
        });

        Button rectButton = new Button("Прямоугольник");
        rectButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                getRectMenu(root);

            }
        });

        Button rombButton = new Button("Дуга");
        rombButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                getArcMenu(root);

            }
        });

        Button squareButton = new Button("Ломанная");
        squareButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                getPolyMenu(root);

            }
        });

        Button lineButton = new Button("Линия");
        lineButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                getLineMenu(root);;

            }
        });

        planeBox.getChildren().addAll(ovalButton, rectButton, rombButton, squareButton, lineButton);
        return planeBox;
    }

    public void draw(ActionEvent event, BorderPane root, ArrayList<Integer> coords) {
        Canvas myCanvas;
        GraphicsContext gc;
        Button selRadio=(Button)event.getSource();
        switch (selRadio.getText()) {
            case "Прямоугольник":
                myCanvas=new Canvas(315, 600);
                gc = myCanvas.getGraphicsContext2D();
                gc.setFill(Color.BLACK);
                gc.fillRect(coords.get(0), coords.get(1), coords.get(2), coords.get(3));
                root.setCenter(myCanvas);
                break;
            case "Овал":
                myCanvas = new Canvas(315, 600);
                gc = myCanvas.getGraphicsContext2D();
                gc.setFill(Color.BLACK);
                gc.strokeOval(coords.get(1), coords.get(0), coords.get(3), coords.get(2));
                root.setCenter(myCanvas);
                break;
            case "Линия":
                myCanvas = new Canvas(315, 600);
                gc = myCanvas.getGraphicsContext2D();
                gc.setFill(Color.BLACK);
                gc.strokeLine(coords.get(1), coords.get(0), coords.get(3), coords.get(2));
                root.setCenter(myCanvas);
                break;
            case "Ломанная":
                myCanvas = new Canvas(315, 600);
                gc = myCanvas.getGraphicsContext2D();
                gc.setFill(Color.BLACK);
                gc.strokePolyline(new double[] { coords.get(0), coords.get(1), coords.get(2), coords.get(3) }, new double[] { coords.get(4), coords.get(5), coords.get(6), coords.get(7) }, 4);
                root.setCenter(myCanvas);
                break;
            case "Дуга":
                myCanvas = new Canvas(315, 600);
                gc = myCanvas.getGraphicsContext2D();
                gc.setFill(Color.BLACK);
                gc.strokeArc(coords.get(0), coords.get(1), coords.get(2), coords.get(3), coords.get(4), coords.get(5), ArcType.OPEN);
                root.setCenter(myCanvas);
                break;
        }
    }

    public void getRectMenu(BorderPane root) {
        root.setCenter(null);
        ArrayList<Integer> coords = new ArrayList<Integer>();
        GridPane pane = new GridPane();
        TextField x1 = new TextField();
        TextField x2 = new TextField();
        TextField y1 = new TextField();
        TextField y2 = new TextField();

        Label x1Label = new Label("x1: ");
        Label x2Label = new Label("x2: ");
        Label y1Label = new Label("y1: ");
        Label y2Label = new Label("y2: ");

        pane.add(x1Label, 0, 0);
        pane.add(y1Label, 0, 1);
        pane.add(x2Label, 0, 2);
        pane.add(y2Label, 0, 3);

        pane.add(x1, 1, 0);
        pane.add(y1, 1, 1);
        pane.add(x2, 1, 2);
        pane.add(y2, 1, 3);

        Button drawButton = new Button("Прямоугольник");
        drawButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                coords.add(Integer.parseInt(x1.getText()));
                coords.add(Integer.parseInt(y1.getText()));
                coords.add(Integer.parseInt(x2.getText()));
                coords.add(Integer.parseInt(y2.getText()));
                draw(arg0, root, coords);
            }
        });

        pane.add(drawButton, 2, 0);
        root.setBottom(pane);
    }

    public void getOvalMenu(BorderPane root) {
        root.setCenter(null);
        ArrayList<Integer> coords = new ArrayList<Integer>();
        HashMap<String, TextField> texts = new HashMap<String, TextField>();
        HashMap<String, Label> lablesHashMap = new HashMap<String, Label>();
        GridPane pane = new GridPane();
        int i = 0;

        for (i = 0; i < 2; i++) {
            texts.put("x"+i, new TextField());
            texts.put("y"+i, new TextField());
        }

        for (i = 1; i < 3; i++) {
            lablesHashMap.put("x"+i, new Label("x"+i+":"));
            lablesHashMap.put("y"+i, new Label("y"+i+":"));
        }

        i = 0;
        for (String key: lablesHashMap.keySet()) {
            pane.add(lablesHashMap.get(key), 0, i);
            i += 1;
        }

        i = 0;
        for (String key: texts.keySet()) {
            pane.add(texts.get(key), 1, i);
            i += 1;
        }

        Button drawButton = new Button("Овал");
        drawButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                for (String key: texts.keySet()) {
                    coords.add(Integer.parseInt(texts.get(key).getText()));
                }
                draw(arg0, root, coords);
            }
        });

        pane.add(drawButton, 2, 0);
        root.setBottom(pane);
    }

    public void getLineMenu(BorderPane root) {
        root.setCenter(null);
        ArrayList<Integer> coords = new ArrayList<Integer>();
        HashMap<String, TextField> texts = new HashMap<String, TextField>();
        HashMap<String, Label> lablesHashMap = new HashMap<String, Label>();
        GridPane pane = new GridPane();
        int i = 0;

        for (i = 0; i < 2; i++) {
            texts.put("x"+i, new TextField());
            texts.put("y"+i, new TextField());
        }

        for (i = 1; i < 3; i++) {
            lablesHashMap.put("x"+i, new Label("x"+i+":"));
            lablesHashMap.put("y"+i, new Label("y"+i+":"));
        }

        i = 0;
        for (String key: lablesHashMap.keySet()) {
            pane.add(lablesHashMap.get(key), 0, i);
            i += 1;
        }

        i = 0;
        for (String key: texts.keySet()) {
            pane.add(texts.get(key), 1, i);
            i += 1;
        }

        Button drawButton = new Button("Линия");
        drawButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                for (String key: texts.keySet()) {
                    coords.add(Integer.parseInt(texts.get(key).getText()));
                }
                draw(arg0, root, coords);
            }
        });

        pane.add(drawButton, 2, 0);
        root.setBottom(pane);
    }

    public void getPolyMenu(BorderPane root) {
        root.setCenter(null);
        ArrayList<Integer> paramArrayList = new ArrayList<Integer>();
        ArrayList<TextField> inputParamArrayList = new ArrayList<TextField>();
        ArrayList<Label> labels = new ArrayList<Label>();
        GridPane pane = new GridPane();

        for (int i = 0; i < 8; i++) {
            inputParamArrayList.add(new TextField());
        }

        for (int i = 1; i < 5; i++) {
            labels.add(new Label("x"+ i + ": "));
        }

        for (int i = 1; i < 5; i++) {
            labels.add(new Label("y"+ i + ": "));
        }


        for (int i = 0; i < 8; i++) {
            pane.add(labels.get(i), 0, i);
            pane.add(inputParamArrayList.get(i), 1, i);
        }

        Button drawButton = new Button("Ломанная");
        drawButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                for (int i = 0; i < 8; i++) {
                    paramArrayList.add(Integer.parseInt(inputParamArrayList.get(i).getText()));
                }
                draw(arg0, root, paramArrayList);

            }
        });

        pane.add(drawButton, 2, 0);
        root.setBottom(pane);
    }

    public void getArcMenu(BorderPane root) {
        root.setCenter(null);
        ArrayList<Integer> paramArrayList = new ArrayList<Integer>();
        ArrayList<TextField> inputParamArrayList = new ArrayList<TextField>();
        ArrayList<Label> labels = new ArrayList<Label>();
        GridPane pane = new GridPane();

        for (int i = 0; i < 6; i++) {
            inputParamArrayList.add(new TextField());
        }

        labels.add(new Label("x: "));
        labels.add(new Label("y: "));
        labels.add(new Label("w: "));
        labels.add(new Label("h: "));
        labels.add(new Label("startAngle: "));
        labels.add(new Label("arcExtent: "));

        for (int i = 0; i < 6; i++) {
            pane.add(labels.get(i), 0, i);
            pane.add(inputParamArrayList.get(i), 1, i);
        }

        Button drawButton = new Button("Дуга");
        drawButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                for (int i = 0; i < 6; i++) {
                    paramArrayList.add(Integer.parseInt(inputParamArrayList.get(i).getText()));
                }
                draw(arg0, root, paramArrayList);
            }
        });
        pane.add(drawButton, 2, 0);
        root.setBottom(pane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
