package com.megazone.springbootbackend.communityAPI.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reply")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private long replyId;

    @Column(nullable = false, length = 1000)
    private String content;

//    @Enumerated(value = EnumType.STRING)
//    private DeleteStatus isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Reply parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Reply> children = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    public static Reply createReply(String content, Article article, Users user, Reply parent) {
        Reply reply = new Reply();
        reply.content = content;
        reply.article = article;
        reply.users = user;
        reply.parent = parent;
        return reply;
    }

}
