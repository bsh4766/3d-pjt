package com.geulgrim.community.share.application.service;

import com.geulgrim.community.share.application.dto.request.ShareCommentUpdateRequest;
import com.geulgrim.community.share.application.dto.request.ShareCommentWriteRequest;
import com.geulgrim.community.share.domain.entity.Share;
import com.geulgrim.community.share.domain.entity.ShareComment;
import com.geulgrim.community.share.domain.repository.ShareCommentRepository;
import com.geulgrim.community.share.domain.repository.ShareRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShareCommentService {

    private final ShareRepository shareRepository;
    private final ShareCommentRepository shareCommentRepository;

    // 작성
    public List<ShareComment> writeComment(ShareCommentWriteRequest shareCommentWriteRequest) {
        Share share = shareRepository.findByShareId(shareCommentWriteRequest.getShareId());
        // 유저 아이디 수정
        long userId = 1;
        ShareComment shareComment = ShareComment.builder()
                .content(shareCommentWriteRequest.getContent())
                .share(share)
                .build();
        shareCommentRepository.save(shareComment);
        return shareCommentRepository.findAllByShareId(shareCommentWriteRequest.getShareId());
    }

    // 조회
    public List<ShareComment> commentList(long shareId) {
        return shareCommentRepository.findAllByShareId(shareId);
    }

    // 수정
    public ShareComment modifyComment(long shareCommentId, ShareCommentUpdateRequest shareCommentUpdateRequest) {
        Share share = shareRepository.findByShareId(shareCommentUpdateRequest.getShareId());

        ShareComment shareComment = ShareComment.builder()
                .shareCommentId(shareCommentId)
                .content(shareCommentUpdateRequest.getContent())
                .share(share)
                .build();
        shareCommentRepository.save(shareComment);
        return shareComment;
    }

    // 삭제
    public void deleteComment(long shareCommentId) {
        shareCommentRepository.deleteById(shareCommentId);
    }
}
