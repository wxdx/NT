package com.bjpowernode.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjpowernode.springboot.model.User;
import com.bjpowernode.springboot.service.UserService;
import com.bjpowernode.springboot.model.vo.Pagination;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * ClassName:UserController
 * Package:com.bjpowernode.springboot.controller
 * Description:
 *
 * @Date:2018/10/11 16:09
 * @Author:hiwangxiaodong@hotmail.com
 */
@Controller
public class UserController {
    @Reference
    private UserService userService;

    @GetMapping("/")
    public String index(@RequestParam(value = "p", required = false) Integer p, Model model){

        if (p == null){
            p = 1;
        }
        Integer pageSize = 5;
        model.addAttribute("p", p);
        p = (p - 1) * pageSize;
        Pagination<User> pagination = userService.findAllByPage(p, pageSize);

        Long totalPage = pagination.getTotalRows() / pageSize;
        Long mod = pagination.getTotalRows() % pageSize;
        if (mod > 0){
            totalPage += 1;
        }

        model.addAttribute("totalRows", pagination.getTotalRows());
        model.addAttribute("userList", pagination.getDataList());
        model.addAttribute("totalPage", totalPage);


        return "index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        User user = userService.findOne(id);
        model.addAttribute("user", user);
        return "user";
    }

    @PutMapping("/update")
    public String update(User user){
        userService.modify(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        userService.removeById(id);
        return "redirect:/";
    }
}
