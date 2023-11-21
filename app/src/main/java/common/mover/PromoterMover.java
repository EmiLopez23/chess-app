package common.mover;

import common.*;
import common.enums.Color;
import common.enums.GameState;
import common.enums.PieceType;
import common.factory.PieceFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PromoterMover implements Mover {

    private final PieceFactory pieceFactory;

    public PromoterMover(PieceFactory pieceFactory) {
        this.pieceFactory = pieceFactory;
    }

    @Override
    public MoveResponse move(Game game, Movement move) {
        Board currentBoard = game.getBoard();
        Map<Coordinate, Piece> newPieces = new HashMap<>(currentBoard.getPieces());
        Piece pieceToPromote = currentBoard.getPiece(move.to());
        if ( pieceToPromote.pieceType() != PieceType.PAWN || move.to().row() < currentBoard.getRowSize() && move.to().row() > 1) {
            return new MoveResponse(game, null, GameState.KEEP_PLAYING);
        }
        newPieces.remove(move.to());
        newPieces.put(move.to(), pieceFactory.createPiece(pieceToPromote.id(), pieceToPromote.color(), PieceType.QUEEN));

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
