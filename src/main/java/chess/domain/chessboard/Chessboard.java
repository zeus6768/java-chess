package chess.domain.chessboard;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import chess.domain.chessboard.attribute.File;
import chess.domain.chessboard.attribute.Rank;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Position;
import chess.domain.piece.attribute.Positions;

public class Chessboard {

    private final Map<Position, Piece> chessboard;

    protected Chessboard(final Map<Position, Piece> chessboard) {
        this.chessboard = chessboard;
    }

    public static boolean isInBoard(final int column, final int row) {
        return File.isInRange(column) && Rank.isInRange(row);
    }

    public Piece move(final Position source, final Position target) {
        validateSource(source);
        Piece sourcePiece = chessboard.get(source);
        Piece targetPiece = sourcePiece.move(this, target);
        remove(source);
        put(target, targetPiece);
        return targetPiece;
    }

    private void validateSource(final Position position) {
        if (isEmpty(position)) {
            throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다: %s".formatted(position));
        }
    }

    public boolean isEmpty(final Position position) {
        return !isPresent(position);
    }

    public boolean isPresent(final Position position) {
        return chessboard.containsKey(position);
    }

    public Piece get(final Position position) {
        validateSource(position);
        return chessboard.get(position);
    }

    public List<Piece> getAllFrom(final File file) {
        return Positions.from(file)
                .stream()
                .filter(this::isPresent)
                .map(chessboard::get)
                .toList();
    }

    private void remove(final Position position) {
        chessboard.remove(position);
    }

    private void put(final Position position, final Piece piece) {
        chessboard.put(position, piece);
    }

    public String toFen() {
        // Todo: FEN 변환 구현
        return "";
    }

    public Map<Position, Piece> toMap() {
        return Collections.unmodifiableMap(chessboard);
    }
}
