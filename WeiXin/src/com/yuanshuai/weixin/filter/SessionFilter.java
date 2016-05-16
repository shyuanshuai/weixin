package com.yuanshuai.weixin.filter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class SessionFilter implements Interceptor {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LogManager.getLogger(SessionFilter.class);

	@Override
	public void init() {
		logger.info("init");
	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		return actionInvocation.invoke();
	}

	@Override
	public void destroy() {
		logger.info("destroy");
	}

}
