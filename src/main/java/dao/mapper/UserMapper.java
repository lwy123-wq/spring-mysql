package dao.mapper;

import entity.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {

    @Select(value = "SELECT id,name,age FROM user WHERE id=#{id}")
    User findByUserId(@Param("id") int id);

    @Select(value = "SELECT id,name,age FROM user WHERE name=#{name}")
    User findByName(@Param("name") String name);

    @Select(value = "SELECT id,name FROM user WHERE name=#{name} AND password=#{password}")
    User findByNameAndPassword(@Param(value = "name") String name, @Param(value = "password") String password);

    @Insert(value = "INSERT INTO user (name,age,password) VALUES (#{user.name},#{user.age},#{user.password})")
    int insertUser(@Param(value = "user") User user);

    @Update(value = "UPDATE user SET name=#{user.name} WHERE id=#{user.id}")
    int updateUser(@Param(value = "user") User user);
}
