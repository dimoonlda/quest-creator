package ua.kiev.dimoon.questcreator.common.exceptions;

public class QuestCreatorException extends RuntimeException{
    public QuestCreatorException() {
    }

    public QuestCreatorException(String message) {
        super(message);
    }

    public QuestCreatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuestCreatorException(Throwable cause) {
        super(cause);
    }

    public QuestCreatorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
