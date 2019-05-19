package com.willetac.mmall01.service.impl;

import com.willetac.mmall01.common.Const;
import com.willetac.mmall01.common.ResponseCode;
import com.willetac.mmall01.common.Result;
import com.willetac.mmall01.dao.UserMapper;
import com.willetac.mmall01.pojo.User;
import com.willetac.mmall01.service.IUserService;
import com.willetac.mmall01.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iUserService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Result<User> login(String username, String password) {
        int resultCount = userMapper.checkUsername(username);
        if(resultCount == 0 ){
            return Result.<User>error("用户不存在");
        }

        String md5_password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.login(username,md5_password);
        if(user == null){
            return Result.<User>error("用户名或密码不正确");
        }

        return Result.<User>build(ResponseCode.SUCCESS.getCode(),user);
    }

    @Override
    public Result<User> regist(User user) {
        int userCount = userMapper.checkUsername(user.getUsername());
        if(userCount > 0 ){
            return Result.<User>error("用户名已存在");
        }
        int emailCount = userMapper.checkEmail(user.getEmail());
        if(emailCount > 0 ){
            return Result.<User>error("邮箱已存在");
        }
        user.setRole(Const.USER_ROLE);

        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));

        userMapper.insert(user);

        return Result.<User>success(user);
    }

    @Override
    public Result<String> registCheck(String item,String type) {
        if("email".equals(type)){
            int emailCount = userMapper.checkEmail(item);
            if(emailCount > 0 ){
                return Result.<String>error("邮箱已存在");
            }
        }else if("username".equals(type)){
            int emailCount = userMapper.checkUsername(item);
            if(emailCount > 0 ){
                return Result.<String>error("用户名已存在");
            }
        }else{
            return Result.<String>error("参数有误");
        }

        return Result.<String>success("");
    }
    @Override
    public Result<String> checkAdmin(User user) {
        if(user !=null && user.getRole().equals(Const.ADMIN_ROLE)){
            return Result.<String>success("");
        }

        return Result.<String>error("");
    }

}
