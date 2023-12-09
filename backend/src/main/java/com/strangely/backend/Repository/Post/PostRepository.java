package com.strangely.backend.Repository.Post;

import com.strangely.backend.Model.Post.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByAreaId(int area_id);
    List<Post> findByPostCategoryId(int post_category_id);  // Use 'post_category_id' here
    List<Post> findByAreaIdAndPostCategoryId(int area_id, int post_category_id);

}