package org.triathlongirls.doranssam.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@JsonPropertyOrder({"status", "results"})
public class ApiResponse<T> implements Serializable {
    private static ResponseStatus OK = new ResponseStatus(2000, "OK");

    @Getter
    private ResponseStatus status;

    @Getter
    private List<T> results;

    public ApiResponse<T> ok() {
        this.status = OK;
        return this;
    }

    public ApiResponse<T> ok(List<T> results) {
        this.status = OK;
        this.results = results;
        return this;
    }

    public ApiResponse<T> results(List<T> results) {
        this.results = results;
        return this;
    }

    public ApiResponse<T> fail(Integer code, String message) {
        this.status = new ResponseStatus(code, message);
        return this;
    }

    public ApiResponse<T> fail(Integer code) {
        this.status = new ResponseStatus(code, "");
        return this;
    }

    public ApiResponse<T> fail() {
        this.status = new ResponseStatus(404, "Unknown Error");
        return this;
    }

    public ApiResponse() {
    }

    public ApiResponse(int code, String message) {
        this.status = new ResponseStatus(code, message);
    }


    @NoArgsConstructor
    public static class ResponseStatus implements Serializable {
        @Getter
        private int code;

        @Getter
        private String message;

        public ResponseStatus(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }

}