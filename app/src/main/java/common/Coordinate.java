package common;

import java.util.Objects;

public record Coordinate(int column, int row) {

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Coordinate coordinate)) return false;
        return coordinate.column() == this.column() && coordinate.row() == this.row();
    }


    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
