package com.strangely.backend.Service.Category.Implementation;

import com.strangely.backend.Model.Category.Entities.PostCategory;
import com.strangely.backend.Model.Category.DTOs.PostCategoryDTO;
import com.strangely.backend.Repository.Category.PostCategoryRepository;
import com.strangely.backend.Service.Category.IPostCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCategoryService implements IPostCategoryService {
    @Autowired
    private final PostCategoryRepository postCategoryRepository;

    @Autowired
    public PostCategoryService(PostCategoryRepository postCategoryRepository) {
        this.postCategoryRepository = postCategoryRepository;
    }

    // Add a new PostCategory based on the information provided in the PostCategoryDTO.
    @Override
    public PostCategory addPostCategory(PostCategoryDTO postCategoryDTO) {
        PostCategory postCategory = new PostCategory();
        PostCategory result;
        try {
            PostCategory exist = postCategoryRepository.findByPostCategoryName(postCategoryDTO.getPostCategoryName());
            if(exist == null)
            {
                // Set the post category name from the PostCategoryDTO.
                postCategory.setPostCategoryName(postCategoryDTO.getPostCategoryName());

                result = postCategoryRepository.save(postCategory);
            }else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            // If an exception occurs while setting the post category name, throw a RuntimeException.
            throw new RuntimeException("Failed to add Post Category.");
        }
        // Save the PostCategory entity to the postCategoryRepository and return the saved PostCategory.
        return result;
    }


    // Retrieve the ID of a PostCategory based on the provided postCategoryName.
    @Override
    public Long getCategoryId(String postCategoryName) {
        try {
            // Attempt to find the PostCategory by its name in the postCategoryRepository.
            PostCategory postCategory = postCategoryRepository.findByPostCategoryName(postCategoryName);

            // Check if the PostCategory is found.
            if (postCategory != null) {
                return postCategory.getPostCategoryId();
            }
        } catch (Exception e) {
            // If an exception occurs while finding the PostCategory, throw a RuntimeException.
            throw new RuntimeException("Category not found");
        }

        // Return null if the PostCategory is not found.
        return null;
    }

    // Retrieve a list of all PostCategories.
    @Override
    public List<PostCategory> getAllCategories() {
        List<PostCategory> categories = (List<PostCategory>) postCategoryRepository.findAll();

        // Check if the list of categories is empty.
        if (categories.isEmpty()) {
            return null;
        }

        // Return the list of categories.
        return categories;
    }


}
