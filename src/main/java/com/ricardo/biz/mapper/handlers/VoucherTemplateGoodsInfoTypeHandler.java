package com.ricardo.biz.mapper.handlers;

import com.google.common.collect.Lists;
import com.ricardo.biz.mapper.entity.VoucherTemplate;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ricardo
 * @date 2018/12/15
 */
public class VoucherTemplateGoodsInfoTypeHandler extends BaseTypeHandler<List<VoucherTemplate.GoodsInfo>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<VoucherTemplate.GoodsInfo> parameter, JdbcType jdbcType) throws SQLException {
        String s = "";
        if (parameter != null) {
            s = parameter.stream().map(e -> e.getGoodsId() + "/" + e.getGoodsQuantity()).collect(Collectors.joining(","));
        }
        ps.setString(i, s);
    }

    @Override
    public List<VoucherTemplate.GoodsInfo> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String s = rs.getString(columnName);
        return this.getGoodsInfoList(s);
    }

    @Override
    public List<VoucherTemplate.GoodsInfo> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String s = rs.getString(columnIndex);
        return this.getGoodsInfoList(s);
    }

    @Override
    public List<VoucherTemplate.GoodsInfo> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String s = cs.getString(columnIndex);
        return this.getGoodsInfoList(s);
    }

    private List<VoucherTemplate.GoodsInfo> getGoodsInfoList(String s) {
        List<VoucherTemplate.GoodsInfo> result = Lists.newArrayList();
        if (StringUtils.isNotBlank(s)) {
            String[] temp = s.split(",");
            for (String e : temp) {
                String[] goodsInfoStr = e.split("/");
                VoucherTemplate.GoodsInfo goodsInfo = VoucherTemplate.GoodsInfo.builder()
                        .goodsId(Integer.valueOf(goodsInfoStr[0]))
                        .goodsQuantity(Integer.valueOf(goodsInfoStr[1]))
                        .build();
                result.add(goodsInfo);
            }
        }
        return result;
    }
}
