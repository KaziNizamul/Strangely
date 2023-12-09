package com.strangely.backend.Mapper.Post;

import com.strangely.backend.Model.Post.Entities.Images;
import com.strangely.backend.Model.Post.Entities.Post;
import com.strangely.backend.Model.Post.DTOs.PostResponseDTO;
import com.strangely.backend.Repository.Post.ImagesRepository;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PostMP {

    public static PostResponseDTO toPostResponseDTO(Post post, String status, ImagesRepository imagesRepository )
   {

       PostResponseDTO responseDTO = new PostResponseDTO();
       // Map the fields from the Post entity to the PostResponseDTO
       responseDTO.setUsername(post.getUsername()); // Update to match your field name
       responseDTO.setLoveCount(post.getPostLoveReaction ()); // Update to match your field name
       responseDTO.setDislikeCount(post.getPostDislike ()); // Update to match your field name
       responseDTO.setPostDate(post.getPostDate()); // Update to match your field name
       responseDTO.setDescription(post.getDescription());
       responseDTO.setCategoryId(post.getPostCategoryId ()); // Update to match your field name
       responseDTO.setArea_id(post.getAreaId ()); // Update to match your field name
       responseDTO.setLikeCount(post.getPostLikeReaction());
       responseDTO.setPostId(post.getPostId());
       responseDTO.setStatus(status);
       // You can also add images as needed
       if(imagesRepository != null)
       {
           List<Images> images = imagesRepository.findByPostId (post.getPostId ());
           if(images!=null) {
               List<String> imageList = new ArrayList<>();
               int index = 0;
               for (Images image : images) {
                   String reconstructedImage = image.getPrefix() + new String(Base64.getEncoder().encode(image.getImage()));
                   imageList.add (index, reconstructedImage);
                   index++;
               }
               responseDTO.setImages(imageList);
           }
       }

       return responseDTO;
   }

   //this is done to get posts in descending order of the time it is created
    public static List<PostResponseDTO> mapPostsToResponseDTOs(List<Post> posts, ImagesRepository imagesRepository) {
        return posts.stream().sorted(Comparator.comparing(Post::getPostDate).reversed()).map(postEntity -> {
                    String status = "Getting posts";
                    return toPostResponseDTO(postEntity,status,imagesRepository);
                }).collect(Collectors.toList());
    }
}
