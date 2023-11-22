package games.chess.mover;
import common.*;
import common.enums.GameState;
import common.enums.PieceType;
import common.mover.Mover;
import common.validators.FirstMoveValidator;
import common.validators.MovementValidator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CastlingMover implements Mover {

    MovementValidator isFirstMove = new FirstMoveValidator();
    @Override
    public MoveResponse move(Game game, Movement move) {
        Board currentBoard = game.getBoard();
        Piece currentPiece = currentBoard.getPieces().get(move.to());
        if(!isValidForCastling(move, currentPiece)){
            return new MoveResponse(game, null, GameState.KEEP_PLAYING);
        }
        if( move.directionX() == 1 ){
            return executeShortCastling(game, move);
        }
        return executeLongCastling(game,move);
    }

    private boolean isValidForCastling(Movement move, Piece currentPiece) {
        return currentPiece.pieceType() == PieceType.KING && move.colDifference() > 1 && move.rowDifference() == 0;
    }

    private MoveResponse executeShortCastling(Game game, Movement move){
        Coordinate rookCoordinate = move.to().add(new Coordinate(1,0));
        Coordinate rookDestiny = move.to().add(new Coordinate(-1,0));
        return executeCastling(game,rookCoordinate,rookDestiny);
    };

    private MoveResponse executeLongCastling(Game game, Movement move){
        Coordinate rookCoordinate = move.to().add(new Coordinate(-2,0));
        Coordinate rookDestiny = move.to().add(new Coordinate(+1,0));
        return executeCastling(game, rookCoordinate,rookDestiny);
    };

    private MoveResponse executeCastling(Game game, Coordinate rookCoordinate, Coordinate rookDestiny){
        Board currentBoard = game.getBoard();
        Piece rook = currentBoard.getPiece(rookCoordinate);
        if ( rook == null ) {
            return new MoveResponse(game, "No rook available for castling", GameState.INVALID_MOVE);
        }
        if (!isFirstMove.isValid(game.history(), new Movement(rookCoordinate, rookDestiny))){
            return new MoveResponse(game, "Rook has previously moved", GameState.INVALID_MOVE);
        }
        Map<Coordinate, Piece> newPieces = new HashMap<>(currentBoard.getPieces());
        newPieces.remove(rookCoordinate);
        newPieces.put(rookDestiny, rook);
        Board newBoard = new Board(currentBoard.getRowSize(), currentBoard.getColumnSize(), newPieces);
        return new MoveResponse(newGame(game,newBoard), null, GameState.KEEP_PLAYING);
    }

    private Game newGame (Game currentGame, Board newBoard){
        List<Board> newHistory = new ArrayList<>(currentGame.history());
        newHistory.remove(newHistory.size()-1);
        newHistory.add(newBoard);
        return new Game(newHistory, currentGame.mover(), currentGame.players(), currentGame.turnManager(), currentGame.winValidator());
    }
}
