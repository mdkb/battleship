package matt.sandbox.model;

import java.util.Arrays;

public class Vessel {

    public enum Type {
        CARRIER(5, 'A'), BATTLESHIP(4, 'B'), CRUISER(3, 'C'), SUBMARINE(3,'S'), DESTROYER(2,'D');

        Type(int size, char symbol) {
            this.size = size;
            this.symbol = symbol;
        }

        final int size;
        final char symbol;

        public char getSymbol() {
            return symbol;
        }

        public int getSize() {
            return size;
        }
    }

    private final Type type;

    private final boolean [] hits;

    public void hit(int index) {
        hits[index] = true;
    }

    public boolean isSunk() {
        for (boolean hit : hits) {
            if (!hit) {
                return false;
            }
        }
        return true;
    }

    public Vessel(Type type) {
        this.type = type;
        hits = new boolean[type.size];
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Vessel{" +
                "type=" + type +
                ", hits=" + Arrays.toString(hits) +
                '}';
    }
}
