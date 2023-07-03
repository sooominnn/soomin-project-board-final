package com.soomin.projectboardfinal.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

/**
 * fileName     : Article
 * author       : lia
 * date         : 2023/07/03
 * description  : 게시글 Entity
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/03       lia          최초 생성
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Article {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long    id;                                             // 게시글 고유번호
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "member_id")
        private Member  member;                                         // 회원
        @Column(nullable = false)
        private String  title;                                          // 제목
        @Column(nullable = false, length = 10000)
        private String  content;                                        // 본문
        @CreatedDate
        @Column(nullable = false, updatable = false)
        private LocalDateTime   createdAt;                              // 생성일시
        @CreatedBy
        @Column(nullable = false, updatable = false, length = 100)
        private String          createdBy;                              // 생성자
        @LastModifiedDate
        @Column(nullable = false)
        private LocalDateTime   modifiedAt;                             // 수정일시
        @LastModifiedBy
        @Column(nullable = false, length = 100)
        private String          modifiedBy;                             // 수정자

        @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
        private List<ArticleComment> articleCommentList;                // 댓글
}
