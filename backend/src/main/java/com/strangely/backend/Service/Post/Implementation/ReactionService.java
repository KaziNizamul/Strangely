package com.strangely.backend.Service.Post.Implementation;

import com.strangely.backend.Mapper.Post.PostMP;
import com.strangely.backend.Model.Post.Entities.Post;
import com.strangely.backend.Model.Post.DTOs.PostResponseDTO;
import com.strangely.backend.Repository.Post.ImagesRepository;
import com.strangely.backend.Repository.Post.PostRepository;
import com.strangely.backend.Service.Post.IReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReactionService implements IReactionService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ImagesRepository imagesRepository;

    public ReactionService(PostRepository postRepository, ImagesRepository imagesRepository) {
        this.postRepository = postRepository;
        this.imagesRepository = imagesRepository;
    }

    @Override
    public PostResponseDTO addLikeCount(int postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
       //null check
        if (postOptional.isEmpty()) {
            return null;
        }
        //updating the like count
        Post post = postOptional.get();
        post.setPostLikeReaction(post.getPostLikeReaction() + 1);
        postRepository.save(post);
        String status = "Like added successfully";
        return PostMP.toPostResponseDTO(post,status,imagesRepository);
    }

    public PostResponseDTO removeLikeCount(int postId)
    {
        //this meathod is important to remove the like count
        Optional<Post> postOptional = postRepository.findById(postId);

        // Null check
        if (postOptional.isEmpty()) {
            return null;
        }
        Post post = postOptional.get();
        if (post.getPostLikeReaction() > 0) {
            post.setPostLikeReaction(post.getPostLikeReaction() - 1);
            postRepository.save(post);
        }
        String status = "Like removed successfully";
        return PostMP.toPostResponseDTO(post,status,imagesRepository);

    }

    @Override
    public PostResponseDTO addLoveCount (int postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isEmpty()) {
            return null;
        }
        Post post = postOptional.get();
        post.setPostLoveReaction(post.getPostLoveReaction() + 1);
        postRepository.save(post);
        String status = "Love added successfully";
        return PostMP.toPostResponseDTO(post,status,imagesRepository);
    }

    public PostResponseDTO removeLoveCount(int postId)
    {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isEmpty()) {
            return null;
        }
        Post post = postOptional.get();
        if (post.getPostLoveReaction() > 0) {
            post.setPostLoveReaction(post.getPostLoveReaction() - 1);
            postRepository.save(post);
        }
        String status = "Love removed successfully";
        return PostMP.toPostResponseDTO(post,status,imagesRepository);
    }

    @Override
    public PostResponseDTO addDislikeCount (int postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isEmpty()) {
            return null;
        }
        Post post = postOptional.get();
        post.setPostDislike (post.getPostDislike () + 1);
        postRepository.save(post);
        String status = "Dislike added successfully";
        return PostMP.toPostResponseDTO(post,status,imagesRepository);
    }

    @Override
    public PostResponseDTO removeDislikeCount (int postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isEmpty()) {
            return null;
        }
        Post post = postOptional.get();
        if (post.getPostDislike () > 0) {
            post.setPostDislike (post.getPostDislike () - 1);
            postRepository.save(post);
        }
        String status = "Dislike removed successfully";
        return PostMP.toPostResponseDTO(post,status,imagesRepository);
    }
}
