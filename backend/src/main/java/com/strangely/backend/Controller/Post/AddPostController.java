package com.strangely.backend.Controller.Post;

import com.strangely.backend.Config.Exception.exceptionHandler;
import com.strangely.backend.Model.Post.DTOs.PostDTO;
import com.strangely.backend.Model.Post.DTOs.PostResponseDTO;
import com.strangely.backend.Service.Post.IaddPostService;
import com.strangely.backend.Service.Exception.Restexception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@CrossOrigin
public class AddPostController {
    @Autowired
    public IaddPostService addPostService;
    @Autowired
    private exceptionHandler exceptionHandler;


    // This method is a POST mapping for the "/addpost" endpoint, allowing the addition of a new post.
    // The CrossOrigin annotation is used to handle Cross-Origin Resource Sharing (CORS) for this endpoint.
    @PostMapping("/addpost")
    @CrossOrigin
    public ResponseEntity<PostResponseDTO> addPostCase(@RequestBody PostDTO postDTO) {
        // Initialize variables to hold the post response DTO and the overall response entity.
        PostResponseDTO postResponseDTO;
        ResponseEntity response;

        try {
            // Attempt to add a new post using the provided postDTO through the addPostService.
            postResponseDTO = addPostService.addPost(postDTO);

            // If the post is successfully added, create a 200 OK response with the post response DTO in the response body.
            response = ResponseEntity.status(HttpStatus.OK).body(postResponseDTO);

        } catch (Restexception e) {
            // If a Restexception is caught during the operation:

            // Generate a 400 BAD REQUEST response with the exception details in the response body.
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
            exceptionHandler.handleException(e);
        }
        return response;
    }


    // This method is a PUT mapping for the "/edit-post/{postId}" endpoint, allowing the update of an existing post.
// The CrossOrigin annotation is used to handle Cross-Origin Resource Sharing (CORS) for this endpoint.
    @PutMapping("/edit-post/{postId}")
    @CrossOrigin
    public ResponseEntity<PostResponseDTO> editPost(@PathVariable int postId, @RequestBody PostDTO postDTO) {
        // Initialize the response entity to be populated based on the outcome of the operation.
        ResponseEntity response = null;

        try {
            // Attempt to edit the existing post with the provided postID and postDTO through the addPostService.
            PostResponseDTO postResponseDTO = addPostService.editPost(postId, postDTO);

            // Check if the post is successfully edited.
            if (postResponseDTO != null) {
                // If the edit is successful, create a 200 OK response with the updated post response DTO in the response body.
                response = ResponseEntity.ok().body(postResponseDTO);
            } else {
                // If the post to be edited is not found, generate a 404 NOT FOUND response.
                response = ResponseEntity.notFound().build();
            }
        } catch (Restexception e) {

            // Generate a 400 BAD REQUEST response with the exception details in the response body.
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
            exceptionHandler.handleException(e);
        }
          return response;
    }


    // This method is a DELETE mapping for the "/delete-post/{postId}" endpoint, allowing the removal of a specific post.
    @DeleteMapping("/delete-post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable int postId) {
        // Initialize the response entity to be populated based on the outcome of the operation.
        ResponseEntity response = null;

        try {
            // Attempt to delete the post with the provided postID and postDTO through the addPostService.
            boolean deleted = addPostService.deletePost(postId);

            // Check if the post is successfully deleted.
            if (deleted) {
                // If the deletion is successful, create a 200 OK response with a success message in the response body.
                response = ResponseEntity.status(HttpStatus.OK).body("Post deleted successfully");
            } else {
                // If the post to be deleted is not found, generate a 404 NOT FOUND response with an appropriate message.
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
            }
        } catch (Restexception e) {
            // Generate a 400 BAD REQUEST response with the exception details in the response body.
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
            exceptionHandler.handleException(e);
        }
        return response;
    }


}
