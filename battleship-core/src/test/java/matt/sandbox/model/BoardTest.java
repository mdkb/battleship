package matt.sandbox.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    private final Board board;

    public BoardTest() {
        board = new Board();
    }


    private boolean isValid(Vessel.Type type, int x, int y, Direction direction) {
        Coordinates coordinates = new Coordinates(x, y);
        Placement placement = new Placement(coordinates, direction);
        return board.isValidPlacement(type, placement);
    }


    private boolean place(Vessel vessel, int x, int y, Direction direction) {
        Coordinates coordinates = new Coordinates(x, y);
        Placement placement = new Placement(coordinates, direction);
        return board.placeVessel(vessel, placement);
    }


    //
    // Placement tests
    //

    @Test
    public void placeVessel() throws Exception {
        Vessel vessel = new Vessel(Vessel.Type.BATTLESHIP);
        Placement placement = new Placement(new Coordinates(0,0), Direction.HORIZONTAL);
        assertTrue(board.getPlacedVessels().isEmpty());
        assertTrue(place(vessel, 0, 0, Direction.HORIZONTAL));
        assertEquals(1, board.getPlacedVessels().size());
        assertEquals(placement, board.getPlacedVessels().get(vessel));

        assertEquals(vessel, board.getSquare(0, 0).getContents());
        assertEquals(vessel, board.getSquare(1, 0).getContents());
        assertEquals(vessel, board.getSquare(2, 0).getContents());
        assertEquals(vessel, board.getSquare(3, 0).getContents());
        assertNull(board.getSquare(4, 0).getContents());
    }


    @Test
    public void placeMultipleVessel() throws Exception {
        Vessel vessel1 = new Vessel(Vessel.Type.BATTLESHIP);
        Placement placement1 = new Placement(new Coordinates(0,0), Direction.HORIZONTAL);
        assertTrue(board.getPlacedVessels().isEmpty());
        assertTrue(place(vessel1, 0, 0, Direction.HORIZONTAL));
        assertEquals(1, board.getPlacedVessels().size());
        assertEquals(placement1, board.getPlacedVessels().get(vessel1));

        assertEquals(vessel1, board.getSquare(0, 0).getContents());
        assertEquals(vessel1, board.getSquare(1, 0).getContents());
        assertEquals(vessel1, board.getSquare(2, 0).getContents());
        assertEquals(vessel1, board.getSquare(3, 0).getContents());
        assertNull(board.getSquare(4, 0).getContents());

        Vessel vessel2 = new Vessel(Vessel.Type.CRUISER);
        Placement placement2 = new Placement(new Coordinates(4,4), Direction.VERTICAL);
        assertTrue(place(vessel2, 4, 4, Direction.VERTICAL));
        assertEquals(2, board.getPlacedVessels().size());
        assertEquals(placement2, board.getPlacedVessels().get(vessel2));

        assertEquals(vessel2, board.getSquare(4, 4).getContents());
        assertEquals(vessel2, board.getSquare(4, 5).getContents());
        assertEquals(vessel2, board.getSquare(4, 6).getContents());
        assertNull(board.getSquare(4, 7).getContents());

        Vessel vessel3 = new Vessel(Vessel.Type.DESTROYER);
        Placement placement3 = new Placement(new Coordinates(7,7), Direction.VERTICAL);
        assertTrue(place(vessel3, 7, 7, Direction.VERTICAL));
        assertEquals(3, board.getPlacedVessels().size());
        assertEquals(placement3, board.getPlacedVessels().get(vessel3));

        assertEquals(vessel3, board.getSquare(7, 7).getContents());
        assertEquals(vessel3, board.getSquare(7, 8).getContents());
        assertNull(board.getSquare(7, 9).getContents());
    }

    @Test
    public void invalidPlaceVessel() throws Exception {
        assertFalse(place(new Vessel(Vessel.Type.BATTLESHIP), 8, 0, Direction.HORIZONTAL));
        assertFalse(place(new Vessel(Vessel.Type.BATTLESHIP), 8, 8, Direction.VERTICAL));
    }


    //
    // placement validity tests
    //


    @Test
    public void invalidCoordinate() throws Exception {
        assertFalse(isValid(Vessel.Type.CARRIER, 10, 0, Direction.HORIZONTAL));
        assertFalse(isValid(Vessel.Type.CARRIER, 0, 10, Direction.HORIZONTAL));
    }

    @Test
    public void emptyTopRowCarrier() throws Exception {
        assertTrue(isValid(Vessel.Type.CARRIER, 0, 0, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.CARRIER, 1, 0, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.CARRIER, 2, 0, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.CARRIER, 3, 0, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.CARRIER, 4, 0, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.CARRIER, 5, 0, Direction.HORIZONTAL));
        assertFalse(isValid(Vessel.Type.CARRIER, 6, 0, Direction.HORIZONTAL));
        assertFalse(isValid(Vessel.Type.CARRIER, 7, 0, Direction.HORIZONTAL));
        assertFalse(isValid(Vessel.Type.CARRIER, 8, 0, Direction.HORIZONTAL));
        assertFalse(isValid(Vessel.Type.CARRIER, 9, 0, Direction.HORIZONTAL));


        assertTrue(isValid(Vessel.Type.CARRIER, 0, 0, Direction.VERTICAL));
        assertTrue(isValid(Vessel.Type.CARRIER, 1, 0, Direction.VERTICAL));
        assertTrue(isValid(Vessel.Type.CARRIER, 2, 0, Direction.VERTICAL));
        assertTrue(isValid(Vessel.Type.CARRIER, 3, 0, Direction.VERTICAL));
        assertTrue(isValid(Vessel.Type.CARRIER, 4, 0, Direction.VERTICAL));
        assertTrue(isValid(Vessel.Type.CARRIER, 5, 0, Direction.VERTICAL));
        assertTrue(isValid(Vessel.Type.CARRIER, 6, 0, Direction.VERTICAL));
        assertTrue(isValid(Vessel.Type.CARRIER, 7, 0, Direction.VERTICAL));
        assertTrue(isValid(Vessel.Type.CARRIER, 8, 0, Direction.VERTICAL));
        assertTrue(isValid(Vessel.Type.CARRIER, 9, 0, Direction.VERTICAL));
    }

    @Test
    public void emptyTopRowDESTROYER() throws Exception {
        assertTrue(isValid(Vessel.Type.DESTROYER, 0, 0, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.DESTROYER, 1, 0, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.DESTROYER, 2, 0, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.DESTROYER, 3, 0, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.DESTROYER, 4, 0, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.DESTROYER, 5, 0, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.DESTROYER, 6, 0, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.DESTROYER, 7, 0, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.DESTROYER, 8, 0, Direction.HORIZONTAL));
        assertFalse(isValid(Vessel.Type.DESTROYER, 9, 0, Direction.HORIZONTAL));
    }

    @Test
    public void emptyBottomRowCruiser() throws Exception {
        assertTrue(isValid(Vessel.Type.CRUISER, 0, 9, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.CRUISER, 1, 9, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.CRUISER, 2, 9, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.CRUISER, 3, 9, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.CRUISER, 4, 9, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.CRUISER, 5, 9, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.CRUISER, 6, 9, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.CRUISER, 7, 9, Direction.HORIZONTAL));
        assertFalse(isValid(Vessel.Type.CRUISER, 8, 9, Direction.HORIZONTAL));
        assertFalse(isValid(Vessel.Type.CRUISER, 9, 9, Direction.HORIZONTAL));

        assertFalse(isValid(Vessel.Type.CRUISER, 0, 9, Direction.VERTICAL));
        assertFalse(isValid(Vessel.Type.CRUISER, 1, 9, Direction.VERTICAL));
        assertFalse(isValid(Vessel.Type.CRUISER, 2, 9, Direction.VERTICAL));
        assertFalse(isValid(Vessel.Type.CRUISER, 3, 9, Direction.VERTICAL));
        assertFalse(isValid(Vessel.Type.CRUISER, 4, 9, Direction.VERTICAL));
        assertFalse(isValid(Vessel.Type.CRUISER, 5, 9, Direction.VERTICAL));
        assertFalse(isValid(Vessel.Type.CRUISER, 6, 9, Direction.VERTICAL));
        assertFalse(isValid(Vessel.Type.CRUISER, 7, 9, Direction.VERTICAL));
        assertFalse(isValid(Vessel.Type.CRUISER, 8, 9, Direction.VERTICAL));
        assertFalse(isValid(Vessel.Type.CRUISER, 9, 9, Direction.VERTICAL));
    }

    @Test
    public void emptyFirstColBattleship() throws Exception {
        assertTrue(isValid(Vessel.Type.BATTLESHIP, 0, 0, Direction.VERTICAL));
        assertTrue(isValid(Vessel.Type.BATTLESHIP, 0, 1, Direction.VERTICAL));
        assertTrue(isValid(Vessel.Type.BATTLESHIP, 0, 2, Direction.VERTICAL));
        assertTrue(isValid(Vessel.Type.BATTLESHIP, 0, 3, Direction.VERTICAL));
        assertTrue(isValid(Vessel.Type.BATTLESHIP, 0, 4, Direction.VERTICAL));
        assertTrue(isValid(Vessel.Type.BATTLESHIP, 0, 5, Direction.VERTICAL));
        assertTrue(isValid(Vessel.Type.BATTLESHIP, 0, 6, Direction.VERTICAL));
        assertFalse(isValid(Vessel.Type.BATTLESHIP, 0, 7, Direction.VERTICAL));
        assertFalse(isValid(Vessel.Type.BATTLESHIP, 0, 8, Direction.VERTICAL));
        assertFalse(isValid(Vessel.Type.BATTLESHIP, 0, 9, Direction.VERTICAL));

        assertTrue(isValid(Vessel.Type.BATTLESHIP, 0, 0, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.BATTLESHIP, 0, 1, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.BATTLESHIP, 0, 2, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.BATTLESHIP, 0, 3, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.BATTLESHIP, 0, 4, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.BATTLESHIP, 0, 5, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.BATTLESHIP, 0, 6, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.BATTLESHIP, 0, 7, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.BATTLESHIP, 0, 8, Direction.HORIZONTAL));
        assertTrue(isValid(Vessel.Type.BATTLESHIP, 0, 9, Direction.HORIZONTAL));
    }


    @Test
    public void emptyLastColSub() throws Exception {
        assertTrue(isValid(Vessel.Type.SUBMARINE, 9, 0, Direction.VERTICAL));
        assertTrue(isValid(Vessel.Type.SUBMARINE, 9, 1, Direction.VERTICAL));
        assertTrue(isValid(Vessel.Type.SUBMARINE, 9, 2, Direction.VERTICAL));
        assertTrue(isValid(Vessel.Type.SUBMARINE, 9, 3, Direction.VERTICAL));
        assertTrue(isValid(Vessel.Type.SUBMARINE, 9, 4, Direction.VERTICAL));
        assertTrue(isValid(Vessel.Type.SUBMARINE, 9, 5, Direction.VERTICAL));
        assertTrue(isValid(Vessel.Type.SUBMARINE, 9, 6, Direction.VERTICAL));
        assertTrue(isValid(Vessel.Type.SUBMARINE, 9, 7, Direction.VERTICAL));
        assertFalse(isValid(Vessel.Type.SUBMARINE, 9, 8, Direction.VERTICAL));
        assertFalse(isValid(Vessel.Type.SUBMARINE, 9, 9, Direction.VERTICAL));

        assertFalse(isValid(Vessel.Type.SUBMARINE, 9, 0, Direction.HORIZONTAL));
        assertFalse(isValid(Vessel.Type.SUBMARINE, 9, 1, Direction.HORIZONTAL));
        assertFalse(isValid(Vessel.Type.SUBMARINE, 9, 2, Direction.HORIZONTAL));
        assertFalse(isValid(Vessel.Type.SUBMARINE, 9, 3, Direction.HORIZONTAL));
        assertFalse(isValid(Vessel.Type.SUBMARINE, 9, 4, Direction.HORIZONTAL));
        assertFalse(isValid(Vessel.Type.SUBMARINE, 9, 5, Direction.HORIZONTAL));
        assertFalse(isValid(Vessel.Type.SUBMARINE, 9, 6, Direction.HORIZONTAL));
        assertFalse(isValid(Vessel.Type.SUBMARINE, 9, 7, Direction.HORIZONTAL));
        assertFalse(isValid(Vessel.Type.SUBMARINE, 9, 8, Direction.HORIZONTAL));
        assertFalse(isValid(Vessel.Type.SUBMARINE, 9, 9, Direction.HORIZONTAL));
    }


    @Test
    public void collision() {

        Vessel vessel = new Vessel(Vessel.Type.BATTLESHIP);
        Vessel vessel2 = new Vessel(Vessel.Type.CRUISER);
        assertTrue(board.getPlacedVessels().isEmpty());
        assertTrue(place(vessel, 0, 0, Direction.VERTICAL));
        assertFalse(place(vessel2, 0, 0, Direction.VERTICAL));
        assertEquals(1, board.getPlacedVessels().size());
    }

    @Test
    public void collisionSameSquare() {
        Vessel vessel = new Vessel(Vessel.Type.BATTLESHIP);
        Vessel vessel2 = new Vessel(Vessel.Type.CRUISER);
        assertTrue(board.getPlacedVessels().isEmpty());
        assertTrue(place(vessel, 2, 0, Direction.VERTICAL));
        assertFalse(place(vessel2, 0, 2, Direction.HORIZONTAL));
        assertEquals(1, board.getPlacedVessels().size());
    }

    @Test
    public void collisionStartsOnExisting() {
        Vessel vessel = new Vessel(Vessel.Type.BATTLESHIP);
        Vessel vessel2 = new Vessel(Vessel.Type.CRUISER);
        assertTrue(board.getPlacedVessels().isEmpty());
        assertTrue(place(vessel, 0, 0, Direction.HORIZONTAL));
        assertFalse(place(vessel2, 1, 0, Direction.VERTICAL));
        assertEquals(1, board.getPlacedVessels().size());
    }

    @Test
    public void testGetSquare() throws Exception {

    }

    @Test
    public void testGetSquare1() throws Exception {

    }
}