package com.infinitePossibilities.utils;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.alibaba.fastjson.JSONObject;

public class xmlToJson {

	/**
     * 获得xml
     *
     * @param xmlStr
     * @return
     * @throws DocumentException
     */
    public static JSONObject xml(String xmlStr) throws DocumentException {
        Document doc = DocumentHelper.parseText(xmlStr);
        JSONObject json = new JSONObject();
        xmlToJson(doc.getRootElement(), json);
        return json;
    }
 
    
    /**
     * xml转json
     *
     * @param element
     * @param json
     */
    public static void xmlToJson(Element element, JSONObject json) {
        // 如果是属性
        for (Object o : element.attributes()) {
            Attribute attr = (Attribute) o;
            if (!isEmpty(attr.getValue())) {
                json.put("@" + attr.getName(), attr.getValue());
            }
        }
        List<Element> chdEl = element.elements();
        if (chdEl.isEmpty() && !isEmpty(element.getText())) {// 如果没有子元素,只有一个值
            json.put(element.getName(), element.getText());
        }
 
        for (Element e : chdEl) {// 有子元素
            if (!e.elements().isEmpty()) {// 子元素也有子元素
                JSONObject chdjson = new JSONObject();
                xmlToJson(e, chdjson);
                Object o = json.get(e.getName());
                if (o != null) {
                    JSONArray jsona = null;
                    if (o instanceof JSONObject) {// 如果此元素已存在,则转为jsonArray
                        JSONObject jsono = (JSONObject) o;
                        json.remove(e.getName());
                        jsona = new JSONArray();
                        jsona.add(jsono);
                        jsona.add(chdjson);
                    }
                    if (o instanceof JSONArray) {
                        jsona = (JSONArray) o;
                        jsona.add(chdjson);
                    }
                    json.put(e.getName(), jsona);
                } else {
                    if (!chdjson.isEmpty()) {
                        json.put(e.getName(), chdjson);
                    }
                }
 
            } else {// 子元素没有子元素
                for (Object o : element.attributes()) {
                    Attribute attr = (Attribute) o;
                    if (!isEmpty(attr.getValue())) {
                        json.put("@" + attr.getName(), attr.getValue());
                    }
                }
                if (!e.getText().isEmpty()) {
                    json.put(e.getName(), e.getText());
                }
            }
        }
    }
 
    public static boolean isEmpty(String str) {
 
        if (str == null || str.trim().isEmpty() || "null".equals(str)) {
            return true;
        }
        return false;
    }
	
    public static void main(String[] args) {
    	String reqXML =  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<msg>\n" + "  <header desc=\"报文头\">\n"
				+ "    <app_id desc=\"应用ID\">" + "11111111111111111" + "</app_id>\n"
				+ "    <method desc=\"接口名称\">$jkmc$</method>\n" + "    <timestamp desc=\"交易发起时间\">"
				+ "2020/04/23" + "</timestamp>\n" + "    <object_dm desc=\"业务对象代码\">" + "22222222222222222" + "</object_dm>\n"
				+ "    <version desc=\"版本号\">1.0</version>\n" + "    <jylsh desc=\"交易流水号\">" + "333333333333333"
				+ "</jylsh>\n" + "    <channel desc=\"渠道\">03</channel>\n"
				+ "      <agent_name desc=\"网点名称\">测试机构</agent_name>\n"
				+ "      <agent_code desc=\"网点编号\">100000001</agent_code>\n"
				+ "      <terminal_code desc=\"终端编号\"></terminal_code>\n"
				+ "      <operater_name desc=\"操作人姓名\">test</operater_name>\n"
				+ "      <operater_code desc=\"操作人代码\">100001</operater_code>\n"
				+ "      <district desc=\"所属区县\">310104</district>\n"  + "  </header>\n"
				+ "  <body desc=\"渠道信息\">\n$body$</body>\n" + "</msg>";
    	try {
    		JSONObject json = xml(reqXML);
			System.out.println(json);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
    	
	}
    
	
}
