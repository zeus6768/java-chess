package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Position;

public abstract class AbstractPawn extends UnslidingPiece {
    protected AbstractPawn(final Color color, final PieceType pieceType) {
        super(color, pieceType);
    }
}
