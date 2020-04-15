package fis.com.vn.jwt;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.google.gson.Gson;

import fis.com.vn.resp.Resp;
import fis.com.vn.table.MUser;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
	public JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		String username = request.getParameter("username");

		/**
		 * truyen pass word rong de bo qua kiem tra password
		 */
		return getAuthenticationManager()
				.authenticate(new UsernamePasswordAuthenticationToken(username, "", Collections.emptyList()));
	}

//	Authentication authentication = TokenAuthenticationService.getAuthentication(request);
//	String username = authentication.getName();
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		CustomUserDetails customUserDetails = (CustomUserDetails) authResult.getPrincipal();

		System.out.println("JWTLoginFilter.successfulAuthentication:");
		response.setHeader("Content-type", "application/json; charset=utf-8");
		boolean checkLogin = true;

		checkLogin = checkPassword(request, customUserDetails.user);

		Resp resp = new Resp();
		if (checkLogin) {
			TokenAuthenticationService.addAuthentication(response, authResult.getName());

			String authorizationString = response.getHeader(TokenAuthenticationService.HEADER_STRING);

			resp.setData(authorizationString);
			resp.setStatusCode(200);
		} else {
			resp.setMsg("Mật khẩu không đúng");
			resp.setStatusCode(400);
		}
		response.getWriter().print(new Gson().toJson(resp));
	}

	public boolean checkPassword(HttpServletRequest request, MUser mUser) throws IOException {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		if (bCryptPasswordEncoder.matches(request.getParameter("password"), mUser.getPassword())) {
			return true;
		}
		return false;
	}

	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		response.setHeader("Content-type", "application/json; charset=utf-8");
		Resp resp = new Resp();
		resp.setMsg("Tên đăng nhập không đúng");
		resp.setStatusCode(400);
		response.getWriter().print(new Gson().toJson(resp));
	}
	
	// Decodes a URL encoded string using `UTF-8`
    public static String decodeValue(String value) {
        try {
            return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }
}
