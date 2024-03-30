package chess;

import static chess.exception.ExceptionHandler.retry;

import chess.domain.chessboard.Chessboard;
import chess.domain.chessboard.ChessboardFactory;
import chess.domain.score.Scoreboard;
import chess.view.InputView;
import chess.view.ResultView;
import chess.view.command.Command;
import chess.view.command.MoveCommand;
import chess.view.dto.ChessResultDto;
import chess.view.dto.ChessboardDto;

public class ChessGame {

    private final InputView inputView;
    private final ResultView resultView;

    public ChessGame(final InputView inputView, final ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        resultView.printGameStartMessage();
        Command command = inputView.askStartOrEnd();
        if (command.isStart()) {
            play();
        }
        resultView.printGameEnd();
    }

    private void play() {
        Chessboard chessboard = ChessboardFactory.create();
        resultView.printBoard(new ChessboardDto(chessboard));
        retry(() -> playByCommand(chessboard, inputView.askMoveOrEnd()));
        printResultIfCheckmate(chessboard);
    }

    private void playByCommand(final Chessboard chessboard, final Command command) {
        if (!command.isMove()) {
            return;
        }
        MoveCommand moveCommand = command.toMoveCommand();
        chessboard.move(moveCommand.getSource(), moveCommand.getTarget());
        if (chessboard.isCheckmate()) {
            return;
        }
        resultView.printBoard(new ChessboardDto(chessboard));
        playByCommand(chessboard, inputView.askMoveOrEnd());
    }

    private void printResultIfCheckmate(final Chessboard chessboard) {
        if (!chessboard.isCheckmate()) {
            return;
        }
        resultView.printCheckmate();
        Command command = inputView.askStatusOrEnd();
        if (command.isStatus()) {
            resultView.printResult(new ChessResultDto(new Scoreboard(chessboard), chessboard.winner()));
        }
    }
}
