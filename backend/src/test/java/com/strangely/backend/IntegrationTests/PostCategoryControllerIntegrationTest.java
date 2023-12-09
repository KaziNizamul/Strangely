package com.strangely.backend.IntegrationTests;
import com.strangely.backend.Config.Exception.exceptionHandler;
import com.strangely.backend.Controller.Category.PostCategoryController;
import com.strangely.backend.Model.Category.Entities.PostCategory;
import com.strangely.backend.Model.Category.DTOs.CategoryRequestDTO;
import com.strangely.backend.Model.Category.DTOs.PostCategoryDTO;
import com.strangely.backend.Service.Category.IPostCategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.strangely.backend.Repository.Category.PostCategoryRepository;
import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class PostCategoryControllerIntegrationTest {

    @Mock
    private IPostCategoryService postCategoryService;
    @Mock
    private PostCategoryRepository postCategoryRepository;

    @InjectMocks
    private PostCategoryController postCategoryController;

    @Mock
    private exceptionHandler exceptionhandler;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddPostCategory_Success() {

        PostCategoryDTO postCategoryDTO = new PostCategoryDTO();
        PostCategory expectedPostCategory = new PostCategory();

        when(postCategoryService.addPostCategory(any(PostCategoryDTO.class))).thenReturn(expectedPostCategory);


        ResponseEntity<PostCategory> response = postCategoryController.addPostCategory(postCategoryDTO);


        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedPostCategory, response.getBody());
    }

    @Test
    public void testGetCategoryId_Exists() {
        String categoryName = "CategoryName";
        Long expectedCategoryId = 123L;

        when(postCategoryService.getCategoryId(categoryName)).thenReturn(expectedCategoryId);

        CategoryRequestDTO requestDTO = new CategoryRequestDTO();
        requestDTO.setPostCategoryName(categoryName);

        ResponseEntity<?> response = postCategoryController.getCategoryId(requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCategoryId, response.getBody());
    }

    @Test
    public void testGetCategoryId_NotFound() {
        String categoryName = "NonExistingCategory";

        when(postCategoryService.getCategoryId(categoryName)).thenReturn(null);

        CategoryRequestDTO requestDTO = new CategoryRequestDTO();
        requestDTO.setPostCategoryName(categoryName);

        ResponseEntity<?> response = postCategoryController.getCategoryId(requestDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Post category not found", response.getBody());
    }

    @Test
    public void testGetAllCategories_Empty() {
        List<PostCategory> emptyList = new ArrayList<>();

        ResponseEntity<?> response = postCategoryController.getallcategories();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No categories found", response.getBody());
    }


}
