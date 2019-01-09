package com.bjpowernode.springboot.Controller;

import com.bjpowernode.springboot.model.User;
import com.bjpowernode.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName:TestController
 * Package:com.bjpowernode.springboot.Controller
 * Description:
 *
 * @Date:2018/10/8 20:22
 * @Author:hiwangxiaodong@hotmail.com
 */
@Controller
public class TestController {
    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public @ResponseBody Object add(){
        User user = new User();
        user.setName("lisi");
        user.setAge(20);
        userService.addUser(user);
        return "success";
    }
    @RequestMapping("/select")
    public @ResponseBody Object select(){
        List<User> userList = userService.findUserList();
        return userList;
    }

    @RequestMapping("/transaction")
    public @ResponseBody String transaction(){
        User user = new User();
        user.setName("zhangsan");
        user.setAge(20);
        userService.addTransaction(user);
        return "success";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Object delete(@PathVariable("id") Long id){
        userService.removeById(id);
        return "success";
    }
    //RESTful风格
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public @ResponseBody Object selectOne(@PathVariable("id") Long id){
        User user = userService.findById(id);
        return user;
    }
}
