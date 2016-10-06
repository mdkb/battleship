package matt.sandbox.controller;

import matt.sandbox.model.Board;
import matt.sandbox.model.Coordinates;
import matt.sandbox.view.TextView;
import org.junit.Test;

public class GameControllerTest {


    @Test
    public void testPlacement() {
        GameController controller = new GameController();
        controller.placeVessels();


        Board board = controller.getBoard1();
        RandomVesselPlacer placer = new RandomVesselPlacer(board);
        for (int i = 0; i < 70; i++) {
            Coordinates coordinates =  placer.randomTarget();
            board.getSquare(coordinates).fire();
        }

        TextView view = new TextView();
        view.printBoard(controller.getBoard1());
    //    view.printBoard(controller.getBoard2());




    }


}