package com.strangely.backend.Repository.Category;

import com.strangely.backend.Model.Category.Entities.PostCategory;
import org.springframework.data.repository.CrudRepository;

public interface PostCategoryRepository extends CrudRepository<PostCategory, Long> {

    PostCategory findByPostCategoryName(String postCategoryName);
}
