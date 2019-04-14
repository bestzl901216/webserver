package com.ricardo.biz;

import com.ricardo.biz.mapper.entity.Goods;
import com.ricardo.biz.mapper.entity.Voucher;
import com.ricardo.utils.DozerUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WebServerApplicationTests {

    @Test
    public void testGoods() {
        Goods goods = new Goods();
        goods.setId(1000);
        goods.setCreateTime(123456);
        Voucher voucher = DozerUtils.map(goods, Voucher.class);

    }

}
