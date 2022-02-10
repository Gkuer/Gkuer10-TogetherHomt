package com.ssafy.togetherhomt.feed;

import io.swagger.annotations.ApiParam;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedDto {

    private Long id;

    @ApiParam(value = "피드 제목")
    @NotNull
    private String title;

    @ApiParam(value = "피드 내용")
    @NotNull
    private String content;

    @ApiParam(value = "태그")
    private List<String> tags;

    @ApiParam(value = "게시 날짜시간")
    private LocalDateTime created_at;

    @ApiParam(value = "수정 날짜시간")
    private LocalDateTime updated_at;

    @ApiParam(value = "피드 첨부파일")
    private MultipartFile media;

    @ApiParam(value = "피드 첨부파일 저장 경로")
    private String media_url;

    @ApiParam(value = "피드 좋아요 수")
    private Long like_cnt;

    @ApiParam(value = "피드 작성자")
    private String userName;

    @ApiParam(value = "좋아요 여부")
    private Boolean like_status;
}
