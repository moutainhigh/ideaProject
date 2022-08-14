package com.zming.cloudnacosstock2002.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface StockMapper {
    @Update("update stock set count = count-1 where product_id = 1")
    public void decr();
}
