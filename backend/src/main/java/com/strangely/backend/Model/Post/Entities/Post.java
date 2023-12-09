package com.strangely.backend.Model.Post.Entities;
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
@Entity(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name ="postId")
    private int postId;

    @Column(name ="username")
    private String username;

    @Column(name ="postDate")
    private long postDate;

    @Column(name ="description")
    private String description;

    @Column(name ="post_category_id")
    private int postCategoryId;

    @Column(name ="postLikeReaction")
    private int postLikeReaction;

    @Column(name ="postLoveReaction")
    private int postLoveReaction;

    @Column(name ="postDislike")
    private int postDislike;

    @Column(name ="areaId")
    private int areaId;

}
