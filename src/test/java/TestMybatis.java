import config.DataConfig;
import dao.mapper.UserMapper;
import entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataConfig.class, config.info.JdbcInfo.class})
public class TestMybatis {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSelect() {
        User u = userMapper.findByUserId(1);
//        Assert.assertEquals(true,u != null);
        if (u != null) {
            Assert.assertEquals(1, u.getId());
        }
        User u2 = userMapper.findByName("test2");
        if (u2 != null) {
            Assert.assertEquals("test2", u2.getUserName());
        } else {
            Assert.fail("u2 is null");
        }

        User u3 = userMapper.findByNameAndPassword("test2", DigestUtils.md5DigestAsHex("123456".getBytes()));
        if (u3 != null) {
            Assert.assertEquals(6, u3.getId());
        } else {
            Assert.fail("u3 is null");
        }
    }

    @Test
    public void testInsert() {
        User u = new User();
        u.setUserName("test3");
        u.setAge(100);
        u.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        userMapper.insertUser(u);
        User result = userMapper.findByName("test3");
        if (result != null) {
            Assert.assertEquals("test3", result.getUserName());
        } else {
            Assert.fail();
        }
    }

    @Test
    public void testUpdate() {
        User u = new User();
        u.setId(6);
        u.setUserName("test2");
        userMapper.updateUser(u);
        User result = userMapper.findByUserId(u.getId());
        if (result != null) {
            Assert.assertEquals("test2", result.getUserName());
        } else {
            Assert.fail();
        }
    }
}
