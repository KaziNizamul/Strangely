package com.strangely.backend.UnitTest.Service.Homepage;

import com.strangely.backend.Model.Category.Entities.PostCategory;
import com.strangely.backend.Model.Category.DTOs.PostCategoryDTO;
import com.strangely.backend.Repository.Category.PostCategoryRepository;
import com.strangely.backend.Service.Category.Implementation.PostCategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class PostCategoryServiceTest {

    @Mock
    private PostCategoryRepository postCategoryRepository;

    @InjectMocks
    private PostCategoryService postCategoryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddPostCategory() {
        // Mocking repository behavior
        when(postCategoryRepository.findByPostCategoryName("Tech")).thenReturn(null);

        // Setting up input DTO
        PostCategoryDTO postCategoryDTO = new PostCategoryDTO();
        postCategoryDTO.setPostCategoryName("Tech");

        // Setting up expected PostCategory entity
        PostCategory expectedPostCategory = new PostCategory();
        expectedPostCategory.setPostCategoryName(postCategoryDTO.getPostCategoryName());

        // Mocking repository save behavior
        when(postCategoryRepository.save(any(PostCategory.class))).thenReturn(expectedPostCategory);

        // Invoking the service method
        PostCategory addedCategory = postCategoryService.addPostCategory(postCategoryDTO);

        // Assertions
        assertEquals("Tech", addedCategory.getPostCategoryName());
        // Assuming the service assigns an ID to the created category

        // Verifying repository interactions
        verify(postCategoryRepository, times(1)).findByPostCategoryName("Tech");
        verify(postCategoryRepository, times(1)).save(any(PostCategory.class));
    }

    @Test
    public void testGetCategoryId_CategoryExists() {
        String categoryName = "Technology";
        PostCategory postCategory = new PostCategory();
        postCategory.setPostCategoryName(categoryName);
        postCategory.setPostCategoryId(1L);

        when(postCategoryRepository.findByPostCategoryName(categoryName)).thenReturn(postCategory);

        Long categoryId = postCategoryService.getCategoryId(categoryName);

        assertEquals(1L, categoryId);
        verify(postCategoryRepository, times(1)).findByPostCategoryName(categoryName);
    }

    @Test
    public void testGetCategoryId_CategoryNotExists() {
        String categoryName = "NonExistentCategory";

        when(postCategoryRepository.findByPostCategoryName(categoryName)).thenReturn(null);

        Long categoryId = postCategoryService.getCategoryId(categoryName);

        assertNull(categoryId);
        verify(postCategoryRepository, times(1)).findByPostCategoryName(categoryName);
    }

    @Test
    public void testAddPostCategory_ExceptionScenario() {
        PostCategoryDTO postCategoryDTO = new PostCategoryDTO();
        postCategoryDTO.setPostCategoryName("Technology");

        when(postCategoryRepository.save(any(PostCategory.class))).thenThrow(new RuntimeException("Failed to save"));

        try {
            postCategoryService.addPostCategory(postCategoryDTO);
        } catch (Exception e) {
            assertEquals("Failed to add Post Category.", e.getMessage());
        }

        verify(postCategoryRepository, times(1)).save(any(PostCategory.class));
    }

    // Add more test cases for edge cases, error handling, etc.
}

