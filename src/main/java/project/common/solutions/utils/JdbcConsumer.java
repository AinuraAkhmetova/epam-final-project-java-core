package project.common.solutions.utils;

public interface JdbcConsumer<T> {
    void accept(T t) throws Exception;
}
