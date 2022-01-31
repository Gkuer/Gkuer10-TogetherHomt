package com.ssafy.togetherhomt.user.info;

import io.swagger.annotations.ApiParam;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @ApiParam(value = "사용자 계정 주소")
    @NotNull
    @Size(max = 50)
    private String email;

    @ApiParam(value = "사용자 계정 별명(nickname)")
    @NotNull
    @Size(min = 3, max = 10)
    private String username;


}
