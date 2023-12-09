package com.strangely.backend.Controller.Post;


import com.strangely.backend.Config.Exception.exceptionHandler;
import com.strangely.backend.Model.Post.DTOs.PostResponseDTO;
import com.strangely.backend.Service.Post.IReactionService;
import com.strangely.backend.Service.Exception.Restexception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class ReactionController {
    @Autowired
    private IReactionService reactionService;
    @Autowired
    private  exceptionHandler exceptionHandler;

    public ReactionController(IReactionService reactionService) {
        this.reactionService = reactionService;
    }

    @PostMapping("/add-like/{postId}")
    public ResponseEntity<PostResponseDTO> addLikeCount(@PathVariable int postId) {
        ResponseEntity response = null;
        try {
            PostResponseDTO postResponseDTO = reactionService.addLikeCount (postId);// calling to service class for user service
            response = ResponseEntity.ok (postResponseDTO);
        } catch (Restexception e) {
            response = ResponseEntity.status (HttpStatus.BAD_REQUEST).body (e);
            exceptionHandler.handleException (e);
        }
        return response;
    }

    @DeleteMapping("/remove-like/{postId}")
    public ResponseEntity<PostResponseDTO> removeLikeCount(@PathVariable int postId) {
        ResponseEntity response = null;
        try {
            PostResponseDTO postResponseDTO = reactionService.removeLikeCount (postId);
            response = ResponseEntity.ok (postResponseDTO);
        }
        catch (Restexception e) {
            response = ResponseEntity.status (HttpStatus.BAD_REQUEST).body (e);
            exceptionHandler.handleException (e);
        }
        return response;
    }

    @PostMapping("/add-love/{postId}")
    public ResponseEntity<PostResponseDTO> addLoveCount(@PathVariable int postId) {
        ResponseEntity response = null;
        try {
            PostResponseDTO postResponseDTO = reactionService.addLoveCount (postId);
            response = ResponseEntity.ok (postResponseDTO);
        }
        catch (Restexception e) {
            response = ResponseEntity.status (HttpStatus.BAD_REQUEST).body (e);
            exceptionHandler.handleException (e);
        }
        return response;
    }

    @DeleteMapping("/remove-love/{postId}")
    public ResponseEntity<PostResponseDTO> removeLoveCount(@PathVariable int postId) {
        ResponseEntity response = null;
        try {
            PostResponseDTO postResponseDTO = reactionService.removeLoveCount (postId);
            response = ResponseEntity.ok (postResponseDTO);
        }
        catch (Restexception e) {
            response = ResponseEntity.status (HttpStatus.BAD_REQUEST).body (e);
            exceptionHandler.handleException (e);
        }
        return response;
    }

    @PostMapping("/add-dislike/{postId}")
    public ResponseEntity<PostResponseDTO> addDislikeCount(@PathVariable int postId) {
        ResponseEntity response = null;
        try {
            PostResponseDTO postResponseDTO = reactionService.addDislikeCount (postId);
            response = ResponseEntity.ok (postResponseDTO);
        }
        catch (Restexception e) {
            response = ResponseEntity.status (HttpStatus.BAD_REQUEST).body (e);
            exceptionHandler.handleException (e);
        }
        return response;
    }

    @DeleteMapping("/remove-dislike/{postId}")
    public ResponseEntity<PostResponseDTO> removeDislikeCount(@PathVariable int postId) {
        ResponseEntity response = null;
        try {
            PostResponseDTO postResponseDTO = reactionService.removeDislikeCount (postId);
            response = ResponseEntity.ok (postResponseDTO);
        }
        catch (Restexception e) {
            response = ResponseEntity.status (HttpStatus.BAD_REQUEST).body (e);
            exceptionHandler.handleException (e);
        }
        return response;
    }
}
