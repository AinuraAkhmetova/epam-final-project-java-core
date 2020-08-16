package project.common.solutions.utils;

public interface JdbcFunction<FROM, TO> {
    TO apply(FROM from) throws Exception;
}
