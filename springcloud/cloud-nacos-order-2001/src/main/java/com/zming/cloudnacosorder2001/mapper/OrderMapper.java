package com.zming.cloudnacosorder2001.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    @Insert("Insert into order_at set product_id='1', context='桌子'")
    public void createOrder();
}
