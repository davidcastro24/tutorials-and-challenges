package com.socialapp.javareact.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
public class ApiError {

    public ApiError(int status, String message, String url) {
        this.status = status;
        this.message = message;
        this.url = url;
    }

    private long timestamp = new Date().getTime();
    private int status;
    private String message;
    private String url;
    private Map<String,String> validationErrors;

}
