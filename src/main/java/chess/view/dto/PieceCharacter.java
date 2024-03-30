package chess.view.dto;

import java.util.Arrays;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.attribute.Color;

public enum PieceCharacter {

    KING(King.class, "k", "K"),
    QUEEN(Queen.class, "q", "Q"),
    BISHOP(Bishop.class, "b", "B"),
    KNIGHT(Knight.class, "n", "N"),
    ROOK(Rook.class, "r", "R"),
    PAWN(Pawn.class, "p", "P");

    private final Class<? extends Piece> pieceClass;
    private final String white;
    private final String black;

    <P extends Piece> PieceCharacter(final Class<P> pieceClass, final String white, final String black) {
        this.pieceClass = pieceClass;
        this.white = white;
        this.black = black;
    }

    public static String of(final Piece piece) {
        return Arrays.stream(values())
                .filter(pieceType -> pieceType.isAssignableFrom(piece.getClass()))
                .map(character -> character.valueBy(piece.color()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private <P extends Piece> boolean isAssignableFrom(final Class<P> other) {
        return pieceClass.isAssignableFrom(other);
    }

    private String valueBy(final Color color) {
        if (color.isBlack()) {
            return black;
        }
        return white;
    }
}
