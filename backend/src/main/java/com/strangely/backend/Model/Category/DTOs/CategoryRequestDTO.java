package com.strangely.backend.Model.Category.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class CategoryRequestDTO {
    private String postCategoryName;
}
