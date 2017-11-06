package ua.kiev.dimoon.questcreator.rest.frontend.service;

public interface NotificationService {
    void addInfoMessage(String msg);
    void addInfoMessageFromCode(String msgCode);
    void addErrorMessage(String msg);
    void addErrorMessageFromCode(String msgCode);
}
