package sample;

public class WinSeries {
    private Square[] squares;

    public WinSeries(Square... squares) {
        this.squares = squares;
    }


    public boolean isDone() {
        if (squares[0].isFill() == false)
            return false;

        return (squares[0].isInsideO() == true && squares[1].isInsideO() == true && squares[2].isInsideO() == true)
                || (squares[0].isInsideX() == true && squares[1].isInsideX() == true && squares[2].isInsideX() == true);
    }
}
