package com.ricardo.biz;

import com.ricardo.biz.mapper.GoodsMapper;
import com.ricardo.biz.mapper.entity.Goods;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WebServerApplicationTests {

    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    public void testGoods() {
        Goods goods = new Goods();
        goods.setId(1);
        goods.setName("");
        Example example = new Example(Goods.class);
        Example.Criteria criteria = example.createCriteria();
        if(goods.getId() != null && goods.getId() != 0) {
            criteria.andEqualTo("id", goods.getId());
        }
        if(goods.getName() != null && !"".equals(goods.getName())) {
            criteria.andEqualTo("name", goods.getName());
        }
        log.info("**********************goodsList = {}", goodsMapper.selectByExample(example));
    }

}
