package com.strangely.backend.UnitTest.Service.Post;

import com.strangely.backend.Model.Post.DTOs.PostFilterDTO;
import com.strangely.backend.Model.Post.Entities.Images;
import com.strangely.backend.Model.Post.Entities.Post;
import com.strangely.backend.Model.Post.DTOs.PostResponseDTO;
import com.strangely.backend.Repository.Post.ImagesRepository;
import com.strangely.backend.Repository.Post.PostRepository;
import com.strangely.backend.Service.Post.Implementation.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PostserviceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private ImagesRepository imagesRepository;

    @InjectMocks
    private PostService postService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPosts() {
        // Mock data
        Post post = new Post();
        post.setPostId(1);
        post.setUsername("user1");
        post.setDescription("Test post");
        // Add more fields as needed

        Images image = new Images();
        image.setImage(new byte[]{1, 2, 3}); // Sample image data

        // Mock repository responses
        when(postRepository.findAll()).thenReturn(Collections.singletonList(post));
        when(imagesRepository.findByPostId(1)).thenReturn(Collections.singletonList(image));

        // Call the service method
        List<PostResponseDTO> result = postService.getAllPosts();

        // Verify repository interactions
        verify(postRepository, times(1)).findAll();
        verify(imagesRepository, times(1)).findByPostId(1);

        // Verify the result
        assertEquals(1, result.size());
        PostResponseDTO responseDTO = result.get(0);
        assertEquals("user1", responseDTO.getUsername());
        assertEquals("Test post", responseDTO.getDescription());
        // Add more assertions based on your fields

        // Verify image data
        assertEquals(1, responseDTO.getImages().size());
    }

    @Test
    public void testGetPostsByFilter() {
        // Mock data
        Post post = new Post();
        post.setPostId(1);
        post.setUsername("user1");
        post.setDescription("Test post");
        // Add more fields as needed

        Images image = new Images();
        image.setImage(new byte[]{1, 2, 3}); // Sample image data

        // Mock repository responses
        when(postRepository.findByAreaIdAndPostCategoryId(anyInt(), anyInt())).thenReturn(Collections.singletonList(post));
        when(imagesRepository.findByPostId(1)).thenReturn(Collections.singletonList(image));

        // Call the service method with a filter
        PostFilterDTO filter = new PostFilterDTO();
        filter.setArea_id(1);
        filter.setPost_category_id(2);
        List<PostResponseDTO> result = postService.getPostsByFilter(filter);

        // Verify repository interactions
        verify(postRepository, times(1)).findByAreaIdAndPostCategoryId(1, 2);
        verify(imagesRepository, times(1)).findByPostId(1);

        // Verify the result
        assertEquals(1, result.size());
        PostResponseDTO responseDTO = result.get(0);
        assertEquals("user1", responseDTO.getUsername());
        assertEquals("Test post", responseDTO.getDescription());
        // Add more assertions based on your fields

        // Verify image data
        assertEquals(1, responseDTO.getImages().size());
    }

    // Add more test cases to cover other scenarios

}
