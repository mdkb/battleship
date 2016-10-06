package matt.sandbox.view;

import matt.sandbox.model.Board;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextViewTest {

    @Test
    public void testEmptyBoard() {
        Board board = new Board();
        TextView view = new TextView();
        view.printBoard(board);
    }
}