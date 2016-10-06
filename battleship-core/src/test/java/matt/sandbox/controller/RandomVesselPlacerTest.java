package matt.sandbox.controller;

import matt.sandbox.model.Board;
import matt.sandbox.model.Placement;
import matt.sandbox.model.Vessel;
import org.junit.Test;

import static org.junit.Assert.*;

public class RandomVesselPlacerTest {

    @Test
    public void placeOnEmptyBoard() {
        Board board = new Board();
        RandomVesselPlacer placer = new RandomVesselPlacer(board);

        Placement placement = placer.placeVessel(new Vessel(Vessel.Type.BATTLESHIP));
        System.out.println(placement);

    }
}