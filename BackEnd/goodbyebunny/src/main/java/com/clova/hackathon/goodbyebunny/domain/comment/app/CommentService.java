package com.clova.hackathon.goodbyebunny.domain.comment.app;

import com.clova.hackathon.goodbyebunny.domain.comment.api.request.CommentCreateRequest;
import com.clova.hackathon.goodbyebunny.domain.comment.api.response.CommentReadResponse;
import com.clova.hackathon.goodbyebunny.domain.comment.dao.CommentRepository;
import com.clova.hackathon.goodbyebunny.domain.comment.model.Comment;
import com.clova.hackathon.goodbyebunny.domain.member.dao.MemberRepository;
import com.clova.hackathon.goodbyebunny.domain.member.model.Member;
import com.clova.hackathon.goodbyebunny.domain.review.dao.ReviewRepository;
import com.clova.hackathon.goodbyebunny.domain.review.model.Review;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final MemberRepository memberRepository;

    private final ReviewRepository reviewRepository;

    public ResponseEntity<Long> createComment(String memberNickname, Long reviewId, CommentCreateRequest request) {

        Member member = memberRepository.findMemberByNickname(memberNickname)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));


        Review review = reviewRepository.findReviewById(reviewId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회고입니다."));

        Comment comment = Comment.builder()
                .member(member)
                .review(review)
                .commentContent(request.getCommentContent())
                .build();
        return ResponseEntity.ok(commentRepository.save(comment).getId());

    }
    public ResponseEntity<List<CommentReadResponse>> getComments(Long reviewId) {


        return ResponseEntity.ok(commentRepository.findCommentsByReviewId(reviewId).stream().map(comment ->
            CommentReadResponse.of(comment.getId(), comment.getCommentContent(), comment.getUpdatedDate(),comment.getMember().getNickname(),comment.getMember().getProfile())
        ).toList());

    }


}
