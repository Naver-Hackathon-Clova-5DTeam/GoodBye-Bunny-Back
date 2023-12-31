package com.clova.hackathon.goodbyebunny.domain.review.api.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ReviewReadResponse {

    private Long reviewId;
    private String memberNickname;
    private int point;
    private String title;
    private String content;
    private List<String> keywordsList;
    private LocalDateTime updateTime;


    public static ReviewReadResponse of(Long reviewId, String memberNickname, String title
            , String content, LocalDateTime reviewUpdateTime,List<String> keywordsList, int point) {
        return ReviewReadResponse.builder()
                .reviewId(reviewId)
                .memberNickname(memberNickname)
                .point(point)
                .title(title)
                .content(content)
                .keywordsList(keywordsList)
                .updateTime(reviewUpdateTime)
                .build();
    }

}
