package sample;


public class ArtificialIntelligence {

    private static boolean isAIFigureX;

    public Square possibleMove(Square[][] squares) {
        Square square = new Square();
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!squares[i][j].getIsFill()) {
                    square = squares[i][j];
                    break;
                }
            }
        }
        return square;
    }

    public boolean getIsAIFigureX() {
        return isAIFigureX;
    }

    public void setAIFigureX(boolean AIFigureX) {
        isAIFigureX = AIFigureX;
    }
}
