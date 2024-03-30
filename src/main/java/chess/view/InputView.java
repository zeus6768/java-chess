package chess.view;

import static chess.exception.ExceptionHandler.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import chess.view.command.Command;

public class InputView {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public Command askStartOrEnd() {
        return retry(() -> requireStartOrEnd(new Command(input())));
    }

    private Command requireStartOrEnd(final Command command) {
        if (!(command.isStart() || command.isEnd())) {
            throw new IllegalArgumentException("start 또는 end 명령어를 입력해주세요.");
        }
        return command;
    }

    public Command askMoveOrEnd() {
        return retry(() -> requireMoveOrEnd(new Command(input())));
    }

    public Command requireMoveOrEnd(final Command command) {
        if (!(command.isMove() || command.isEnd())) {
            throw new IllegalArgumentException("move 또는 end 명령어를 입력해주세요.");
        }
        return command;
    }

    public Command askStatusOrEnd() {
        return retry(() -> requireStatusOrEnd(new Command(input())));
    }

    private Command requireStatusOrEnd(final Command command) {
        if (!(command.isStatus() || command.isEnd())) {
            throw new IllegalArgumentException("status 또는 end 명령어를 입력해주세요.");
        }
        return command;
    }

    private String input() {
        try {
            return READER.readLine();
        } catch (IOException ignored) {
        }
        return "";
    }
}
