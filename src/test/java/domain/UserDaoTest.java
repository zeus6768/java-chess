package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class UserDaoTest {

    private final UserDao userDao = new UserDao();

    @Test
    public void connection() throws SQLException {
        try (final var connection = userDao.getConnection()) {
            assertThat(connection).isNotNull();
        }
    }

    @Test
    void findAll() {
        final var users = userDao.findAll();
        assertThat(users).contains(
                new User("pobiconan", "pobi"),
                new User("sugarbrown", "brown")
        );
    }

    @Test
    void findById() throws SQLException {
        final var user = userDao.findById("pobiconan");
        assertThat(user).isEqualTo(new User("pobiconan", "pobi"));
    }

    @Test
    void save() throws SQLException {
        final var user = new User("godzeus", "zeus");
        userDao.save(user);
        final var actual = userDao.findById("godzeus");
        assertThat(actual).isEqualTo(user);
    }

    @Test
    void update() throws SQLException {
        final var user = new User("godzeus", "zeus");
        final var newUser = new User("zeusGod", "zeus2");
        userDao.update(user, newUser);
        final var actual = userDao.findById("zeusgod");
        assertThat(actual).isEqualTo(newUser);
    }

    @Test
    void delete() throws SQLException {
        final var user = new User("zeusgodzeus", "zeus");
        userDao.save(user);
        userDao.deleteById(user.userId());
        final var actual = userDao.findById(user.userId());
        assertThat(actual).isNull();
    }
}
