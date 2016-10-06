package matt.sandbox.view;

import matt.sandbox.model.Board;
import matt.sandbox.model.Square;
import matt.sandbox.model.Vessel;

public class TextView {

    public void printBoard(Board board) {
        int size = board.SIZE;

        // 1 print header
        StringBuilder buf = new StringBuilder();
        buf.append("  ");
        for (int x = 0; x < size; x++) {
            buf.append("|");
            buf.append(x);
        }
        buf.append("\n");

        for (int x = 0; x <= size; x++) {
            buf.append("--");
        }
        buf.append("\n");

        for (int y = 0; y < size; y++) {
            buf.append(y);
            buf.append("|");
            for (int x = 0; x < size; x++) {
                Square square = board.getSquare(x,y);
                buf.append(getDisplay(square));
                buf.append("|");
            }
            buf.append("\n");
            for (int x = 0; x <= size; x++) {
                buf.append("--");
            }
            buf.append("\n");
        }

        System.out.println(buf);
    }

    private char getDisplay(Square square) {
        Vessel vessel = square.getContents();
        if (vessel == null) {
            if (square.isTargeted()) {
                return 'O'; // miss
            } else {
                return ' '; // no shot
            }
        }

        if (vessel.isSunk()) {
            return 'X';
        }

        if (square.isTargeted()) {
           return 'x'; // hit
        }

        return square.getContents().getType().getSymbol();
    }
}
