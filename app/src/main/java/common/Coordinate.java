package common;

public record Coordinate(int column, int row) {

    public boolean isDiagonal(Coordinate other) {
        return Math.abs(this.column() - other.column()) == Math.abs(this.row() - other.row());
    }

    public boolean isVertical(Coordinate other) {
        return this.column() == other.column();
    }

    public boolean isHorizontal(Coordinate other) {
        return this.row() == other.row();
    }

    public Coordinate add(Coordinate other) {
        return new Coordinate(this.column() + other.column(), this.row() + other.row());
    }
}