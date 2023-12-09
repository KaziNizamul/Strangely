package com.strangely.backend.Model.Post.DTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostDTO {

    private String username;

    private String description;

    private List<String> images;

    private int categoryId;

    private int area_id;

    private int likeCount;

    private int loveCount;

    private int dislikeCount;

}
