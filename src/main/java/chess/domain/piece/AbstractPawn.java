package chess.domain.piece;

import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;

public abstract class AbstractPawn extends UnslidingPiece {

    protected AbstractPawn(final Color color, final Position position) {
        super(color, position);
    }
}
