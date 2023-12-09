package com.strangely.backend.Model.Category.Entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "postcategory")
public class PostCategory {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "postCategoryId")
    private Long postCategoryId;

    @Column(name = "postCategoryName")
    private String postCategoryName;


}
