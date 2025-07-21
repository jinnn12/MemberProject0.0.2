package com.cash.memberProject002.Post.domain;

import com.cash.memberProject002.author.domain.Author;
import com.cash.memberProject002.common.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
//@ManyToOne()

public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private String title;
    @Column(length = 3000)
    private String contents;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;
    @Builder.Default
    private String delYn = "N";
}
