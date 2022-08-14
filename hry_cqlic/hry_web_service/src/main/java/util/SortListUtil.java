package util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

public class SortListUtil<E> {
    /**
     * 对jsonArray包含jsonObject进行排序
     *
     * @param array    jsonArray数组
     * @param KEY_NAME 要排序的key
     * @param flag     true是升序 false是降序
     * @return
     */
    public static JSONArray sortJsonArrayTest(JSONArray array, final String KEY_NAME, final boolean flag) {

        JSONArray sortedJsonArray = new JSONArray();
        List<JSONObject> jsonValues = new ArrayList<JSONObject>();
        for (int i = 0; i < array.size(); i++) {
            jsonValues.add(array.getJSONObject(i));
        }
        Collections.sort(jsonValues, new Comparator<JSONObject>() {
            // You can change "Name" with "ID" if you want to sort by ID

            @Override
            public int compare(JSONObject a, JSONObject b) {
                String valA = new String();
                String valB = new String();
                try {
                    // 这里是a、b需要处理的业务，需要根据你的规则进行修改。
                    valA = a.getString(KEY_NAME);
                    valB = b.getString(KEY_NAME);

                  /*  String aStr = a.getString(KEY_NAME);
                    valA = aStr.replaceAll("-", "");
                    String bStr = b.getString(KEY_NAME);
                    valB = bStr.replaceAll("-", "");*/
                } catch (JSONException e) {
                    // do something
                }
                if (isNumber(valA)&&isNumber(valB)) {
                    Double douA = Double.valueOf(valA);
                    Double douB = Double.valueOf(valB);
                    if (flag) {
                        return douA.compareTo(douB);//升序
                    } else {
                        return -douA.compareTo(douB);//降序
                    }
                }
                if (flag) {
                    return valA.compareTo(valB);//升序
                } else {
                    return -valA.compareTo(valB);//降序
                }

            }
        });
        for (int i = 0; i < array.size(); i++) {
            sortedJsonArray.add(jsonValues.get(i));
        }
        return sortedJsonArray;

    }

    public static boolean isNumber(String str) {
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }

    /**
     * @param list   要排序的list
     * @param method 排序的字段 即get方法名 如：age字段 传 getAge
     * @param sort   默认正序 desc倒序
     */
    public void Sort(List<E> list, final String method, final String sort) {
        Collections.sort(list, new Comparator() {
            public int compare(Object a, Object b) {
                int ret = 0;
                try {
                    Method m1 = ((E) a).getClass().getMethod(method, null);
                    Method m2 = ((E) b).getClass().getMethod(method, null);
                    if (sort != null && "desc".equals(sort))// 倒序
                        ret = m2.invoke(((E) b), null).toString().compareTo(m1.invoke(((E) a), null).toString());
                    else// 正序
                        ret = m1.invoke(((E) a), null).toString().compareTo(m2.invoke(((E) b), null).toString());
                    if (isNumber(m1.invoke(((E) a), null).toString())&&isNumber(m2.invoke(((E) b), null).toString())) {
                        Double douA = Double.valueOf(m1.invoke(((E) a), null).toString());
                        Double douB = Double.valueOf(m2.invoke(((E) b), null).toString());
                        if (sort != null && "desc".equals(sort)) {
                            return -douA.compareTo(douB);//降序
                        } else {
                            return douA.compareTo(douB);//升序
                        }
                    }
                } catch (NoSuchMethodException ne) {
                    System.out.println(ne);
                } catch (IllegalAccessException ie) {
                    System.out.println(ie);
                } catch (InvocationTargetException it) {
                    System.out.println(it);
                }
                return ret;
            }
        });
    }

    /**
     * 排序
     *
     * @param list
     * @param index
     * @param sort
     */
    public void SortBigDecimalArray(List<BigDecimal[]> list, final int index, final String sort) {
        Collections.sort(list, new Comparator() {
            public int compare(Object a, Object b) {
                int ret = 0;
                try {
                    BigDecimal[] atest = (BigDecimal[]) a;
                    BigDecimal[] btest = (BigDecimal[]) b;
                    if (sort != null && "desc".equals(sort)) {
                        ret = btest[index].compareTo(atest[index]);
                    } else {
                        ret = atest[index].compareTo(btest[index]);
                    }
                } catch (Exception ne) {
                    System.out.println(ne);
                }
                return ret;
            }
        });
    }

    /**
     * 排序
     *
     * @param list
     * @param method
     * @param sort
     */
    public void SortStringDate(List<E> list, final String method, final String sort) {
        Collections.sort(list, new Comparator() {
            public int compare(Object a, Object b) {
                int ret = 0;
                try {
                    Method m1 = ((E) a).getClass().getMethod(method, null);
                    Method m2 = ((E) b).getClass().getMethod(method, null);
                    if (sort != null && "desc".equals(sort))// 倒序
                    {
                        Date invoke = (Date) m1.invoke(((E) b), null);
                        Date invoke2 = (Date) m1.invoke(((E) a), null);

                        ret = Long.valueOf(invoke.getTime()).compareTo(Long.valueOf(invoke2.getTime()));
                    } else// 正序
                    {
                        Date invoke = (Date) m1.invoke(((E) a), null);
                        Date invoke2 = (Date) m2.invoke(((E) b), null);

                        ret = Long.valueOf(invoke.getTime()).compareTo(Long.valueOf(invoke2.getTime()));

                    }
                } catch (NoSuchMethodException ne) {
                    System.out.println(ne);
                } catch (IllegalAccessException ie) {
                    System.out.println(ie);
                } catch (InvocationTargetException it) {
                    System.out.println(it);
                }
                return ret;
            }
        });
    }
}
