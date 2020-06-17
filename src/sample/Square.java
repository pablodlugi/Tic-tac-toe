package sample;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends StackPane{

    private boolean insideO = false;
    private boolean isInsideX = false;
    private boolean isFill = false;
    private Canvas canvas;

    public Square(){
        Rectangle field = new Rectangle(100, 100);
        canvas = new Canvas(100, 100);
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


    @Override
    public String toString() {
        return "Square{" +
                ", insideO=" + insideO +
                ", isInsideX=" + isInsideX +
                ", isFill=" + isFill +
                '}';
    }
}
