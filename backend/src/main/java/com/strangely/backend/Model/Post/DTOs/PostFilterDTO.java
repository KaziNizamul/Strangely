package com.strangely.backend.Model.Post.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostFilterDTO {
    private int area_id;
    private int post_category_id;

}
