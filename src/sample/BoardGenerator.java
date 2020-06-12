package sample;

import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class BoardGenerator {

    private ArtificialIntelligence artificialIntelligence = new ArtificialIntelligence();

    private boolean playable = true;

    private boolean turnO = true;
    private List<WinSeries> seriesList = new ArrayList<>();
    private Square[][] fields = new Square[3][3];
    private List<Square> listOfFilledSquares = new ArrayList<>();


    public Parent drawBoard() {

        Button refreshButton = getRefreshButton();

        Pane pane = getPane();

        AnchorPane anchorPane = getAnchorPane(refreshButton, pane);

        createSquares(refreshButton, pane);
        generateSeriesList();
        return anchorPane;
    }

    private Pane getPane() {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: darkgray");
        pane.setPrefSize(300,300);
        return pane;
    }

    private AnchorPane getAnchorPane(Button refreshButton, Pane pane) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(600, 600);

        anchorPane.setStyle("-fx-background-color: fuchsia");
        anchorPane.getChildren().addAll(pane, refreshButton);
        anchorPane.setTopAnchor(pane, 100.0);
        anchorPane.setLeftAnchor(pane, 140.0);
        anchorPane.setBottomAnchor(refreshButton, 50.0);
        anchorPane.setLeftAnchor(refreshButton, 140.0);
        return anchorPane;
    }

    private Button getRefreshButton() {
        Button refreshButton = new Button("Refresh");
        refreshButton.setPrefSize(100, 20);
        refreshButton.setStyle("-fx-background-color: gold");
        refreshButton.setFont(new Font(20));
        refreshButton.setLayoutX(150);
        refreshButton.setLayoutY(20);
        return refreshButton;
    }

    private void createSquares(Button refreshButton, Pane pane) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                Square square = new Square();
                square.setTranslateX(j*100);
                square.setTranslateY(i*100);
                square.setOnMouseClicked(event -> squareClickEventHandler(square));
                refreshButton.setOnAction(event -> refreshButtonHandler());
                fields[j][i] = square;
                pane.getChildren().add(square);
            }
        }
    }

    private void refreshButtonHandler() {
        for (Square square1 : listOfFilledSquares) {
            clearCanvas(square1);
        }
        listOfFilledSquares.clear();
    }

    private void squareClickEventHandler(Square square) {
        if(playable == false) {
            return;
        }
        if (square.getIsFill() == false && turnO == true) {
            drawO(square);
            checkFields();
        }
        if (square.getIsFill() == false && turnO == false) {
            drawX(square);
            checkFields();
            printList(listOfFilledSquares);
        }
    }


    private void checkFields() {
        for (WinSeries winSeries : seriesList) {
            if(winSeries.isDone() == true) {
                playable = false;
                break;
            }
        }
    }

    private void drawO(Square square) {
        GraphicsContext graphicsContext = square.getCanvas().getGraphicsContext2D();
        graphicsContext.setLineWidth(10);
        graphicsContext.setStroke(Color.WHITE);
        graphicsContext.strokeOval(25, 25, 50 , 50);
        square.setIsFill(true);
        square.setIsInsideO(true);
        turnO = false;
        listOfFilledSquares.add(square);
    }

    private void drawX(Square square) {
        GraphicsContext graphicsContext = square.getCanvas().getGraphicsContext2D();
        graphicsContext.setLineWidth(10);
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.strokeLine(25, 25, 75, 75);
        graphicsContext.strokeLine(25, 75, 75, 25);
        square.setIsFill(true);
        square.setIsInsideX(true);
        turnO = true;
        listOfFilledSquares.add(square);
    }

    private void clearCanvas(Square square) {
        Canvas canvas = square.getCanvas();
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, 100, 100);
        square.setIsFill(false);
        square.setIsInsideO(false);
        square.setIsInsideX(false);
        turnO = true;
        playable = true;
    }


    private void generateSeriesList() {
        for (int col = 0; col < 3; col++) {
            seriesList.add(new WinSeries(fields[0][col], fields[1][col], fields[2][col]));
        }

        for (int row = 0; row < 3; row++) {
            seriesList.add(new WinSeries(fields[row][0], fields[row][1], fields[row][2]));
        }

        seriesList.add(new WinSeries(fields[0][0], fields[1][1], fields[2][2]));
        seriesList.add(new WinSeries(fields[0][2], fields[1][1], fields[2][0]));
    }

    public void setTurnO(boolean turnO) {
        this.turnO = turnO;
    }

    public void printList(List<Square> list) {
        System.out.println(list);
    }

}
