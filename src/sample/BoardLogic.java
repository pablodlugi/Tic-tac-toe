package sample;

import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class BoardLogic {

    private boolean playable = true;
    private List<WinSeries> seriesList = new ArrayList<>();
    private Square[][] fields = new Square[3][3];
    private ArtificialIntelligence artificialIntelligence = new ArtificialIntelligence();

    private Canvas canvas = new Canvas(50, 50);
    private Text text = new Text();



    public Parent drawBoard() {

        Button refreshButton = getRefreshButton();
        Pane paneWin = getWinPane();
        Pane paneText = getTextPane();
        Pane pane = getPane();
        AnchorPane anchorPane = getAnchorPane(refreshButton, pane, paneWin, paneText);
        createSquares(refreshButton, pane);
        generateSeriesList();

        return anchorPane;
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
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                clearCanvas(fields[i][j]);
            }
        }
    }

    private void squareClickEventHandler(Square square) {
        if (playable) {
            if(artificialIntelligence.getIsAIFigureX() == true && square.getIsFill() == false) {
                drawO(square);
                drawX(artificialIntelligence.possibleMove(fields));
            }else if(artificialIntelligence.getIsAIFigureX() == false && square.getIsFill() == false) {
                drawX(square);
                drawO(artificialIntelligence.possibleMove(fields));
            }
            checkFields();
        }else {
            return;
        }
    }


    private void checkFields() {
        for (WinSeries winSeries : seriesList) {
            if(winSeries.isDone() == true) {
                GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
                canvasO(graphicsContext);
                playable = false;
                break;
            }
        }
    }

    private void drawO(Square square) {
        GraphicsContext graphicsContext = square.getCanvas().getGraphicsContext2D();
        canvasO(graphicsContext);
        square.setIsFill(true);
        square.setIsInsideO(true);
    }

    private void drawX(Square square) {
        GraphicsContext graphicsContext = square.getCanvas().getGraphicsContext2D();
        canvasX(graphicsContext);
        square.setIsFill(true);
        square.setIsInsideX(true);
    }

    private void clearCanvas(Square square) {
        Canvas canvas = square.getCanvas();
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, 100, 100);
        square.setIsFill(false);
        square.setIsInsideO(false);
        square.setIsInsideX(false);
        playable = true;
    }

    private void canvasO(GraphicsContext graphicsContext) {
        graphicsContext.setLineWidth(10);
        graphicsContext.setStroke(Color.WHITE);
        graphicsContext.strokeOval(25, 25, 50, 50);
    }

    private void canvasX(GraphicsContext graphicsContext) {
        graphicsContext.setLineWidth(10);
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.strokeLine(25, 25, 75, 75);
        graphicsContext.strokeLine(25, 75, 75, 25);
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

    private Pane getWinPane() {
        Pane pane = new Pane();
        pane.setPrefSize(50,50);
        pane.getChildren().add(canvas);
        pane.setStyle("-fx-background-color: blue");
        return pane;
    }

    private Pane getTextPane() {
        Pane pane = new Pane();
        pane.setPrefSize(100,75);
        pane.setStyle("-fx-background-color: chartreuse");
        text.setFont(Font.font("Arial Black", 25));
        pane.getChildren().add(text);
        return pane;
    }

    private Pane getPane() {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: darkgray");
        pane.setPrefSize(300,300);
        return pane;
    }

    private AnchorPane getAnchorPane(Button refreshButton, Pane... panes) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(600, 600);

        anchorPane.setStyle("-fx-background-color: fuchsia");
        anchorPane.getChildren().addAll(panes[0], panes[1], panes[2], refreshButton);
        anchorPane.setTopAnchor(panes[0], 100.0);
        anchorPane.setLeftAnchor(panes[0], 140.0);
        anchorPane.setBottomAnchor(refreshButton, 50.0);
        anchorPane.setLeftAnchor(refreshButton, 140.0);
        anchorPane.setBottomAnchor(panes[1], 50.0);
        anchorPane.setLeftAnchor(panes[1], 300.0);
        anchorPane.setBottomAnchor(panes[2], 50.0);
        anchorPane.setLeftAnchor(panes[2], 380.0);

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

    public void printList(List<Square> list) {
        System.out.println(list.size());
    }

}
