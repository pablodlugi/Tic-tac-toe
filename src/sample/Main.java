package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        sceneOfChoice(primaryStage);
    }

    private void sceneOfChoice(Stage primaryStage) {
        ChooseFigureScene chooseFigure = new ChooseFigureScene();
        BoardLogic boardLogic = new BoardLogic();
        primaryStage.setScene(new Scene(chooseFigure.drawChooseFigureScene()));
        chooseFigure.getterPane0().setOnMouseClicked(event -> paneOEventHandler(primaryStage, boardLogic));
        chooseFigure.getterPaneX().setOnMouseClicked(event -> paneXEventHandler(primaryStage, boardLogic));

        primaryStage.setHeight(600);
        primaryStage.setWidth(600);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(600);
        primaryStage.setMaxHeight(600);
        primaryStage.setMaxWidth(600);
        primaryStage.show();
    }

    private void paneOEventHandler(Stage primaryStage, BoardLogic boardLogic) {
        ArtificialIntelligence artificialIntelligence = new ArtificialIntelligence();
        artificialIntelligence.setAIFigureX(true);
        primaryStage.setScene(new Scene(boardLogic.drawBoard()));
    }

    private void paneXEventHandler(Stage primaryStage, BoardLogic boardLogic) {
        ArtificialIntelligence artificialIntelligence = new ArtificialIntelligence();
        artificialIntelligence.setAIFigureX(false);
        primaryStage.setScene(new Scene(boardLogic.drawBoard()));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
