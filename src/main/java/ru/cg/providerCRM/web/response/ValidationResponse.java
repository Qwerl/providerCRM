package ru.cg.providerCRM.web.response;

import java.util.List;

public class ValidationResponse extends Response {

    private List<ErrorMessage> errorMessageList;

    public List<ErrorMessage> getErrorMessageList() {
        return this.errorMessageList;
    }

    public void setErrorMessageList(List<ErrorMessage> errorMessageList) {
        this.errorMessageList = errorMessageList;
    }

}
