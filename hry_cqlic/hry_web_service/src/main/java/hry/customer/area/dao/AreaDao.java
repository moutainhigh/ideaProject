package hry.customer.area.dao;

import hry.area.model.Area;
import hry.core.mvc.dao.base.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;



public interface AreaDao extends  BaseDao<Area, Long> { // 查询省  
    public List<Area> findProvince();  
  
    // 查询市  
    public List<Area> findCity(@Param("provinceId") Long provinceId);
  
    // 查询县区  
    public List<Area> findCounty(@Param("cityId") Long cityId); }
