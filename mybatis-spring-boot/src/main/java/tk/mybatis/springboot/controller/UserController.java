package tk.mybatis.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.simple.model.SysUser;
import tk.mybatis.springboot.service.UserService;

import java.util.List;

/**
 * @author mwstart
 * @create 2023-02-19-18:26
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("user/{id}")
    SysUser user(@PathVariable("id") Long id){
        return userService.findById(id);
    }

    @RequestMapping("users")
    List<SysUser> users(){
        return userService.findAll();
    }


}
