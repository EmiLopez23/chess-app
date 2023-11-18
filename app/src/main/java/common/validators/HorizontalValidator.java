package common.validators;

import common.Board;
import common.Movement;

import java.util.List;

public class HorizontalValidator implements MovementValidator {

    @Override
    public boolean isValid(List<Board> boardHistory, Movement movement) {
        return movement.isHorizontal();
    }
}
