package com.strangely.backend.Service.Post.Implementation;

import com.strangely.backend.Mapper.Post.PostMP;
import com.strangely.backend.Model.Post.DTOs.PostDTO;
import com.strangely.backend.Model.Post.Entities.Images;
import com.strangely.backend.Model.Post.Entities.Post;
import com.strangely.backend.Model.Post.DTOs.PostResponseDTO;
import com.strangely.backend.Repository.Post.ImagesRepository;
import com.strangely.backend.Repository.Post.PostRepository;
import com.strangely.backend.Service.Post.IaddPostService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.logging.Logger;

@Service
public class addPostService implements IaddPostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ImagesRepository imagesRepository;

    @Override
    public PostResponseDTO addPost(PostDTO postDTO) {

        // Create a Post entity from the PostDTO
        Post post = new Post();
        post.setUsername(postDTO.getUsername());
        post.setDescription(postDTO.getDescription());
        post.setPostCategoryId(postDTO.getCategoryId());
        post.setAreaId (postDTO.getArea_id());
        post.setPostDate(Instant.now().getEpochSecond());

        // Save the Post entity
        Post savedPost = postRepository.save(post);
        List<String> savedImages= new ArrayList<String>();
        int index = 0;
        // Create Images entities and associate them with the saved Post
        for (String imageData : postDTO.getImages()) {
            Images i = new Images();
            i.setPostId(savedPost.getPostId());
            // Splitting the base64 data
            int indexOfComma = imageData.indexOf(",");
            String prefix = imageData.substring(0, indexOfComma + 1); // Include the comma in the prefix
            String base64ImageData = imageData.substring(indexOfComma + 1);
            // Decoding the base64 payload into a byte array
            byte[] base64Image = Base64.getDecoder().decode(base64ImageData);
            //Adding to database
            i.setPrefix(prefix);
            i.setImage(base64Image);
            imagesRepository.save(i);
            savedImages.add(index,i.getPrefix()+ new String(Base64.getEncoder().encode(i.getImage())));
            index++;
        }
        String status = "Post added successfully";
        PostResponseDTO response = PostMP.toPostResponseDTO(post,status,imagesRepository);
        if(response.getImages().isEmpty() && !savedImages.isEmpty())
        {
            response.setImages(savedImages);
        }
        return response;
    }
    @Override
    public PostResponseDTO editPost(int postId, PostDTO postDTO) {

        //This is done to find the post from the database using postId
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isEmpty()) {
            return null;
        }
        //new post data
        Post post = optionalPost.get();
        post.setDescription(postDTO.getDescription());
        post.setPostCategoryId(postDTO.getCategoryId());
        post.setAreaId(postDTO.getArea_id());
        Post updatedPost = postRepository.save(post);//updating the post

        // creating new post data based on existing post
        String status = "Post Edited Successfully";
        return PostMP.toPostResponseDTO(updatedPost,status,imagesRepository);
    }

    @Override
    public boolean deletePost (int postId) {
        //This is written to find the user from database
        try {
            Optional<Post> op = postRepository.findById(postId);
            List<Images> images = imagesRepository.findByPostId(postId);
            if (op.isPresent()) {
                Post post = op.get();
                if (images != null) {
                    for (Images image : images) {
                        imagesRepository.delete(image);
                    }
                    System.out.println("images removed successfully");
                }
                postRepository.delete(post);//delete post meathod called
                System.out.println("post removed successfully");
                return true;
            }else {
                System.out.println("post not found");
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }
}
