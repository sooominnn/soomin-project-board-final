package com.soomin.projectboardfinal.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * fileName     : ArticleCommentQueryRepository
 * author       : lia
 * date         : 2023/07/03
 * description  : 게시글 댓글 Query Repository
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/03       lia          최초 생성
 */
@Repository
@RequiredArgsConstructor
public class ArticleCommentQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
}
