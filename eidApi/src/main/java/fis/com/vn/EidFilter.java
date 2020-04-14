package fis.com.vn;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.GenericFilterBean;

import fis.com.vn.common.Contains;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class EidFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		
//		if(req.getRequestURI().startsWith("/public")) {
//			if(req.getHeader("token") != null && req.getHeader("token").equals(Contains.TOKEN_PUBLIC_API)) {
//				chain.doFilter(request, response);
//				return;
//			} else {
//				resp.getWriter().print("Not permission");
//				return;
//			}
//		}
		
		chain.doFilter(request, response);
	}
}
