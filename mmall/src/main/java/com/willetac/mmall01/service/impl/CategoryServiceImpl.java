package com.willetac.mmall01.service.impl;

import com.willetac.mmall01.common.ResponseCode;
import com.willetac.mmall01.common.Result;
import com.willetac.mmall01.dao.CategoryMapper;
import com.willetac.mmall01.pojo.Category;
import com.willetac.mmall01.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public Result<Category> add(String categoryName, Integer parentId){
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);
        categoryMapper.insert(category);
        return Result.<Category>build(ResponseCode.SUCCESS.getCode(),category);
    }
}
