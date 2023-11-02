package common;

import common.Coordinate;

public class Movement {
    private final Coordinate from;
    private final Coordinate to;

    public Movement(Coordinate from, Coordinate to) {
        this.from = from;
        this.to = to;
    }

    public Coordinate getFrom() {
        return this.from;
    }

    public Coordinate getTo() {
        return this.to;
    }

}
