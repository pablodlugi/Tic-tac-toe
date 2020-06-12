package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        chooseScene(primaryStage);

    }

    private void chooseScene(Stage primaryStage) {
        ChooseFigure chooseFigure = new ChooseFigure();
        BoardGenerator boardGenerator = new BoardGenerator();
        primaryStage.setScene(new Scene(chooseFigure.drawChooseFigureScene()));
        panesEventHandlers(primaryStage, chooseFigure, boardGenerator);

        primaryStage.setHeight(600);
        primaryStage.setWidth(600);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(600);
        primaryStage.setMaxHeight(600);
        primaryStage.setMaxWidth(600);
        primaryStage.show();
    }

    private void panesEventHandlers(Stage primaryStage, ChooseFigure chooseFigure, BoardGenerator boardGenerator) {
        EventHandler<MouseEvent> pane0clicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                chooseFigure.setFigureO(true);
                primaryStage.setScene(new Scene(boardGenerator.drawBoard()));
                event.consume();
            }
        };

        EventHandler<MouseEvent> paneXclicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                chooseFigure.setFigureX(true);
                primaryStage.setScene(new Scene(boardGenerator.drawBoard()));
                event.consume();
            }
        };
        chooseFigure.getPanelX().addEventHandler(MouseEvent.MOUSE_CLICKED, paneXclicked);
        chooseFigure.getPanel0().addEventHandler(MouseEvent.MOUSE_CLICKED, pane0clicked);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
