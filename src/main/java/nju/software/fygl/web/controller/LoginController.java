package nju.software.fygl.web.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nju.software.fygl.dynamic.DataSourceMap;
import nju.software.fygl.dynamic.DataSourceRouter;
import nju.software.fygl.dynamic.FYDataSourceEnum;
import nju.software.fygl.model.YhModel;
import nju.software.fygl.service.YhService;
import nju.software.fygl.util.ResponseBuilder;
import nju.software.fygl.util.StringUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	Log logger = LogFactory.getLog(LoginController.class);


	@Resource
	private YhService yhService;

	private ResponseBuilder responseBuilder;

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String checkIn(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		//切换到相应数据库
		String fydm = request.getParameter("bmbhselectname");
		if(StringUtil.equalsIgnoreWhitespace(fydm,"1111")){
            DataSourceRouter.routerToDefault();
        }else{
			DataSourceRouter.routerTo(fydm);
        }

        String datasource = DataSourceMap.getDataSourceKey(fydm);

        String fymc= FYDataSourceEnum.getFymcByFydm(fydm);

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (StringUtil.isBlank(password) || StringUtil.isBlank(username)) {
			return "login";

		}
		YhModel yh = yhService.getYhByYhdm(username);
		if (yh == null) {
			return "login";
		}
		boolean success = yhService.checkIn(yh, password);
		if (!success) {
			return "login";
		}

        request.getSession().setAttribute("username",yh.getYhmc());
        request.getSession().setAttribute("fymc", fymc);
        request.getSession().setAttribute("fydm", fydm);
		return "index";

	}


	@RequestMapping(value = "/login.aj", method = RequestMethod.GET)
	public void loginAj(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//切换到相应数据库
		String fydm = request.getParameter("bmbhselectname");
        if(StringUtil.equalsIgnoreWhitespace(fydm,"1111")){
            DataSourceRouter.routerToDefault();
        }else{
			DataSourceRouter.routerTo(fydm);
        }
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String[] result = new String[] { "success", "" };
		if (StringUtil.isBlank(username)) {
			result[1] = "请输入用户名!";
			result[0] = "error";
			responseBuilder.writeJsonResponse(response, new Gson().toJson(result));
		}
		if (StringUtil.isBlank(password)) {
			result[1] = "请输入密码!";
			result[0] = "error";
			responseBuilder.writeJsonResponse(response, new Gson().toJson(result));
		}
		YhModel yh = yhService.getYhByYhdm(username);
		if (yh == null) {
			result[1] = "不存在该用户!";
			result[0] = "error";
		} else {
			boolean success = yhService.checkIn(yh, password.trim());
			if (!success) {
				result[1] = "密码错误!";
				result[0] = "error";
			}
		}
		responseBuilder.writeJsonResponse(response, new Gson().toJson(result));
	}

	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("fydm");
		return "login";
	}

}