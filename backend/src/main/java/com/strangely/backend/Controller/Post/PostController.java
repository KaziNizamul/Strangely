package com.strangely.backend.Controller.Post;

import com.strangely.backend.Model.Post.DTOs.PostFilterDTO;
import com.strangely.backend.Model.Post.DTOs.PostResponseDTO;
import com.strangely.backend.Service.Post.IPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private IPostsService postsService;


    @GetMapping("/all_posts")
    public ResponseEntity<List<PostResponseDTO>> allPosts() {

        ResponseEntity response = null;
        try {
            List<PostResponseDTO> allpost = postsService.getAllPosts();
            response = ResponseEntity.status(HttpStatus.OK).body(allpost);
        } catch (Exception e)
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
        return response;
    }

    @PostMapping("/get_post_by_filter")
    @CrossOrigin
    public ResponseEntity<List<PostResponseDTO>> getPostFilters(@RequestBody PostFilterDTO filterDTO) {
        List<PostResponseDTO> postResponseDTO;
        ResponseEntity response;
        try {
            postResponseDTO = postsService.getPostsByFilter (filterDTO);
            response = ResponseEntity.status (HttpStatus.OK).body(postResponseDTO);
            return response;
        } catch (Exception e) {
            response = ResponseEntity.status (HttpStatus.BAD_REQUEST).body(e);
            return response;
        }

    }

}
