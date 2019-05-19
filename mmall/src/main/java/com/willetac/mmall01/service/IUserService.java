package com.willetac.mmall01.service;

import com.willetac.mmall01.common.Result;
import com.willetac.mmall01.pojo.User;

public interface IUserService {
    Result<User> login(String username,String password);

    Result<User> regist(User user);

    Result<String> registCheck(String item, String type);

    Result<String> checkAdmin(User user);
}
