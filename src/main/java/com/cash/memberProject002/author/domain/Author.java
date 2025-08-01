package com.cash.memberProject002.author.domain;

import com.cash.memberProject002.Post.domain.Post;
import com.cash.memberProject002.common.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class Author extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private String role;

    @OneToMany(mappedBy = "author")
    @Builder.Default
    List<Post> postList = new ArrayList<>();


    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }


}
