package common.validators;

import common.Board;
import common.Movement;

import java.util.List;

public class HasEatenValidator implements MovementValidator{
    @Override
    public boolean isValid(List<Board> boardHistory, Movement movement) {
        Board pastBoard = boardHistory.get(boardHistory.size() - 2);
        Board currentBoard = boardHistory.get(boardHistory.size() - 1);
        return currentBoard.getPieces().size() < pastBoard.getPieces().size();
    }
}
