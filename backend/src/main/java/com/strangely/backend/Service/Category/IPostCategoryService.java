package com.strangely.backend.Service.Category;

import com.strangely.backend.Model.Category.Entities.PostCategory;
import com.strangely.backend.Model.Category.DTOs.PostCategoryDTO;

import java.util.List;

public interface IPostCategoryService {
    public PostCategory addPostCategory(PostCategoryDTO postCategoryDTO);

    public Long getCategoryId(String postCategoryName);

    public List<PostCategory> getAllCategories();
}
