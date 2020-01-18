package org.geekbang.time.beautypatterninspring.auth;

public class AuthResult<T> {

    private T data;
    private String message;
    private int code;

    public AuthResult(T data, AuthMessage authMessage) {
        this.data = data;
        this.code = authMessage.getCode();
        this.message = authMessage.getMessage();
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public enum AuthMessage {

        SUCCESS(1, "Success."),
        TOKEN_IS_EXPIRED(1, "Token is expired."),
        TOKEN_VERFICATION_FAILED(2, "Token verfication failed.");

        private final int code;
        private final String message;

        AuthMessage(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

}
