package com.strangely.backend.Service.Post;

import com.strangely.backend.Model.Post.DTOs.PostDTO;
import com.strangely.backend.Model.Post.DTOs.PostResponseDTO;

public interface IaddPostService {
    PostResponseDTO addPost(PostDTO postDTO);

    PostResponseDTO editPost(int postId, PostDTO postDTO);

    boolean deletePost (int postId);
}
