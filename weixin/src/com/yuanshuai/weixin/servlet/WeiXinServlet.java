package com.yuanshuai.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.yuanshuai.weixin.beans.User;
import com.yuanshuai.weixin.po.AccessToken;
import com.yuanshuai.weixin.po.RobotMessage;
import com.yuanshuai.weixin.po.RobotMessageDetail;
import com.yuanshuai.weixin.po.TextMessage;
import com.yuanshuai.weixin.service.IUserService;
import com.yuanshuai.weixin.servlet.util.CheckSignature;
import com.yuanshuai.weixin.servlet.util.HttpRequest;
import com.yuanshuai.weixin.servlet.util.MessageUtil;
import com.yuanshuai.weixin.servlet.util.WeixinUtil;

public class WeiXinServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private IUserService userService;

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");

		PrintWriter out = resp.getWriter();
		if (CheckSignature.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();

		try {
			Map<String, String> map = MessageUtil.xmlToMap(req);
			String toUserName = map.get("ToUserName");
			String fromUserName = map.get("FromUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");

			User obj = new User();
			obj.setOpenId(fromUserName);

			userService.save(obj);

			String message = null;

			if ("text".equals(msgType)) {

				if ("3".equals(content) || "３".equals(content)) {
					TextMessage text = new TextMessage();
					text.setFromUserName(toUserName);
					text.setToUserName(fromUserName);
					text.setMsgType("text");
					text.setCreateTime(new Date().getTime());

					String toUserContent = "业务范围，回复【】内的数字即可进入相应的模块:";
					toUserContent += "\n【31】校园活动赞助";
					toUserContent += "\n【32】校园跳蚤市场";
					toUserContent += "\n【33】兼职招聘";
					toUserContent += "\n【34】校园交友";
					toUserContent += "\n【35】心灵鸡汤";
					toUserContent += "\n【36】消费易分期";

					text.setContent(toUserContent);
					message = MessageUtil.textMessageToXml(text);
				} else if ("4".equals(content) || "４".equals(content)) {
					TextMessage text = new TextMessage();
					text.setFromUserName(toUserName);
					text.setToUserName(fromUserName);
					text.setMsgType("text");
					text.setCreateTime(new Date().getTime());

					String toUserContent = "校园文化";
					toUserContent += "\n<a href='http://www.ycit.cn/'>盐城工学院</a>";
					toUserContent += "\n<a href='http://www.yctc.edu.cn/'>盐城师范学院</a>";
					toUserContent += "\n<a href='http://www.yctei.cn/'>盐城纺织职业技术学院</a>";
					toUserContent += "\n<a href='http://www.jsycmc.com/'>盐城卫生职业技术学院</a>";
					toUserContent += "\n<a href='http://www.mdut.cn/'>明达职业技术学院</a>";

					text.setContent(toUserContent);
					message = MessageUtil.textMessageToXml(text);
				} else if ("6".equals(content) || "６".equals(content)) {
					TextMessage text = new TextMessage();
					text.setFromUserName(toUserName);
					text.setToUserName(fromUserName);
					text.setMsgType("text");
					text.setCreateTime(new Date().getTime());

					String toUserContent = "欢迎使用聊天机器人！如想退出聊天请输入exit";

					if (!userService.updateRobotEnter(fromUserName)) {
						toUserContent = "进入聊天室失败！！";
					}

					text.setContent(toUserContent);
					message = MessageUtil.textMessageToXml(text);
				} else if ("exit".equals(content) || "EXIT".equals(content)) {

					TextMessage text = new TextMessage();
					text.setFromUserName(toUserName);
					text.setToUserName(fromUserName);
					text.setMsgType("text");
					text.setCreateTime(new Date().getTime());

					String toUserContent = "你已退出聊天机器人！";

					if (!userService.updateRobotExit(fromUserName)) {
						toUserContent = "退出失败！";
					}

					text.setContent(toUserContent);
					message = MessageUtil.textMessageToXml(text);
				} else {
					String toUserContent = "";
					if (userService.getRoboted(fromUserName)) {
						User user = userService.findByOpenId(fromUserName);

						String params = "key=e1fd4db2cdbd77bc3d90bef7ab6b0d96&info=" + content + "&userid="
								+ user.getId();
						RobotMessage robotMessage = HttpRequest
								.getRobotMessageByPost("http://www.tuling123.com/openapi/api", params);
						if ("100000".equals(robotMessage.getCode())) {
							toUserContent = robotMessage.getText();
						} else if ("200000".equals(robotMessage.getCode())) {
							toUserContent += "<a href='" + robotMessage.getUrl() + "'>" + robotMessage.getText()
									+ "</a>";
						} else if ("302000".equals(robotMessage.getCode())) {
							for (RobotMessageDetail detail : robotMessage.getList()) {
								toUserContent += "<a href='" + detail.getDetailurl() + "'>" + detail.getArticle()
										+ "</a>\n";
							}
						} else if ("308000".equals(robotMessage.getCode())) {
							for (RobotMessageDetail detail : robotMessage.getList()) {
								toUserContent += "<a href='" + detail.getDetailurl() + "'>" + detail.getName()
										+ "</a>\n";
								toUserContent += detail.getInfo() + "\n";
							}
						} else {
							toUserContent = "当天请求次数已使用完";
						}
					} else {
						toUserContent += "你好！欢迎关注盐城轶伦网络科技有限公司官方微信，下面是我们的导航，回复【】内的数字即可进入相应的模块:";
						toUserContent += "\n【1】<a href='http://121.196.206.158/'>盐城轶伦网络科技有限公司简介</a>";
						toUserContent += "\n【2】公司企业文化";
						toUserContent += "\n【3】业务范围";
						toUserContent += "\n【4】校园文化";
						toUserContent += "\n【5】盐城旅游美食";
						toUserContent += "\n【6】聊天机器人";
					}

					TextMessage text = new TextMessage();
					text.setFromUserName(toUserName);
					text.setToUserName(fromUserName);
					text.setMsgType("text");
					text.setCreateTime(new Date().getTime());
					text.setContent(toUserContent);
					message = MessageUtil.textMessageToXml(text);
				}

			} else if (MessageUtil.MESSAGE_EVNET.equals(msgType)) {
				String eventType = map.get("Event");
				if (MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)) {
					message = MessageUtil.initText(toUserName, fromUserName, "点击事件");
				} else if (MessageUtil.MESSAGE_CLICK.equals(eventType)) {
					AccessToken token = WeixinUtil.getAccessToken();
					System.out.println("Token:" + token.getToken());
					System.out.println("有效时间：" + token.getExpiresIn());
					message = MessageUtil.initText(toUserName, fromUserName, token.getToken());
					System.out.println(toUserName);
					System.out.println(fromUserName);
					System.out.println();
				} else if (MessageUtil.MESSAGE_VIEW.equals(eventType)) {
					String url = map.get("EventKey");
					message = MessageUtil.initText(toUserName, fromUserName, url);
				} else if (MessageUtil.MESSAGE_SCANCODE.equals(eventType)) {
					String key = map.get("EventKey");
					message = MessageUtil.initText(toUserName, fromUserName, key);
				}
			} else if (MessageUtil.MESSAGE_LOCATION.equals(msgType)) {
				String label = map.get("Label");
				message = MessageUtil.initText(toUserName, fromUserName, label);
			}
			out.print(message);
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}
