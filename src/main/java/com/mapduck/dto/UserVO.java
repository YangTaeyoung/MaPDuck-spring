package com.mapduck.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

/**
 * 작성자: 강동연
 * 작성일: 2021.11.3
 * 설명: UserDto
 */


@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class) // camelCase to snake
public class UserVO {

    private Long id;
    private String username;
    private String email;
    private String password;
    private List<String> roles;

    private Boolean isEnable = true;
}
