package com.strangely.backend.UnitTest.Service.Post;

import com.strangely.backend.Model.Post.DTOs.PostDTO;
import com.strangely.backend.Model.Post.Entities.Images;
import com.strangely.backend.Model.Post.Entities.Post;
import com.strangely.backend.Model.Post.DTOs.PostResponseDTO;
import com.strangely.backend.Repository.Post.ImagesRepository;
import com.strangely.backend.Repository.Post.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddPostServiceTest {

    @Mock
    private PostRepository postRepositoryMock;

    @Mock
    private ImagesRepository imagesRepositoryMock;

    @InjectMocks
    private com.strangely.backend.Service.Post.Implementation.addPostService addPostService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddPost_Successful() {
        PostDTO postDTO = new PostDTO();
        postDTO.setUsername("TestUser");
        postDTO.setDescription("Test description");
        postDTO.setCategoryId(1);
        postDTO.setArea_id(123);
        List<String> imagesData = new ArrayList<>();
        imagesData.add("");
        postDTO.setImages(imagesData);

        Post savedPost = new Post();
        savedPost.setPostId(1);
        when(postRepositoryMock.save(any(Post.class))).thenReturn(savedPost);
        when(imagesRepositoryMock.save(any(Images.class))).thenReturn(new Images());

        PostResponseDTO postResponseDTO = addPostService.addPost(postDTO);

        assertNotNull(postResponseDTO);
        assertEquals("Post added successfully", postResponseDTO.getStatus());
    }

    @Test
    public void testEditPost_Successful() {
        int postId = 1;
        PostDTO postDTO = new PostDTO();
        postDTO.setDescription("Updated description");
        postDTO.setCategoryId(2);
        postDTO.setArea_id(456);

        Post existingPost = new Post();
        existingPost.setPostId(postId);
        when(postRepositoryMock.findById(postId)).thenReturn(Optional.of(existingPost));
        when(postRepositoryMock.save(any(Post.class))).thenReturn(existingPost);

        PostResponseDTO postResponseDTO = addPostService.editPost(postId, postDTO);

        assertNotNull(postResponseDTO);
        assertEquals("Post Edited Successfully", postResponseDTO.getStatus());

    }

    @Test
    public void testEditPost_PostNotFound() {
        int postId = 1;
        PostDTO postDTO = new PostDTO();

        when(postRepositoryMock.findById(postId)).thenReturn(Optional.empty());

        PostResponseDTO postResponseDTO = addPostService.editPost(postId, postDTO);

        assertNull(postResponseDTO);
    }

    @Test
    public void testDeletePost_WhenPostExists() {
        int postId = 1;
        Post existingPost = new Post();
        when(postRepositoryMock.findById(postId)).thenReturn(Optional.of(existingPost));
        doNothing().when(postRepositoryMock).delete(existingPost);

        boolean isDeleted = addPostService.deletePost(postId);

        assertTrue(isDeleted);
    }

    @Test
    public void testDeletePost_WhenPostDoesNotExist() {
        int postId = 1;
        when(postRepositoryMock.findById(postId)).thenReturn(Optional.empty());

        boolean isDeleted = addPostService.deletePost(postId);

        assertFalse(isDeleted);
    }
}
