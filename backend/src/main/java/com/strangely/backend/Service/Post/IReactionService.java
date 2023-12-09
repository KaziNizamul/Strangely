package com.strangely.backend.Service.Post;

import com.strangely.backend.Model.Post.DTOs.PostResponseDTO;

public interface IReactionService {

    PostResponseDTO addLikeCount(int postId);
    PostResponseDTO removeLikeCount(int postId);

    PostResponseDTO addLoveCount(int postId);

    PostResponseDTO removeLoveCount(int postId);

    PostResponseDTO addDislikeCount(int postId);

    PostResponseDTO removeDislikeCount(int postId);
}
