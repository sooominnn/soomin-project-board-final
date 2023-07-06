package com.soomin.projectboardfinal.service.serviceinterface;

import com.soomin.projectboardfinal.dto.req.ReqArticleCommentDto;

/**
 * fileName     : ArticleCommentService
 * author       : lia
 * date         : 2023/07/03
 * description  : 게시글 댓글 Service
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/03       lia          최초 생성
 */
public interface ArticleCommentService {

    /**
     * 댓글 생성
     *
     * @param articleId             게시글 고유번호
     * @param reqArticleCommentDto  댓글 정보
     */
    void saveArticleComment(long articleId, ReqArticleCommentDto reqArticleCommentDto);

    /**
     * 댓글 수정
     *
     * @param articleId             게시글 고유번호
     * @param articleCommentId      댓글 고유번호
     * @param reqArticleCommentDto  댓글 정보
     */
    void updateArticleComment(long articleId, long articleCommentId, ReqArticleCommentDto reqArticleCommentDto);

    /**
     * 댓글 삭제
     *
     * @param articleId             게시글 고유번호
     * @param articleCommentId      댓글 고유번호
     */
    void deleteArticleComment(long articleId, long articleCommentId);
}
