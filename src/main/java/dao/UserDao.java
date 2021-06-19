package dao;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findByUserId(int id){
        final User user = new User();
        String sql = "SELECT id,userName,age FROM user WHERE id=?";
        jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setId(resultSet.getInt(1));
                user.setUserName(resultSet.getString(2));
                user.setAge(resultSet.getInt(3));
            }
        });
        return user;
    }

    public int updateUser(User user){
        String sql="update user set name=? where id=?";
        return jdbcTemplate.update(sql,user.getAge(),user.getId());
    }

    public int insertUser(User user) {
        String sql = "INSERT INTO user (id,userName,age) VALUES (?,?,?)";
        return jdbcTemplate.update(sql, user.getUserName(), user.getAge(), user.getId());
    }

    public User findByNameAndPassword(String name, String password) {

        final User user = new User();
        String sql = "SELECT id,name FROM user WHERE name=? AND password=?";
        jdbcTemplate.query(sql, new Object[]{name, password}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setId(resultSet.getInt(1));
                user.setUserName(resultSet.getString(2));
            }
        });
        return user;
    }

    public User findByName(String name) {


        final User user = new User();
        String sql = "SELECT id,name FROM user WHERE name=?";
        jdbcTemplate.query(sql, new Object[]{name}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setId(resultSet.getInt(1));
                user.setUserName(resultSet.getString(2));
            }
        });
        return user;
    }
}
