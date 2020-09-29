package org.rg.messenger.model;

import javax.xml.bind.annotation.XmlRootElement;

public class ErrorMessage {

    private int errorCode;
    private String documentation;
    private String errorMessage;

    public ErrorMessage() {
    }

    public ErrorMessage(int errorCode, String documentation, String message) {
        super();
        this.errorCode = errorCode;
        this.documentation = documentation;
        this.errorMessage = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
