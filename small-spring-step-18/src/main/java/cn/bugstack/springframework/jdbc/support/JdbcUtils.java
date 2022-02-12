package cn.bugstack.springframework.jdbc.support;

//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;

import cn.bugstack.springframework.util.NumberUtils;
import cn.hutool.core.util.StrUtil;

import java.math.BigDecimal;
import java.sql.*;

/**
 * @author zhangdd on 2022/2/9
 */
public class JdbcUtils {

//    private static final Logger logger = LoggerFactory.getLogger(JdbcUtils.class);

    public static void closeStatement(Statement stmt) {
        if (null != stmt) {
            try {
                stmt.close();
            } catch (SQLException e) {
//                logger.trace("Could not close JDBC statement " + e);
            }
        }
    }

    public static void closeResultSet( ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            }
            catch (SQLException ex) {
//                logger.trace("Could not close JDBC ResultSet", ex);
            }
            catch (Throwable ex) {
                // We don't trust the JDBC driver: It might throw RuntimeException or Error.
//                logger.trace("Unexpected exception on closing JDBC ResultSet", ex);
            }
        }
    }

    public static String lookupColumnName(ResultSetMetaData resultSetMetaData, int columnIndex) throws SQLException {
        String name = resultSetMetaData.getColumnLabel(columnIndex);
        if (StrUtil.isEmpty(name)) {
            name = resultSetMetaData.getColumnName(columnIndex);
        }
        return name;
    }

    public static Object getResultSetValue(ResultSet rs, int index) throws SQLException {
        Object obj = rs.getObject(index);
        String className = null;
        if (null != obj) {
            className = obj.getClass().getName();
        }

        if (obj instanceof Blob) {
            Blob blob = (Blob) obj;
            obj = blob.getBytes(1, (int) blob.length());
        } else if (obj instanceof Clob) {
            Clob clob = (Clob) obj;
            obj = clob.getSubString(1, (int) clob.length());
        } else if ("oracle.sql.TIMESTAMP".equals(className) || "oracle.sql.TIMESTAMPTZ".equals(className)) {
            obj = rs.getTimestamp(index);
        } else if (null != className && className.startsWith("oracle.sql.DATE")) {
            String metadataClassName = rs.getMetaData().getColumnClassName(index);
            if ("java.sql.Timestamp".equals(metadataClassName) || "oracle.sql.TIMESTAMP".equals(metadataClassName)) {
                obj = rs.getTimestamp(index);
            } else {
                obj = rs.getDate(index);
            }
        } else if (obj instanceof Date) {
            if ("java.sql.Timestamp".equals(rs.getMetaData().getColumnClassName(index))) {
                obj = rs.getDate(index);
            }
        }

        return obj;
    }

    public static Object getResultSetValue(ResultSet rs, int index, Class<?> requiredType) throws SQLException {
        if (null == requiredType) {
            return getResultSetValue(rs, index);
        }
        Object value;
        if (String.class == requiredType) {
            return rs.getString(index);
        } else if (boolean.class == requiredType || Boolean.class == requiredType) {
            value = rs.getBoolean(index);
        } else if (byte.class == requiredType || Byte.class == requiredType) {
            value = rs.getByte(index);
        } else if (short.class == requiredType || Short.class == requiredType) {
            value = rs.getShort(index);
        } else if (int.class == requiredType || Integer.class == requiredType) {
            value = rs.getInt(index);
        } else if (long.class == requiredType || Long.class == requiredType) {
            value = rs.getLong(index);
        } else if (float.class == requiredType || Float.class == requiredType) {
            value = rs.getFloat(index);
        } else if (double.class == requiredType || Double.class == requiredType ||
                Number.class == requiredType) {
            value = rs.getDouble(index);
        } else if (BigDecimal.class == requiredType) {
            return rs.getBigDecimal(index);
        } else if (Date.class == requiredType) {
            return rs.getDate(index);
        } else if (Time.class == requiredType) {
            return rs.getTime(index);
        } else if (Timestamp.class == requiredType || java.util.Date.class == requiredType) {
            return rs.getTimestamp(index);
        } else if (byte[].class == requiredType) {
            return rs.getBytes(index);
        } else if (Blob.class == requiredType) {
            return rs.getBlob(index);
        } else if (Clob.class == requiredType) {
            return rs.getClob(index);
        } else if (requiredType.isEnum()) {
            Object obj = rs.getObject(index);
            if (obj instanceof String) {
                return obj;
            } else if (obj instanceof Number) {
                return NumberUtils.convertNumberToTargetClass(((Number) obj), Integer.class);
            } else {
                return rs.getString(index);
            }

        } else {
            return rs.getObject(index, requiredType);
        }

        return (rs.wasNull() ? null : value);
    }
}
