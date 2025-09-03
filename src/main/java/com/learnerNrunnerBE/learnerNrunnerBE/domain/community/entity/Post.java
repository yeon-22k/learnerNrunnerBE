package com.learnerNrunnerBE.learnerNrunnerBE.domain.community.entity;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.dto.PostRequestDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "isStudy")
    private Boolean isStudy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Post(PostRequestDto dto, User user){
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.isStudy = dto.getIsStudy();
        this.user = user;
    }

    public void updatePost(PostRequestDto dto){
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.isStudy = dto.getIsStudy();
    }
}
