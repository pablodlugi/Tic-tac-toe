package sample;

import java.util.List;
import java.util.Random;

public class ArtificialIntelligence {

    public Square randomEmptySquare(List<Square> emptySquares) {
        if (emptySquares.size() == 0) {
            return null;
        }else {
            Random random = new Random();
            int i = random.nextInt(emptySquares.size());

            return emptySquares.get(i);
        }
    }
}
