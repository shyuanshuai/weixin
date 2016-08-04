package com.yuanshuai.weixin.servletUtil;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.yuanshuai.weixin.po.RobotMessage;
import com.yuanshuai.weixin.po.RobotMessageDetail;

public class HttpRequest {

	public static RobotMessage getRobotMessageByPost(String url, String param) {
		JSONObject result = (JSONObject) JSONSerializer.toJSON(postUrl(url + "?" + param));
		
		@SuppressWarnings("rawtypes")
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("list", RobotMessageDetail.class);

		RobotMessage robotMessage = (RobotMessage) JSONObject.toBean(result, RobotMessage.class, classMap);

		return robotMessage;
	}

	private static String postUrl(String url) {
		try {
			HttpGet request = new HttpGet(url);
			HttpResponse response = HttpClients.createDefault().execute(request);
			String result = "";
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity());
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}