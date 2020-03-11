package fis.com.vn.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;

import fis.com.vn.common.Contains;
import fis.com.vn.common.Request;
import fis.com.vn.midpoint.JsonUser;
import fis.com.vn.midpoint.ParamsUser;
import fis.com.vn.midpoint.User;

@Controller
public class DangKyController extends BaseController{
	@GetMapping(value = "/register") 
	public String login(Model model, HttpServletRequest req) {
		
		
		return "register";
	}
	
	@PostMapping(value = "/register") 
	public String postLogin(Model model, HttpServletRequest req, @RequestParam Map<String, String> allParams,RedirectAttributes redirectAttributes) {
		try {
			UriComponentsBuilder builderChitieuTemplate = UriComponentsBuilder.fromHttpUrl(origin + Contains.URL_DANG_KY)
					.queryParam("name", allParams.get("name"))
					.queryParam("fullName", allParams.get("fullName"))
					.queryParam("password", allParams.get("password"));
			
			ParamsUser paramsUser = new ParamsUser();
			paramsUser.setName(allParams.get("name"));
			paramsUser.setFullName(allParams.get("fullName"));
			paramsUser.setPassword(allParams.get("password"));
			
			String json = Request.post(new Gson().toJson(paramsUser), this.getAuthorizationToken(req), origin + Contains.URL_DANG_KY);
			if(Request.getStatus(json) == 200) {
				JsonUser user = Request.getList(json, JsonUser.class, "data");
				model.addAttribute("success", "Đăng ký thành công");
			} else {
				model.addAttribute("error", "Lỗi đăng ký");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Lỗi api");
		}
		
		forwardParams(model, allParams);

		return "register";
	}
}
