package com.wondersgroup.utils.requestUtils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;

import tw.tool.helper.LogHelper;
import wfc.service.config.Config;
import wfc.service.log.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wondersgroup.utils.StreamHelperUtils;

public class UcenterClient {

	private static RequestConfig requestConfig = RequestConfig.custom()
			.setSocketTimeout(100000).setConnectTimeout(15000)
			.setConnectionRequestTimeout(15000).build();

	/**
	 * 发送Post请求
	 * 
	 * @param httpPost
	 * @return
	 */
	private static String sendHttpPost(HttpPost httpPost) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.

			httpClient = HttpClients.createDefault();
			httpPost.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpPost);
			entity = response.getEntity();
			LogHelper.info("调用获取用户信息接口返回：" + entity.toString());
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}
	
	
	
	/**
	 * 获取政务大厅用户信息
	 * 
	 * @param token
	 * @return
	 */
	public static JSONObject getUserData(String token) {
		if (StringUtils.isBlank(token) || "null".equals(token))
			return new JSONObject();
//		String url = Config.get("zwdt.user.api.addr");
		String url = "http://zwdtuser.sh.gov.cn/uc";
		String result = sendHttpPost(url + "/oauth2.0/me.do", "access_token="
				+ token);
		JSONObject data = new JSONObject();
		try {
			data = JSONObject.parseObject(result);
		} catch (Exception e) {
		}
		LogHelper.info("用户信息:" + data);
		JSONObject jsonObject = new JSONObject();
		if (data == null)
			return jsonObject;
		jsonObject.put("userId", "");
		if (data.containsKey("zwdtsw_user_id"))
			jsonObject.put("userId", data.get("zwdtsw_user_id"));
		jsonObject.put("userType", "");
		if (data.containsKey("zwdtsw_user_type"))
			jsonObject.put("userType", data.get("zwdtsw_user_type"));
		jsonObject.put("username", "");
		if (data.containsKey("zwdtsw_link_person"))
			jsonObject.put("username", data.get("zwdtsw_link_person"));
		if (data.containsKey("zwdtsw_name"))
			jsonObject.put("username", data.get("zwdtsw_name"));
		jsonObject.put("idCardNo", "");
		if (data.containsKey("zwdtsw_cert_id"))
			jsonObject.put("idCardNo", data.get("zwdtsw_cert_id"));
		jsonObject.put("account", "");
		if (data.containsKey("zwdtsw_account"))
			jsonObject.put("account", data.get("zwdtsw_account"));
		jsonObject.put("mobile", "");
		if (data.containsKey("zwdtsw_link_phone"))
			jsonObject.put("mobile", data.get("zwdtsw_link_phone"));
		jsonObject.put("email", "");
		if (data.containsKey("zwdtsw_email"))
			jsonObject.put("email", data.get("zwdtsw_email"));
		jsonObject.put("address", "");
		if (data.containsKey("zwdtsw_link_address"))
			jsonObject.put("address", data.get("zwdtsw_link_address"));
		jsonObject.put("zipCode", "");
		if (data.containsKey("zwdtsw_zip_code"))
			jsonObject.put("zipCode", data.get("zwdtsw_zip_code"));
		jsonObject.put("status", "");
		if (data.containsKey("zwdtsw_user_status"))
			jsonObject.put("status", data.get("zwdtsw_user_status"));
		jsonObject.put("realNameStatus", "");
		if (data.containsKey("zwdtsw_after_certification"))
			jsonObject.put("realNameStatus",
					data.get("zwdtsw_after_certification"));
		jsonObject.put("completeType", "");
		jsonObject.put("companyName", "");
		if (data.containsKey("zwdtsw_law_person"))
			jsonObject.put("companyName", data.get("zwdtsw_law_person"));
		jsonObject.put("socialCreditCode", "");
		if (data.containsKey("zwdtsw_credit_code"))
			jsonObject.put("socialCreditCode", data.get("zwdtsw_credit_code"));
		if (data.containsKey("zwdtsw_org_code"))
			jsonObject.put("orgCode", data.get("zwdtsw_org_code"));
		jsonObject.put("level", 0);
		jsonObject.put("caCode", "");
		if (data.containsKey("zwdtsw_ca_code"))
			jsonObject.put("caCode", data.get("zwdtsw_ca_code"));
		jsonObject.put("certSn", "");
		if (data.containsKey("zwdtsw_cert_sn"))
			jsonObject.put("certSn", data.get("zwdtsw_cert_sn"));
		jsonObject.put("regTime", "");
		if (data.containsKey("zwdtsw_reg_time"))
			jsonObject.put("regTime", data.get("zwdtsw_reg_time"));
		jsonObject.put("updateTime", "");
		if (data.containsKey("zwdtsw_last_update"))
			jsonObject.put("updateTime", data.get("zwdtsw_last_update"));
		jsonObject.put("userCacert", "");
		if (data.containsKey("zwdtsw_user_CAcert"))
			jsonObject.put("userCacert", data.get("zwdtsw_user_CAcert"));
		jsonObject.put("permisssion", "");
		jsonObject.put("certificate", "");
		if (data.containsKey("zwdtsw_certificate"))
			jsonObject.put("certificate", data.get("zwdtsw_certificate"));
		jsonObject.put("commerceCode", "");
		if (data.containsKey("zwdtsw_commerce_code"))
			jsonObject.put("commerceCode", data.get("zwdtsw_commerce_code"));
		jsonObject.put("certType", "");
		if (data.containsKey("zwdtsw_cert_type"))
			jsonObject.put("certType", data.get("zwdtsw_cert_type"));
		return jsonObject;
	}

	/**
	 * 通过市民云token获取政务大厅用户token
	 * 
	 * @param token
	 * @return
	 */
	public static String getZwdtUserToken(String smyToken) {
		String url = Config.get("zwdt.user.api.addr");
		String result = sendHttpPost(url + "/cloudUser/cloudIdGetToken.do",
				"smyToken=" + smyToken);
		JSONObject data = new JSONObject();
		try {
			data = JSONObject.parseObject(result);
		} catch (Exception e) {
		}
		LogHelper.info("用户信息:" + data);
		if (data == null || !data.containsKey("access_token"))
			return "";
		return data.getString("access_token");
	}

	/**
	 * 通过政务大厅用户id获取用户token
	 * 
	 * @param userId
	 * @return
	 */
	public static String getUserTokenById(String userId) {
		String url = Config.get("zwdt.user.api.addr");
		String result = sendHttpPost(url + "/oauth2.0/authorize.do",
				"zwdtsw_user_id=" + userId);
		return result;
	}

	// ucenter接口令牌获取
	public static String getUcenterSessionId() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("account", "admin");
		jsonObject.put("password", "admin");
		String url = "/uniquespace/login.do";
		String res = getUcenterResult(url, jsonObject);
		if (StringUtils.isBlank(res))
			return "";
		try {
			jsonObject = JSONObject.parseObject(res);
		} catch (Exception e) {
		}
		String sessionId = "";
		if (jsonObject.containsKey("data"))
			sessionId = jsonObject.getString("data");
		return sessionId;
	}

	// ucenter接口退出
	public static void logOutForUcenter(String sessionId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", sessionId);
		String url = "/uniquespace/logoff.do";
		getUcenterResult(url, jsonObject);
	}

	// ucenter接口调用
	public static String getUcenterResult(String url, JSONObject params) {
		url = Config.get("ucenter.api.addr") + url;
		String result = "";
		HttpClient httpClient = HttpClients.createDefault();
		try {
			// String encoderJson = URLEncoder.encode(json, HTTP.UTF_8);
			HttpPost method = new HttpPost(url);
			method.addHeader(HTTP.CONTENT_TYPE,
					"application/json;charset=utf-8");
			StringEntity se = new StringEntity(params.toString(), "UTF-8");
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
					"application/json"));
			method.setEntity(se);
			HttpResponse resp = httpClient.execute(method);
			// url = URLDecoder.decode(url, "UTF-8");
			/** 请求发送成功，并得到响应 **/
			if (resp.getStatusLine().getStatusCode() == 200) {
				try {
					/** 读取服务器返回过来的json字符串数据 **/
					result = EntityUtils.toString(resp.getEntity());
				} catch (Exception e) {

				}
			} else {
				result = EntityUtils.toString(resp.getEntity());
			}
		} catch (Exception e) {
			Log.debug("访问接口失败");
			Log.debug(e);
		}
		return result;
	}
	
	public static String getResult(String urlPath) {
		HttpURLConnection connection = null;
		StringBuffer sb = null;
		try {
			URL url = new URL(urlPath);
			connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			InputStream inputStream = connection.getInputStream();
			// 对应的字符编码转换
			Reader reader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String str = null;
			sb = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				sb.append(str);
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		connection.disconnect();
		return sb.toString();
	}
	
	
	//交通委--传json 接收JSONArray
		public static JSONArray getData(String url, JSONObject jsonObject) {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(180 * 1000)
					.setConnectionRequestTimeout(180 * 1000)
					.setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();
			httpPost.setConfig(requestConfig);
			httpPost.setHeader("Content-Type", "application/json");
			try {
				httpPost.setEntity(new StringEntity(jsonObject.toString(),
						"UTF-8"));
				HttpResponse response = httpClient.execute(httpPost);
				JSONArray JSONArrays = JSONArray.parseArray(EntityUtils
						.toString(response.getEntity(),"UTF-8"));
				return JSONArrays;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	
	
	// 通过个人唯一标识查询用户空间个人信息
	public static JSONObject getPersInfo(String type, String uniqueId) {
		String sessionId = UcenterClient.getUcenterSessionId();
		JSONObject params = new JSONObject();
		params.put("sessionId", sessionId);
		params.put("type", type);
		params.put("uniqueId", uniqueId);
		JSONObject persUuidJson = new JSONObject();
		try {
			String persUuidData = UcenterClient.getUcenterResult(
					"/uniquespace/findPersonUuid.do", params);
			if (StringUtils.isNotBlank(persUuidData)) {
				persUuidJson = JSONObject.parseObject(persUuidData)
						.getJSONObject("data");
			}
		} catch (Exception e) {
		}
		if (persUuidJson != null && persUuidJson.containsKey("personUuid")) {
			String uuid = persUuidJson.getString("personUuid");
			params = new JSONObject();
			params.put("sessionId", sessionId);
			params.put("personUuid", uuid);
			String persData = UcenterClient.getUcenterResult(
					"/uniquespace/getPerson.do", params);
			UcenterClient.logOutForUcenter(sessionId);
			String persString = "";
			if (StringUtils.isNotBlank(persData)) {
				try {
					persString = JSONObject.parseObject(persData).getString(
							"data");
					if (StringUtils.isNotBlank(persString)) {
						JSONObject persJson = JSONObject
								.parseObject(persString);
						return persJson;
					}
				} catch (Exception e) {
				}
			}
		}
		return null;
	}

	// 查询用户空间个人或企业绑定的材料列表
	public static JSONObject getUcenterStuff(JSONObject params) {
		if (params == null)
			return null;
		String sessionId = UcenterClient.getUcenterSessionId();
		params.put("sessionId", sessionId);
		String stuffStr = getUcenterResult("/uniquespace/getStuffList.do",
				params);
		if (StringUtils.isNotBlank(stuffStr)) {
			try {
				JSONObject stuffObject = JSONObject.parseObject(stuffStr)
						.getJSONObject("data");
				return stuffObject;
			} catch (Exception e) {

			}
		}
		return null;
	}

	// 通过统一社会信用代码或组织机构代码查询企业信息
	public static JSONObject getOrgInfo(String orgCode) {
		String sessionId = UcenterClient.getUcenterSessionId();
		JSONObject params = new JSONObject();
		params.put("sessionId", sessionId);
		params.put("code", orgCode);
		String orgUuidData = UcenterClient.getUcenterResult(
				"/uniquespace/findOrgByCode.do", params);
		String uuid = StringUtils.EMPTY;
		if (StringUtils.isNotBlank(orgUuidData)) {
			try {
				uuid = JSONObject.parseObject(orgUuidData).getString("data");
			} catch (Exception e) {
			}
		}
		params = new JSONObject();
		params.put("sessionId", sessionId);
		params.put("organizationUuid", uuid);
		String orgData = UcenterClient.getUcenterResult(
				"/uniquespace/getOrganization.do", params);
		UcenterClient.logOutForUcenter(sessionId);
		String orgString = "";
		if (StringUtils.isNotBlank(orgData)) {
			try {
				orgString = JSONObject.parseObject(orgData).getString("data");
				if (StringUtils.isNotBlank(orgString)) {
					JSONObject orgJson = JSONObject.parseObject(orgString);
					return orgJson;
				}
			} catch (Exception e) {
			}
		}
		return null;
	}

	// ucenter材料上传
	public static String uploadUcenterStuff(String uuid, String stuffCode,
			byte[] bytes, String fileName) {
		String url = Config.get("ucenter.api.addr")
				+ "/uniquespace/addPersonStuff.do";
		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder
				.create();
		multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		try {
			multipartEntityBuilder.setCharset(CharsetUtils.get("UTF-8"));
			multipartEntityBuilder.addPart(
					"sessionId",
					new StringBody(getUcenterSessionId(), ContentType.create(
							ContentType.TEXT_PLAIN.getMimeType(), "UTF-8")));
			multipartEntityBuilder.addPart(
					"uuid",
					new StringBody(uuid, ContentType.create(
							ContentType.TEXT_PLAIN.getMimeType(), "UTF-8")));
			multipartEntityBuilder.addPart(
					"stuffCode",
					new StringBody(stuffCode, ContentType.create(
							ContentType.TEXT_PLAIN.getMimeType(), "UTF-8")));
			InputStream is = new ByteArrayInputStream(bytes);
			multipartEntityBuilder.addBinaryBody("FileData", is,
					ContentType.MULTIPART_FORM_DATA, fileName);
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			HttpEntity reqEntity = multipartEntityBuilder
					.build();
			httpPost.setEntity(reqEntity);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			InputStream is2 = httpEntity.getContent();
			bytes = StreamHelperUtils.toByteArray(is2);
			String responseString = new String(bytes, "UTF-8");
			StatusLine statusLine = httpResponse.getStatusLine();
			if (statusLine.getStatusCode() >= 400) {
				return "post failure :caused by-->" + responseString;
			} else {
				return responseString;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * ucenter材料获取
	 * 
	 * @param uuid
	 * @param stuffCode
	 * @return
	 */
	public static Map<String, Object> getStuffStream(String uniqueType,
			String uniqueId, String usertype, String stuffId) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", getUcenterSessionId());
		jsonObject.put("uniqueType", uniqueType);
		jsonObject.put("uniqueId", uniqueId);
		jsonObject.put("userType", usertype);
		jsonObject.put("stuffId", stuffId);
		String url = Config.get("ucenter.api.addr")
				+ "/uniquespace/downStuff.do";
		HttpClient httpClient = HttpClients.createDefault();
		try {
			// String encoderJson = URLEncoder.encode(json, HTTP.UTF_8);
			HttpPost method = new HttpPost(url);
			method.addHeader(HTTP.CONTENT_TYPE,
					"application/json;charset=utf-8");
			StringEntity se = new StringEntity(jsonObject.toString(), "UTF-8");
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
					"application/json"));
			method.setEntity(se);
			HttpResponse httpResponse = httpClient.execute(method);
			BufferedOutputStream bos = null;
			FileOutputStream fos = null;
			InputStream is = null;
			try {
				Log.debug("httpClient状态:"
						+ httpResponse.getStatusLine().getStatusCode());
				// 连接成功
				if (200 == httpResponse.getStatusLine().getStatusCode()) {
					HttpEntity httpEntity = httpResponse.getEntity();
					is = httpEntity.getContent();
					map.put("byte", StreamHelperUtils.toByteArray(is));
					Header contentHeader = httpResponse
							.getFirstHeader("Content-Disposition");
					if (contentHeader != null) {
						HeaderElement[] values = contentHeader.getElements();
						if (values.length == 1) {
							NameValuePair param = values[0]
									.getParameterByName("filename");
							if (param != null) {
								String fileName = new String(param.getValue()
										.toString().getBytes("ISO-8859-1"),
										"GBK");
								map.put("fileName", fileName);
							}
						}
					}
					return map;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bos != null) {
					try {
						bos.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			Log.debug("访问接口失败");
			Log.debug(e);
		}

		return null;
	}
	
	/**
	 * 发送 post请求
	 * 
	 * @param httpUrl
	 *            地址
	 * @param params
	 *            参数(格式:key1=value1&key2=value2)
	 */
	public static String sendHttpPost(String httpUrl, String params) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost\
		boolean isHttps = httpUrl.startsWith("https://");
		LogHelper.info("post请求:" + httpUrl);
		try {
			// 设置参数
			StringEntity stringEntity = new StringEntity(params, "UTF-8");
			stringEntity.setContentType("application/x-www-form-urlencoded");
			httpPost.setEntity(stringEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(httpPost,isHttps);
	}

	
	/**
	 * Https请求处理
	 * @param httpPost
	 * @param isHttps
	 * @return
	 */
	private static String sendHttpPost(HttpPost httpPost,boolean isHttps) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		httpClient=(CloseableHttpClient)getHttpClient(isHttps);
		httpPost.setConfig(requestConfig);
		// 执行请求
		try {
			response = httpClient.execute(httpPost);
			entity = response.getEntity();
			LogHelper.info("调用获取用户信息接口返回："+ entity.toString());
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return responseContent;
	}
	
	/**
	 * HttpClient创建，根据情况创建请求方式
	 * @param isHttps
	 * @return
	 */
	private	static HttpClient getHttpClient(boolean isHttps) {
		if (isHttps) {
			SSLContext sslContext = createIgnoreVerifySSL();
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
					.register("http", PlainConnectionSocketFactory.INSTANCE)
					.register("https", new SSLConnectionSocketFactory(sslContext)).build();
			PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(
					socketFactoryRegistry);
			return HttpClients.custom().setConnectionManager(connManager).build();
		} else {
			return HttpClients.createDefault();
		}
	}	
	

    /**
     * HTTPS请求证书处理
     *     
     * @return
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */	
	private static SSLContext createIgnoreVerifySSL() {
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			X509TrustManager trustManager = new X509TrustManager() {
				@Override
				public void checkClientTrusted(X509Certificate[] x509Certificates, String paramString) {
				}

				@Override
				public void checkServerTrusted(X509Certificate[] x509Certificates, String paramString) {
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			sc.init(null, new TrustManager[] { trustManager }, null);
			return sc;
		} catch (KeyManagementException e) {
			throw new RuntimeException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	} 
	
	public static void main(String[] args) {
		StreamHelperUtils s = new StreamHelperUtils();
		
	}
	
}
