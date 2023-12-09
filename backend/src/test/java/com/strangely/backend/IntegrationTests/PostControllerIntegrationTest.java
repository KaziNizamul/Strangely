package com.strangely.backend.IntegrationTests;

import com.strangely.backend.Controller.Post.PostController;
import com.strangely.backend.Model.Post.DTOs.PostFilterDTO;
import com.strangely.backend.Model.Post.DTOs.PostResponseDTO;
import com.strangely.backend.Service.Post.IPostsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PostControllerIntegrationTest {

    @Mock
    private IPostsService postsService;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testAllPosts() {
        // Arrange
        List<PostResponseDTO> mockPostList = Collections.singletonList(new PostResponseDTO());
        when(postsService.getAllPosts()).thenReturn(mockPostList);

        // Act
        ResponseEntity<List<PostResponseDTO>> response = postController.allPosts();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPostList, response.getBody());
    }

    @Test
    void testAllPosts_Exception() {
        // Arrange
        when(postsService.getAllPosts()).thenThrow(new RuntimeException("Simulated exception"));

        // Act
        ResponseEntity<List<PostResponseDTO>> response = postController.allPosts();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testGetPostFilters() {
        // Arrange
        PostFilterDTO filterDTO = new PostFilterDTO();
        List<PostResponseDTO> mockPostList = Collections.singletonList(new PostResponseDTO());
        when(postsService.getPostsByFilter(any(PostFilterDTO.class))).thenReturn(mockPostList);

        // Act
        ResponseEntity<List<PostResponseDTO>> response = postController.getPostFilters(filterDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPostList, response.getBody());
    }

    @Test
    void testGetPostFilters_Exception() {
        // Arrange
        PostFilterDTO filterDTO = new PostFilterDTO();
        when(postsService.getPostsByFilter(any(PostFilterDTO.class))).thenThrow(new RuntimeException("Simulated exception"));

        // Act
        ResponseEntity<List<PostResponseDTO>> response = postController.getPostFilters(filterDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
