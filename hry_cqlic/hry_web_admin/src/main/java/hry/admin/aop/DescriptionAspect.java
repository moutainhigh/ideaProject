package hry.admin.aop;

import hry.bean.PageResult;
import hry.util.BeanUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Copyright (c)</ b> HeC<br/>
 * @createTime 2018/6/26 11:08
 * @Description:科学计数法控制
 */
@Aspect
@Component
public class DescriptionAspect {
    private static final Integer keepDecimal = 6;

    @Pointcut("execution(hry.bean.PageResult hry..*Controller.*(..))")
    private void pointCut(){}

    @Around("pointCut()")
    public PageResult arround(ProceedingJoinPoint point) throws Throwable {
        PageResult result = (PageResult)point.proceed();
        List<Object> rowList = new LinkedList<>();
        for (Object o : result.getRows()) {
            Map beanMap = BeanUtil.obj2Map(o);

            Map<String,Object> rmap = new HashMap<>();
            for (Object key : beanMap.keySet()) {
                Object value = beanMap.get(key);
                if(value instanceof Number && !value.toString().matches("^[0-9]+?$")){
                    BigDecimal number = new BigDecimal(value.toString()).setScale(keepDecimal, BigDecimal.ROUND_DOWN);
                    rmap.put(key.toString(),number.toPlainString());
                }else if(value instanceof Map){
                    Map map = (Map) value;
                    for (Object mp : map.keySet()) {
                        map.put(mp,map.get(mp));
                    }
                    rmap.put(key.toString(),map);
                }else{
                    rmap.put(key.toString(),value==null?null:value);
                }
            }
            rowList.add(rmap);
        }
        result.setRows(rowList);
        return result;
    }
}
