package sample;

import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class BoardGenerator {

    private boolean playable = true;
    private boolean turn = true;
    private List<WinSeries> seriesList = new ArrayList<>();
    private Square[][] fields = new Square[3][3];
    private List<Square> listOfFilledSquares = new ArrayList<>();
    private List<Square> listOfEmptySquares = new ArrayList<>();
    private ArtificialIntelligence artificialIntelligence = new ArtificialIntelligence();

    private Canvas canvas = new Canvas(50, 50);
    private Text text = new Text();


    public Parent drawBoard() {

        Button refreshButton = getRefreshButton();
        Pane pane = getPane();
        Pane paneWin = getWinPane();
        Pane paneText = getTextPane();
        AnchorPane anchorPane = getAnchorPane(refreshButton, pane, paneWin, paneText);
        createSquares(refreshButton, pane);
        generateSeriesList();

        return anchorPane;
    }

    private Pane getWinPane() {
        Pane pane = new Pane();
        pane.setPrefSize(75,75);
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

    private void createSquares(Button refreshButton, Pane pane) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                Square square = new Square();
                square.setTranslateX(j*100);
                square.setTranslateY(i*100);
                listOfEmptySquares.add(square);
                BoardInformations.getHelpfulList().add(square);
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
        listOfEmptySquares.clear();
        listOfEmptySquares = new ArrayList<Square>(BoardInformations.getHelpfulList());
        BoardInformations.sizeOfHelpfulList();
        printList(listOfEmptySquares);
    }

    private void squareClickEventHandler(Square square) {
        if(playable == false) {
            return;
        }

        if (square.getIsFill() == false && turn == true) {
            if(FigureInformations.getFigureX() == true) {
                drawX(square);
                checkFields();
                if(playable == true) {
                    try {
                        drawO(artificialIntelligence.randomEmptySquare(listOfEmptySquares));
                    } catch (NullPointerException npe) {
                        System.out.println(npe + "1");
                    }
                }
            }else {
                drawO(square);
                checkFields();
                if(playable == true) {
                    try {
                        drawX(artificialIntelligence.randomEmptySquare(listOfEmptySquares));
                    } catch (NullPointerException npe) {
                        System.out.println(npe);
                    }
                }
            }
            turn = true;
        }
    }


    private void checkFields() {
        for (WinSeries winSeries : seriesList) {
            if(winSeries.isDone() == true || listOfFilledSquares.size() == 9) {
                playable = false;
                break;
            }
        }
    }

    private void drawO(Square square) {
        if(playable == true) {
            GraphicsContext graphicsContext = square.getCanvas().getGraphicsContext2D();
            graphicsContext.setLineWidth(10);
            graphicsContext.setStroke(Color.WHITE);
            graphicsContext.strokeOval(25, 25, 50, 50);
            square.setIsFill(true);
            square.setIsInsideO(true);
            turn = false;
            listOfEmptySquares.remove(square);
            listOfFilledSquares.add(square);
        }
    }

    private void drawX(Square square) {
        if (playable == true) {
            GraphicsContext graphicsContext = square.getCanvas().getGraphicsContext2D();
            graphicsContext.setLineWidth(10);
            graphicsContext.setStroke(Color.BLACK);
            graphicsContext.strokeLine(25, 25, 75, 75);
            graphicsContext.strokeLine(25, 75, 75, 25);
            square.setIsFill(true);
            square.setIsInsideX(true);
            turn = true;
            listOfEmptySquares.remove(square);
            listOfFilledSquares.add(square);
        }
    }

    private void clearCanvas(Square square) {
        Canvas canvas = square.getCanvas();
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, 100, 100);
        square.setIsFill(false);
        square.setIsInsideO(false);
        square.setIsInsideX(false);
        turn = true;
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

    public void printList(List<Square> list) {
        System.out.println(list.size());
    }

}
