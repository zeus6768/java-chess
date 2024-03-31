package chess.fen;

import static chess.fen.PieceFenMapper.fenFrom;
import static chess.fen.PieceFenMapper.pieceOf;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import chess.domain.chessboard.Chessboard;
import chess.domain.chessboard.ChessboardFactory;
import chess.domain.chessboard.attribute.Rank;
import chess.domain.piece.Piece;

public class Fen {

    private static final String PATTERN = "^(?:[rnbqkpRNBQKP1-8]{1,8}/){7}[rnbqkpRNBQKP1-8]{1,8}$";

    private final String fen;

    public Fen(final String fen) {
        if (!fen.matches(PATTERN)) {
            throw new IllegalArgumentException("FEN 형식에 맞지 않습니다: %s".formatted(fen));
        }
        this.fen = fen;
    }

    public static Fen from(final Chessboard chessboard) {
        StringBuilder fen = new StringBuilder();
        for (final Rank rank : Rank.values()) {
            List<Piece> pieces = chessboard.getAllFrom(rank);
            appendFens(pieces, fen);
            fen.append('/');
        }
        return new Fen(fen.toString());
    }

    private static void appendFens(final List<Piece> pieces, final StringBuilder fen) {
        for (final Piece piece : pieces) {
            fen.append(fenFrom(piece));
        }
    }

    public Chessboard toChessboard() {
        Set<Piece> pieces = new HashSet<>();
        String[] piecesByFen = fen.split("/");
        for (int row = 0; row < piecesByFen.length; row++) {
            pieces.addAll(toPieces(piecesByFen[row], row));
        }
        return ChessboardFactory.from(pieces);
    }

    private Set<Piece> toPieces(final String rowByFen, final int row) {
        Set<Piece> pieces = new HashSet<>();
        int column = 0;
        for (final char squareByFen : rowByFen.toCharArray()) {
            pieceOf(squareByFen, row, column).ifPresent(pieces::add);
            column += getIfDigit(squareByFen);
        }
        return pieces;
    }

    private int getIfDigit(final char fen) {
        if (Character.isDigit(fen)) {
            return Character.getNumericValue(fen);
        }
        return 1;
    }

    public String value() {
        return fen;
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof Fen other
                && fen.equals(other.fen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fen);
    }
}
