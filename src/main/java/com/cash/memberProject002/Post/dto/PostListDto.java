package com.cash.memberProject002.Post.dto;

import com.cash.memberProject002.Post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PostListDto {
    private Long id;
    private String title;
    private String authorEmail;

    public static PostListDto fromEntity(Post post) {
        return PostListDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .authorEmail(post.getAuthor().getEmail())
                .build();
    }

}
