package common;

import java.util.List;

public interface SimpleMover {
    public MoveResult<Board,String> move(List<Board> history, Coordinate from, Coordinate to, Player currentPlayer);
}
