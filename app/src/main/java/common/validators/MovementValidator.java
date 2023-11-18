package common.validators;

import common.Board;
import common.Movement;

import java.util.List;

public interface MovementValidator {
    boolean isValid(List<Board> boardHistory, Movement movement);
}
