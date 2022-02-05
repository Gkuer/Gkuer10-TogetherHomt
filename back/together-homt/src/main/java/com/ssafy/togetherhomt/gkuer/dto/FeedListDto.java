package com.ssafy.togetherhomt.gkuer.dto;

import io.swagger.annotations.ApiParam;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedListDto {

    @ApiParam(value = "피드아이디")
    private Long feed_id;

    @ApiParam(value = "유저이름")
    private String username;

    @ApiParam(value = "피드사진")
    private String media_url;

    @ApiParam(value = "좋아요개수")
    private Long like_cnt;

    @ApiParam(value = "피드내용")
    private String content;

    @ApiParam(value = "태그")
    private List<String> tags;

    @ApiParam(value = "게시날짜")
    private LocalDateTime created_at;
}
