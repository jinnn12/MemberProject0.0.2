package com.cash.memberProject002.Post.domain;

import com.cash.memberProject002.author.domain.Author;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Post {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String title;
    private String contents;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

}
