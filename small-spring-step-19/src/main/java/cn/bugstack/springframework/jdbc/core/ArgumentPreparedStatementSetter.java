package cn.bugstack.springframework.jdbc.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author zhangdd on 2022/2/12
 */
public class ArgumentPreparedStatementSetter implements PreparedStatementSetter {

    private final Object[] args;

    public ArgumentPreparedStatementSetter(Object[] args) {
        this.args = args;
    }

    @Override
    public void setValues(PreparedStatement ps) throws SQLException {
        if (null != args) {
            for (int i = 1; i <= args.length; i++) {
                ps.setObject(i, args[i - 1]);
            }
        }
    }
}
