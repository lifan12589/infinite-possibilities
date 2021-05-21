//package com.wondersgroup.utils.requestUtils;
//
//
//import org.apache.cxf.binding.soap.SoapMessage;
//import org.apache.cxf.endpoint.Client;
//import org.apache.cxf.frontend.ClientProxy;
//import org.apache.cxf.headers.Header;
//import org.apache.cxf.helpers.DOMUtils;
//import org.apache.cxf.interceptor.Fault;
//import org.apache.cxf.phase.AbstractPhaseInterceptor;
//import org.apache.cxf.phase.Phase;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import javax.xml.namespace.QName;
//import java.util.List;
//
//public class ClientAuthInterceptor  extends AbstractPhaseInterceptor<SoapMessage> {
//    private static final String NAME = "admin";
//    private static final String PASSWORD = "!@#qQ123";
//    private static final String SIGNSTR = "0lKcbEtPGBxJ9oLU/eOKwMDTBMGUhyk7UppqMTdCw7wFm+AQothrVbNgKBC0MtkdFZ6XK5Ld6FamO3MVePTr2QnUHHIbxMdkzLmq9Sahyv/=1ybW=tbVAv9B4ezaqF2epktcwRJd171r8lCOLtcXB7tFQ9PMegy6NhC/2wn7sHv1qhmUmNpq+K/XogN57IBbesuiQFwcisuL7Ui4KnP=v1ZVpYyebNq26=uiT7/o=i89u3bzkxeUIc4O3YvtXKE5MeZ=fTPoIzolaZunjVyBe/xRcDuSsogLhx+axPyNjU8CInaheo2QVFS2hNNCPrmbmN=1WymNJrM3JKv8Ll+gpClZ8=IeqN0wo2DiTRih8hOTkArE5=/3M4dzYefy3VzUgZTnFa1i+yR/2ZJskFuSfLxNLDX48HiOIw0h1SlL=MsLWajZAf6dmAeOG8Co1UYhNRVYl/kKDSaRUWkqsUh60dJ0Nhn+aeDizN1L/A=8SaQseohrTm+bUlddgHlTSGfUlZ/ndPXEVUzcFW=06DW7Lr27nfD9/2egtM=SUV3A5iOHtsz18Ierv1MZfbst9GZNfCvLnnVhRN03Xwh5qPWxVgASzbia0npPvn8W/FpVnJCWoJSTphK7XlHKkfc5fT1luGSSEcXgb=4Frayvifx5htPpfuihzSV58EXQFoOGvVVmpzGkAdv8hY=PhQ8cf9RpeMLZs06ed4Gz56eq47r1BGdQC+3eBMd6Lvt1ZwB7ap3lT/t/aKiB/I0bMf+X6IbDZuoO+kB2rUOR020+1o2ZRoB4Cyy5VeG6cKE0mEfaZZpb6ZOWK75MjIo1+oob9eo764CttLRrCuAfL=MU";
//    public ClientAuthInterceptor() {
//        //准备发送阶段
//        super(Phase.PREPARE_SEND);
//    }
//    @Override
//    public void handleMessage(SoapMessage message) throws Fault {
//        List<Header> headers = message.getHeaders();
//        Document doc = DOMUtils.createDocument();
//        Element auth = doc.createElement("auth");
//        // 用户名
//        Element name = doc.createElement("username");
//        // 密码
//        Element password = doc.createElement("password");
//        // 数字签名
//        Element signstr = doc.createElement("signstr");
//        name.setTextContent(NAME);
//        password.setTextContent(PASSWORD);
//        signstr.setTextContent(SIGNSTR);
//        auth.appendChild(name);
//        auth.appendChild(password);
//        auth.appendChild(signstr);
//        headers.add(new Header(new QName(""), name));
//        headers.add(new Header(new QName(""), password));
//        headers.add(new Header(new QName(""), signstr));
//    }
//
//
//
//
//    public static void main(String[] args) {
//
////    	//道路旅客运输站经营许可  测试代码
////        BasicDataServiceImpl impl = new BasicDataServiceImpl();
////        BasicDataService service = impl.getBasicDataServiceImplPort();
////        //配置验证信息
////        Client client = ClientProxy.getClient(service);
////        client.getOutInterceptors().add(new ClientAuthInterceptor());
////        try {
////            //传入模糊查询参数和分页参数 ResultBo接收数据
////            ResultBo resultBo =  service.queryBasicTradeForKyList("上海大运盛客运服务有限公司","1","","","43200","1","10");
////            JSONObject jsonObject = (JSONObject) JSON.toJSON(resultBo);
////            if(jsonObject.containsKey("result")){
////            	 JSONObject result = (JSONObject)jsonObject.get("result");
////                 JSONArray array = (JSONArray) result.get("records");
////                 if(array.size()==0){
////                	 return;
////                 }else{
////                	 com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject();
////                     json = array.getJSONObject(0);
////                     System.out.println(json);
////                 }
////            }else{
////            	jsonObject.put("err", "调用接口失败！");
////            	System.out.println(jsonObject);
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//	}
//
//}
