package com.example.javasb_taskms.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Response<T>{

    private final String message;
    private final Integer code;
    private final T data;

    public Response(Builder<T> builder) {
        this.code = builder._code;
        this.message = builder._message;
        this.data = builder._data;
    }

    @Data
    @NoArgsConstructor
    public static class Builder<T> {

        private String _message;
        private int _code;
        private T _data;

        public Builder<T> message(String message) {
            this._message = message;
            return this;
        }

        public Builder<T> code(int code) {
            this._code = code;
            return this;
        }

        public Builder<T> data(T data) {
            this._data = data;
            return this;
        }

        public Response<T> build() {
            return new Response<T>(this);
        }

    }

}
