package sample;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        BoardGenerator boardGenerator = new BoardGenerator();
        primaryStage.setScene(new Scene(boardGenerator.drawBoard()));
        primaryStage.setHeight(600);
        primaryStage.setWidth(600);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(600);
        primaryStage.setMaxHeight(600);
        primaryStage.setMaxWidth(600);

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
