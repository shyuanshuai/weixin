package com.yuanshuai.weixin.servlet.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.yuanshuai.weixin.po.RobotMessage;
import com.yuanshuai.weixin.po.RobotMessageDetail;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

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
			DefaultHttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(request);
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