package com.soomin.projectboardfinal.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

/**
 * fileName     : Member
 * author       : lia
 * date         : 2023/07/03
 * description  : 회원 Entity
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/03       lia          최초 생성
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long    id;                                                                 // 회원 고유번호
    private String  email;                                                              // 이메일
    private String  password;                                                           // 비밀번호
    private String  nickname;                                                           // 닉네임
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime   createdAt;                                                  // 생성일시
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime   modifiedAt;                                                 // 수정일시

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Article> articleList;	                                                // 게시글
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleComment> articleCommentList;	                                // 댓글
}
