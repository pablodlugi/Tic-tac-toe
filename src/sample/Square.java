package sample;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends StackPane{

    private int row;
    private int col;
    private boolean insideO = false;
    private boolean isInsideX = false;
    private boolean isFill = false;
    private Canvas canvas;
    private double xCoord;
    private double yCoord;

    public Square(int col, int row){
        this.col = col;
        this.row = row;
        Rectangle field = new Rectangle(100, 100);
        field.setStyle("-fx-background-color: darkgray");
        canvas= new Canvas(100, 100);
        xCoord = canvas.getLayoutX();
        yCoord = canvas.getLayoutY();
        field.setFill(null);
        field.setStroke(Color.BLACK);
        field.setStrokeWidth(5);
        setAlignment(Pos.CENTER);
        getChildren().addAll(field, canvas);
    }



    public  Canvas getCanvas() {
        return canvas;
    }

    public boolean getIsFill() {
        return isFill;
    }

    public void setIsFill(boolean fill) {
        isFill = fill;
    }

    public boolean getIsInsideO() {
        return insideO;
    }

    public void setIsInsideO(boolean insideO) {
        this.insideO = insideO;
    }

    public boolean getIsInsideX() {
        return isInsideX;
    }

    public void setIsInsideX(boolean insideX) {
        isInsideX = insideX;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return "Square{" +
                "row=" + row +
                ", col=" + col +
                ", insideO=" + insideO +
                ", isInsideX=" + isInsideX +
                ", isFill=" + isFill +
                ", xCoord=" + xCoord +
                ", yCcoord=" + yCoord +
                '}';
    }
}
