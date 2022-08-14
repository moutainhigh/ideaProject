/**
 * Copyright:
 *
 * @author: kongdebiao
 * @version: V1.0
 * @Date: 2018-11-16 10:44:42
 */
package hry.mall.goods.service.impl;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.goods.dao.GoodsDao;
import hry.mall.goods.dao.GoodsSpecDao;
import hry.mall.goods.dao.SpecDao;
import hry.mall.goods.dao.SpecValueDao;
import hry.mall.goods.model.*;

import hry.mall.goods.model.vo.FeixiaohaoPriceVo;
import hry.mall.goods.service.GoodsService;
import hry.mall.goods.service.GoodsSpecService;
import hry.redis.common.utils.RedisService;
import hry.util.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

/**
 * <p> GoodsService </p>
 *
 * @author: kongdebiao
 * @Date :          2018-11-16 10:44:42
 */
@Service("goodsService")
public class GoodsServiceImpl extends BaseServiceImpl<Goods,Long> implements GoodsService {

    @Resource
    private SpecDao specDao;

    @Resource
    private GoodsSpecDao goodsSpecDao;

    @Resource
    private SpecValueDao specValueDao;

    @Resource
    private GoodsSpecService goodsSpecService;

    @Resource
    private GoodsDao goodsDao;

    @Resource(name = "goodsDao")
    @Override
    public void setDao(BaseDao<Goods,Long> dao) {
        super.dao = dao;
    }

    /**
     * 分页查询商品集合
     * goodsName 商品名称
     * goodsClass 商品分类
     * goodsBrand 商品品牌
     * menuType 菜单类型 --- 1：已上架 2：已下架 3：未上架（放入仓库） 4：待审核 5：审核未通过 6：库存告急 7：草稿箱
     * 注：菜单类型与数据库中字段无关，只与前台菜单有关。
     */
    @Override
    public PageResult findPageBySql(QueryFilter filter) {
        //----------------------分页查询头部外壳------------------------------
        //创建PageResult对象
        Page<Goods> page = PageFactory.getPage(filter);
        //参数集合
        Map<String,Object> map = new HashMap<String,Object>();

        //----------------------查询开始------------------------------
        String goodsName = filter.getRequest().getParameter("goodsName");
        String goodsClass = filter.getRequest().getParameter("goodsClass");
        String goodsBrand = filter.getRequest().getParameter("goodsBrand");
        String menuType = filter.getRequest().getParameter("menuType");

        if (!StringUtils.isEmpty(goodsName)) {
            map.put("goodsName", goodsName + "%");
        }
        if (!StringUtils.isEmpty(goodsClass)) {
            map.put("goodsClass", goodsClass);
        }
        if (!StringUtils.isEmpty(goodsBrand)) {
            map.put("goodsBrand", goodsBrand);
        }
        //菜单类别
        if (!StringUtils.isEmpty(menuType)) {
            switch (menuType) {
                case "1":
                    map.put("goodsShow", "1");
                    break;
                case "2":
                    map.put("goodsShow", "2");
                    break;
                case "3":
                    map.put("isAudit", "0");
                    map.put("goodsShow", "3");
                    break;
                case "4":
                    map.put("isAudit", "1");
                    break;
                case "5":
                    map.put("isAudit", "3");
                    break;
                case "6":
                    map.put("goodsShow", "1");
                    map.put("warning", "warning");
                    break;
                case "7":
                    map.put("goodsIsComplete", "0");
                    break;
            }
        }
        ((GoodsDao) dao).findPageBySql(map);
        return new PageResult(page, filter.getPage(), filter.getPageSize());
    }

    @Override
    public List<SearchGoods> findSearchGoodsList() {
        List<SearchGoods> searchGoodsList = ((GoodsDao) dao).findSearchGoodsList(new HashMap<>());
        return searchGoodsList;
    }

    @Override
    public SearchGoods findSearchGoodsById(Long goodsId) {
        SearchGoods searchGoods = null;
        Map<String,Object> map = new HashMap<>();
        map.put("goodsId", goodsId);
        List<SearchGoods> searchGoodsList = ((GoodsDao) dao).findSearchGoodsList(map);
        if (searchGoodsList != null && searchGoodsList.size() > 0) {
            searchGoods = searchGoodsList.get(0);
        }
        return searchGoods;
    }

    @Override
    public Goods findGoodsById(Long goodsId) {
        //参数集合
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("goodsId", goodsId);
        List<Goods> goodsList = ((GoodsDao) dao).findPageBySql(map);
        Goods goods = null;
        if (goodsList != null && goodsList.size() > 0) {
            goods = goodsList.get(0);
        }
        return goods;
    }

    @Override
    public FrontPage findGoodsList(Map<String,String> params) {
        Page<Goods> page = PageFactory.getPage(params);
        Map<String,Object> selectParams = new HashMap<>();
        if (!StringUtils.isEmpty(params.get("goodsName"))) {
            selectParams.put("goodsName", params.get("goodsName") + "%");
        }
        if (!StringUtils.isEmpty(params.get("goodsClass"))) {
            selectParams.put("goodsClass", params.get("goodsClass"));
        }
        if (!StringUtils.isEmpty(params.get("goodsBrand"))) {
            selectParams.put("goodsBrand", params.get("goodsBrand"));
        }
        if (!StringUtils.isEmpty(params.get("goodsCommend"))) {
            selectParams.put("goodsCommend", params.get("goodsCommend"));
        }
        if (!StringUtils.isEmpty(params.get("goodsShow"))) {
            selectParams.put("goodsShow", params.get("goodsShow"));
        }
        List<Goods> goodsList = goodsDao.findPageBySql(selectParams);
        return new FrontPage(goodsList, page.getTotal(), page.getPages(), page.getPageSize());
    }

    @Override
    public BigDecimal getCoinRate(String coinCode) {
        // 获取 平台币
        String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");
        // 获取 平台币市价(RMB)
        String platformMarkPriceStr = BaseConfUtil.getConfigSingle("platformMarkPrice", "configCache:basefinanceConfig");
        BigDecimal platformMarkPrice = new BigDecimal(platformMarkPriceStr);
        BigDecimal codePrice = BigDecimal.ZERO;
        // 如果是平台币，取平台币市价(RMB)
        if (coinCode.equals(platCoin)) {
            codePrice = platformMarkPrice;
        } else {
            // 如果是其它币，取非小号
            RedisService redisService = SpringUtil.getBean("redisService");
            String result = redisService.get("cn:newFeixiaohaoPrice");
            if (StringUtil.isNull(result)) {
                List<FeixiaohaoPriceVo> list = JSON.parseArray(result, FeixiaohaoPriceVo.class);
                for (FeixiaohaoPriceVo feixiaohaoPriceVo : list) {
                    String name = feixiaohaoPriceVo.getName();
                    // 如果是自定义币，取非小号的Price为空会报错，做个判断
                    if (name.equals(coinCode) && feixiaohaoPriceVo.getPrice() != null) {
                        codePrice = new BigDecimal(feixiaohaoPriceVo.getPrice());
                        return codePrice;
                    }
                }
            } else {
                System.out.println("未查询到redis中" + coinCode + "行情信息");
                return codePrice;
            }

        }
        return codePrice;
    }

    @Override
    public JsonResult addGoodsSpec(Map<String,Object> paramMap) {
        //参数校验
        if (StringUtils.isEmpty(paramMap.get("gcId")))
            return new JsonResult().setSuccess(false).setMsg("请选择分类").setCode("1");
        if (StringUtils.isEmpty(paramMap.get("specName")))
            return new JsonResult().setSuccess(false).setMsg("规格名称不能为空").setCode("2");
        if (StringUtils.isEmpty(paramMap.get("specValue")))
            return new JsonResult().setSuccess(false).setMsg("规格值不能为空").setCode("3");

        //保存规格
        Spec spec = new Spec();
        spec.setGcId(Long.parseLong(paramMap.get("gcId").toString()));
        spec.setSpName(paramMap.get("specName").toString());
        specDao.insertSelective(spec);

        //保存类型
        SpecValue value = new SpecValue();
        value.setSpId(spec.getId());
        value.setSpValueName(paramMap.get("specValue").toString());
        specValueDao.insertSelective(value);

        //返回规格、规格值
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("spec", spec);
        resultMap.put("specValue", value);
        return new JsonResult().setSuccess(true).setObj(resultMap).setCode("8888");
    }

    @Override
    public JsonResult addGoodsSpecValue(Map<String,Object> paramMap) {
        //参数校验
        if (StringUtils.isEmpty(paramMap.get("specId")))
            return new JsonResult().setSuccess(false).setMsg("规格Id为空").setCode("1");
        if (StringUtils.isEmpty(paramMap.get("specValue")))
            return new JsonResult().setSuccess(false).setMsg("规格值不能为空").setCode("3");
        SpecValue value = new SpecValue();
        value.setSpId(Long.parseLong(paramMap.get("specId").toString()));
        value.setSpValueName(paramMap.get("specValue").toString());
        specValueDao.insertSelective(value);
        return new JsonResult().setSuccess(true).setObj(value).setCode("8888");
    }

    @Override
    public JsonResult delGoodsSpec(Map<String,Object> paramMap) {
        //参数校验
        if (StringUtils.isEmpty(paramMap.get("specId")))
            return new JsonResult().setSuccess(false).setMsg("规格Id为空").setCode("1");
        //查询规格下是否有规格值
        List<SpecValue> specValues = specValueDao.findSpecValueList(paramMap);
        if (specValues != null && specValues.size() > 0)
            return new JsonResult().setSuccess(false).setMsg("规格下有规格值").setCode("2");

        Spec spec = new Spec();
        spec.setId(Long.parseLong(paramMap.get("specId").toString()));
        specDao.delete(spec);

        return new JsonResult().setSuccess(true).setMsg("规格删除成功").setCode("8888");
    }

    @Override
    public JsonResult delGoodsSpecValue(Map<String,Object> paramMap) {
        //参数校验
        if (StringUtils.isEmpty(paramMap.get("specValueId")))
            return new JsonResult().setSuccess(false).setMsg("规格值Id为空").setCode("1");

        SpecValue value = new SpecValue();
        value.setId(Long.parseLong(paramMap.get("specValueId").toString()));
        specValueDao.delete(value);

        return new JsonResult().setSuccess(true).setMsg("规格值删除成功").setCode("8888");
    }

    @Override
    public JsonResult saveGoods(Goods goods, Map<String,Object> paramMap) {
        if ("1".equals(paramMap.get("guige").toString())) { //保存统一规格
            //保存商品
            super.update(goods);
            String specGoodsSerial = paramMap.get("specGoodsSerial").toString();
            String specGoodsPrice = paramMap.get("specGoodsPrice").toString();
            String specGoodsStorage = paramMap.get("specGoodsStorage").toString();
            String specStorageWarning = paramMap.get("specStorageWarning").toString();
            GoodsSpec goodsSpec = new GoodsSpec();
            goodsSpec.setGoodsId(goods.getId());
            goodsSpec.setSpecGoodsSerial(specGoodsSerial);
            goodsSpec.setSpecGoodsPrice(new BigDecimal(specGoodsPrice));
            goodsSpec.setSpecGoodsStorage(Integer.parseInt(specGoodsStorage));
            goodsSpec.setSpecStorageWarning(Integer.parseInt(specStorageWarning));
            //保存规格
            saveToGoodsSpecOne(goods, goodsSpec);
        } else if ("2".equals(paramMap.get("guige").toString())) {//保存多规格
            String specValueList = paramMap.get("specValueList").toString();
            //保存商品
            super.update(goods);
            //保存goodsSpec
            saveToGoodsSpec(goods, specValueList);
        }
        return new JsonResult().setSuccess(true).setMsg("保存成功");
    }

    @Override
    public JsonResult saveGoodsThree(Goods goods, Map<String,Object> paramMap) {
        goods.setGoodsIsComplete(1);//商品已完成
        //sjtime: 1 立即上架（进入待审核） 2：放入仓库未上架中
        if ("1".equals(paramMap.get("sjtime").toString())) {
            goods.setIsAudit(1);
        } else {
            goods.setGoodsShow(3);
        }
        //保存商品
        super.update(goods);
        //更新规格
        saveSpec(goods, paramMap.get("otherSpecValueList").toString());

        return new JsonResult().setSuccess(true).setMsg("保存成功");
    }

    @Override
    public JsonResult saveGoodsSpec(Goods goods, String goodsSpecList) {
        String[] specList = goodsSpecList.split(",");
        for (String spec : specList) {
            String[] value = spec.split("_");
            GoodsSpec goodsSpec = goodsSpecService.get(Long.parseLong(value[0]));
            goodsSpec.setSpecGoodsStorage(Integer.parseInt(value[1]));
            goodsSpec.setSpecStorageWarning(Integer.parseInt(value[2]));
            goodsSpecService.update(goodsSpec);
        }
        return new JsonResult().setSuccess(true);
    }

    //统一规格保存
    private void saveToGoodsSpecOne(Goods goods, GoodsSpec goodsSpec) {
        //根据商品id查询goodsSpec
        QueryFilter filter = new QueryFilter(GoodsSpec.class);
        filter.addFilter("goodsId=", goods.getId());
        List<GoodsSpec> goodsSpecList = goodsSpecService.find(filter);
        if (goodsSpecList != null && goodsSpecList.size() > 1) {
            //删除商品下所有规格商品
            goodsSpecService.delete(filter);
            goodsSpecService.save(goodsSpec);
        } else if (goodsSpecList != null && goodsSpecList.size() == 1) {
            GoodsSpec goodsSpec1 = goodsSpecList.get(0);
            if ("".equals(goodsSpec1.getSpecName())) {
                goodsSpec.setId(goodsSpec1.getId());
                goodsSpecService.update(goodsSpec);
            } else {
                //删除商品下所有规格商品
                goodsSpecService.delete(filter);
                goodsSpecService.save(goodsSpec);
            }

        } else {
            goodsSpecService.save(goodsSpec);
        }
    }

    //多规格保存
    private void saveToGoodsSpec(Goods goods, String goodsSpecJson) {
        //根据商品id查询goodsSpec
        QueryFilter filter = new QueryFilter(GoodsSpec.class);
        filter.addFilter("goodsId=", goods.getId());
        List<GoodsSpec> goodsSpecList = goodsSpecService.find(filter);
        String[] valueList = goodsSpecJson.split("&");
        //存在规格商品修改 不存在添加
        if (goodsSpecList != null && goodsSpecList.size() > 0) {
            //删除数据库无意义商品
            for (GoodsSpec goodsSpec : goodsSpecList) {
                int i = 0;
                for (String v : valueList) {
                    String[] value = v.split("_");
                    //判断规格商品是否存在
                    if (value[0].equals(goodsSpec.getSpecName()) && value[1].equals(goodsSpec.getSpecNameValue())) {
                        i++;
                    }
                }
                if (i == 0) {
                    //删除以不存在规格
                    goodsSpecService.delete(goodsSpec.getId());
                }
            }
            //添加不存在商品
            for (String v : valueList) {
                String[] value = v.split("_");
                int i = 0;
                for (GoodsSpec goodsSpec : goodsSpecList) {
                    //判断规格商品是否存在
                    if (value[0].equals(goodsSpec.getSpecName()) && value[1].equals(goodsSpec.getSpecNameValue())) {
                        i++;
                    }
                }
                if (i == 0) {
                    GoodsSpec goodsSpec = new GoodsSpec();
                    goodsSpec.setGoodsId(goods.getId());
                    goodsSpec.setSpecName(value[0]);
                    goodsSpec.setSpecNameValue(value[1]);
                    ((GoodsSpecDao) goodsSpecDao).insertSelective(goodsSpec);
                }
            }
        } else {
            for (String v : valueList) {
                String[] value = v.split("_");
                GoodsSpec goodsSpec = new GoodsSpec();
                goodsSpec.setGoodsId(goods.getId());
                goodsSpec.setSpecName(value[0]);
                goodsSpec.setSpecNameValue(value[1]);
                ((GoodsSpecDao) goodsSpecDao).insertSelective(goodsSpec);
            }
        }
    }

    private void saveSpec(Goods goods, String specJson) {
        String[] specList = specJson.split(",");
        for (String spec : specList) {
            String[] value = spec.split("_");
            GoodsSpec goodsSpec = goodsSpecService.get(Long.parseLong(value[0]));
            goodsSpec.setSpecGoodsSerial(value[1]);
            goodsSpec.setSpecGoodsPrice(new BigDecimal(value[2]));
            goodsSpec.setSpecGoodsStorage(Integer.parseInt(value[3]));
            goodsSpec.setSpecStorageWarning(Integer.parseInt(value[4]));
            goodsSpec.setSpecIsOpen(Integer.parseInt(value[5]));
            goodsSpecService.update(goodsSpec);
        }
    }

    @Override
    public List<Goods> findIntegralGoodsList(Map<String,Object> params) {
        // TODO Auto-generated method stub
        List<Goods> list = ((GoodsDao) dao).findIntegralGoods(params);
        return list;
    }

    @Override
    public List<SearchGoods> findActivityGoodsList(Map<String,Object> params) {
        List<SearchGoods> list = ((GoodsDao) dao).findActivityGoodsList(params);
        return list;
    }

    @Override
    public List<SearchGoods> findActivityGoodsAllList(Map<String,Object> params) {
        List<SearchGoods> list = ((GoodsDao) dao).findActivityGoodsAllList(params);
        return list;
    }
}
