package com.yuanshuai.weixin.servlet;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * spring对servlet进行注入
 * 
 * @author YuanShuai 2016年8月10日 上午9:02:11
 */
public class ServletToBeanProxy extends GenericServlet {

	private static final long serialVersionUID = 1L;

	// 当前客户端请求的Servlet名字
	private String targetBean;

	// 代理Servlet
	private Servlet proxy;

	@Override
	public void init() throws ServletException {

		super.init();

		// 初始化Spring容器
		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		this.targetBean = getServletName();

		// 调用ServletBean
		this.proxy = (Servlet) webApplicationContext.getBean(targetBean);

		// 调用初始化方法将ServletConfig传给Bean
		proxy.init(getServletConfig());
	}

	@Override
	public void service(ServletRequest servletRequest, ServletResponse servletResponse)
			throws ServletException, IOException {
		// 在service方法中调用bean的service方法，servlet会根据客户的请求去调用相应的请求方法（Get/Post）
		proxy.service(servletRequest, servletResponse);
	}

}
