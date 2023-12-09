package com.strangely.backend.Service.Post;

import com.strangely.backend.Model.Post.DTOs.PostFilterDTO;
import com.strangely.backend.Model.Post.DTOs.PostResponseDTO;

import java.util.List;

public interface IPostsService {
    List<PostResponseDTO> getAllPosts ();

    List<PostResponseDTO> getPostsByFilter(PostFilterDTO filter);

}
