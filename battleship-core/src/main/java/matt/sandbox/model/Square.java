package matt.sandbox.model;

public class Square {
    private final Coordinates coordinates;
    private Vessel contents = null;
    int vesselIndex = -1;
    private boolean targeted = false;


    public Square(int x, int y) {
        this(new Coordinates(x, y));
    }

    public Square(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setContents(Vessel contents, int vesselIndex) {
        this.contents = contents;
        this.vesselIndex = vesselIndex;
    }

    public ShotResult fire() {
        targeted = true;
        if (contents != null) {
            contents.hit(vesselIndex);
            if (contents.isSunk()) {
                return ShotResult.HIT_SINK;
            }
            return ShotResult.HIT;
        }
        return ShotResult.MISS;
    }


    public Vessel getContents() {
        return contents;
    }

    public int getVesselIndex() {
        return vesselIndex;
    }

    public boolean isTargeted() {
        return targeted;
    }

    @Override
    public String toString() {
        return "Square{" +
                "coordinates=" + coordinates +
                ", contents=" + contents +
                ", targeted=" + targeted +
                '}';
    }
}
