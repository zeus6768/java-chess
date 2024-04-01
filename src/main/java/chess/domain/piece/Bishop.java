package chess.domain.piece;

import java.util.Set;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import chess.domain.chessboard.attribute.Direction;

public class Bishop extends SlidingPiece {

    public Bishop(final Color color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public Set<Square> movableSquaresFrom(final Square source) {
        return movableSquaresOf(Direction.ofBishop(), source);
    }
}
