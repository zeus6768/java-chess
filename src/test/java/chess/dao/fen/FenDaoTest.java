package chess.dao.fen;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import chess.fen.Fen;
import chess.fen.dao.FenDao;

class FenDaoTest {

    private final FenDao fenDao = new FenDao();

    @AfterEach
    void clean() {
        fenDao.delete();
    }

    @Test
    void getConnection() throws SQLException {
        try (final var connection = fenDao.getConnection()) {
            assertThat(connection).isNotNull();
        }
    }

    @Test
    void find() {
        fenDao.save(new Fen("4k2r/6r1/8/8/8/8/3R4/R3K3"));
        Fen fen = fenDao.find();
        assertThat(fen.value()).isEqualTo("4k2r/6r1/8/8/8/8/3R4/R3K3");
    }

    @Test
    void save() {
        Fen fen = new Fen("8/8/8/4p1K1/2k1P3/8/8/8");
        fenDao.save(fen);
        Fen expected = fenDao.find();
        assertThat(fen).isEqualTo(expected);
    }

    @Test
    void update() {
        fenDao.save(new Fen("8/8/8/4p1K1/2k1P3/8/8/8"));
        Fen fen = new Fen("4k2r/6r1/8/8/8/8/3R4/R3K3");
        fenDao.update(fen);
        Fen expected = fenDao.find();
        assertThat(fen).isEqualTo(expected);
    }

    @Test
    void delete() {
        fenDao.delete();
        assertThat(fenDao.find()).isNull();
    }

    @Test
    void exists() {
        assertThat(fenDao.exists()).isFalse();
        fenDao.save(new Fen("8/8/8/4p1K1/2k1P3/8/8/8"));
        assertThat(fenDao.exists()).isTrue();
    }
}
