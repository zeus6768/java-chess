package domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public final class UserDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public List<User> findAll() {
        try (final var connection = getConnection()) {
            assert connection != null;
            final var statement = connection.prepareStatement("SELECT * FROM user");
            final var resultSet = statement.executeQuery();
            final var users = new ArrayList<User>();
            while (resultSet.next()) {
                var userId = resultSet.getString("user_id");
                var name = resultSet.getString("name");
                users.add(new User(userId, name));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findById(final String userId) throws SQLException {
        try (final var connection = getConnection()) {
            assert connection != null;
            final var statement = connection.prepareStatement("SELECT * FROM user WHERE user_id = ?");
            statement.setString(1, userId);
            final var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getString("user_Id"),
                        resultSet.getString("name")
                );
            }
        }
        return null;
    }

    public void save(final User user) throws SQLException {
        try (final var connection = getConnection()) {
            assert connection != null;
            final var statement = connection.prepareStatement("INSERT INTO user VALUES (?, ?)");
            statement.setString(1, user.userId());
            statement.setString(2, user.name());
            statement.execute();
        }
    }

    public void update(final User user, final User newUser) throws SQLException {
        try (final var connection = getConnection()) {
            assert connection != null;
            final var statement = connection.prepareStatement("UPDATE user SET user_id=?, name=? WHERE user_id=?");
            statement.setString(1, newUser.userId());
            statement.setString(2, newUser.name());
            statement.setString(3, user.userId());
            statement.execute();
        }
    }

    public void deleteById(final String userId) throws SQLException {
        try (final var connection = getConnection()) {
            assert connection != null;
            final var statement = connection.prepareStatement("DELETE FROM user WHERE user_id=?");
            statement.setString(1, userId);
            statement.execute();
        }
    }
}

