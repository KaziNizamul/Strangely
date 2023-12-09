package com.strangely.backend.IntegrationTests;

import com.strangely.backend.Controller.Post.AddPostController;
import com.strangely.backend.Model.Post.DTOs.PostDTO;
import com.strangely.backend.Model.Post.DTOs.PostResponseDTO;
import com.strangely.backend.Service.Post.IaddPostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;


import com.strangely.backend.Config.Exception.exceptionHandler;

class AddPostControllerIntegrationTest {

    private MockMvc mockMvc;

    @Mock
    private IaddPostService addPostService;
    @Mock
    private exceptionHandler exceptionHandler;

    @InjectMocks
    private AddPostController addPostController;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(addPostController).build();
    }

    @Test
    void testDeletePost_shouldReturnOk() {
        // Arrange
        int postId = 1;
        PostDTO postDTO = new PostDTO();
        when(addPostService.deletePost(any(Integer.class))).thenReturn(true);

        // Act
        ResponseEntity<String> response = addPostController.deletePost(postId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Mockito.verify(addPostService, Mockito.times(1)).deletePost(postId);
    }

    @Test
    void testDeletePost_shouldReturnNotFound() {
        // Arrange
        int postId = 100000;
        PostDTO postDTO = new PostDTO();
        when(addPostService.deletePost(any(Integer.class))).thenReturn(false);

        // Act
        ResponseEntity<String> response = addPostController.deletePost(postId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Post not found", response.getBody());
        Mockito.verify(addPostService, Mockito.times(1)).deletePost(postId);

    }

    @Test
    void addPostCase_shouldReturnOk() {
        // Arrange
        IaddPostService addPostService = mock(IaddPostService.class);
        PostDTO postDTO = new PostDTO(); // Initialize with necessary data
        when(addPostService.addPost(any(PostDTO.class))).thenReturn(new PostResponseDTO());

        AddPostController controller = new AddPostController();
        controller.addPostService = addPostService;

        // Act
        ResponseEntity<PostResponseDTO> response = controller.addPostCase(postDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Mockito.verify(addPostService, Mockito.times(1)).addPost(postDTO);
    }

    @Test
    void editPost_shouldReturnOk() {
        // Arrange
        IaddPostService addPostService = mock(IaddPostService.class);
        int postId = 1; // Provide a valid postId
        PostDTO postDTO = new PostDTO(); // Initialize with necessary data
        when(addPostService.editPost(anyInt(), any(PostDTO.class))).thenReturn(new PostResponseDTO());

        AddPostController controller = new AddPostController();
        controller.addPostService = addPostService;

        // Act
        ResponseEntity<PostResponseDTO> response = controller.editPost(postId, postDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Mockito.verify(addPostService, Mockito.times(1)).editPost(postId, postDTO);
    }
}
