package com.strangely.backend.IntegrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.strangely.backend.Controller.Post.ReactionController;
import com.strangely.backend.Model.Post.DTOs.PostResponseDTO;
import com.strangely.backend.Service.Post.IReactionService;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;
class ReactionControllerIntegrationTest {

    private MockMvc mockMvc;

    // Mock service
    private IReactionService reactionService = mock(IReactionService.class);

    // Controller to be tested
    private ReactionController reactionController = new ReactionController(reactionService);

    // ObjectMapper to convert objects to JSON
    private ObjectMapper objectMapper = new ObjectMapper();

    public ReactionControllerIntegrationTest() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(reactionController).build();
    }

    @Test
    void addLoveCountTest() {
        // Arrange
        IReactionService reactionService = mock(IReactionService.class);
        when(reactionService.addLoveCount(anyInt())).thenReturn(new PostResponseDTO());
        ReactionController controller = new ReactionController(reactionService);
        // Act
        ResponseEntity<PostResponseDTO> response = controller.addLoveCount(1);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(reactionService, times(1)).addLoveCount(1);
    }

    @Test
    void removeLoveCountTest() {
        //Arrange
        IReactionService reactionService = mock(IReactionService.class);
        when(reactionService.removeLoveCount(anyInt())).thenReturn(new PostResponseDTO());
        ReactionController controller = new ReactionController(reactionService);

        // Act
        ResponseEntity<PostResponseDTO> response = controller.removeLoveCount(1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(reactionService, times(1)).removeLoveCount(1);
    }

    @Test
    void addDislikeCountTest() {
        // Arrange
        IReactionService reactionService = mock(IReactionService.class);
        when(reactionService.addDislikeCount(anyInt())).thenReturn(new PostResponseDTO());
        ReactionController controller = new ReactionController(reactionService);
        // Act
        ResponseEntity<PostResponseDTO> response = controller.addDislikeCount (1);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(reactionService, times(1)).addDislikeCount(1);
    }

    @Test
    void removeDislikeCountTest() {
        //Arrange
        IReactionService reactionService = mock(IReactionService.class);
        when(reactionService.removeDislikeCount(anyInt())).thenReturn(new PostResponseDTO());
        ReactionController controller = new ReactionController(reactionService);

        // Act
        ResponseEntity<PostResponseDTO> response = controller.removeDislikeCount(1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(reactionService, times(1)).removeDislikeCount(1);
    }

    @Test
    void addLikeCountTest() {
        // Arrange
        IReactionService reactionService = mock(IReactionService.class);
        when(reactionService.addLikeCount(anyInt())).thenReturn(new PostResponseDTO());
        ReactionController controller = new ReactionController(reactionService);
        // Act
        ResponseEntity<PostResponseDTO> response = controller.addLikeCount(1);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(reactionService, times(1)).addLikeCount(1);
    }

    @Test
    void removeLikeCountTest() {
        //Arrange
        IReactionService reactionService = mock(IReactionService.class);
        when(reactionService.removeLikeCount(anyInt())).thenReturn(new PostResponseDTO());
        ReactionController controller = new ReactionController(reactionService);

        // Act
        ResponseEntity<PostResponseDTO> response = controller.removeLikeCount(1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(reactionService, times(1)).removeLikeCount(1);
    }
}
