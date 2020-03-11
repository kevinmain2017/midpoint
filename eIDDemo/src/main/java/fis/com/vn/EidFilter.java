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

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class EidFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		if (allowUrl(req)) {
			chain.doFilter(request, response);
			return;
		}
		
		if(req.getSession().getAttribute("userName") == null) {
			resp.sendRedirect("/login");
		}
		
		chain.doFilter(request, response);
	}
	public Boolean allowUrl(HttpServletRequest req) {
		ArrayList<String> listAllow = new ArrayList<>();
		listAllow.add("/font/*");
		listAllow.add("/fonts/*");
		listAllow.add("/webfonts/*");
		listAllow.add("/select2-develop/*");
		listAllow.add("/css/*");
		listAllow.add("/js/*");
		listAllow.add("/image/*");
		listAllow.add("/images/*");
		listAllow.add("/img/*");
		listAllow.add("/favicon.ico");
		listAllow.add("/login");
		listAllow.add("/logout");
		listAllow.add("/static/file/*");
		listAllow.add("/register");
		listAllow.add("/dist/*");
		listAllow.add("/plugins/*");
		listAllow.add("/docs/*");

		return checkAllow(listAllow, req);
	}
	private Boolean checkAllow(ArrayList<String> listAllow, HttpServletRequest req) {
		for (String string : listAllow) {
			if (req.getRequestURI().matches(string.replace("*", "[\\w\\W]*"))) {
				return true;
			}
		}
		return false;
	}
}
