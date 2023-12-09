package com.strangely.backend.Service.Post.Implementation;

import com.strangely.backend.Mapper.Post.PostMP;
import com.strangely.backend.Model.Post.DTOs.PostFilterDTO;
import com.strangely.backend.Model.Post.Entities.Images;
import com.strangely.backend.Model.Post.Entities.Post;
import com.strangely.backend.Model.Post.DTOs.PostResponseDTO;
import com.strangely.backend.Repository.Post.ImagesRepository;
import com.strangely.backend.Repository.Post.PostRepository;
import com.strangely.backend.Service.Post.IPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostsService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ImagesRepository imagesRepository;

    // Retrieve a list of all posts and map them to PostResponseDTOs.
    @Override
    public List<PostResponseDTO> getAllPosts() {
        // Retrieve all Post entities from the postRepository.
        List<Post> postEntities = postRepository.findAll();

        // Map the Post entities to PostResponseDTOs using the PostMP utility class.
        return PostMP.mapPostsToResponseDTOs(postEntities, imagesRepository);
    }

    @Override
    public List<PostResponseDTO> getPostsByFilter(PostFilterDTO filter) {
        List<Post> filteredPosts;
        List<Images> filteredImages;
        int area_id =0;
        int post_category_id = 0;
        area_id = filter.getArea_id ();
        post_category_id = filter.getPost_category_id ();
        if (area_id!=0 && post_category_id!=0)
        {
            filteredPosts = postRepository.findByAreaIdAndPostCategoryId (area_id, post_category_id);
        }
        else if (area_id!=0)
        {
            // Handle the case where only areaId is provided.
            filteredPosts = postRepository.findByAreaId(area_id);
        }
        else if (post_category_id!=0) {
            // Handle the case where only postCategoryId is provided.
            filteredPosts = postRepository.findByPostCategoryId (post_category_id);
        }
        else {
            // Handle the case where neither areaId nor postCategoryId is provided.
            return getAllPosts();
        }
        return PostMP.mapPostsToResponseDTOs(filteredPosts,imagesRepository);
    }

}