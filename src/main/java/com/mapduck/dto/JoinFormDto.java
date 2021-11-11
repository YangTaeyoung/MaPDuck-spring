package com.mapduck.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinFormDto {
    private String name;
    private String email;
    private String password;
    private String phone;
}
