package chess;

import static chess.exception.ExceptionHandler.retry;

import chess.domain.chessboard.Chessboard;
import chess.domain.chessboard.ChessboardFactory;
import chess.domain.score.Scoreboard;
import chess.fen.Fen;
import chess.fen.dao.FenDao;
import chess.view.InputView;
import chess.view.ResultView;
import chess.view.command.Command;
import chess.view.command.MoveCommand;
import chess.view.dto.ChessResultDto;
import chess.view.dto.ChessboardDto;

public class ChessGame {

    // TODO: DAO 로직 분리

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
        Chessboard chessboard = initializeChessboard();
        resultView.printBoard(new ChessboardDto(chessboard));
        retry(() -> playByCommand(chessboard, inputView.askMoveOrEnd()));
        printResultIfCheckmate(chessboard);
    }

    private Chessboard initializeChessboard() {
        // TODO: 들여쓰기 1단계, 10줄 이하
        FenDao fenDao = new FenDao();
        if (fenDao.exists()) {
            Command command = inputView.askLoadOrStart();
            if (command.isLoad()) {
                Fen fen = fenDao.find("1");
                return ChessboardFactory.from(fen);
            }
            Chessboard chessboard = ChessboardFactory.create();
            fenDao.update(Fen.from(chessboard));
            return chessboard;

        }
        Chessboard chessboard = ChessboardFactory.create();
        fenDao.save(Fen.from(chessboard));
        return chessboard;
    }

    private void playByCommand(final Chessboard chessboard, final Command command) {
        // TODO: 10줄 이하
        if (!command.isMove()) {
            return;
        }
        MoveCommand moveCommand = command.toMoveCommand();
        chessboard.move(moveCommand.getSource(), moveCommand.getTarget());
        FenDao fenDao = new FenDao();
        fenDao.update(Fen.from(chessboard));
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
        FenDao fenDao = new FenDao();
        fenDao.delete();
        resultView.printCheckmate();
        Command command = inputView.askStatusOrEnd();
        if (command.isStatus()) {
            resultView.printResult(new ChessResultDto(new Scoreboard(chessboard), chessboard.winner()));
        }
    }
}
