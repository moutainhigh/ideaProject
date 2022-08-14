package hry.exchange.lend.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.exchange.lend.model.ExDmPing;

import java.util.List;
import java.util.Map;

public interface ExDmLendPingDao extends BaseDao<ExDmPing,Long> {

    public List<ExDmPing> see(Map<String, String> map);
}
