package com.ricardo.biz.mapper.handlers;

import com.ricardo.utils.MyStringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Set<Integer>转为String
 * 使用逗号作为分割符
 * @author Ricardo
 * @date 2019/3/27
 */
public class IntegerListToStringTypeHandler extends BaseTypeHandler<List<Integer>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Integer> parameter, JdbcType jdbcType) throws SQLException {
        String s = "";
        if (parameter != null) {
            s = parameter.stream().map(e -> e + "").collect(Collectors.joining(","));
        }
        ps.setString(i, s);
    }

    @Override
    public List<Integer> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String s = rs.getString(columnName);
        return this.stringToList(s);
    }

    @Override
    public List<Integer> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String s = rs.getString(columnIndex);
        return this.stringToList(s);
    }

    @Override
    public List<Integer> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String s = cs.getString(columnIndex);
        return this.stringToList(s);
    }

    private List<Integer> stringToList(String s) {
        return MyStringUtils.split(s);
    }
}
