package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.File;
import chess.domain.attribute.Position;
import chess.domain.attribute.Rank;

public class StartingPawn extends AbstractPawn {
    public StartingPawn(final Color color, final PieceType pieceType) {
        super(color, pieceType);
    }
}
