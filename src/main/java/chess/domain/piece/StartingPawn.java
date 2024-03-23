package chess.domain.piece;

import static chess.domain.piece.attribute.Color.BLACK;
import static chess.domain.piece.attribute.Color.WHITE;

import java.util.Set;

import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;
import chess.domain.piece.attribute.Positions;

public class StartingPawn extends AbstractPawn {

    private static final Set<Position> WHITE_INITIAL_POSITIONS = Positions.of(
            "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2");
    private static final Set<Position> BLACK_INITIAL_POSITIONS = Positions.of(
            "a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7");

    public StartingPawn(final Color color, final Position position) {
        super(color, position);
    }

    public static Set<StartingPawn> ofInitialPositions(final Color color) {
        if (color.isBlack()) {
            return initialPiecesOf(BLACK_INITIAL_POSITIONS, BLACK, StartingPawn::new);
        }
        return initialPiecesOf(WHITE_INITIAL_POSITIONS, WHITE, StartingPawn::new);
    }

    @Override
    public Set<Position> move(final Position source, final Position target) {
        return null;
    }
}
