package sample;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class BoardInformations {
    private static List<Square> helpfulList = new ArrayList<>();

    public static List<Square> getHelpfulList() {
        return helpfulList;
    }

    public static void sizeOfHelpfulList() {
        System.out.println(BoardInformations.helpfulList.size());
    }
}
