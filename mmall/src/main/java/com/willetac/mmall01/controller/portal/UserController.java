package com.willetac.mmall01.controller.portal;

import com.willetac.mmall01.common.Const;
import com.willetac.mmall01.common.Result;
import com.willetac.mmall01.pojo.User;
import com.willetac.mmall01.service.IUserService;
import com.willetac.mmall01.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iUserService;
    @PostMapping("/login")
    public Result<User> login(String username, String password, HttpSession session){
        Result<User> result= iUserService.login(username,password);
        if(result.isSuccess()){
            session.setAttribute(Const.USER_SESSION,result.getData());
        }
        return result;
    }
    @GetMapping("/logout")
    public Result logout(HttpSession httpSession){
        httpSession.removeAttribute(Const.USER_SESSION);
        return Result.success("已退出登录");
    }

    @PostMapping("/regist")
    public Result<User> regist(User user){
        return iUserService.regist(user);
    }

    @GetMapping("/registCheck")
    public Result<String> registCheck(String item,String type){
        return iUserService.registCheck(item,type);
    }

    @GetMapping("/getUserInfo")
    public Result<User> getUserInfo(HttpSession httpSession){
        User currentUser = (User)httpSession.getAttribute(Const.USER_SESSION);
        return Result.<User>success(currentUser);
    }
}
