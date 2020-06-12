package sample;


import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ChooseFigure {
    private boolean figureX;
    private boolean figureO;

    private Pane paneO;
    private Pane paneX;
    private Pane paneLabel;
    private AnchorPane anchorPane;

    public ChooseFigure() {
        this.paneO = getPaneO();
        this.paneX = getPaneX();
        this.paneLabel = getPaneLabel();
        this.anchorPane = getAnchorPane(this.paneO, this.paneX, this.paneLabel);
        this.figureO = false;
        this.figureX = false;

    }

    public Parent drawChooseFigureScene() {
        createFieldsToChoose(paneO, paneX);
        return anchorPane;
    }

    private AnchorPane getAnchorPane(Pane... panes) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: fuchsia");
        anchorPane.setTopAnchor(panes[0], 200.0);
        anchorPane.setLeftAnchor(panes[0], 195.0);
        anchorPane.setTopAnchor(panes[1], 200.0);
        anchorPane.setLeftAnchor(panes[1], 305.0);
        anchorPane.setTopAnchor(panes[2], 100.0);
        anchorPane.setLeftAnchor(panes[2], 175.0);
        anchorPane.getChildren().addAll(panes[0], panes[1], panes[2]);
        return anchorPane;
    }


    private Pane getPaneLabel() {
        Pane pane = new Pane();
        Text text = new Text();
        text.setText("Choose your figure");
        text.setFont(Font.font("Arial Black", 25));
        pane.setStyle("-fx-background-color: fuchsia");
        pane.setPrefSize(250, 75);
        pane.getChildren().add(text);


        return pane;
    }

    private void createFieldsToChoose(Pane paneO, Pane paneX) {
        for(int i = 0; i < 2; i++) {
            Square square = new Square();
            GraphicsContext graphicsContext = square.getCanvas().getGraphicsContext2D();
            if (i==0) {
                graphicsContext.setLineWidth(10);
                graphicsContext.setStroke(Color.WHITE);
                graphicsContext.strokeOval(25, 25, 50, 50);
                paneO.getChildren().add(square);
            }else {
                graphicsContext.setLineWidth(10);
                graphicsContext.setStroke(Color.BLACK);
                graphicsContext.strokeLine(25, 25, 75, 75);
                graphicsContext.strokeLine(25, 75, 75, 25);
                paneX.getChildren().add(square);
            }
        }
    }

    private Pane getPaneX() {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: darkgray");
        pane.setPrefSize(100, 100);
        return pane;
    }

    private Pane getPaneO() {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: darkgray");
        pane.setPrefSize(100, 100);
        return pane;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public Pane getPanel0() {
        return paneO;
    }

    public Pane getPanelX() {
        return paneX;
    }

    public boolean getFigureX() {
        return figureX;
    }

    public boolean getFigureO() {
        return figureO;
    }

    public void setFigureX(boolean figureX) {
        this.figureX = figureX;
    }

    public void setFigureO(boolean figureO) {
        this.figureO = figureO;
    }
}
