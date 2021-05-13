package com.springboot.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.core.util.Json;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse implements Serializable {

    private String message;
    private boolean success;
    private String transactionId;

    public BaseResponse() {
        this.transactionId = UUID.randomUUID().toString();
    }

    public BaseResponse(final boolean success){
        this();
        this.success = success;
    }
}
