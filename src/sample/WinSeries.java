package sample;

public class WinSeries {
    private Square[] squares;

    public WinSeries(Square... squares) {
        this.squares = squares;
    }


    public boolean isDone() {
        if (squares[0].getIsFill() == false)
            return false;

        return (squares[0].getIsInsideO() == true && squares[1].getIsInsideO() == true && squares[2].getIsInsideO() == true)
                || (squares[0].getIsInsideX() == true && squares[1].getIsInsideX() == true && squares[2].getIsInsideX() == true);
    }
}
