package matt.sandbox.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private static final Logger LOG = LoggerFactory.getLogger(Board.class);

    public static final int SIZE = 10;
    private Square [][] grid = new Square[SIZE][SIZE];

    private final Map<Vessel, Placement> placedVessels = new HashMap<>();

    public Board() {
        for (int y = 0; y < grid.length; y++) {
            Square[] row = grid[y];
            for (int x = 0; x < row.length; x++) {
                row[x] = new Square(x, y);
            }

        }
    }


    public boolean placeVessel(Vessel vessel, Placement placement) {
        Vessel.Type type = vessel.getType();
        if (isValidPlacement(type, placement)) {
            placedVessels.put(vessel, placement);

            int x = placement.getCoordinates().getX();
            int y = placement.getCoordinates().getY();

            for (int i = 0; i < type.getSize(); i++) {
                Square square = getSquare(x, y);
                square.setContents(vessel, i);

                switch (placement.getDirection()) {
                    case HORIZONTAL:
                        x++;
                        break;
                    case VERTICAL:
                        y++;
                        break;
                }
            }

            return true;
        }
        return false;
    }


    public boolean isValidPlacement(Vessel.Type type, Placement placement) {
        int x = placement.getCoordinates().getX();
        int y = placement.getCoordinates().getY();

        Direction direction = placement.getDirection();

        for (int i = 0; i < type.getSize(); i++) {
            if (x>=SIZE) {
                LOG.info("x:"+x+" is out of range");
                return false;
            }

            if (y >= SIZE) {
                LOG.info("y:"+y+" is out of range");
                return false;
            }

            Square square = getSquare(x, y);
            if (square.getContents() != null) {
                LOG.info("square "+ square +" is already full");

                return false;
            }

            switch (direction) {
                case HORIZONTAL:
                    x++;
                    break;
                case VERTICAL:
                    y++;
                    break;
            }
        }
        return true;
    }

    public Map<Vessel, Placement> getPlacedVessels() {
        return placedVessels;
    }

    public Square getSquare(Coordinates coordinates) {
        return grid[coordinates.getX()][coordinates.getY()];
    }

    public Square getSquare(int x, int y) {
        return grid[x][y];
    }
}
