package com.wondersgroup.utils.file;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import wfc.service.database.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author Q Z L
 * @Date 2019/9/16 14:35
 * @Class com.wondersgroup.util.CommonUtils
 * Description
 **/
public class CommonUtils {
    private static CloseableHttpClient httpClient = HttpClientBuilder.create().build();




    //从数据库拉取附件
    public static void main(String[] args) {

        Conditions conditions = Conditions.newAndConditions();
        conditions.add(new Condition("ST_ATTACH_ID", Condition.OT_EQUAL, "c984042e-428b-48f0-aadb-7d2670235e83"));
        byte[] attach = BlobHelper.getBlob("AC_UAPPLY_ATTACH", "BL_CONTENT",
                conditions.toString(), conditions.getObjectArray());

        CommonUtils.byte2File(attach, "C:\\Users\\lifan\\Desktop\\", 1 + ".pdf");


    }



    /**
     * 将request流中的数据转换成String
     *
     * @param request
     * @return
     */
    public static String getParam(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap != null && parameterMap.size() > 0) {
            return paramMapToString(parameterMap);
        }
        String valueStr = "";
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "";
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            valueStr = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            valueStr = "";
        }
        System.out.println(defaultTag().getString("currentSystemOutPrint") + "===-------------------==---入参---======" + valueStr + "--------------------_______________________---------------------------------------");
        return valueStr;
    }

    /**
     * @Description 解析接口中的参数，返回json
     * @参数 permissionEmptyParam是否允许空参数
     * @返回值
     * @Authur Q Z L
     * @创建时间 2020/4/29 14:27
     * @修改人和其它信息
     * @ 2020/4/29
     */
    public static JSONObject getParamJson(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap != null && parameterMap.size() > 0) {
            return paramMapToJson(parameterMap);
        }
        String valueStr = "";
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "";
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            valueStr = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            valueStr = "";
        }
        if (StringUtils.isBlank(valueStr)) {
            return json;
        }
        json = JSONObject.parseObject(valueStr);
        System.out.println(defaultTag().getString("currentSystemOutPrint") + "===-------------------==---入参---======" + json + "--------------------_______________________---------------------------------------");
        return json;
    }

    private static String paramMapToString(Map<String, String[]> map) {
        Set<Map.Entry<String, String[]>> entries = map.entrySet();
        StringBuilder buf = new StringBuilder();
        for (Map.Entry entry : entries) {
            buf.append("\"" + entry.getKey() + "\"");
            Object o = entry.getValue();
            if (o == null) {

            } else if (o instanceof String[]) {
                String[] s = (String[]) o;
                if (s.length == 0) {

                } else {

                    for (String ss : s) {
                        buf.append(":").append("\"" + ss + "\"");
                    }
                }
            } else {

            }
            buf.append(",");
        }
        buf.delete(buf.length() - 1, buf.length());
        String result = "{" + buf.toString() + "}";
        System.out.println(defaultTag().getString("currentSystemOutPrint") + "===================---------入参------------==========" + result);
        return result;
    }

    private static JSONObject paramMapToJson(Map<String, String[]> map) {
        JSONObject json = new JSONObject();
        if (map == null || map.isEmpty()) {
            return json;
        }

        Set<Map.Entry<String, String[]>> entries = map.entrySet();
        for (Map.Entry entry : entries) {
            Object o = entry.getValue();
            if (o == null) {

            } else if (o instanceof String[]) {
                String[] s = (String[]) o;
                if (s.length == 0) {

                } else {
                    String value = "";
                    for (String ss : s) {
                        value += ss;
                    }
                    json.put("" + entry.getKey(), value);
                }
            } else {

            }
        }

        System.out.println(defaultTag().getString("currentSystemOutPrint") + "===================---------入参------------==========" + json);
        return json;
    }

    /**
     * @Description 请求返回json数据
     * @参数
     * @返回值
     * @Authur Q Z L
     * @创建时间 2019/10/10 17:22
     * @修改人和其它信息
     * @ 2019/10/10
     */
    public static void resJson(HttpServletResponse res, JSONObject json) {
        JSONObject jsonObject = CommonUtils.defaultTag();
        System.out.println(jsonObject.getString("currentSystemOutPrint") + "=---------------------===返回json串内容=------------------" + json + "------------------------------------------------------------");
        res.setContentType("application/json; charset=UTF-8");
        try {
            res.getWriter().print(json);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * @Description 请求返回字符串数据
     * @参数
     * @返回值
     * @Authur Q Z L
     * @创建时间 2019/10/10 17:22
     * @修改人和其它信息
     * @ 2019/10/10
     */
    public static void resStr(HttpServletResponse res, String resStr) {
        JSONObject jsonObject = CommonUtils.defaultTag();
        System.out.println(jsonObject.getString("currentSystemOutPrint") + "=---------------------===返回json串内容=------------------" + resStr + "------------------------------------------------------------");
        res.setContentType("application/json; charset=UTF-8");
        try {
            res.getWriter().print(resStr);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * @Description 访问外部接口方法
     * @参数
     * @返回值
     * @Authur Q Z L
     * @创建时间 2019/10/10 17:22
     * @修改人和其它信息
     * @ 2019/10/10
     */
    public static String apiResData(String url, JSONObject jsonObject) {
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(180 * 1000)
                .setConnectionRequestTimeout(180 * 1000).setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type", "application/json");
        try {
            httpPost.setEntity(new StringEntity(jsonObject.toString(), ContentType.create("application/json", "utf-8")));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            String str = EntityUtils.toString(response.getEntity(), "UTF-8");
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return "post failure :caused by-->" + e.getMessage().toString();
        } finally {
            httpPost.releaseConnection(); // 释放连接
        }
    }

    /**
     * @Description 访问外部接口方法
     * @参数
     * @返回值
     * @Authur Q Z L
     * @创建时间 2019/10/10 17:22
     * @修改人和其它信息
     * @ 2019/10/10
     */
    public static String apiResData(String url, Map<String, Object> map) {
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(180 * 1000)
                .setConnectionRequestTimeout(180 * 1000).setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type", "application/json");
        String string = JSON.toJSONString(map);
        try {
            System.out.println(string);
            httpPost.setEntity(new StringEntity(string, ContentType.create("application/json", "utf-8")));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            String str = EntityUtils.toString(response.getEntity(), "UTF-8");
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return "post failure :caused by-->" + e.getMessage().toString();
        } finally {
            httpPost.releaseConnection(); // 释放连接
        }
    }

    /**
     * @Description 访问外部接口方法
     * @参数
     * @返回值
     * @Authur Q Z L
     * @创建时间 2019/10/10 17:22
     * @修改人和其它信息
     * @ 2019/10/10
     */
    public static String apiResData(String url, String jsonStr) {
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(180 * 1000)
                .setConnectionRequestTimeout(180 * 1000).setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type", "application/json");
        try {
            httpPost.setEntity(new StringEntity(jsonStr, ContentType.create("application/json", "utf-8")));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            String str = EntityUtils.toString(response.getEntity(), "UTF-8");
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return "post failure :caused by-->" + e.getMessage().toString();
        } finally {
            httpPost.releaseConnection(); // 释放连接
        }
    }

    /**
     * @Description 访问外部接口方法
     * @参数
     * @返回值
     * @Authur Q Z L
     * @创建时间 2019/10/10 17:22
     * @修改人和其它信息
     * @ 2019/10/10
     */
    public static String apiResDataGet(String url, String jsonStr) {
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(180 * 1000)
                .setConnectionRequestTimeout(180 * 1000).setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();
        httpGet.setConfig(requestConfig);
        httpGet.setHeader("Content-Type", "application/json");
        try {
//            httpGet.setEntity(new StringEntity(jsonStr, ContentType.create("application/json", "utf-8")));
            CloseableHttpResponse response = httpClient.execute(httpGet);
            String str = EntityUtils.toString(response.getEntity(), "UTF-8");
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return "post failure :caused by-->" + e.getMessage().toString();
        } finally {
            httpGet.releaseConnection(); // 释放连接
        }
    }

    /**
     * @Description 获取调用方法的相关信息
     * @参数
     * @返回值
     * @Authur Q Z L
     * @创建时间 2019/10/21 14:38
     * @修改人和其它信息
     * @ 2019/10/21
     */
    public static JSONObject defaultTag() {
        JSONObject json = new JSONObject();
        try {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            StackTraceElement log = stackTrace[1];
            for (int i = 1; i < stackTrace.length; i++) {
                StackTraceElement e = stackTrace[i];
                if (!e.getClassName().equals(log.getClassName())) {
                    json.put("currentClass", e.getClassName());
                    json.put("currentMethod", e.getMethodName());
                    break;
                }
            }
            for (StackTraceElement e : stackTrace) {
                if (json != null && json.getString("currentClass").equals(e.getClassName()) && json.getString("currentMethod").equals(e.getMethodName())) {
                    json.put("currentLineNum", e.getLineNumber());
                }
            }
            json.put("currentSystemOutPrint", "  [" + json.getString("currentClass") + "." + json.getString("currentMethod") + "," + json.getString("currentLineNum") + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }

//        System.out.print
//        ln(json);
        return json;
    }

    public static String getSystemOutPrint() {
        return defaultTag().getString("currentSystemOutPrint") + " ";
    }

    public static JSONObject returnEceptionJson() {
        JSONObject json = new JSONObject();
        json.put("resultCode", "9999");
        json.put("resultMessage", "网络或系统异常，请稍后重试");
        return json;
    }

    public static JSONObject returnEceptionJson(JSONObject json) {
        if (json == null) {
            json = new JSONObject();
        }
        json.put("resultCode", "9999");
        if (StringUtils.isEmpty(json.getString("resultMessage"))) {
            json.put("resultMessage", "网络或系统异常，请稍后重试");
        }
        return json;
    }

    public static JSONObject returnEceptionJson(String exceptionMessage) {
        if (StringUtils.isBlank(exceptionMessage)) {
            return returnEceptionJson();
        }
        JSONObject json = new JSONObject();
        json.put("resultCode", "9999");
        if (StringUtils.isEmpty(exceptionMessage)) {
            json.put("resultMessage", "网络或系统异常，请稍后重试");
        } else {
            json.put("resultMessage",exceptionMessage);
        }
        return json;
    }

    public static List<Map<String, Object>> getRedisListData(String redisData) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (StringUtils.isBlank(redisData)) {
            return null;
        }
        JSONArray parse = (JSONArray) JSONObject.parse(redisData);
        Iterator<Object> iterator = parse.iterator();
        while (iterator.hasNext()) {
            JSONObject next = (JSONObject) iterator.next();
            Map<String, Object> map = (Map<String, Object>) next;
            list.add(map);
        }
        return list;
    }

    public static Map<String, Object> getRedisMapData(String redisData) {
        if (StringUtils.isBlank(redisData)) {
            return null;
        }
        JSONObject parse = (JSONObject) JSONObject.parse(redisData);
        return (Map<String, Object>) parse;
    }

    public static JSONObject getRedisJsonData(String redisData) {
        if (StringUtils.isBlank(redisData)) {
            return null;
        }
        JSONObject parse = (JSONObject) JSONObject.parse(redisData);
        return (JSONObject) parse;
    }

    public static BlockingQueue<Integer> getQueue(int count) {

        BlockingQueue<Integer> basket = new ArrayBlockingQueue<>(count);
        for (int i = 0; i < count; i++) {
            try {
                basket.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return basket;
    }

    /**
     * @Description 判断list, map, json是否为空
     * @参数
     * @返回值
     * @Authur Q Z L
     * @创建时间 2020/5/7 17:38
     * @修改人和其它信息
     * @ 2020/5/7
     */
    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (map.isEmpty()) {
                return true;
            }
            return false;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            if (list.isEmpty()) {
                return true;
            }
            return false;
        }
        if (obj instanceof JSONObject) {
            JSONObject json = (JSONObject) obj;
            if (json.isEmpty()) {
                return true;
            }
            return false;
        }
        if (obj instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) obj;
            if (jsonArray.isEmpty()) {
                return true;
            }
            return false;
        }
        return false;

    }

    /**
     * 方法描述 判断一个对象是否是一个数组
     *
     * @param obj
     */
    public static boolean isArray(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj.getClass().isArray();
    }



    /**
     * 根据byte数组，生成文件
     *
     * @param bytes    文件数组
     * @param filePath 文件存放路径
     * @param fileName 文件名称
     */
    public static File byte2File(byte[] bytes, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && !dir.isDirectory()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return file;
    }

    //base64字符串转byte[]
    public static byte[] base64String2ByteFun(String base64Str) {
        return Base64.decodeBase64(base64Str);
    }

    //byte[]转base64
    public static String byte2Base64StringFun(byte[] b) {
        return Base64.encodeBase64String(b);
    }


    /**
     * @Description 把一个文件转化为byte字节数组。
     * @参数
     * @返回值
     * @Authur Q Z L
     * @创建时间 2020/4/17 13:50
     * @修改人和其它信息
     * @ 2020/4/17
     */
    public static byte[] fileConvertToByteArray(File file) {
        byte[] data = null;

        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int len;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }

            data = baos.toByteArray();

            fis.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }


    public static List<Map<String, Object>> query(Connection con, String sql, Object[] obj) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        RecordSet execute = null;
        try {
            if (con == null) {
                execute = SQL.execute(sql, obj);
            } else {
                execute = SQL.execute(con, sql, obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        String[] field_name = execute.FIELD_NAME;
        if (execute == null) {
            return list;
        }
        while (execute.next()) {
            Map<String, Object> queryTodoMap = new HashMap<String, Object>();
            for (String fieldName : field_name) {
                queryTodoMap.put(fieldName, execute.getOriginalString(fieldName));
            }
            list.add(queryTodoMap);
        }
        return list;
    }

    public static List<Map<String, Object>> query(Connection con, String sql, Object[] obj, List<String> timeFieldList, List<String> integerFieldList) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        RecordSet execute = null;
        try {
            if (con == null) {
                execute = SQL.execute(sql, obj);
            } else {
                execute = SQL.execute(con, sql, obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        String[] field_name = execute.FIELD_NAME;
        if (execute == null) {
            return list;
        }
        while (execute.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (String fieldName : field_name) {
                if (timeFieldList != null && timeFieldList.contains(fieldName)) {
                    map.put(fieldName.toUpperCase(), execute.getTimestamp(fieldName));
                } else if (integerFieldList != null && integerFieldList.contains(fieldName)) {
                    map.put(fieldName.toUpperCase(), execute.getBigDecimal(fieldName));
                } else {
                    map.put(fieldName.toUpperCase(), execute.getOriginalString(fieldName));
                }

                map.put(fieldName, execute.getOriginalString(fieldName));
            }
            list.add(map);
        }
        return list;
    }

    public static List<Map<String, Object>> query(Connection con, String sql, Object[] obj, List<String> timeFieldList) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        RecordSet execute = null;
        try {
            if (con == null) {
                execute = SQL.execute(sql, obj);
            } else {
                execute = SQL.execute(con, sql, obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        String[] field_name = execute.FIELD_NAME;
        if (execute == null) {
            return list;
        }
        while (execute.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (String fieldName : field_name) {
                if (timeFieldList != null && timeFieldList.contains(fieldName)) {
                    map.put(fieldName.toUpperCase(), execute.getTimestamp(fieldName));
                } else {
                    map.put(fieldName.toUpperCase(), execute.getOriginalString(fieldName));
                }

                map.put(fieldName, execute.getOriginalString(fieldName));
            }
            list.add(map);
        }
        return list;
    }

    public static int update(Connection con, String sql, Object[] obj) {
        if (con == null) {
            return SQL.execute(sql, obj).TOTAL_RECORD_COUNT;
        } else {
            return SQL.execute(con, sql, obj).TOTAL_RECORD_COUNT;
        }
    }

    public static int updateOrAdd(Connection con, String sql, Object[] obj) {
        if (con == null) {
            return SQL.execute(sql, obj).TOTAL_RECORD_COUNT;
        } else {
            return SQL.execute(con, sql, obj).TOTAL_RECORD_COUNT;
        }
    }











}
