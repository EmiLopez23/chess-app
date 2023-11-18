package games.chess.mover;

import common.*;
import common.enums.Color;
import common.enums.GameState;
import common.enums.PieceType;
import common.mover.Mover;
import games.chess.factory.PieceFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessPromoterMover implements Mover {

    private final PieceFactory pieceFactory;

    public ChessPromoterMover() {
        this.pieceFactory = new PieceFactory();
    }
    @Override
    public MoveResponse move(Game game, Movement move) {
        Board currentBoard = game.getBoard();
        Map<Coordinate, Piece> newPieces = new HashMap<>(currentBoard.getPieces());
        Piece pieceToPromote = currentBoard.getPiece(move.to());
        if ( pieceToPromote.getPieceType() != PieceType.PAWN || move.to().row() < currentBoard.getRowSize() && move.to().row() > 1) {
            return new MoveResponse(game, null, GameState.KEEP_PLAYING);
        }
        newPieces.remove(move.to());
        if ( pieceToPromote.getColor() == Color.WHITE ){
            newPieces.put(move.to(), pieceFactory.createWhiteQueen(pieceToPromote.getId()));
        }
        else {
            newPieces.put(move.to(), pieceFactory.createBlackQueen(pieceToPromote.getId()));
        }

        return new MoveResponse(createNewGame(game, newPieces), null, GameState.KEEP_PLAYING);
    }

    public Game createNewGame(Game currentGame, Map<Coordinate, Piece> newPieces){
        Board currentBoard = currentGame.getBoard();
        List<Board> newHistory = new ArrayList<>(currentGame.history());
        Board newBoard = new Board(currentBoard.getRowSize(), currentBoard.getColumnSize(), newPieces);
        newHistory.remove(newHistory.size()-1);
        newHistory.add(newBoard);
        return new Game(newHistory, currentGame.mover(), currentGame.players(), currentGame.turnManager(), currentGame.winValidator());
    }
}
