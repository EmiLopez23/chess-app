package games.chess.mover;

import common.*;
import common.Game;
import common.enums.Color;
import common.enums.PieceType;
import games.chess.factory.PieceFactory;
import games.chess.validators.CheckValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mover implements SimpleMover {

    private final CheckValidator checkValidator = new CheckValidator();
    private final PieceFactory pieceFactory = new PieceFactory();
    @Override
    public MoveResponse move(Game game, Coordinate from, Coordinate to) {
        Board currentBoard = game.getBoard();
        Piece currentPiece = currentBoard.getBoard().get(from);
        if(currentPiece.getValidator().isValid(game.getBoards(), from, to)) {
            Map<Coordinate, Piece> newPieces = new HashMap<>(currentBoard.getBoard());
            tryPromotion(currentBoard,newPieces,currentPiece, to);
            newPieces.remove(from);
            Board newBoardObject = new Board(currentBoard.getRowSize(), currentBoard.getColumnSize(), newPieces);
            Game newGame = getNewGame(game, newBoardObject);
            if(!checkValidator.isValid(newGame.getBoards(), from, to)){
                return new MoveResponse(game, "Check");
            }
            return new MoveResponse(newGame, null);
        }
        return new MoveResponse(game, "Invalid movement");

    }

    private void tryPromotion(Board board, Map<Coordinate, Piece> pieces, Piece currentPiece, Coordinate to) {
        String pieceId = currentPiece.getId();
        Color color = currentPiece.getColor();
        PieceType pieceType = currentPiece.getPieceType();
        if( pieceType == PieceType.PAWN && (to.row() == board.getRowSize() || to.row() == 1)) {
            if (color == Color.WHITE) {
                pieces.put(to, pieceFactory.createWhiteQueen(pieceId));
            }
            else {
                pieces.put(to, pieceFactory.createBlackQueen(pieceId));
            }
            return;
        }
        pieces.put(to, currentPiece);
    }

    private Game getNewGame(Game game, Board newBoard){
        List<Board> newBoards = new ArrayList<>(game.getBoards());
        newBoards.add(newBoard);
        return new Game(newBoards, game.getMover(), game.getPlayers(), new TurnManager(game.getTurnManager().nextPlayer()), game.getWinValidator());
    }

}