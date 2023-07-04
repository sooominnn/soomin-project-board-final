package com.soomin.projectboardfinal.service.serviceinterface;

import com.querydsl.jpa.impl.JPAQuery;
import com.soomin.projectboardfinal.dto.res.ResArticleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * fileName     : ArticleService
 * author       : lia
 * date         : 2023/07/03
 * description  : 게시글 Service
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/03       lia          최초 생성
 */
public interface ArticleService {

    /**
     * 게시글 리스트 조회
     *
     * @param   pageable    pageable
     * @return  게시글 리스트
     */
    Page<ResArticleDto> getArticleList(Pageable pageable);

    /**
     * 게시글 Count 조회
     *
     * @return 게시글 count
     */
    JPAQuery<Long> getArticleCount();
}
