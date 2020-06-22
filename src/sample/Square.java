package sample;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends StackPane{

    private boolean insideO;
    private boolean insideX;
    private boolean isFill;
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

    public boolean isFill() {
        return isFill;
    }

    public void setFill(boolean fill, String filledFigure) {
        if(fill) {
            this.isFill = true;
            if (filledFigure.equals("X")) {
                this.insideX = true;
                this.insideO = false;
            }else if (filledFigure.equals("O")) {
                this.insideX = false;
                this.insideO = true;
            }
        }else {
            this.isFill = false;
            this.insideX = false;
            this.insideO = false;
        }
    }

    public boolean isInsideO() {
        return insideO;
    }

    public boolean isInsideX() {
        return insideX;
    }

    @Override
    public String toString() {
        return "Square{" +
                " insideO=" + insideO +
                ", isInsideX=" + insideX +
                ", isFill=" + isFill +
                '}';
    }
}
