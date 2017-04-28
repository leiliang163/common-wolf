package com.mjoys.common.wolf.utils;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Url工具类
 */
public class UrlUtils {
    private static Joiner joiner = Joiner.on("&").skipNulls();

    /**
     * 解析出url参数中的键值对 如 "action=del&id=123"，解析出action:del,id:123存入map中
     */
    public static Map<String, String> explainURLParams(String params) {
        Map<String, String> mapRequest = new HashMap<String, String>();
        String[] arrSplit = null;
        // 每个键值为一组
        arrSplit = params.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");
            // 解析出键值
            if (arrSplitEqual.length > 1) {
                // 正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
            } else {
                if (arrSplitEqual[0] != "") {
                    // 只有参数没有值，加入空字符
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }

    public static String buildURLParams(Map<String, String> params) {
        if (Objects.equal(null, params) || params.isEmpty()) {
            return "";
        }
        List<String> paramList = Lists.transform(Lists.newArrayList(params.entrySet()), new Function<Entry<String, String>, String>() {
            @Override
            public String apply(Entry<String, String> entry) {
                StringBuffer sb = new StringBuffer();
                sb.append(entry.getKey()).append("=").append(entry.getValue());
                return sb.toString();
            }
        });
        return joiner.join(paramList);
    }

    /**
     * 去掉url中的路径，留下请求参数部分
     *
     * @param strURL url地址
     * @return url请求参数部分
     */
    private static String truncateUrlPage(String strURL) {
        String strAllParam = null;
        String[] arrSplit = null;

        strURL = strURL.trim().toLowerCase();

        arrSplit = strURL.split("[?]");
        if (strURL.length() > 1) {
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    strAllParam = arrSplit[1];
                }
            }
        }

        return strAllParam;
    }

    /**
     * 解析出url参数中的键值对
     * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     *
     * @param URL url地址
     * @return url请求参数部分
     */
    public static Map<String, String> uRLRequest(String URL) {
        Map<String, String> mapRequest = new LinkedHashMap<String, String>();

        String[] arrSplit = null;

        String strUrlParam = truncateUrlPage(URL);
        if (strUrlParam == null) {
            return mapRequest;
        }
        //每个键值为一组 www.2cto.com
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");

            //解析出键值
            if (arrSplitEqual.length > 1) {
                //正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);

            } else {
                if (arrSplitEqual[0] != "") {
                    //只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }

    /**
     * 解析出url请求的路径，包括页面
     *
     * @param strURL url地址
     * @return url路径
     */
    public static String urlPage(String strURL) {
        String strPage = null;
        String[] arrSplit = null;

        strURL = strURL.trim().toLowerCase();

        arrSplit = strURL.split("[?]");
        if (strURL.length() > 0) {
            if (arrSplit.length > 1) {
                if (arrSplit[0] != null) {
                    strPage = arrSplit[0];
                }
            } else {
                return strURL;
            }
        }

        return strPage;
    }
    
    /**
     * appendParams:(url拼参数). <br/>
     * 不会urlencode
     * @param url
     * @param params
     * @return url
     */
    public static String appendParams(String url,Map<String,String> params){
        StringBuffer sb = new StringBuffer(url);
        if(sb.indexOf("?") > 0){
            sb.append("&");
            sb.append(buildURLParams(params));
        }else{
            sb.append("?");
            sb.append(buildURLParams(params));
        }
        return sb.toString();
    }
}
