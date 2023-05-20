package com.javaexample.springmongodb.exception;

public class SpringMongodbException extends RuntimeException {
    private final Type type;
    public SpringMongodbException(final Type type, Throwable t) {
        super(t);
        this.type = type;
    }
    public SpringMongodbException(final Type type) {
        this(type, null);
    }

    public enum Type {
        TASK_IS_NOT_FOUND("task.is.not.found"),
        TASK_ERROR_SAVE("task.error.save"),
        TASK_ERROR_UPDATE("task.error.update"),
        TASK_ERROR_SEARCH("task.error.search"),
        TASK_ERROR_SEARCH_SEVERITY("task.error.search.severity"),
        TASK_ERROR_ASSIGNEE("task.error.search.assignee"),
        TASK_ERROR_DELETE("task.error.delete");
        private final String prefixKey;
        Type(final String prefixKey) {
            this.prefixKey = prefixKey;
        }
        public String getCode() {
            return prefixKey + ".code";
        }
        public String getDescription() {
            return prefixKey + ".description";
        }
    }
    public Type getType() {
        return type;
    }
}
