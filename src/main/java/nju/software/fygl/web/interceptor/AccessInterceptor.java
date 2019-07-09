package nju.software.fygl.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nju.software.fygl.model.YhModel;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



/**
 * ����������
 * 
 */
public class AccessInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log = Logger.getLogger(AccessInterceptor.class);

	private List<String> excludeUrls;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String requestURI = request.getRequestURI();
		String toLogin = request.getRequestURL().toString();
		String doName = request.getServletPath();
		if (doName.contains("admin")) {
			String cannotAccess = toLogin.substring(0, toLogin.length() - doName.length()) + "/admin/login.do";
			YhModel user = (YhModel) request.getSession().getAttribute("user");
			if (user == null) {
				for (int i = 0; i < excludeUrls.size(); i++) {
					if (requestURI.endsWith(excludeUrls.get(i))) {
						return true;
					}
				}
				log.warn("����ķ���ҳ�档requestURI:" + requestURI);
				response.sendRedirect(cannotAccess);
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

}
