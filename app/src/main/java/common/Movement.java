package common;

public record Movement(Coordinate from, Coordinate to) {

    public int rowDifference(){
        return Math.abs(from.row() - to.row());
    }

    public int colDifference(){
        return Math.abs(from.column() - to.column());
    }

    public boolean isDiagonal(){
        return from.isDiagonal(to);
    }

    public boolean isHorizontal(){
        return from.isHorizontal(to);
    }

    public boolean isVertical(){
        return from.isVertical(to);
    }

    public int directionX(){return Integer.compare(to.column(), from.column());}

    public int directionY(){return Integer.compare(to.row(), from.row());}

    public Coordinate direction(){
        return new Coordinate(directionX(), directionY());
    }
}
