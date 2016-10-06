package matt.sandbox.controller;

import matt.sandbox.model.Board;
import matt.sandbox.model.Placement;
import matt.sandbox.model.Vessel;

import java.util.Map;

public class GameController {

    Board board1 = new Board();
    Board board2 = new Board();


    public void placeVessels() {
        placeVessels(board1);
        placeVessels(board2);
    }

    private void placeVessels(Board board) {
        RandomVesselPlacer placer = new RandomVesselPlacer(board);

        for (Vessel.Type type : Vessel.Type.values()) {
            Vessel vessel = new Vessel(type);
            placer.placeVessel(vessel);
        }
    }

    public Board getBoard1() {
        return board1;
    }

    public Board getBoard2() {
        return board2;
    }
}
