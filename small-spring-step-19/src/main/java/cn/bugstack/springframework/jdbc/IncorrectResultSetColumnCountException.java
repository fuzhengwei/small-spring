package cn.bugstack.springframework.jdbc;

/**
 * @author zhangdd on 2022/2/10
 */
public class IncorrectResultSetColumnCountException extends RuntimeException {

    private final int expectedCount;

    private final int actualCount;

    public IncorrectResultSetColumnCountException(int expectedCount, int actualCount) {
        super("Incorrect column count: expected " + expectedCount + ", actual " + actualCount);
        this.expectedCount = expectedCount;
        this.actualCount = actualCount;
    }
}
