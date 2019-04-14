package com.ricardo.biz.mapper.handlers;

import com.ricardo.biz.mapper.entity.Goods;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ricardo
 * @date 2018/12/15
 */
public class GoodsStatusEnumTypeHandler extends BaseTypeHandler<Goods.StatusEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Goods.StatusEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setByte(i, parameter.getValue());
    }

    @Override
    public Goods.StatusEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        byte value = rs.getByte(columnName);
        return Goods.StatusEnum.MAP.get(value);
    }

    @Override
    public Goods.StatusEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        byte value = rs.getByte(columnIndex);
        return Goods.StatusEnum.MAP.get(value);
    }

    @Override
    public Goods.StatusEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        byte value = cs.getByte(columnIndex);
        return Goods.StatusEnum.MAP.get(value);
    }
}
