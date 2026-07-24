package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private String message;
    private Object meta;
    private Object errors;

    public ApiResponse() {}

    public ApiResponse(boolean success, T data, String message, Object meta, Object errors) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.meta = meta;
        this.errors = errors;
    }

    public static <T> ApiResponse<T> success(T data, String message, Object meta) {
        return new ApiResponse<>(true, data, message, meta, null);
    }

    public static <T> ApiResponse<T> error(String message, Object errors) {
        return new ApiResponse<>(false, null, message, null, errors);
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Object getMeta() { return meta; }
    public void setMeta(Object meta) { this.meta = meta; }

    public Object getErrors() { return errors; }
    public void setErrors(Object errors) { this.errors = errors; }
}
