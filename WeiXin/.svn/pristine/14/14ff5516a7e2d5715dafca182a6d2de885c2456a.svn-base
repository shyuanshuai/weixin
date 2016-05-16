package com.yuanshuai.weixin.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport
		implements ServletRequestAware, ServletResponseAware, ServletContextAware {

	private static final long serialVersionUID = 1L;

	// log4j
	protected static Logger logger = LogManager.getLogger(BaseAction.class);

	// 返回结果集
	protected Map<String, Object> map = new HashMap<String, Object>();

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected ServletContext application;

	// 当前登录的用户

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		this.session = this.request.getSession();

	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.application = arg0;
	}

	/**
	 * 将对象转换成JSON数据，并返回到页面
	 * 
	 * @param object
	 * @return
	 */
	protected String responseJSONData(Object object) {
		ObjectMapper objectMapper = new ObjectMapper();
		PrintWriter out = null;
		try {
			String result = objectMapper.writeValueAsString(object);
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.println(result);
			out.flush();
		} catch (Exception e) {
			logger.error(e);
		} finally {
			out.close();
		}

		return SUCCESS;
	}

}
