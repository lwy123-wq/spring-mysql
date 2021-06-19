package controller;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import service.UserServiceImpl;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public String login(String username,String password){
        User user=userService.findUserByNameAndPassword(username,DigestUtils.md5DigestAsHex(password.getBytes()));
        if(user==null||user.getUserName()==null){
            return "用户不存在或用户名、密码错误";
        }
        return "hello"+user.getUserName();
    }
    @RequestMapping(value = "find_name",method =RequestMethod.POST)
    @ResponseBody
    public String registry(@RequestParam String username){
        User userByName=userService.findUserByName(username);
        if(userByName!=null){
            return userByName.getUserName();
        }
        return "未找到该用户";
    }
    @RequestMapping(value = "find_id",method =RequestMethod.POST)
    @ResponseBody
    public String findById(@RequestParam  int id){
        User userById=userService.findById(id);
        if(userById!=null){
            return userById.getUserName();
        }
        return "未找到该用户";
    }
    @RequestMapping(value = "update_name",method =RequestMethod.POST)
    @ResponseBody
    public String updateName(@RequestParam User user){
        int count=userService.updateUser(user);
        if(count!=0){
            return "更新成功";
        }
        return "未找到该用户";
    }
}
