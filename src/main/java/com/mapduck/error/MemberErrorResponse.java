package com.mapduck.error;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberErrorResponse {
    private int StatusCode;
    private String message;
    private long timestamp;
}
