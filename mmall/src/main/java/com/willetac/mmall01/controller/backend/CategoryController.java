package com.willetac.mmall01.controller.backend;

import com.willetac.mmall01.common.Const;
import com.willetac.mmall01.common.ResponseCode;
import com.willetac.mmall01.common.Result;
import com.willetac.mmall01.pojo.Category;
import com.willetac.mmall01.pojo.User;
import com.willetac.mmall01.service.ICategoryService;
import com.willetac.mmall01.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController("manage/category")
public class CategoryController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private ICategoryService iCategoryService;

    public Result<Category> addCategory(HttpSession httpSession,String categoryName,
                              @RequestParam(value="parentId",required = true,defaultValue = "0") Integer parentId){
        User user = (User)httpSession.getAttribute(Const.USER_SESSION);
        if(user == null){
            return Result.<Category>error("请先登录");
        }
        if(!iUserService.checkAdmin(user).isSuccess()){
            return Result.<Category>error("没有管理员权限");
        }
        return iCategoryService.add(categoryName,parentId);
    }
}
