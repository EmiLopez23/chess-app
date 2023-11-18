package common.validators;

import common.Board;
import common.Coordinate;
import common.Movement;

import java.util.List;

public class JumpValidator implements MovementValidator {

    List<Coordinate> possibleJumps;

    public JumpValidator(List<Coordinate> possibleJumps) {
        this.possibleJumps = possibleJumps;
    }
    @Override
    public boolean isValid(List<Board> boardHistory, Movement movement) {
        return possibleJumps.stream().anyMatch(jump -> movement.from().add(jump).equals(movement.to()));
    }
}
