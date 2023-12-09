package com.strangely.backend.Repository.Post;

import com.strangely.backend.Model.Post.Entities.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Long> {
    // Add a custom method to fetch image data associated with a post
    List<Images> findByPostId(int postId);

}
