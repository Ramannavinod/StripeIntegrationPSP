package com.Stripe.exception;

public class StripeExceptionHandling extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String errorCode;
    private String errorMessage;
    private int httpStatusCode;

    public StripeExceptionHandling() {
        super();
    }

    public StripeExceptionHandling(String errorCode, String errorMessage, int httpStatusCode) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatusCode = httpStatusCode;
    }

    public StripeExceptionHandling(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }
}
