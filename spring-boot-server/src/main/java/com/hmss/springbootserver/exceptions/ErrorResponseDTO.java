package com.hmss.springbootserver.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponseDTO {
    private String error;
    private int status;
    private HttpStatus statusText;
    private LocalDateTime timestamp;
    private String path;

    public ErrorResponseDTO(String error, int status, HttpStatus statusText, LocalDateTime timestamp, String path) {
        this.error = error;
        this.status = status;
        this.statusText = statusText;
        this.timestamp = timestamp;
        this.path = path;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public HttpStatus getStatusText() {
        return statusText;
    }

    public void setStatusText(HttpStatus statusText) {
        this.statusText = statusText;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
