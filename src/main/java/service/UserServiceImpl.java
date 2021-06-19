package service;

import dao.UserDao;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.util.DigestUtils;

public class UserServiceImpl {
    @Autowired
    private UserDao userDao;


    public User findUserByName(String username) {
        return userDao.findByName(username);
    }

    public User findUserByNameAndPassword(String username, String password) {
        return userDao.findByNameAndPassword(username, password);
    }

    public User findById(int id){
        return userDao.findByUserId(id);
    }

    public int updateUser(User user){
        return userDao.updateUser(user);
    }
    public int insertUser(User user){
        return userDao.insertUser(user);
    }
    public boolean register(User user) {
        //用户重名校验
        User userByName = findUserByName(user.getUserName());
        if (userByName != null && userByName.getUserName() != null && userByName.getUserName().equals(user.getUserName())) {
            return true;
        }
        //对用户密码进行MD5,目的是，数据库中的敏感数据，不要存储明文。
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        return userDao.insertUser(user) != 0;
    }

}
