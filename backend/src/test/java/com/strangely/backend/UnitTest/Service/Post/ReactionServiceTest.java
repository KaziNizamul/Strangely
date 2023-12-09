package com.strangely.backend.UnitTest.Service.Post;//package com.strangely.backend.Service;

import com.strangely.backend.Model.Post.Entities.Post;
import com.strangely.backend.Model.Post.DTOs.PostResponseDTO;
import com.strangely.backend.Repository.Post.PostRepository;
import com.strangely.backend.Service.Post.Implementation.ReactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReactionServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private ReactionService reactionService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddLikeCount() {
        Post mockedPost = new Post();
        mockedPost.setPostLikeReaction(5);
        when(postRepository.findById(1)).thenReturn(Optional.of(mockedPost));

        PostResponseDTO response = reactionService.addLikeCount(1);

        assertEquals(6, response.getLikeCount());
        assertEquals("Like added successfully", response.getStatus());
    }

    @Test
    public void testRemoveLikeCount() {
        Post mockedPost = new Post();
        mockedPost.setPostLikeReaction(5);
        when(postRepository.findById(1)).thenReturn(Optional.of(mockedPost));

        PostResponseDTO response = reactionService.removeLikeCount(1);

        assertEquals(4, response.getLikeCount());
        assertEquals("Like removed successfully", response.getStatus());
    }

    @Test
    public void testAddLoveCount() {
        Post mockedPost = new Post();
        mockedPost.setPostLoveReaction(3);
        when(postRepository.findById(1)).thenReturn(Optional.of(mockedPost));

        PostResponseDTO response = reactionService.addLoveCount(1);

        assertEquals(4, response.getLoveCount());
        assertEquals("Love added successfully", response.getStatus());
    }

    @Test
    public void testRemoveLoveCount() {
        Post mockedPost = new Post();
        mockedPost.setPostLoveReaction(3);
        when(postRepository.findById(1)).thenReturn(Optional.of(mockedPost));

        PostResponseDTO response = reactionService.removeLoveCount(1);

        assertEquals(2, response.getLoveCount());
        assertEquals("Love removed successfully", response.getStatus());
    }

    @Test
    public void testAddDislikeCount() {

        Post mockedPost = new Post();
        mockedPost.setPostDislike(2);
        when(postRepository.findById(1)).thenReturn(Optional.of(mockedPost));

        PostResponseDTO response = reactionService.addDislikeCount(1);

        assertEquals(3, response.getDislikeCount());
        assertEquals("Dislike added successfully", response.getStatus());
    }

    @Test
    public void testRemoveDislikeCount() {
        Post mockedPost = new Post();
        mockedPost.setPostDislike(2);
        when(postRepository.findById(1)).thenReturn(Optional.of(mockedPost));

        PostResponseDTO response = reactionService.removeDislikeCount(1);

        assertEquals(1, response.getDislikeCount());
        assertEquals("Dislike removed successfully", response.getStatus());
    }
}
