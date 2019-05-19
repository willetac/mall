package com.willetac.mmall01.service;

import com.willetac.mmall01.common.Result;
import com.willetac.mmall01.pojo.Category;

public interface ICategoryService {
    Result<Category> add(String categoryName, Integer parentId);
}
