package com.strangely.backend.Controller.Category;

import com.strangely.backend.Config.Exception.exceptionHandler;
import com.strangely.backend.Model.Category.Entities.PostCategory;
import com.strangely.backend.Model.Category.DTOs.CategoryRequestDTO;
import com.strangely.backend.Model.Category.DTOs.PostCategoryDTO;
import com.strangely.backend.Repository.Category.PostCategoryRepository;
import com.strangely.backend.Service.Category.IPostCategoryService;
import com.strangely.backend.Service.Category.Implementation.PostCategoryService;
import com.strangely.backend.Service.Exception.Restexception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post-categories")
public class PostCategoryController {
    @Autowired
    private IPostCategoryService postCategoryService;
    private final exceptionHandler exceptionhandler;

    public PostCategoryController(PostCategoryService postCategoryService, PostCategoryRepository postCategoryRepository, exceptionHandler exceptionhandler) {
        this.postCategoryService = postCategoryService;
        this.exceptionhandler = exceptionhandler;
    }

    // This method is a POST mapping for the "/add" endpoint, responsible for adding a new post category.
    @PostMapping("/add")
    public ResponseEntity<PostCategory> addPostCategory(@RequestBody PostCategoryDTO postCategoryDTO) {
        // Initialize the response entity to be populated based on the outcome of the operation.
        ResponseEntity response = null;
        try {
            // Attempt to add the post category using the provided DTO through the service layer.
            PostCategory postCategory = postCategoryService.addPostCategory(postCategoryDTO);

            // If the post category is successfully added, create a 201 CREATED response with the added category in the response body.
            response = ResponseEntity.status(HttpStatus.CREATED).body(postCategory);
        } catch (Restexception e) {
            // If an exception (e.g., Restexception) is caught during the operation:
            exceptionhandler.handleException(e);
        }catch (Exception e)
        {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
        return response;
    }


    // This method is a POST mapping for the "/getCategoryId" endpoint, designed to retrieve the ID of a post category.
    @PostMapping("/getCategoryId")
    public ResponseEntity<?> getCategoryId(@RequestBody CategoryRequestDTO requestDto) {
        // Initialize the response entity to be populated based on the outcome of the operation.
        ResponseEntity response = null;

        // Extract the post category name from the request DTO.
        String postCategoryName = requestDto.getPostCategoryName();

        try {
            // Attempt to retrieve the category ID from the service layer based on the provided category name.
            Long categoryId = postCategoryService.getCategoryId(postCategoryName);

            // Check if a valid category ID is obtained.
            if (categoryId != null) {
                // If a category ID is found, create a 200 OK response with the ID in the response body.
                response = ResponseEntity.ok(categoryId);
            } else {
                // If no category ID is found, generate a 404 NOT FOUND response with an informative message.
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post category not found");
            }
        } catch (Restexception e) {
            // Catch and handle any exceptions thrown by the service layer, specifically Restexception.
            // In case of an exception, generate a 400 BAD REQUEST response with the exception details in the response body.
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }
        return response;
    }


    // This method is a GET mapping to "/getAllCategories" endpoint, designed to retrieve all post categories.
    @GetMapping("/getAllCategories")
    public ResponseEntity<List<PostCategory>> getallcategories() {
        ResponseEntity response = null;

        try {
            // Attempt to fetch all post categories from the service layer.
            List<PostCategory> output = postCategoryService.getAllCategories();

            // Check if any categories were retrieved.
            if (!output.isEmpty()) {
                // If categories are found, return a 200 OK response with the list of categories in the response body.
                response = ResponseEntity.status(HttpStatus.OK).body(output);
            } else {
                // If no categories are found, return a 404 NOT FOUND response with an appropriate message.
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No categories found");
            }
        } catch (Restexception e) {
            // Catch and handle any exceptions thrown by the service layer, such as Restexception.
            // In case of an exception, return a 400 BAD REQUEST response with the exception details in the response body.
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
        return response;
    }
}

