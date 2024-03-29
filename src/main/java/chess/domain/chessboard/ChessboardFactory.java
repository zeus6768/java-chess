package chess.domain.chessboard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.StartingPawn;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;

public class ChessboardFactory {

    private ChessboardFactory() {
    }

    public static Chessboard empty() {
        return new Chessboard(new HashMap<>());
    }

    public static Chessboard create() {
        Map<Position, Piece> chessboard = new HashMap<>();
        for (final Piece piece : initialPieces()) {
            Position position = piece.position();
            chessboard.put(position, piece);
        }
        return new Chessboard(chessboard);
    }

    private static Set<Piece> initialPieces() {
        Set<Piece> pieces = new HashSet<>();
        for (Color color : Color.values()) {
            pieces.add(King.ofInitialPosition(color));
            pieces.add(Queen.ofInitialPosition(color));
            pieces.addAll(Bishop.ofInitialPositions(color));
            pieces.addAll(Knight.ofInitialPositions(color));
            pieces.addAll(Rook.ofInitialPositions(color));
            pieces.addAll(StartingPawn.ofInitialPositions(color));
        }
        return pieces;
    }
}
