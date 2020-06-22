package sample;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class BoardLogic {

    private boolean playable = true;
    private int movesCounter = 0;
    private List<WinSeries> seriesList = new ArrayList<>();
    private Square[][] fields = new Square[3][3];
    private ArtificialIntelligence artificialIntelligence = new ArtificialIntelligence();

    private static int boardDim = 3;

    private Label label = getLabel();
    private Pane paneWin = getWinPane();

    public Parent drawBoard() {

        Button refreshButton = getRefreshButton();
        Pane pane = getPane();
        AnchorPane anchorPane = getAnchorPane(refreshButton, label, pane, paneWin);
        createSquares(refreshButton, pane);
        generateSeriesList();

        return anchorPane;
    }

    private void createSquares(Button refreshButton, Pane pane) {
        for(int i = 0; i < boardDim; i++) {
            for(int j = 0; j < boardDim; j++) {
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
        Node node = paneWin.getChildren().get(0);
        if (node instanceof Square) {
            Square square = (Square) node;
            clearCanvas(square);
        }

        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                clearCanvas(fields[i][j]);
            }
        }
        label.setText("");
        movesCounter = 0;
    }

    private void squareClickEventHandler(Square square) {
        if (playable) {
            if(artificialIntelligence.IsAIFigureX() == true && square.isFill() == false) {
                drawO(square);
                drawX(artificialIntelligence.possibleMove(fields));
            }else if(artificialIntelligence.IsAIFigureX() == false && square.isFill() == false) {
                drawX(square);
                drawO(artificialIntelligence.possibleMove(fields));
            }
        }else {
            return;
        }
    }


    private void checkFields(Square square) {
        movesCounter++;
        Node node = paneWin.getChildren().get(0);
        for (WinSeries winSeries : seriesList) {
            if(winSeries.isDone() == true && square.isInsideO() == true) {
                if(node instanceof Square) {
                    GraphicsContext graphicsContext = ((Square) node).getCanvas().getGraphicsContext2D();
                    canvasO(graphicsContext);
                    label.setText("Wins!");
                    playable = false;
                    break;
                }
            }else if(winSeries.isDone() == true && square.isInsideX() == true) {
                if (node instanceof Square) {
                    GraphicsContext graphicsContext = ((Square) node).getCanvas().getGraphicsContext2D();
                    canvasX(graphicsContext);
                    label.setText("Wins!");
                    playable = false;
                    break;
                }
            }else if(winSeries.isDone() == false && movesCounter == 9) {
                label.setText("Draw!");
                playable = false;
            }
        }
    }

    private void drawO(Square square) {
        if(playable) {
            GraphicsContext graphicsContext = square.getCanvas().getGraphicsContext2D();
            canvasO(graphicsContext);
            square.setFill(true, "O");
            checkFields(square);
        }
    }

    private void drawX(Square square) {
        if(playable) {
            GraphicsContext graphicsContext = square.getCanvas().getGraphicsContext2D();
            canvasX(graphicsContext);
            square.setFill(true, "X");
            checkFields(square);
        }
    }

    private void clearCanvas(Square square) {
        Canvas canvas = square.getCanvas();
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, 100, 100);
        square.setFill(false, null);
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
        Square square = new Square();
        pane.setPrefSize(100,100);
        pane.setStyle("-fx-background-color: blueviolet");
        pane.getChildren().add(square);
        pane.getChildren().set(0, square);
        return pane;
    }

    private Label getLabel() {
        Label label = new Label();
        label.setFont(new Font("Arial BLack",30));
        return label;
    }

    private Pane getPane() {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: darkgray");
        pane.setPrefSize(300,300);
        return pane;
    }

    private AnchorPane getAnchorPane(Button refreshButton, Label label, Pane... panes) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(600, 600);

        anchorPane.setStyle("-fx-background-color: royalblue");
        anchorPane.getChildren().addAll(panes[0], panes[1], refreshButton, label);
        anchorPane.setTopAnchor(panes[0], 100.0);
        anchorPane.setLeftAnchor(panes[0], 140.0);
        anchorPane.setBottomAnchor(refreshButton, 50.0);
        anchorPane.setLeftAnchor(refreshButton, 140.0);
        anchorPane.setBottomAnchor(panes[1], 25.0);
        anchorPane.setLeftAnchor(panes[1], 300.0);
        anchorPane.setBottomAnchor(label, 50.0);
        anchorPane.setLeftAnchor(label, 450.0);

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
}
