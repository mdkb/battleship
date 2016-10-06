package matt.sandbox.controller;

import matt.sandbox.model.*;

import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomVesselPlacer {

    private static final Logger LOG = LoggerFactory.getLogger(RandomVesselPlacer.class);

    Board board;

    public RandomVesselPlacer(Board board) {
        this.board = board;
    }

    public Placement placeVessel(Vessel vessel) {

        int maxAttempts = 1000;
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            int x = ThreadLocalRandom.current().nextInt(0, board.SIZE);
            int y = ThreadLocalRandom.current().nextInt(0, board.SIZE);
            Coordinates coordinates = new Coordinates(x, y);

            Direction direction = ThreadLocalRandom.current().nextBoolean() ? Direction.HORIZONTAL : Direction.VERTICAL;

            Placement placement = new Placement(coordinates, direction);

            LOG.info("testing " + coordinates + ", " + direction);

            if (board.placeVessel(vessel, placement)) {
                return placement;
            }
        }
        return null;
    }

    public Coordinates randomTarget() {
        int maxAttempts = 1000;
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            int x = ThreadLocalRandom.current().nextInt(0, board.SIZE);
            int y = ThreadLocalRandom.current().nextInt(0, board.SIZE);
            Coordinates coordinates = new Coordinates(x, y);

            if (!board.getSquare(x, y).isTargeted()) {
                return coordinates;
            }
        }
        return null;
    }
}
