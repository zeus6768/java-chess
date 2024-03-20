package chess.domain.board;

import static java.util.Map.entry;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.attribute.Color;
import chess.domain.attribute.File;
import chess.domain.attribute.Position;
import chess.domain.attribute.Rank;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.PieceType;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.StartingPawn;

class BoardTest {


    @DisplayName("체스판을 생성한다.")
    @Test
    void create() {
        Board board = Board.create();
        assertThat(board.getBoard())
                .contains(
                        entry(Position.of(File.E, Rank.ONE), new King(Color.WHITE, PieceType.KING)),
                        entry(Position.of(File.E, Rank.EIGHT), new King(Color.BLACK, PieceType.KING)),
                        entry(Position.of(File.D, Rank.ONE), new Queen(Color.WHITE, PieceType.QUEEN)),
                        entry(Position.of(File.D, Rank.EIGHT), new Queen(Color.BLACK, PieceType.QUEEN)),
                        entry(Position.of(File.C, Rank.ONE), new Bishop(Color.WHITE, PieceType.BISHOP)),
                        entry(Position.of(File.F, Rank.ONE), new Bishop(Color.WHITE, PieceType.BISHOP)),
                        entry(Position.of(File.C, Rank.EIGHT), new Bishop(Color.BLACK, PieceType.BISHOP)),
                        entry(Position.of(File.F, Rank.EIGHT), new Bishop(Color.BLACK, PieceType.BISHOP)),
                        entry(Position.of(File.B, Rank.ONE), new Knight(Color.WHITE, PieceType.KNIGHT)),
                        entry(Position.of(File.G, Rank.ONE), new Knight(Color.WHITE, PieceType.KNIGHT)),
                        entry(Position.of(File.B, Rank.EIGHT), new Knight(Color.BLACK, PieceType.KNIGHT)),
                        entry(Position.of(File.G, Rank.EIGHT), new Knight(Color.BLACK, PieceType.KNIGHT)),
                        entry(Position.of(File.A, Rank.ONE), new Rook(Color.WHITE, PieceType.ROOK)),
                        entry(Position.of(File.H, Rank.ONE), new Rook(Color.WHITE, PieceType.ROOK)),
                        entry(Position.of(File.A, Rank.EIGHT), new Rook(Color.BLACK, PieceType.ROOK)),
                        entry(Position.of(File.H, Rank.EIGHT), new Rook(Color.BLACK, PieceType.ROOK)),
                        entry(Position.of(File.A, Rank.TWO), new StartingPawn(Color.WHITE, PieceType.PAWN)),
                        entry(Position.of(File.B, Rank.TWO), new StartingPawn(Color.WHITE, PieceType.PAWN)),
                        entry(Position.of(File.C, Rank.TWO), new StartingPawn(Color.WHITE, PieceType.PAWN)),
                        entry(Position.of(File.D, Rank.TWO), new StartingPawn(Color.WHITE, PieceType.PAWN)),
                        entry(Position.of(File.E, Rank.TWO), new StartingPawn(Color.WHITE, PieceType.PAWN)),
                        entry(Position.of(File.F, Rank.TWO), new StartingPawn(Color.WHITE, PieceType.PAWN)),
                        entry(Position.of(File.G, Rank.TWO), new StartingPawn(Color.WHITE, PieceType.PAWN)),
                        entry(Position.of(File.H, Rank.TWO), new StartingPawn(Color.WHITE, PieceType.PAWN)),
                        entry(Position.of(File.A, Rank.SEVEN), new StartingPawn(Color.BLACK, PieceType.PAWN)),
                        entry(Position.of(File.B, Rank.SEVEN), new StartingPawn(Color.BLACK, PieceType.PAWN)),
                        entry(Position.of(File.C, Rank.SEVEN), new StartingPawn(Color.BLACK, PieceType.PAWN)),
                        entry(Position.of(File.D, Rank.SEVEN), new StartingPawn(Color.BLACK, PieceType.PAWN)),
                        entry(Position.of(File.E, Rank.SEVEN), new StartingPawn(Color.BLACK, PieceType.PAWN)),
                        entry(Position.of(File.F, Rank.SEVEN), new StartingPawn(Color.BLACK, PieceType.PAWN)),
                        entry(Position.of(File.G, Rank.SEVEN), new StartingPawn(Color.BLACK, PieceType.PAWN)),
                        entry(Position.of(File.H, Rank.SEVEN), new StartingPawn(Color.BLACK, PieceType.PAWN))
                );
    }
}