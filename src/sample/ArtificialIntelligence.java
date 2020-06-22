package sample;


import java.util.Random;

public class ArtificialIntelligence {

    private static boolean isAIFigureX;

    public Square possibleMove(Square[][] squares) {
        Square square = new Square();
        Random random = new Random();

        int numberOfFields = squares.length * squares[1].length;

        for(int k = 0; k < numberOfFields; k++) {
            int i = random.nextInt(squares.length);
            int j = random.nextInt(squares[1].length);
            if (!squares[i][j].isFill()) {
                square = squares[i][j];
                break;
            } else {
                continue;
            }
        }
        return square;
    }

    public boolean IsAIFigureX() {
        return isAIFigureX;
    }

    public void setAIFigureX(boolean AIFigureX) {
        isAIFigureX = AIFigureX;
    }
}
