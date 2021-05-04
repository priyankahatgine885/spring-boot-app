package com.springboot.demo.exception;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    public ErrorResponse(String message) {
        super();
        this.message = message;
    }
}
