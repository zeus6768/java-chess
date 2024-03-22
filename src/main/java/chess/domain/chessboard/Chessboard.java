package chess.domain.chessboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import chess.domain.attribute.Color;
import chess.domain.attribute.File;
import chess.domain.attribute.Position;
import chess.domain.attribute.Rank;
import chess.domain.chessboard.attribute.square.Square;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.StartingPawn;

public class Chessboard {

    private final List<List<Square>> chessboard;

    private Chessboard(final List<List<Square>> chessboard) {
        this.chessboard = chessboard;
    }

    public static Chessboard create() {
        List<List<Square>> chessboard = emptyChessboard();
        putPieces(chessboard);
        return new Chessboard(chessboard);
    }

    private static List<List<Square>> emptyChessboard() {
        List<List<Square>> chessboard = new ArrayList<>();
        for (final Rank ignored : Rank.values()) {
            initialize(chessboard);
        }
        return chessboard;
    }

    private static void initialize(final List<List<Square>> chessboard) {
        List<Square> squares = new ArrayList<>();
        for (final File ignored : File.values()) {
            squares.add(Square.empty());
        }
        chessboard.add(squares);
    }

    private static void putPieces(final List<List<Square>> chessboard) {
        for (final Piece piece : initialPieces()) {
            Position position = piece.position();
            Rank rank = position.rank();
            File file = position.file();
            List<Square> squares = chessboard.get(rank.toRow());
            squares.set(file.toColumn(), Square.from(piece));
        }
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

    public Optional<Piece> pieceIn(final Position position) {
        return pieceIn(position.rank(), position.file());
    }

    public Optional<Piece> pieceIn(final Rank rank, final File file) {
        List<Square> row = chessboard.get(rank.toRow());
        Square square = row.get(file.toColumn());
        return square.piece();
    }

    public List<List<Square>> getChessboard() {
        return List.copyOf(chessboard.stream()
                .map(List::copyOf)
                .toList());
    }
}
