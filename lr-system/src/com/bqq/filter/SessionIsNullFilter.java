package com.bqq.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionIsNullFilter implements Filter{
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		//获取根路径
		String url = request.getServletPath();
		if(!url.equals("/welcome") 
				&& !url.equals("/register")
				&& !url.equals("/findPwd")
				&& !url.equals("/index")
				&& !url.equals("/")) {
			if(request.getSession(false) == null) {
				if(request.getSession(true).isNew()) {
					//当前session是刚建立的
				} else {
					if(session.getAttribute("user") == null) {
						session.invalidate();
						response.setContentType("text/html;charset=utf-8");
						PrintWriter out = response.getWriter();
						out.println("<script language='javascript' type='text/javascript'>");  
		                out.println("alert('由于你长时间没有操作,导致Session失效!请你重新登录!');window.location.href='" + request.getContextPath() + "/welcome'");  
		                out.println("</script>"); 
					} else {
						chain.doFilter(request, response);
					}
				}
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}

}
