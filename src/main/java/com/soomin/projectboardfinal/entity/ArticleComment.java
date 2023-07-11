package com.soomin.projectboardfinal.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * fileName     : ArticleComment
 * author       : lia
 * date         : 2023/07/03
 * description  : 게시글 댓글 Entity
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/03       lia          최초 생성
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ArticleComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long            id;                           // 댓글 고유번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member          member;                       // 회원
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article         article;                      // 게시글
    @Column(nullable = false, length = 500)
    private String          content;                      // 본문
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime   createdAt;                    // 생성일시
    @CreatedBy
    @Column(nullable = false, length = 100)
    private String          createdBy;                    // 생성자
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime   modifiedAt;                   // 수정일시
    @LastModifiedBy
    @Column(nullable = false, length = 100)
    private String          modifiedBy;                   // 수정자

    @Builder
    public ArticleComment(String content, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
                this.content = content;
                this.createdAt = createdAt;
                this.createdBy = createdBy;
                this.modifiedAt = modifiedAt;
                this.modifiedBy = modifiedBy;
    }
}
